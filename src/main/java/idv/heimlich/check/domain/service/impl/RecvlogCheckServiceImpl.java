package idv.heimlich.check.domain.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import idv.heimlich.check.common.exception.ApBusinessException;
import idv.heimlich.check.common.log.LogFactory;
import idv.heimlich.check.domain.bean.MailBean;
import idv.heimlich.check.domain.bean.dto.impl.RecvlogCheckDTO;
import idv.heimlich.check.domain.bean.po.impl.RecvlogPo;
import idv.heimlich.check.domain.bean.po.impl.SyscodePo;
import idv.heimlich.check.domain.bean.vo.RecvlogCheckReportVo;
import idv.heimlich.check.domain.excel.RecvlogCheckExcel;
import idv.heimlich.check.domain.service.CheckService;

public class RecvlogCheckServiceImpl implements CheckService {

	private static final Logger LOGGER = LogFactory.getInstance();
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final String SEPARATOR = System.getProperty("line.separator");
	private final String START_TIME;
	private final String END_TIME;

	private enum msgtype {
		NX5105, N5203
	}

	public RecvlogCheckServiceImpl() {
		this.START_TIME = this.getStartTime();
		this.END_TIME = this.getEndTime();
	}

	@Override
	public void sendMail() {
		LOGGER.info("------start-----");
		this.execute();
		LOGGER.info("------end-----");
	}

	/**
	 * 取得異常報單清表
	 */
	private void execute() {
		final RecvlogCheckDTO dto = new RecvlogCheckDTO();
		this.errorList(dto);
		if (CollectionUtils.isNotEmpty(dto.getRecvlogList())) {
			this.executeErrorMsg(dto);
			this.errorExcel(dto);
			this.send(dto);
			LOGGER.info("------send mail-----");
		}
	}

	/**
	 * 建立異常清單Excel
	 */
	private void errorExcel(RecvlogCheckDTO dto) {
		try {
			final RecvlogCheckExcel excel = new RecvlogCheckExcel(dto);
			final File file = excel.buideExcelFile();// 產生excel檔案
			dto.setRecvlogExcel(file);
		} catch (final IOException e) {
			LOGGER.error("寄信錯誤", e);
			throw new ApBusinessException("寄信錯誤", e);
		}
	}

	/**
	 * 異常訊息清單
	 */
	private void errorList(RecvlogCheckDTO dto) {
		final List<RecvlogPo> list = this.getErrorList();
		final List<RecvlogCheckReportVo> result = this.convert2ReportVos(list);
		dto.setRecvlogList(result);
	}

	/**
	 * 異常訊息
	 */
	private void executeErrorMsg(RecvlogCheckDTO dto) {
		final StringBuffer sb = new StringBuffer();
		sb.append(String.format("檢核區間 : %s ~ %s ", this.START_TIME, this.END_TIME)).append(SEPARATOR) //
				.append("異常清單如下 : " + SEPARATOR);
		final String msg = dto.getRecvlogList().stream().map(RecvlogCheckReportVo::getControlno)
				.collect(Collectors.joining(SEPARATOR));
		sb.append(msg);
		dto.setMsg(sb.toString());
		LOGGER.info("訊息內容:" + sb.toString());
	}

	/**
	 * 取得異常報單清表
	 */
	private List<RecvlogPo> getErrorList() {
//		final IDBSession dbSession = DBSessionManager.getDBSession();
//		final List<RecvlogPo> list = new ArrayList<>();
//		for (final msgtype msgtype : msgtype.values()) {
//			final String sql = this.getSql(msgtype.name());
//			LOGGER.debug("sql:" + sql);
//			final SqlSelect sqlSelect = dbSession.getSqlSelect();
//			list.addAll(sqlSelect.select(RecvlogDAOImpl.INSTANCE.getConverter(), sql));
//		}
//		return list;
		return null;
	}

	private String getSql(String msgtype) {
		final StringBuffer sb = new StringBuffer();
		return sb.toString();
	}

	/**
	 * 傳送MAIL
	 */
	private void send(final RecvlogCheckDTO dto) {
		try {
			LOGGER.debug("寄信檔案路徑:" + dto.getRecvlogExcel().getPath());
			dto.setReceiverId(this.getReceiverId());
			dto.setRecvlogExcel(dto.getRecvlogExcel());
			final MailBean MailBean = new MailBean(dto);
			MailBean.sendMail();
		} catch (MessagingException | UnsupportedEncodingException e) {
			LOGGER.error("recvlog check send mail error!", e);
			throw new ApBusinessException("寄送信件失敗", e);
		} finally {
			this.deleteOldExcel(dto.getRecvlogExcel());
		}
	}

	/**
	 * 收件者清單
	 * 
	 * @return
	 */
	private String getReceiverId() {
//		final IDBSession dbSession = DBSessionManager.getDBSession();
//		final DoSqlWhere<SyscodePo.COLUMNS> sqlWhere = new DoSqlWhere<SyscodePo.COLUMNS>();
//		sqlWhere.add(SyscodePo.COLUMNS.TYPE_ID, "MAIL");
//		sqlWhere.add(SyscodePo.COLUMNS.CODE_ID, "CheckMail");
//		final List<SyscodePo> syscodeDos = dbSession.selectPo(SyscodePo.class, sqlWhere);
//		return this.syscodes2String(syscodeDos);
		return null;
	}

	private String syscodes2String(final List<SyscodePo> syscodePos) {
		final List<String> list = new ArrayList<String>();
		for (final SyscodePo syscodePo : syscodePos) {
			list.add(syscodePo.getCodeData2());
		}
		final String mailsString = StringUtils.join(list, ",");
		LOGGER.debug("收信人員:" + mailsString);
		return mailsString;
	}

	private List<RecvlogCheckReportVo> convert2ReportVos(List<RecvlogPo> recvlogPos) {
		final List<RecvlogCheckReportVo> list = new ArrayList<>();
		for (final RecvlogPo po : recvlogPos) {
			final RecvlogCheckReportVo vo = new RecvlogCheckReportVo();
			vo.setControlno(po.getControlno());
			vo.setDeclno(po.getKeyword());
			vo.setMsgtype(po.getMsgtype());
			vo.setProcTimes(po.getProctime());
			vo.setSql(String.format("select * from recvlog where controlno = '%s' order by proctime desc;",
					po.getControlno()));
			list.add(vo);
		}
		return list;
	}

	/**
	 * 起始時間
	 */
	private String getStartTime() {
		final Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -1);
		calendar.add(Calendar.MINUTE, -10);
		return SDF.format(calendar.getTime());
	}

	/**
	 * 結束時間
	 */
	private String getEndTime() {
		final Calendar calendar = Calendar.getInstance();
		return SDF.format(calendar.getTime());
	}

	/**
	 * 刪除舊的excel
	 */
	private void deleteOldExcel(File newFile) {
		final FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(final File f) {
				if (!f.isFile()) {
					return false;
				}
				if (!f.getName().endsWith("_RecvlogCheck.xls")) {
					return false;
				}
				return true;
			}
		};
		final File fileDIR = new File(newFile.getParent());
		final File[] listFiles = fileDIR.listFiles(filter);
		for (final File file : listFiles) {
			if (!newFile.getName().equals(file.getName())) {
				file.delete();
				LOGGER.info("刪除 :" + file.getName());
			}
		}
	}

}

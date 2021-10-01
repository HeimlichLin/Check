package idv.heimlich.check.domain.bean.dto.impl;

import java.io.File;
import java.util.List;

import idv.heimlich.check.common.mail.MailFace;
import idv.heimlich.check.domain.bean.vo.RecvlogCheckReportVo;

public class RecvlogCheckDTO implements MailFace {

	private static final String FORM = "jerry.lin@tradevan.com.tw";
	private static final String SUBJECT = "報單收檔異常清單";

	private String receiverId; // 收件者
	private String msg; // 信件內文
	private List<RecvlogCheckReportVo> recvlogList; // 檢核內容
	private File recvlogExcel; // 附件

	@Override
	public String to() {
		return this.receiverId;
	}

	@Override
	public String from() {
		return FORM;
	}

	@Override
	public String subject() {
		return SUBJECT;
	}

	@Override
	public String msg() {
		return this.msg;
	}

	@Override
	public String cc() {
		return null;
	}

	@Override
	public String[] files() {
		return new String[] { this.getRecvlogExcel().getPath() };
	}

	public String getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<RecvlogCheckReportVo> getRecvlogList() {
		return this.recvlogList;
	}

	public void setRecvlogList(List<RecvlogCheckReportVo> recvlogList) {
		this.recvlogList = recvlogList;
	}

	public File getRecvlogExcel() {
		return this.recvlogExcel;
	}

	public void setRecvlogExcel(File recvlogExcel) {
		this.recvlogExcel = recvlogExcel;
	}

}

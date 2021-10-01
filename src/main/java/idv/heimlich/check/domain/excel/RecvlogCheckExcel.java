package idv.heimlich.check.domain.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import idv.heimlich.check.common.utils.YYYYMMDDHHMMSSUtils;
import idv.heimlich.check.domain.bean.dto.impl.RecvlogCheckDTO;
import idv.heimlich.check.domain.bean.vo.RecvlogCheckReportVo;

/**
 * 附檔
 */
public class RecvlogCheckExcel {

	private static final String PATH = "/PCLMS/TMP/MAIL/RecvlogCheck/";
	private final RecvlogCheckDTO dto;

	public RecvlogCheckExcel(final RecvlogCheckDTO dto) {
		this.dto = dto;
	}

	/**
	 * 產生excel檔案
	 */
	public File buideExcelFile() throws IOException {
		final HSSFWorkbook book = new HSSFWorkbook();
		this.createSheelList(book);
		final String fileNameString = YYYYMMDDHHMMSSUtils.getText() + "_RecvlogCheck.xls";
		this.createFolders(PATH);
		final File outFile = new File(PATH + fileNameString);
		outFile.setReadable(true, false);
		outFile.setExecutable(true, false);
		outFile.setWritable(true, false);
		final FileOutputStream fOut = new FileOutputStream(outFile);
		book.write(fOut);
		fOut.flush();
		fOut.close();
		return outFile;
	}

	private void createSheelList(final HSSFWorkbook book) {
		final HSSFSheet sheet1 = book.createSheet("接收異常報單清單");
		final HSSFRow row = sheet1.createRow((short) 0);
		this.createCell(row, "編號", 0);
		this.createCell(row, "控制碼", 1);
		this.createCell(row, "報單號碼", 2);
		this.createCell(row, "訊息別", 3);
		this.createCell(row, "處理時間", 4);
		this.createCell(row, "查詢SQL", 5);

		int index = 1;
		for (final RecvlogCheckReportVo vo : this.dto.getRecvlogList()) {
			final HSSFRow cellRow = sheet1.createRow((short) index);
			this.createCell(cellRow, Integer.toString(index), 0);
			this.createCell(cellRow, vo.getControlno(), 1);
			this.createCell(cellRow, vo.getDeclno(), 2);
			this.createCell(cellRow, vo.getMsgtype(), 3);
			this.createCell(cellRow, vo.getProcTimes().toString(), 4);
			this.createCell(cellRow, vo.getSql(), 5);
			index++;
		}
	}

	private void createCell(final HSSFRow row, final String value, final int index) {
		final HSSFCell cell = row.createCell(index);
		cell.setCellType(CellType.STRING);
		cell.setCellValue(value);
	}

	private void createFolders(String... paths) {
		for (final String path : paths) {
			final File file = new File(path);
			file.mkdirs();
			file.setExecutable(true, false);
			file.setReadable(true, false);
			file.setWritable(true, false);
		}
	}

}

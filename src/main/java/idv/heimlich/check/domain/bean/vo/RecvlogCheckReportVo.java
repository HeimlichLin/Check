package idv.heimlich.check.domain.bean.vo;

public class RecvlogCheckReportVo {

	private String controlno; // 控制碼
	private String declno; // 未執行完成報單
	private String msgtype; // 訊息別
	private java.sql.Timestamp procTimes; // 處理時間
	private String sql; // 查詢SQL

	public String getControlno() {
		return this.controlno;
	}

	public void setControlno(String controlno) {
		this.controlno = controlno;
	}

	public String getMsgtype() {
		return this.msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public java.sql.Timestamp getProcTimes() {
		return this.procTimes;
	}

	public void setProcTimes(java.sql.Timestamp procTimes) {
		this.procTimes = procTimes;
	}

	public String getDeclno() {
		return this.declno;
	}

	public void setDeclno(String declno) {
		this.declno = declno;
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}

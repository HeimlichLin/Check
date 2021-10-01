package idv.heimlich.check.domain.bean.po.impl;

import idv.heimlich.check.domain.bean.po.IRecvlogPo;

//收單記錄檔
public class RecvlogPo implements IRecvlogPo {

	public enum COLUMNS {
		BONDNO("監管編號", false), //
		RECVTYPE("訊息別", false), //
		KEYWORD("主鍵", false), //
		MSGTYPE("Edi訊息別", false), //
		PROCTIME("系統時間", false), //
		PROCSTATUS("存檔狀態", false), //
		PROCRMK("錯誤訊息", false), //
		CONTROLNO("檔名", false), //
		;//

		final String chineseName;
		final boolean isPK;

		private COLUMNS(String chineseName, boolean isPK) {
			this.chineseName = chineseName;
			this.isPK = isPK;
		}

		public String getChineseName() {
			return this.chineseName;
		}

		public boolean isPK() {
			return this.isPK;
		}
	}

	private String bondno;// 監管編號
	private String recvtype;// 訊息別
	private String keyword;// 主鍵
	private String msgtype;// edi訊息別
	private java.sql.Timestamp proctime;// 系統時間
	private java.math.BigDecimal procstatus;// 存檔狀態
	private String procrmk;// 錯誤訊息
	private String controlno;// 檔名

	@Override
	public String getBondno() {
		return this.bondno;
	}

	@Override
	public void setBondno(String value) {
		this.bondno = value;
	}

	@Override
	public String getRecvtype() {
		return this.recvtype;
	}

	@Override
	public void setRecvtype(String value) {
		this.recvtype = value;
	}

	@Override
	public String getKeyword() {
		return this.keyword;
	}

	@Override
	public void setKeyword(String value) {
		this.keyword = value;
	}

	@Override
	public String getMsgtype() {
		return this.msgtype;
	}

	@Override
	public void setMsgtype(String value) {
		this.msgtype = value;
	}

	@Override
	public java.sql.Timestamp getProctime() {
		return this.proctime;
	}

	@Override
	public void setProctime(java.sql.Timestamp value) {
		this.proctime = value;
	}

	@Override
	public java.math.BigDecimal getProcstatus() {
		return this.procstatus;
	}

	@Override
	public void setProcstatus(java.math.BigDecimal value) {
		this.procstatus = value;
	}

	@Override
	public String getProcrmk() {
		return this.procrmk;
	}

	@Override
	public void setProcrmk(String value) {
		this.procrmk = value;
	}

	@Override
	public String getControlno() {
		return this.controlno;
	}

	@Override
	public void setControlno(String value) {
		this.controlno = value;
	}

}

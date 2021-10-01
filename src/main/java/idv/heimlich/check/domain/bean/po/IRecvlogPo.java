package idv.heimlich.check.domain.bean.po;

//收單記錄檔
public interface IRecvlogPo {

	//get監管編號
	public String getBondno();

	//set監管編號
	public void setBondno(String bondno);

	//get訊息別
	public String getRecvtype();

	//set訊息別
	public void setRecvtype(String recvtype);

	//get主鍵
	public String getKeyword();

	//set主鍵
	public void setKeyword(String keyword);

	//getEdi訊息別
	public String getMsgtype();

	//setEdi訊息別
	public void setMsgtype(String msgtype);

	//get系統時間
	public java.sql.Timestamp getProctime();

	//set系統時間
	public void setProctime(java.sql.Timestamp proctime);

	//get存檔狀態
	public java.math.BigDecimal getProcstatus();

	//set存檔狀態
	public void setProcstatus(java.math.BigDecimal procstatus);

	//get錯誤訊息
	public String getProcrmk();

	//set錯誤訊息
	public void setProcrmk(String procrmk);

	//get檔名
	public String getControlno();

	//set檔名
	public void setControlno(String controlno);

}
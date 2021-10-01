package idv.heimlich.check.domain.bean.po.impl;

import idv.heimlich.check.domain.bean.po.ISyscodePo;

public class SyscodePo implements ISyscodePo {

	public enum COLUMNS {
		TYPE_ID("", false), //
		CODE_ID("", false), //
		CODE_DATA1("", false), //
		CODE_DATA2("", false), //
		CODE_DATA3("", false), //
		CODE_DATA4("", false), //
		;//

		final String chineseName;
		final boolean isPK;

		private COLUMNS(final String chineseName, final boolean isPK) {
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

	private String typeId;//
	private String codeId;//
	private String codeData1;//
	private String codeData2;//
	private String codeData3;//
	private String codeData4;//

	@Override
	public String getTypeId() {
		return this.typeId;
	}

	@Override
	public void setTypeId(final String value) {
		this.typeId = value;
	}

	@Override
	public String getCodeId() {
		return this.codeId;
	}

	@Override
	public void setCodeId(final String value) {
		this.codeId = value;
	}

	@Override
	public String getCodeData1() {
		return this.codeData1;
	}

	@Override
	public void setCodeData1(final String value) {
		this.codeData1 = value;
	}

	@Override
	public String getCodeData2() {
		return this.codeData2;
	}

	@Override
	public void setCodeData2(final String value) {
		this.codeData2 = value;
	}

	@Override
	public String getCodeData3() {
		return this.codeData3;
	}

	@Override
	public void setCodeData3(final String value) {
		this.codeData3 = value;
	}

	@Override
	public String getCodeData4() {
		return this.codeData4;
	}

	@Override
	public void setCodeData4(final String value) {
		this.codeData4 = value;
	}

}

package idv.heimlich.check.common.code;

public enum Encoding {	
	UTF8("UTF-8"),
	UNICODE("UNICODE"),
	UTF16BE("UTF-16BE"),
	BIG5("BIG5"),
	GBK("GBK"),
	UTF8BOM("UTF8BOM"),
	;
	
	final String name;
	final String code;
	
	private Encoding(String code) {
		this.name = name();
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

}

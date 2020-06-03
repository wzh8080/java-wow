package utils.ai;

public enum BaiduAiMap {
	APIKey("RMGSkRhPZVdS67XPmyXFM3pl"),
	SecretKey("G2pFTWiYMXx3LXwnRHWukHzoZrgZ05n7");
	
	private String desc;
	private BaiduAiMap(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return this.desc;
	}
}

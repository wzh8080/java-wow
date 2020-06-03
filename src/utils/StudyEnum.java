package utils;

public enum StudyEnum {
	桌面图标位置("35,146"),
	Test("114, 36");
	
	private String desc;
	private StudyEnum(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return this.desc;
	}
}

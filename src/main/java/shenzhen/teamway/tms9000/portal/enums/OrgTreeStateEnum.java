package shenzhen.teamway.tms9000.portal.enums;

public enum OrgTreeStateEnum {
	NORMAL(0, "正常"), FREEZE(1, "冻结");

	Integer state;
	String lable;

	private OrgTreeStateEnum(Integer state, String lable) {
		this.state = state;
		this.lable = lable;
	}

	public Integer getState() {
		return state;
	}

	public String getLable() {
		return lable;
	}

	public  static String getLable(Integer state) {
		for(OrgTreeStateEnum orgTreeStateEnum : OrgTreeStateEnum.values()) {
			if(orgTreeStateEnum.getState().intValue() == state.intValue()) {
				return orgTreeStateEnum.lable;
			}
		}
		return null;
	}
}

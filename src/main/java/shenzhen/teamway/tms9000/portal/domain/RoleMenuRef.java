package shenzhen.teamway.tms9000.portal.domain;

public class RoleMenuRef {

	private Integer refId;
	private Integer roleId;
	private Integer menuId;

	public Integer getRefId() {
		return refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenuRef [refId=" + refId + ", roleId=" + roleId
				+ ", menuId=" + menuId + "]";
	}

}

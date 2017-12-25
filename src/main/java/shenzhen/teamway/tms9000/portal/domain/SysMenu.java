package shenzhen.teamway.tms9000.portal.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysMenu implements Serializable {
	private Integer menuId;

	private String menuName;

	private String menuNo;

	private String menuUrl;

	private Integer menuType;

	private Integer parentMenuId;

	private Integer menuStatus;

	private Date menuCreateTime;

	private List<SysMenu> menuList = new ArrayList<SysMenu>();

	private static final long serialVersionUID = 1L;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName == null ? null : menuName.trim();
	}

	public String getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo == null ? null : menuNo.trim();
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl == null ? null : menuUrl.trim();
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getParentMenuId() {
		return parentMenuId;
	}

	public void setParentMenuId(Integer parentMenuId) {
		this.parentMenuId = parentMenuId;
	}

	public Integer getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(Integer menuStatus) {
		this.menuStatus = menuStatus;
	}

	public Date getMenuCreateTime() {
		return menuCreateTime;
	}

	public void setMenuCreateTime(Date menuCreateTime) {
		this.menuCreateTime = menuCreateTime;
	}

	public List<SysMenu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<SysMenu> menuList) {
		this.menuList = menuList;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysMenu other = (SysMenu) obj;
		if (menuCreateTime == null) {
			if (other.menuCreateTime != null)
				return false;
		} else if (!menuCreateTime.equals(other.menuCreateTime))
			return false;
		if (menuId == null) {
			if (other.menuId != null)
				return false;
		} else if (!menuId.equals(other.menuId))
			return false;
		if (menuList == null) {
			if (other.menuList != null)
				return false;
		} else if (!menuList.equals(other.menuList))
			return false;
		if (menuName == null) {
			if (other.menuName != null)
				return false;
		} else if (!menuName.equals(other.menuName))
			return false;
		if (menuNo == null) {
			if (other.menuNo != null)
				return false;
		} else if (!menuNo.equals(other.menuNo))
			return false;
		if (menuStatus == null) {
			if (other.menuStatus != null)
				return false;
		} else if (!menuStatus.equals(other.menuStatus))
			return false;
		if (menuType == null) {
			if (other.menuType != null)
				return false;
		} else if (!menuType.equals(other.menuType))
			return false;
		if (menuUrl == null) {
			if (other.menuUrl != null)
				return false;
		} else if (!menuUrl.equals(other.menuUrl))
			return false;
		if (parentMenuId == null) {
			if (other.parentMenuId != null)
				return false;
		} else if (!parentMenuId.equals(other.parentMenuId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuCreateTime == null) ? 0 : menuCreateTime.hashCode());
		result = prime * result + ((menuId == null) ? 0 : menuId.hashCode());
		result = prime * result + ((menuList == null) ? 0 : menuList.hashCode());
		result = prime * result + ((menuName == null) ? 0 : menuName.hashCode());
		result = prime * result + ((menuNo == null) ? 0 : menuNo.hashCode());
		result = prime * result + ((menuStatus == null) ? 0 : menuStatus.hashCode());
		result = prime * result + ((menuType == null) ? 0 : menuType.hashCode());
		result = prime * result + ((menuUrl == null) ? 0 : menuUrl.hashCode());
		result = prime * result + ((parentMenuId == null) ? 0 : parentMenuId.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "SysMenu [menuId=" + menuId + ", menuName=" + menuName + ", menuNo=" + menuNo + ", menuUrl=" + menuUrl
				+ ", menuType=" + menuType + ", parentMenuId=" + parentMenuId + ", menuStatus=" + menuStatus
				+ ", menuCreateTime=" + menuCreateTime + ", menuList=" + menuList + "]";
	}
}
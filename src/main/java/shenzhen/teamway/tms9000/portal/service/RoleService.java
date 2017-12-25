package shenzhen.teamway.tms9000.portal.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import shenzhen.teamway.tms9000.portal.domain.Role;
import shenzhen.teamway.tms9000.portal.util.PageBean;

public interface RoleService {
	
	public PageList<Role> roleAll(Role role , PageBounds page);
	
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	public int addRole(Role role,String menuList);
	
	
	/**
	 * 根据ID查询角色
	 * @param roleId
	 * @return
	 */
	public Role getRoleById(Integer roleId);
	
	/**
	 * 生成角色下拉列表
	 * @param role
	 * @return
	 */
	public List<Role> getRoles(Role role);
	
	/**
	 * 修改角色信息
	 * @param role
	 * @param menuList
	 * @return
	 */
	public int updateRoleInfo(Role role , String menuList);
	
	
	/**
	 * 根据ID删除角色信息
	 * @param roleId
	 * @return
	 */
	public int deleteRole(Integer roleId);
	
	
	public PageBean roleList(Role role ,PageBean page );
	
	/**
	 * 根据用户ID查询权限用户的权限
	 * @param userId
	 * @return
	 */
	public Role getRoleByUserId(Integer userId);

}

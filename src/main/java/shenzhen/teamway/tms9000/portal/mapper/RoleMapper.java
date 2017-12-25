package shenzhen.teamway.tms9000.portal.mapper;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import shenzhen.teamway.tms9000.portal.domain.Role;
import shenzhen.teamway.tms9000.portal.domain.RoleMenuRef;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);
    
public PageList<Role> queryRoleList(Role role,PageBounds page);
	
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	public int addRole(Role role);
	
	
	/**
	 * 保存角色菜单
	 * @param ref
	 * @return
	 */
	public int addRoleMenuRef(RoleMenuRef ref);
	
	
	/**
	 * 根据ID查询角色
	 * @param roleId
	 * @return
	 */
	public Role getRoleById(Integer roleId);
	
	/**
	 * 查询角色
	 * @return
	 */
	public List<Role> getRoles(Role role);
	
	/**
	 * 修改角色信息
	 * @param role
	 * @return
	 */
	public int updateRoleInfo(Role role);
	
	/**
	 * 删除角色拥有资源
	 * @param roleId
	 * @return
	 */
	public int deleteRoleMenuRef(Integer roleId);
	
	/**
	 * 更改角色状态
	 * @param roleStatus
	 * @return
	 */
	public int updateRoleStatus(Integer roleStatus,Integer roleId);
	
	/**
	 * 分页查询角色列表
	 * @param map
	 * @return
	 */
	public List<Role> queryRoleListForPage(Map map);
	
	public int queryRoleListForPageCount(Map map);

	/**
	 * 根据用户ID查询权限用户的权限
	 * @param userId
	 * @return
	 */
	public Role getRoleByUserId(Integer userId);
}
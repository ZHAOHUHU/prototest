package shenzhen.teamway.tms9000.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import shenzhen.teamway.tms9000.portal.domain.Role;
import shenzhen.teamway.tms9000.portal.domain.RoleMenuRef;
import shenzhen.teamway.tms9000.portal.mapper.RoleMapper;
import shenzhen.teamway.tms9000.portal.service.RoleService;
import shenzhen.teamway.tms9000.portal.util.ConstantUtils;
import shenzhen.teamway.tms9000.portal.util.PageBean;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	RoleMapper roleMapper;

	/**
	 * 角色列表
	 */
	@Override
	public PageList<Role> roleAll(Role role, PageBounds page) {
		return roleMapper.queryRoleList(role, page);
	}
	
	public PageBean roleList(Role role ,PageBean page ){
		Map paramMap = new HashMap();
		paramMap.put("page",page.getLimit());
		paramMap.put("limit", page.getEndLimit());
		paramMap.put("roleId",role.getId());
		int count = roleMapper.queryRoleListForPageCount(paramMap);
		if(count>0){
			page.setRows(roleMapper.queryRoleListForPage(paramMap));
		}
		page.setTotal(count);
		return page;
	}
	/**
	 * 新增角色
	 * @param role
	 * @return
	 */
	@Transactional
	public int addRole(Role role,String menuList){
		int roleId = roleMapper.addRole(role);
		String[] menuIds = menuList.split(",");
		for (String string : menuIds) {
			  RoleMenuRef ref = new RoleMenuRef();
			  ref.setMenuId(Integer.valueOf(string));
			  ref.setRoleId(role.getId());
			  roleMapper.addRoleMenuRef(ref);
		}
		return role.getId();
	}
	@Override
	public Role getRoleById(Integer roleId) {
		return roleMapper.getRoleById(roleId);
	}
	
	@Override
	public List<Role> getRoles(Role role) {
		return roleMapper.getRoles(role);
	}
	
	@Transactional
	@Override
	public int updateRoleInfo(Role role, String menuList) {
		int flag =  roleMapper.updateRoleInfo(role);
		roleMapper.deleteRoleMenuRef(role.getId());
		String[] menuIds = menuList.split(",");
		for (String string : menuIds) {
			  RoleMenuRef ref = new RoleMenuRef();
			  ref.setMenuId(Integer.valueOf(string));
			  ref.setRoleId(role.getId());
			  roleMapper.addRoleMenuRef(ref);
		}
		return role.getId();
	}
	@Transactional
	public int deleteRole(Integer roleId){
		return roleMapper.updateRoleStatus(ConstantUtils.ROLE_STATUS_2,roleId); //灰删除
	}

	@Override
	public Role getRoleByUserId(Integer userId) {
		return roleMapper.getRoleByUserId(userId);
	}

}

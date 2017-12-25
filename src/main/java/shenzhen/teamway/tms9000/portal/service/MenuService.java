package shenzhen.teamway.tms9000.portal.service;

import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import shenzhen.teamway.tms9000.portal.domain.Role;
import shenzhen.teamway.tms9000.portal.domain.SysMenu;
import shenzhen.teamway.tms9000.portal.domain.User;
import shenzhen.teamway.tms9000.portal.util.PageBean;

public interface MenuService {

	int deleteByPrimaryKey(Integer menuId);

	int insert(SysMenu record);

	SysMenu selectByPrimaryKey(Integer menuId);

	List<SysMenu> selectAll();

	int updateByPrimaryKey(SysMenu record);

	/**
	 * 用户登录查询菜单列表
	 * @param user
	 * @return
	 */
	List<SysMenu> queryMenusByUser(User user);
	
public List<SysMenu> queryMenuByUser(Role role);
	
	/**
	 * 菜单列表
	 * @param menu
	 * @param page
	 * @return
	 */
	public PageList<SysMenu> menuList(SysMenu menu , PageBounds page);
	
	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	public int addMenu(SysMenu menu);
	
	/**
	 * 查询菜单信息
	 * @param menuId
	 * @return
	 */
	public SysMenu getMenuById(Integer menuId);
	
	/**
	 * 更新菜单信息
	 * @param menu
	 * @return
	 */
	public int updateMenu(SysMenu menu);
	
	/**
	 * 查询角色拥有菜单列表
	 * @param role
	 * @return
	 */
	public List<SysMenu> queryMenuByRole(Role role);
	
	
	/**
	 * 删除菜单
	 * @param menuId
	 * @return
	 */
	public int deleteMenu(Integer menuId);
	
	/**
	 * 分页查询
	 * @param menu
	 * @param page
	 * @return
	 */
	public PageBean  menuListForPage(SysMenu menu , PageBean page);
	
	/**
	 * 根据用户查询拥有菜单
	 * @param user
	 * @return
	 */
	public List<SysMenu> queryMenuByUser(User user) ;
	
	/**
	 * 根据url查询菜单
	 * @param url
	 * @return
	 */
	public List<SysMenu> queryMenuByUrl(String url);
	
}

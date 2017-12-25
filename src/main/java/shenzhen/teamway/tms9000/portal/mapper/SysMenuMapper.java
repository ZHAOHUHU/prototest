package shenzhen.teamway.tms9000.portal.mapper;

import java.util.List;
import java.util.Map;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import shenzhen.teamway.tms9000.portal.domain.SysMenu;

public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(SysMenu record);

    SysMenu selectByPrimaryKey(Integer menuId);

    List<SysMenu> selectAll();

    int updateByPrimaryKey(SysMenu record);

    /**
     * 根据用户表id查询查单列表
     * @param id
     * @return
     */
	List<SysMenu> queryMenuByUserId(int id);
	
	/**
	 * 查询角色拥有菜单
	 * 
	 * @param roleId
	 * @return
	 */
	public List<SysMenu> queryMenuByRoleId(Integer roleId);
	
	
	/**
	 * 查询用户拥有菜单
	 * @param user
	 * @return
	 */
	public List<SysMenu> queryMenuByUserId(Integer userId);
	
	public PageList<SysMenu> queryMenuList(SysMenu menu ,PageBounds page);

	/**
	 * 分页查询菜单列表
	 * 
	 * @param page
	 * @return
	 */
	public List<SysMenu> queryMenuListForPage(Map paramMap);
	
	
	public int queryMenuListCount(Map paramMap);
	
	/**
	 * 保存菜单
	 * @param menu
	 * @return
	 */
	public int saveMenu(SysMenu menu);
	
	/**
	 * 根据ID查询菜单
	 * @param menuId
	 * @return
	 */
	public SysMenu getMenuById(Integer menuId);
	
	
	/**
	 * 菜单更新
	 * @param menu
	 * @return
	 */
	public int updateMenu(SysMenu menu);
	
	
	/**
	 * 查询子菜单
	 * @param parentMenuId
	 * @return
	 */
	public List<SysMenu> querySonMenu(Integer parentMenuId);
	
	
	/**
	 * 修改菜单状态
	 * @param menuId
	 * @param menuStatus
	 * @return
	 */
	public int updateMenuStatus(Integer menuId,Integer menuStatus);
	
	
	
	/**
	 * 根据url查询菜单
	 * @param url
	 * @return
	 */
	public List<SysMenu> getMenuByUrl(String url);
}
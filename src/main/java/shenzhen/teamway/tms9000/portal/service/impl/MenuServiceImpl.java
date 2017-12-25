package shenzhen.teamway.tms9000.portal.service.impl;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import shenzhen.teamway.tms9000.portal.domain.Role;
import shenzhen.teamway.tms9000.portal.domain.SysMenu;
import shenzhen.teamway.tms9000.portal.domain.User;
import shenzhen.teamway.tms9000.portal.mapper.SysMenuMapper;
import shenzhen.teamway.tms9000.portal.service.MenuService;
import shenzhen.teamway.tms9000.portal.util.ConstantUtils;
import shenzhen.teamway.tms9000.portal.util.PageBean;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private SysMenuMapper menuMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer menuId) {
		return menuMapper.deleteByPrimaryKey(menuId);
	}

	@Override
	public int insert(SysMenu record) {
		return menuMapper.insert(record);
	}

	@Override
	public SysMenu selectByPrimaryKey(Integer menuId) {
		return menuMapper.selectByPrimaryKey(menuId);
	}

	@Override
	public List<SysMenu> selectAll() {
		return menuMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(SysMenu record) {
		return menuMapper.updateByPrimaryKey(record);
	}

	/**
	 * 查询菜单
	 */
	@Override
	public List<SysMenu> queryMenuByUser(Role role) {
		List<SysMenu> menuList = menuMapper.queryMenuByRoleId(role.getId());
		Map<Integer, SysMenu> menuMap = new HashMap<Integer, SysMenu>();
		return showMenu(menuList, null, menuMap);
	}
	
	public List<SysMenu> queryMenusByUser(User user){
		List<SysMenu> menuList = menuMapper.queryMenuByUserId(user.getId());
		Map<Integer, SysMenu> menuMap = new HashMap<Integer, SysMenu>();
		return showMenu(menuList, null, menuMap);
	}

	public List<SysMenu> queryMenuByRole(Role role) {
		List<SysMenu> menuList = menuMapper.queryMenuByRoleId(role.getId());
		return menuList;
	}
	public List<SysMenu> queryMenuByUser(User user) {
		List<SysMenu> menuList = menuMapper.queryMenuByUserId(user.getId());

		return menuList;
	}

	@Override
	public PageList<SysMenu> menuList(SysMenu menu, PageBounds page) {

		return menuMapper.queryMenuList(menu, page);
	}
	
	public PageBean  menuListForPage(SysMenu menu , PageBean page){
		Map paramMap = new HashMap();
		paramMap.put("page", page.getLimit());
		paramMap.put("limit",page.getEndLimit());
		int count  = menuMapper.queryMenuListCount(paramMap);
		if(count>0){
			page.setRows(menuMapper.queryMenuListForPage(paramMap));
		}
		page.setTotal(count);
		return page;
		
	}
	@Transactional
	public int addMenu(SysMenu menu) {
		return menuMapper.saveMenu(menu);
	}

	@Override
	public SysMenu getMenuById(Integer menuId) {
		return menuMapper.getMenuById(menuId);
	}
	@Transactional
	@Override
	public int updateMenu(SysMenu menu) {
		return menuMapper.updateMenu(menu);
	}
	@Transactional
	@Override
	public int deleteMenu(Integer menuId) {
		List<SysMenu> menuList = menuMapper.querySonMenu(menuId);
		if (menuList.size() > 0) {
			return -1; // 有子菜单不能禁用
		}
		return menuMapper.updateMenuStatus(menuId,
				ConstantUtils.MENU_STATUS_CANCLE);
	}


	@Override
	public List<SysMenu> queryMenuByUrl(String url) {
		return menuMapper.getMenuByUrl(url);
	}
	/**
	 * 构建菜单
	 * 
	 * @param menuList
	 * @return
	 */
	private List showMenu(List<SysMenu> allMenu, Menu SysMenu,
			Map<Integer, SysMenu> menuMap) {
		if (allMenu == null || allMenu.size() == 0) {
			return null;
		} else {
			Iterator<SysMenu> iter = allMenu.iterator();
			while (iter.hasNext()) {
				SysMenu m = iter.next();
				if ((m != null && m.getParentMenuId() == null)
						|| (m == null)) {
					menuMap.put(m.getMenuId(),m);

				} else {
					if(menuMap.get(m.getParentMenuId())!=null){
						menuMap.get(m.getParentMenuId()).getMenuList().add(m);
					}
				}

			}

		}
		   
		return toList(menuMap);
	}
	private List toList(Map map){
		 List listKey = new ArrayList();  
	        List listValue = new ArrayList();  
	        Iterator it = map.keySet().iterator();  
	        while (it.hasNext()) {  
	            Object key = it.next();  
	            listKey.add(key);  
	            listValue.add(map.get(key));  
	        }  
	         return  listValue;
	}
}

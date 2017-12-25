package shenzhen.teamway.tms9000.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import shenzhen.teamway.tms9000.portal.domain.Role;
import shenzhen.teamway.tms9000.portal.domain.SysMenu;
import shenzhen.teamway.tms9000.portal.service.MenuService;
import shenzhen.teamway.tms9000.portal.util.PageBean;

@Controller
public class MenuController {
	public static Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	MenuService menuService;

	@RequestMapping("/menu/initMenuList.html")
	public ModelAndView initMenulist() {
		logger.info("--------begin initMenulist ----------");
		SysMenu menu = new SysMenu();
		ModelAndView view = new ModelAndView("menu/menuList");
		menu.setMenuType(1);
		PageList<SysMenu> bean = menuService.menuList(menu,new PageBounds());
		view.addObject("modules", bean);
		logger.info("--------end initMenulist ----------");
		return view;
	}

	/**
	 * 菜单列表
	 * 
	 * @param menu
	 * @param page
	 * @return
	 */
	@RequestMapping("/menu/menuList.html")
	public void menuList(SysMenu menu, PageBean page,
			HttpServletResponse response) {
		logger.info("--------begin menuList ----------");
		PageBean pageBean = menuService.menuListForPage(menu, page);
		String json = JSON.toJSONString(pageBean);
		PrintWriter pw = null;
		try {
			response.setCharacterEncoding("UTF-8");
			pw = response.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("--------error menuList ----------", e);
		} finally {
			pw.close();
		}
		logger.info("--------end menuList ----------");
	}
	
	/**
	 * 菜单列表
	 * 
	 * @param menu
	 * @param page
	 * @return
	 */
	@RequestMapping("/menu/menuTree.html")
	public void menuListTree(SysMenu menu,Role role, PageBounds page,
			HttpServletResponse response) {
		logger.info("--------begin menuListTree ----------");
		List pageList = new ArrayList();

		if(role.getId()==null){
			PageList<SysMenu> pageInfo = menuService.menuList(menu, page);
			for (SysMenu m : pageInfo) {
				Map map = new HashMap();
				map.put("id",m.getMenuId() );
				map.put("pId",m.getParentMenuId());
				map.put("name", m.getMenuName());
				pageList.add(map);
			}
		}else {
			List<SysMenu> list  = menuService.queryMenuByRole(role);
			for (SysMenu m : list) {
				Map map = new HashMap();
				map.put("id",m.getMenuId() );
				map.put("pId",m.getParentMenuId());
				map.put("name", m.getMenuName());
				pageList.add(map);
			}
		}
		String json = JSON.toJSONString(pageList);
		PrintWriter pw = null;
		try {
			response.setCharacterEncoding("UTF-8");
			pw = response.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("--------error menuListTree ----------", e);
		} finally {
			pw.close();
		}
		logger.info("--------end menuListTree ----------");
	}

	/**
	 * 新增菜单
	 * 
	 * @param menu
	 */
	@RequestMapping(value = "/menu/addMenu.html", method = RequestMethod.POST)
	public void addMenu(SysMenu menu, HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			logger.info("--------begin addMenu ----------");
			int flag = menuService.addMenu(menu);
			Map resultMap = new HashMap();
			resultMap.put("flag", flag);
			String json = JSON.toJSONString(resultMap);
			response.setCharacterEncoding("UTF-8");
			pw = response.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("--------error addMenu ----------", e);
		} finally {
			pw.close();
		}
		logger.info("--------end addMenu ----------");
	}
	
	/**
	 * 加载菜单信息
	 * @param menuId
	 * @param response
	 * @return
	 */
	@RequestMapping("/menu/intiMenuInfo.html")
	public ModelAndView initMenuInfo(Integer menuId,
			HttpServletResponse response) {
		ModelAndView view = new ModelAndView("/menu/updateMenu");
		logger.info("--------begin initMenuInfo ----------");
		SysMenu menuTemp = new SysMenu();
		menuTemp.setMenuType(1);
		PageList<SysMenu> pageInfo = menuService.menuList(menuTemp, new PageBounds());
		view.addObject("modules", pageInfo);
		SysMenu menu = menuService.getMenuById(menuId);
		view.addObject("menu", menu);
		logger.info("--------begin initMenuInfo ----------");
		return view;

	}
	/**
	 * 更新菜单信息
	 * @param menu
	 */
	@RequestMapping(value="/menu/updateMenu.html",method = RequestMethod.POST)
	public void updateMenu(SysMenu menu,HttpServletResponse response){
		PrintWriter pw = null;
		try {
			logger.info("--------begin updateMenu ----------");
			int flag = menuService.updateMenu(menu);
			Map resultMap = new HashMap();
			resultMap.put("flag", flag);
			String json = JSON.toJSONString(resultMap);
			response.setCharacterEncoding("UTF-8");
			pw = response.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("--------error updateMenu ----------", e);
		} finally {
			pw.close();
		}
		logger.info("--------end updateMenu ----------");
	}
	
	/**
	 * 删除菜单
	 * @param menuId
	 * @param response
	 */
	@RequestMapping(value="/menu/deleteMenu.html",method=RequestMethod.POST)
	public void deleteMenu(Integer menuId , HttpServletResponse response){
		PrintWriter pw = null;
		try {
			logger.info("--------begin deleteMenu ----------");
			int flag = menuService.deleteMenu(menuId);
			Map resultMap = new HashMap();
			resultMap.put("flag", flag);
			String json = JSON.toJSONString(resultMap);
			response.setCharacterEncoding("UTF-8");
			pw = response.getWriter();
			pw.write(json);
		} catch (IOException e) {
			logger.error("--------error deleteMenu ----------", e);
		} finally {
			pw.close();
		}
		logger.info("--------end deleteMenu ----------");
		
	}
}

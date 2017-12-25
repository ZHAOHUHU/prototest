package shenzhen.teamway.tms9000.portal.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 从返回的结果集中获取 分页需要的数据条数
	 * 
	 * @param list
	 * @param bean
	 */
	public static PageBean findPage(List list, PageBean bean) {

		int pageStart = bean.getLimit();// 获取取数据的开始位置
		int pageCount = pageStart + 10; // 默认取10条数据

		if (pageCount > list.size()-1) {
			pageCount = list.size()-1;
		}

		List subList = new ArrayList();
		for (int i = pageStart; i <= pageCount; i++) {
			subList.add(list.get(i));
		}
		bean.setTotal(list.size());
		bean.setRows(subList);
		
		return bean;
	}

}

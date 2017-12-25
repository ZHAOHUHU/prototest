package shenzhen.teamway.tms9000.portal.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageBean implements Serializable {
	/**
	 * 
	 * 
	 * @author alan总记录数 list 
	 * 保存分页的数据 pageNo 当前页
	 * pageSize 页大小 rows 

	 * 
	 * 
	 */

	private int total;

	private List rows=new ArrayList();


	private int pageNo=1;

	private int pageSize=10;
	
	private int offset=1 ;

	
	private int page=1;
	
	
	
	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRows() {
	
		return this.rows;
	}

	public void setRows(List rows) {
		
		this.rows = rows;
	}


	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}




	/**
	 * 取得总页数的方法 return
	 * totalRecords%pageSize==0?(totalRecords/pageSize):(totalRecords
	 * /pageSize+1)
	 * 
	 * @return
	 */
	public int getTotalPages() {
		return (total + pageSize - 1) / pageSize;
	}

	/**
	 * 得到首页
	 * 
	 * @return
	 */
	public int getTopPage() {
		return 1;
	}

	/**
	 * 得到上一页
	 * 
	 * @return
	 */
	public int getPreviousPageNo() {
		if (pageNo <= 1)
			return 1;
		else
			return (pageNo - 1);
	}

	/**
	 * 得到下一页
	 * 
	 * @return
	 */
	public int getNextPageNo() {
		if (pageNo >= getTotalPages()) {
			return getTotalPages() == 0 ? 1 : getTotalPages();
		} else {
			return pageNo + 1;
		}
	}

	/**
	 * 得到尾页
	 * 
	 * @return
	 */
	public int getBottomPageNo() {
		return getTotal() == 0 ? 1 : getTotalPages();
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getLimit(){
		return this.getPage()==1?0:(this.getPage()-1)*this.getPageSize();
	}
	public int getEndLimit(){
		return this.getPageSize();
	}
	

}

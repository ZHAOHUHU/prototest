package shenzhen.teamway.tms9000.portal.service;

import java.util.List;

import shenzhen.teamway.tms9000.portal.domain.RegionInfo;

public interface RegionInfoService {

	int deleteByPrimaryKey(Long id);

	int insert(RegionInfo record);

	RegionInfo selectByPrimaryKey(Long id);

	List<RegionInfo> selectAll();

	int updateByPrimaryKey(RegionInfo record);
	/**
	 * 根据id，pid 删除节点
	 * @param id
	 * @param pid
	 * @return
	 */
	int delOrgTreeById(RegionInfo info);
	/**
	 * 增加节点数据
	 * @param info
	 * @return
	 */
	int addOrgTreeNode(RegionInfo info);
}

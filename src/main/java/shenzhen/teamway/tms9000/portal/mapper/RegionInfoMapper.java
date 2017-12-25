package shenzhen.teamway.tms9000.portal.mapper;

import java.util.List;

import shenzhen.teamway.tms9000.portal.domain.RegionInfo;

public interface RegionInfoMapper {
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
	int updateOrgTreeById(RegionInfo info);

	/**
	 * 增加组织节点数据
	 * @param info
	 * @return
	 */
	int addOrgTreeNode(RegionInfo info);
}
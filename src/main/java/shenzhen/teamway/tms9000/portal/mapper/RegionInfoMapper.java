package shenzhen.teamway.tms9000.portal.mapper;

import java.util.List;

import shenzhen.teamway.tms9000.portal.domain.RegionInfo;

public interface RegionInfoMapper {

    //展示树
    List<RegionInfo> selectAll();

    //删除树
	void deleteTreenodeById(Long id);
	//添加子节点
	RegionInfo addChildrenode(RegionInfo regionInfo);

}
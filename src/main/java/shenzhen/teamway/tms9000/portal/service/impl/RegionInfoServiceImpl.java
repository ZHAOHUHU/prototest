package shenzhen.teamway.tms9000.portal.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import shenzhen.teamway.tms9000.portal.domain.RegionInfo;
import shenzhen.teamway.tms9000.portal.mapper.RegionInfoMapper;
import shenzhen.teamway.tms9000.portal.service.RegionInfoService;

@Service
public class RegionInfoServiceImpl implements RegionInfoService {

	@Resource
	private RegionInfoMapper regionInfoMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return regionInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RegionInfo record) {
		return regionInfoMapper.insert(record);
	}

	@Override
	public RegionInfo selectByPrimaryKey(Long id) {
		return regionInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<RegionInfo> selectAll() {
		return regionInfoMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(RegionInfo record) {
		return regionInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public int delOrgTreeById(RegionInfo info) {
		return regionInfoMapper.updateOrgTreeById(info);
	}
	
	@Override
	public int addOrgTreeNode(RegionInfo info) {
		return regionInfoMapper.addOrgTreeNode(info);
	}
}

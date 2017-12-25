package shenzhen.teamway.tms9000.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shenzhen.teamway.tms9000.portal.domain.RegionInfo;
import shenzhen.teamway.tms9000.portal.mapper.RegionInfoMapper;
import shenzhen.teamway.tms9000.portal.service.Regionservice;

import java.util.List;

@Service
public class RegionserviceImp implements Regionservice{
    @Autowired
    RegionInfoMapper regionInfoMapper;

    @Override
    public List<RegionInfo> showTree() {
        return regionInfoMapper.selectAll();
    }

    @Override
    public void deleteTreenodeById(Long id) {
        regionInfoMapper.deleteTreenodeById(id);
    }

    @Override
    public RegionInfo addChildrenode(RegionInfo regionInfo) {
        return regionInfoMapper.addChildrenode(regionInfo);
    }

}

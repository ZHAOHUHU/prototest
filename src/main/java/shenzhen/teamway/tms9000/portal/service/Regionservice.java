package shenzhen.teamway.tms9000.portal.service;

import shenzhen.teamway.tms9000.portal.domain.RegionInfo;

import java.util.List;

public interface Regionservice {
    public List<RegionInfo> showTree();

    void deleteTreenodeById(Long id);


    RegionInfo addChildrenode(RegionInfo regionInfo);
}

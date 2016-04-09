package pers.evan.fastrepair.service.impl;

import org.springframework.stereotype.Service;
import pers.evan.fastrepair.dao.RoleDao;
import pers.evan.fastrepair.service.RoleService;

import javax.annotation.Resource;

/**
 * Created by Evan on 2016/4/9.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;
    
}

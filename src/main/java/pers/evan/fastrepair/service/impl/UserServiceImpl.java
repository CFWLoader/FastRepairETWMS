package pers.evan.fastrepair.service.impl;

import org.springframework.stereotype.Service;
import pers.evan.fastrepair.dao.UserDao;
import pers.evan.fastrepair.service.UserService;

import javax.annotation.Resource;

/**
 * Created by Evan on 2016/4/9.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

}

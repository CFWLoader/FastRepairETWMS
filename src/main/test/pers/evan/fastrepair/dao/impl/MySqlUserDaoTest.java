package pers.evan.fastrepair.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pers.evan.fastrepair.dao.UserDao;
import pers.evan.fastrepair.model.User;

/**
 * Created by Evan on 2016/4/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MySqlUserDaoTest extends AbstractJUnit4SpringContextTests{

    @Test
    public void testAddUser()
    {
        UserDao userDao = applicationContext.getBean(UserDao.class);

        User user = new User();

        user.setUsername("Evan");

        user.setPassword("123456");

        userDao.addEntity(user);
    }

}

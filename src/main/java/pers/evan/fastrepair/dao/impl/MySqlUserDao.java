package pers.evan.fastrepair.dao.impl;

import org.springframework.stereotype.Repository;
import pers.evan.fastrepair.dao.TemplateDao;
import pers.evan.fastrepair.dao.UserDao;
import pers.evan.fastrepair.model.User;

/**
 * Created by Evan on 2016/4/9.
 */
@Repository
public class MySqlUserDao extends TemplateDaoImpl<User> implements UserDao{
}

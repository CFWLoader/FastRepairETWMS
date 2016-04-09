package pers.evan.fastrepair.dao.impl;

import org.springframework.stereotype.Repository;
import pers.evan.fastrepair.dao.RoleDao;
import pers.evan.fastrepair.model.Role;

/**
 * Created by Evan on 2016/4/9.
 */
@Repository
public class MySqlRoleDao extends TemplateDaoImpl<Role> implements RoleDao {
}

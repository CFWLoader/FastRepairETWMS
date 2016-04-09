package pers.evan.fastrepair.dao.impl;

import org.springframework.stereotype.Repository;
import pers.evan.fastrepair.dao.PrivilegeDao;
import pers.evan.fastrepair.model.Privilege;

/**
 * Created by Evan on 2016/4/9.
 */
@Repository
public class MySqlPrivilegeDao extends TemplateDaoImpl<Privilege> implements PrivilegeDao{
}

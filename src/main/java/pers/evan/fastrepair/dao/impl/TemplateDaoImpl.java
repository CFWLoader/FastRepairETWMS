package pers.evan.fastrepair.dao.impl;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;
import pers.evan.fastrepair.dao.TemplateDao;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by cfwloader on 5/19/15.
 */

@SuppressWarnings("unchecked")
@Transactional
public class TemplateDaoImpl<T> implements TemplateDao<T> {

    @Resource
    protected HibernateTemplate hibernateTemplate;
    /*
    @Resource
    private SessionFactory sessionFactory;
    */

    protected Class<T> clazz;

    public TemplateDaoImpl() {

        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //  获取第一个泛型化参数
        clazz = (Class<T>) type.getActualTypeArguments()[0];

    }

    @Override
    public void addEntity(T entity) {
        //sessionFactory.getCurrentSession().save(entity);
        hibernateTemplate.save(entity);
    }

    @Override
    public void deleteEntity(T entity) {
        //sessionFactory.getCurrentSession().delete(entity);
        hibernateTemplate.delete(entity);
    }

    @Override
    public void updateEntity(T entity) {
        //sessionFactory.getCurrentSession().update(entity);
        hibernateTemplate.update(entity);
    }

    @Override
    public List<T> getEntities() {
        //Session session = sessionFactory.getCurrentSession();
        //return session.createQuery("From " + clazz.getSimpleName()).list();
        DetachedCriteria criteria = DetachedCriteria.forClass(clazz);

        return (List<T>) hibernateTemplate.findByCriteria(criteria);
    }

    @Override
    public T getEntityById(long id) {
        return (T) hibernateTemplate.get(clazz, id);
    }

    @Override
    public Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }
}

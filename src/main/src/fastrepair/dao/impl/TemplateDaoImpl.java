package fastrepair.dao.impl;

import fastrepair.dao.TemplateDao;
import javassist.ClassPath;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    private SessionFactory sessionFactory;

    private Class<T> clazz;

    public TemplateDaoImpl() {

        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        //  获取第一个泛型化参数
        clazz = (Class<T>) type.getActualTypeArguments()[0];

    }

    @Override
    public void addEntity(T entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void deleteEntity(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void updateEntity(T entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public List<T> getEntities() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("From " + clazz.getSimpleName()).list();

    }

    @Override
    public T getEntityById(Long id) {
        if(id != null){
            return (T) sessionFactory.getCurrentSession().get(clazz, id);
        } else {
            return null;
        }
    }

    @Override
    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}

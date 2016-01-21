package pers.evan.fastrepair.dao;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by cfwloader on 5/19/15.
 */

/**
 * @param <T>
 */
public interface TemplateDao<T> {

    Object addEntity(T entity);

    void deleteEntity(T entity);

    void updateEntity(T entity);

    List<T> getEntities();

    List<T> getEntities(int startIndex, int fetchSize);

    T getEntityById(long id);

    Session getSession();

}

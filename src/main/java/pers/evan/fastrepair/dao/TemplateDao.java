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

    void addEntity(T entity);

    void deleteEntity(T entity);

    void updateEntity(T entity);

    List<T> getEntities();

    T getEntityById(Long id);

    Session getSession();

}

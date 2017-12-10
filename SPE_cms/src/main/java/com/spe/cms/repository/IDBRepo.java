package com.spe.cms.repository;

public interface IDBRepo <ID, T> {

    /**
     * @return : the size of the database table
     */
    int size();

    /**
     * @param entity : the entity to save
     */
    void save(T entity);

    /**
     * @param id : the id for the object that is going to be deleted
     */
    void delete(ID id);

    /**
     * @param id : the id for the object that is going to be updated
     * @param entity : the new object
     */
    void update(ID id, T entity);

    /**
     * @param id : the id of the object to search for
     * @return : the found object
     */
    T findOne(ID id);

    /**
     * @return : all the objects in the database table
     */
    Iterable<T> findAll();

}
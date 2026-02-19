package com.academy.four.dao;

import java.util.List;

public interface Dao<E> {

    boolean save(E entity);

    boolean update(E entity);

    boolean delete(E entity);

    E findById(Integer id);

    List<E> findAll();
}

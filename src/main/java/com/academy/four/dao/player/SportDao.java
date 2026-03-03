package com.academy.four.dao.player;

import com.academy.four.dao.Dao;

import java.util.List;

public class SportDao implements Dao<Sport> {
    @Override
    public boolean save(Sport entity) {
        return false;
    }

    @Override
    public boolean update(Sport entity) {
        return false;
    }

    @Override
    public boolean delete(Sport entity) {
        return false;
    }

    @Override
    public Sport findById(Integer id) {
        return null;
    }

    @Override
    public List<Sport> findAll() {
        return List.of();
    }
}

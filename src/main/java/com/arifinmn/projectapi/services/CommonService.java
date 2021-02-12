package com.arifinmn.projectapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CommonService<T, ID> {
    public List<T> findAll();

    public T save(T entity);

    public T findById(ID id);

    public T removeById(ID id);

    public Page<T> findAll(T search, int page, int size, Sort.Direction direction);

}

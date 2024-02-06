package com.javaexpress.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javaexpress.entities.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer>{

}

package com.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecondRepository extends CrudRepository<String, Long> {

    String getByIdAndName(Long id, String name);
}

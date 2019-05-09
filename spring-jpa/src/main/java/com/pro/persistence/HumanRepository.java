package com.pro.persistence;

import com.pro.model.Human;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Collection;

@RepositoryRestController
public interface HumanRepository extends JpaRepository<Human, Long> {

    Collection<Human> getAllByName(@Param("name") String name);
}

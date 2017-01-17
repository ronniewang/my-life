package com.test.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.test.db.entity.EventTypeEntity;

@RepositoryRestResource(collectionResourceRel = "eventTypes", path = "eventTypes")
public interface EventTypeRepository extends JpaRepository<EventTypeEntity, Integer> {

}

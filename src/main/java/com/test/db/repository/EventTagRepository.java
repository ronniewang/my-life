package com.test.db.repository;

import com.test.db.entity.EventTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface EventTagRepository extends JpaRepository<EventTagEntity, Long> {

}

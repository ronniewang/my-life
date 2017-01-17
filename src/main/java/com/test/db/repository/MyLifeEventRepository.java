package com.test.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.test.db.entity.MyLifeEventEntity;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface MyLifeEventRepository extends JpaRepository<MyLifeEventEntity, Long> {

}

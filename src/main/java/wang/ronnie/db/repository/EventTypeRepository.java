package wang.ronnie.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import wang.ronnie.db.entity.EventTypeEntity;
import wang.ronnie.db.entity.MyLifeEventEntity;

@RepositoryRestResource(collectionResourceRel = "eventTypes", path = "eventTypes")
public interface EventTypeRepository extends JpaRepository<EventTypeEntity, Integer> {

}

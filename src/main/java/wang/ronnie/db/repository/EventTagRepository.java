package wang.ronnie.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import wang.ronnie.db.entity.TEventTagEntity;

@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface EventTagRepository extends JpaRepository<TEventTagEntity, Long> {

}

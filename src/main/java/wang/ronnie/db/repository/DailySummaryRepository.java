package wang.ronnie.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import wang.ronnie.db.entity.DailySummaryEntity;
import wang.ronnie.db.entity.EventTagEntity;

@RepositoryRestResource(collectionResourceRel = "summaries", path = "summaries")
public interface DailySummaryRepository extends JpaRepository<DailySummaryEntity, Long> {

}

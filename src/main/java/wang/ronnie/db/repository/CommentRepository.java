package wang.ronnie.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import wang.ronnie.db.entity.ArticleEntity;

/**
 * Created by ronnie wang on 16/12/27.
 */
@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
public interface CommentRepository extends JpaRepository<ArticleEntity, Integer> {

}

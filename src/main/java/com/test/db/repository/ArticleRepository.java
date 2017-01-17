package com.test.db.repository;

import com.test.db.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by ronnie wang on 16/12/27.
 */
@RepositoryRestResource(collectionResourceRel = "articles", path = "articles")
public interface ArticleRepository extends JpaRepository<ArticleEntity, Long> {

}

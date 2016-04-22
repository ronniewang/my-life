package wang.ronnie.solr.repository;

import wang.ronnie.solr.Todo;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface TodoRepository extends SolrCrudRepository<Todo, String> {

}
package wang.ronnie.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.ronnie.db.repository.CommentRepository;
import wang.ronnie.model.Comment;

import java.util.List;

/**
 * Created by ronnie wang on 16/12/27.
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll(Integer articleId) {

        return commentRepository.findByArticleId(articleId);
    }
}

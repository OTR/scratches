package otr.elib.comment.application.use_case;

import otr.elib.comment.domain.entity.Comment;
import otr.elib.comment.domain.repository.CommentRepository;

import java.util.List;

/**
 *
 */
public class GetAllCommentsUseCase {

    private CommentRepository repository;

    public GetAllCommentsUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> invoke() {
        return repository.getAllComments();
    }

}

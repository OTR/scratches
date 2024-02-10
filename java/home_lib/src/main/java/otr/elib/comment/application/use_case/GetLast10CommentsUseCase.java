package otr.elib.comment.application.use_case;

import otr.elib.comment.domain.entity.Comment;
import otr.elib.comment.domain.repository.CommentRepository;

import java.util.List;

/**
 *
 */
public class GetLast10CommentsUseCase {

    private CommentRepository repository;

    public GetLast10CommentsUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    public List<Comment> invoke() {
        return repository.getLast10Comments();
    }

}

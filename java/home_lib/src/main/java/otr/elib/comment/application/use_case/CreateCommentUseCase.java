package otr.elib.comment.application.use_case;

import otr.elib.comment.domain.entity.Comment;
import otr.elib.comment.domain.repository.CommentRepository;

/**
 *
 */
public class CreateCommentUseCase {

    private CommentRepository repository;

    public CreateCommentUseCase(CommentRepository repository) {
        this.repository = repository;
    }

    public boolean invoke(Comment comment) {
        return repository.createComment(comment);
    }

}

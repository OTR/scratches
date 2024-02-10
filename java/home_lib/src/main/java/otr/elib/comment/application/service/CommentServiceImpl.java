package otr.elib.comment.application.service;

import otr.elib.comment.application.validation.CommentValidator;
import otr.elib.comment.domain.entity.Comment;
import otr.elib.comment.domain.repository.CommentRepository;
import otr.elib.comment.domain.service.CommentService;

import java.util.List;

/**
 *
 */
public class CommentServiceImpl implements CommentService {

    public static final boolean UNABLE_TO_ADD_COMMENT = false;

    private final CommentRepository repository;
    private final CommentValidator validator;

    public CommentServiceImpl(CommentRepository repository, CommentValidator validator) {
        this.validator = validator;
        this.repository = repository;
    }

    @Override
    public boolean addComment(Comment comment) {
        if (validator.isValid(comment)) {
            comment = validator.sanitize(comment);
            return repository.createComment(comment);
        } else {
            return UNABLE_TO_ADD_COMMENT;
        }
    }

    @Override
    public List<Comment> getLast10Comments() {
        return repository.getLast10Comments();
    }

}

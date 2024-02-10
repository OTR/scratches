package otr.elib.comment.di;

import otr.elib.comment.application.validation.CommentValidator;
import otr.elib.comment.domain.service.CommentService;
import otr.elib.comment.domain.repository.CommentRepository;

import otr.elib.comment.framework.adapter.repository.ApacheDerbyCommentRepositoryImpl;
import otr.elib.comment.framework.adapter.repository.H2CommentRepositoryImpl;
import otr.elib.comment.application.service.CommentServiceImpl;
import otr.elib.comment.framework.adapter.out.InMemoryListCommentRepositoryImpl;

/**
 *
 */
public class Component {

    // TODO: Read the chosen backend from the Properties file
    private static final String DATABASE_BACKEND = "H2";

    public static CommentService getCommentService() {
        CommentRepository repository = getCommentRepository();
        CommentValidator validator = getCommentValidator();
        return new CommentServiceImpl(repository, validator);
    }

    public static CommentRepository getCommentRepository() {
        CommentRepository repository = switch (DATABASE_BACKEND) {
            case "DERBY" -> new ApacheDerbyCommentRepositoryImpl();
            case "H2" -> new H2CommentRepositoryImpl();
            default -> new InMemoryListCommentRepositoryImpl();
        };
        return repository;
    }

    private static CommentValidator getCommentValidator() {
        return new CommentValidator();
    }

}

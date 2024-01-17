package com.github.otr.home_lib.framework.di;

import com.github.otr.home_lib.application.validation.CommentValidator;
import com.github.otr.home_lib.domain.service.CommentService;
import com.github.otr.home_lib.domain.repository.CommentRepository;

import com.github.otr.home_lib.framework.adapter.repository.ApacheDerbyCommentRepositoryImpl;
import com.github.otr.home_lib.framework.adapter.repository.H2CommentRepositoryImpl;
import com.github.otr.home_lib.application.service.CommentServiceImpl;
import com.github.otr.home_lib.framework.persistence.InMemoryListCommentRepositoryImpl;

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

package otr.elib.comment.framework.adapter.out;

import otr.elib.comment.domain.entity.Comment;
import otr.elib.comment.domain.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class InMemoryListCommentRepositoryImpl implements CommentRepository {

    private List<Comment> dataSource;

    public InMemoryListCommentRepositoryImpl() {
        this.dataSource = new ArrayList<Comment>();
    }

    @Override
    public boolean createComment(Comment comment) {
        return this.dataSource.add(comment);
    }

    @Override
    public List<Comment> getAllComments() {
        return List.copyOf(this.dataSource);
    }

    /**
     * @return a copy of last 10 or less elements from a backing collection
     */
    @Override
    public List<Comment> getLast10Comments() {
        int lastIndex = this.dataSource.isEmpty() ? 0 : this.dataSource.size();
        int fromIndex = Math.max(lastIndex - 10, 0);
        List<Comment> view = this.dataSource.subList(fromIndex, lastIndex);
        return List.copyOf(view);
    }

}

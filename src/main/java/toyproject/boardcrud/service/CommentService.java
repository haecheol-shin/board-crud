package toyproject.boardcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.boardcrud.domain.Comment;
import toyproject.boardcrud.domain.Post;
import toyproject.boardcrud.domain.User;
import toyproject.boardcrud.repository.CommentRepository;
import toyproject.boardcrud.repository.PostRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public List<Comment> findComments(Long postId) {
        Post post = postRepository.findOne(postId);
        return commentRepository.findComments(post);
    }

    @Transactional
    public List<Comment> findUserComments(User author) {
        return commentRepository.findByCommentAuthor(author);
    }

    @Transactional
    public void save(Comment comment, User author, Post post) {
        comment.setCommentAuthor(author);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long commentId, User author) {
        commentRepository.deleteComment(commentId, author);
    }

}

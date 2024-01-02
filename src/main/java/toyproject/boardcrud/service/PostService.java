package toyproject.boardcrud.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.boardcrud.domain.Post;
import toyproject.boardcrud.domain.User;
import toyproject.boardcrud.repository.PostRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post findPost(Long postId) {
        return postRepository.findOne(postId);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public void save(Post post) {
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long postId, User user) {
        postRepository.deletePost(postId, user);
    }

    @Transactional
    public Post findUpdatePost(Long postId, User user) {
        return postRepository.findPostByIdAndAuthor(postId, user);
    }

    @Transactional
    public void update(Post originalPost, Post updatePost) {
        originalPost.setTitle(updatePost.getTitle());
        originalPost.setContent(updatePost.getContent());
    }

    @Transactional
    public List<Post> findPostsByAuthor(User author) {
        return postRepository.findPostsByAuthor(author);
    }
}

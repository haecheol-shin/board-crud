package toyproject.boardcrud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.boardcrud.domain.Post;
import toyproject.boardcrud.domain.User;
import toyproject.boardcrud.repository.PostRepository;
import toyproject.boardcrud.service.PostService;
import toyproject.boardcrud.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping("/create")
    public String createForm() {
        return "post/createForm";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Post post) {
        // 여기서 세션에서 현재 사용자의 name을 가져와서 save를 호출할때 name을 같이 넘기도록 해야할듯.
        User author = userService.getLoggedInUser();
        postService.save(post, author);
        return "redirect:/";
    }

    @GetMapping("/{postId}")
    public String post(@PathVariable Long postId, Model model) {
        Post post = postService.findPost(postId);
        model.addAttribute("post", post);
        return "post/post";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        User loggedInUser = userService.getLoggedInUser();
        Post originalPost = postService.findUpdatePost(postId, loggedInUser);
        model.addAttribute("post", originalPost);
        return "post/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId, @ModelAttribute Post updatePost, RedirectAttributes redirectAttributes) {
        User loggedInUser = userService.getLoggedInUser();
        Post originalPost = postService.findUpdatePost(postId, loggedInUser);

        if (originalPost != null) {
            postService.update(originalPost, updatePost);
            redirectAttributes.addAttribute("postId", postId);
            return "redirect:/post/{postId}";

        } else {
            // 게시글이 없거나 권한이 없는 경우에 대한 처리
            throw new RuntimeException("권한이 없습니다!");
        }
    }

    @PostMapping("/{postId}/delete")
    public String delete(@PathVariable Long postId) {
        User loggedInUser = userService.getLoggedInUser();
        postService.deletePost(postId, loggedInUser);

        return "redirect:/";
    }
}

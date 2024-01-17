package toyproject.boardcrud.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import toyproject.boardcrud.domain.Comment;
import toyproject.boardcrud.domain.Post;
import toyproject.boardcrud.domain.User;
import toyproject.boardcrud.service.CommentService;
import toyproject.boardcrud.service.PostService;
import toyproject.boardcrud.service.UserService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private HttpSession httpSession;

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping()
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping()
    public String login(@ModelAttribute User user, Model model) {
        userService.signin(user);
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "user/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, Model model) {
        userService.signup(user);
        model.addAttribute("successMessage", "회원가입이 성공적으로 완료되었습니다.");
        return "redirect:/user";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/{userId}")
    public String myPage(@PathVariable String userId, Model model) {

        User author = userService.findById(userId);
        List<Post> posts = postService.findPostsByAuthor(author);
        model.addAttribute("posts", posts);

        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser != null) {
            model.addAttribute("loggedInUserName", loggedInUser.getName());
            model.addAttribute("loggedInUserId", loggedInUser.getId());
        }

        return "user/myPage";
    }

    @GetMapping("/{userId}/comments")
    public String myComments(@PathVariable String userId, Model model) {
        // userId로 사용자를 찾고
        User author = userService.findById(userId);

        // 사용자가 작성한 댓글을 찾고
        List<Comment> comments = commentService.findUserComments(author);

        // 모델에 저장해서
        model.addAttribute("comments", comments);

        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser != null) {
            model.addAttribute("loggedInUserName", loggedInUser.getName());
            model.addAttribute("loggedInUserId", loggedInUser.getId());
        }

        // 넘겨준다
        return "user/myComments";
    }
}

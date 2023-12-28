package toyproject.boardcrud.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import toyproject.boardcrud.domain.Post;
import toyproject.boardcrud.domain.User;
import toyproject.boardcrud.service.PostService;
import toyproject.boardcrud.service.UserService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;
    private final UserService userService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);

        User loggedInUser = userService.getLoggedInUser();
        if (loggedInUser != null) {
            model.addAttribute("loggedInUserName", loggedInUser.getName());
        }

        return "index";
    }

    @GetMapping("/checkSession")
    @ResponseBody
    public String checkSession() {
        User loggedInUser = (User) httpSession.getAttribute("loggedInUser");

        if (loggedInUser != null && loggedInUser.getName() != null) {
            return "세션에 사용자 정보가 있습니다. 사용자 이름: " + loggedInUser.getName();
        } else {
            return "세션에 사용자 정보가 없거나 사용자 이름이 null입니다.";
        }
    }

}

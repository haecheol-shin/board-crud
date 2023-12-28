package toyproject.boardcrud.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import toyproject.boardcrud.domain.User;
import toyproject.boardcrud.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String loginForm() {
        return "user/loginForm";
    }

    @PostMapping()
    public String login(@ModelAttribute User user) {
        userService.signin(user);
        return "redirect:index";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "user/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user) {
        userService.signup(user);
        return "redirect:/user";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:index";
    }
}

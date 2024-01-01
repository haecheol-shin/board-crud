package toyproject.boardcrud.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
        // 세션 필요
        // 사용자가 쓴 글을 model에 넣어야 함
        return "user/myPage";
    }
}

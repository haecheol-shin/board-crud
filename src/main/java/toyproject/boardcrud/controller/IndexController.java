package toyproject.boardcrud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import toyproject.boardcrud.domain.Post;
import toyproject.boardcrud.service.PostService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final PostService postService;

    @GetMapping("/")
    public String index(Model model) {
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "index";
    }

}

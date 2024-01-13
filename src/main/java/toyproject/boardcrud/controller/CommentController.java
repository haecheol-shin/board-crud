package toyproject.boardcrud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import toyproject.boardcrud.domain.Comment;
import toyproject.boardcrud.domain.User;
import toyproject.boardcrud.service.CommentService;
import toyproject.boardcrud.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    // post에 달린 댓글들 불러오기(PostController에 있어야 할 수도)

    // 댓글 저장하기
    @PostMapping("/save")
    public String commentCreate(@ModelAttribute("comment") Comment comment, @PathVariable Long postId) {
        User author = userService.getLoggedInUser();

        // postid를 가져와서 save할때 넣어줘야 한다.
        commentService.save(comment, author);
        return "redirect:/";
    }

    // 댓글 삭제하기
    @GetMapping("/delete/{commentId}")
    public String commentDelete(@PathVariable Long commentId) {
        User author = userService.getLoggedInUser();
        commentService.delete(commentId, author);
        return "redirect:/";
    }
}

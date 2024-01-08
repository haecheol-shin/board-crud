package toyproject.boardcrud.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import toyproject.boardcrud.service.CommentService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    // post에 달린 댓글들 불러오기(PostController에 있어야 할 수도)

    // 댓글 저장하기

    // 댓글 삭제하기
}

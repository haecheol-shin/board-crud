package toyproject.boardcrud.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    public Comment() {
        this.date = LocalDateTime.now();
    }

    @Id
    @GeneratedValue
    @Column(name = "commentId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User commentAuthor;

    @Column(name = "commentContent")
    private String content;

    @Column(name = "commentDate")
    private LocalDateTime date;
}

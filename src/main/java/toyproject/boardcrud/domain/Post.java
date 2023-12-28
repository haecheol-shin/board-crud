package toyproject.boardcrud.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Post {

    public Post() {
        this.date = LocalDate.now();
    }

    @Id
    @GeneratedValue
    @Column(name = "postId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User postAuthor;

    @Column(name = "title")
    private String title;

    @Column(name = "postContent")
    private String content;

    @Column(name = "postDate")
    private LocalDate date;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();


}

package toyproject.boardcrud.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    @Column(name = "userId")
    private String id;

    @Column(name = "username")
    @NonNull
    private String name;

    @Column(name = "password")
    @NonNull
    private String password;

    @OneToMany(mappedBy = "postAuthor")
    @Nullable
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "commentAuthor")
    @Nullable
    private List<Comment> commentList = new ArrayList<>();
}

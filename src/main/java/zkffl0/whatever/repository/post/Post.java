package zkffl0.whatever.repository.post;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zkffl0.whatever.repository.comment.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String content;

    @Column(nullable = false)
    private LocalDateTime useTime;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view; // 조회수

    @Column(nullable = true)
    private int thumbCnt;

    @Column(nullable = true)
    private int commentCnt;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setView(int view) {
        this.view = view;
    }

    public void setThumbCnt(int thumbCnt) {
        this.thumbCnt = thumbCnt;
    }


}

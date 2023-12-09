package zkffl0.whatever.repository.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zkffl0.whatever.repository.post.Post;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String content;

    @Column(nullable = false)
    private LocalDateTime useTime;

    @JoinColumn(name = "postId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Post post;

    public void setContent(String content) {
        this.content = content;
    }
}

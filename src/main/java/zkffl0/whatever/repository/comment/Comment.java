package zkffl0.whatever.repository.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zkffl0.whatever.repository.post.Post;

import javax.persistence.*;

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

    @JoinColumn(name = "postId")
    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    public void setContent(String content) {
        this.content = content;
    }
}

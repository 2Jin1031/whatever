package zkffl0.whatever.repository.thumb;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import zkffl0.whatever.repository.member.Member;
import zkffl0.whatever.repository.post.Post;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Thumb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumb_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
}

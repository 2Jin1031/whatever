package zkffl0.whatever.repository.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import zkffl0.whatever.repository.member.memberTypes.CarrierType;
import zkffl0.whatever.repository.member.memberTypes.CitizenShipType;
import zkffl0.whatever.repository.member.memberTypes.SexType;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long birth;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarrierType carrier; // 통신사 종류

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SexType sex;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CitizenShipType citizenShip;

    @Column(nullable = false)
    private String phoneNo;

}

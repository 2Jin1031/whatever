package zkffl0.whatever.MVC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zkffl0.whatever.MVC.domain.Member2;
import zkffl0.whatever.MVC.repository.MemberRepository2;

import java.util.List;

@Service
public class MemberService2 {

    private final MemberRepository2 memberRepository;

    @Autowired
    public MemberService2(MemberRepository2 memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member2 member2) {
        emptyMemberName(member2);
        validateDuplicateMember(member2); // 중복 회원 검증
        memberRepository.save(member2);
        return member2.getId();
    }

    private static void emptyMemberName(Member2 member2) {
        if (member2.getName().isEmpty()) {
            throw new IllegalStateException("이름이 입력되지 않았습니다.");
        }
    }

    private void validateDuplicateMember(Member2 member2) {
        memberRepository.findByName(member2.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member2> findMembers() {
        return memberRepository.findAll();
    }
}

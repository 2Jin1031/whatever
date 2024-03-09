package zkffl0.whatever.API.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zkffl0.whatever.API.common.exception.MemberDuplicateException;
import zkffl0.whatever.API.dto.member.sign_up.SignUpReqDto;
import zkffl0.whatever.API.dto.member.sign_up.SignUpResDto;
import zkffl0.whatever.API.repository.member.Member;
import zkffl0.whatever.API.repository.member.MemberRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResDto createMember(SignUpReqDto signUpReqDto) throws MemberDuplicateException {

        // 전화 번호 중복 확인
        Optional<Member> checkMember = memberRepository.findByPhoneNo(signUpReqDto.getPhoneNo());
        if (checkMember.isPresent()) {
            throw new MemberDuplicateException(signUpReqDto.getPhoneNo() + "는 이미 존재하는 번호입니다.");
        }

        Member member = Member.builder()
                .name(signUpReqDto.getName())
                .password(signUpReqDto.getPassword())
                .phoneNo(signUpReqDto.getPhoneNo())
                .build();

        memberRepository.save(member);

        return SignUpResDto.builder()
                .name(member.getName())
                .phoneNo(member.getPhoneNo())
                .build();
    }
}

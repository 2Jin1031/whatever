package zkffl0.whatever.API.utils.jwt;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import zkffl0.whatever.API.repository.member.Member;
import zkffl0.whatever.API.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNo) throws UsernameNotFoundException {
        Member member = memberRepository.findByPhoneNo(phoneNo).orElseThrow(
                () -> new UsernameNotFoundException("전화번호가 존재하지 않습니다.")
        );

        return new CustomUserDetails(member);

    }

}

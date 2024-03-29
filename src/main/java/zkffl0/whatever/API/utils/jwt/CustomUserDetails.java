//package zkffl0.whatever.API.utils.jwt;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import zkffl0.whatever.API.repository.member.Member;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class CustomUserDetails implements UserDetails {
//    private final Member member;
//
//    public CustomUserDetails(Member member)
//    {
//        this.member = member;
//    }
//
//    // 권한을 스프링 시큐리티가 인식할 수 있는 SimpleGrantedAuthority 객체로 String을 변환함
//    // 따라서 특정 Member가 ROLE_USER라는 권한을 갖고있으면 해당 String을 SimpleGrantedAuthority로
//    // 변환하고 이 객체는 Spring Security가 권한을 인식하고 처리할 수 있다.
////    @Override
////    public Collection<? extends GrantedAuthority> getAuthorities() {
////        return member.getRoles().stream().map(o->new SimpleGrantedAuthority(o.getName()))
////                .collect(Collectors.toList());
////    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // 사용자에게 권한을 부여하지 않음
//        return Collections.emptyList();
//    }
//
//    @Override
//    public String getPassword() {
//        return member.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return member.getPhoneNo();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}

package zkffl0.whatever.MVC.repository;

import zkffl0.whatever.MVC.domain.Member2;

import java.util.List;
import java.util.Optional;

public interface MemberRepository2 {

    Member2 save(Member2 member2);

    Optional<Member2> findById(Long id);

    Optional<Member2> findByName(String name);

    List<Member2> findAll();
}

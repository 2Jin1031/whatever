package zkffl0.whatever.MVC.repository;

import org.springframework.stereotype.Repository;
import zkffl0.whatever.MVC.domain.Member2;

import java.util.*;

@Repository
public class MemberMemoryRepository2 implements MemberRepository2 {

    private static Map<Long, Member2> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member2 save(Member2 member2) {
        member2.setId(++sequence);
        store.put(member2.getId(), member2);
        return member2;
    }

    @Override
    public Optional<Member2> findById(Long id) {
        Member2 member2 = store.get(id);
        return Optional.ofNullable(member2);
    }

    @Override
    public Optional<Member2> findByName(String name) {
        return store.values().stream()
                .filter(member2 -> member2.getName().equals(name))
                .findAny();

    }

    @Override
    public List<Member2> findAll() {
        return new ArrayList<>(store.values());
    }
}

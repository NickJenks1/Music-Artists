package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Member;

public interface MemberDao extends JpaRepository<Member, Long> {

}

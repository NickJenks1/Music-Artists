package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Member;

/**
 * 
 * DAO interface for "Member" entity
 *
 **/
public interface MemberDao extends JpaRepository<Member, Long> {

}

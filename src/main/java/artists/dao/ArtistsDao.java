package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Artists;

/**
 * 
 * DAO interface for "Artists" entity
 *
 **/
public interface ArtistsDao extends JpaRepository<Artists, Long>{

}

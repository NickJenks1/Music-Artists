package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Artists;

public interface ArtistsDao extends JpaRepository<Artists, Long>{

}

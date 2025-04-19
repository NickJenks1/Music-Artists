package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Song;

/**
 * 
 * DAO interface for "Song" entity
 *
 **/
public interface SongDao extends JpaRepository<Song, Long>{

}

package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Song;

public interface SongDao extends JpaRepository<Song, Long>{

}

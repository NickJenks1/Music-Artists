package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Album;

public interface AlbumDao extends JpaRepository<Album, Long> {

}

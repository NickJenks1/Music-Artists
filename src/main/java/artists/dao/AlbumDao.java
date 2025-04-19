package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Album;

/**
 * 
 * DAO interface for "Album" entity
 *
 **/
public interface AlbumDao extends JpaRepository<Album, Long> {

}

package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Instrument;

/**
 * 
 * DAO interface for "Instrument" entity
 *
 **/
public interface InstrumentDao extends JpaRepository<Instrument, Long>{

}

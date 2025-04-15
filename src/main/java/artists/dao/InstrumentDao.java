package artists.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import artists.entity.Instrument;

public interface InstrumentDao extends JpaRepository<Instrument, Long>{

}

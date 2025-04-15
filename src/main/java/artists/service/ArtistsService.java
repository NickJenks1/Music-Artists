package artists.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import artists.controller.model.ArtistsData;
import artists.controller.model.ArtistsData.ArtistsAlbum;
import artists.controller.model.ArtistsData.ArtistsInstrument;
import artists.controller.model.ArtistsData.ArtistsMember;
import artists.controller.model.ArtistsData.ArtistsSong;
import artists.dao.AlbumDao;
import artists.dao.ArtistsDao;
import artists.dao.InstrumentDao;
import artists.dao.MemberDao;
import artists.dao.SongDao;
import artists.entity.Album;
import artists.entity.Artists;
import artists.entity.Song;
import artists.entity.Member;
import artists.entity.Instrument;

@Service
public class ArtistsService {

	@Autowired
	private ArtistsDao artistsDao;
	@Autowired
	private AlbumDao albumDao;
	@Autowired
	private SongDao songDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private InstrumentDao instrumentDao;
	
	@Transactional(readOnly = true)
	public List<ArtistsData> retrieveAllArtists() {
		
		List<Artists> artists = artistsDao.findAll();
		List<ArtistsData> result = new LinkedList<>();
		
		for(Artists artist : artists) {
			ArtistsData artistsData = new ArtistsData(artist);
				result.add(artistsData);
		}
		return result;
	}
	
	@Transactional(readOnly = true)
	public ArtistsData retrieveArtistsById(Long artistsId) {
		return new ArtistsData(findArtistsById(artistsId));
	}
	
	@Transactional(readOnly = true)
	public ArtistsAlbum retrieveAlbumById(Long artistsId, Long albumId) {
		return new ArtistsAlbum(findAlbumById(artistsId, albumId));
	}
	
	@Transactional(readOnly = true)
	public ArtistsSong retrieveSongById(Long artistsId, Long albumId, Long songId) {
		return new ArtistsSong(findSongById(artistsId, albumId, songId));
	}
	
	@Transactional(readOnly = true)
	public ArtistsMember retrieveMemberById(Long artistsId, Long memberId) {
		return new ArtistsMember(findMemberById(artistsId, memberId));
	}
	
	@Transactional(readOnly = true)
	public ArtistsInstrument retrieveInstrumentById(Long artistsId, Long memberId, Long instrumentId) {
		return new ArtistsInstrument(findInstrumentById(artistsId, memberId, instrumentId));
	}
	
	private Artists findArtistsById(Long artistsId) {
		return artistsDao.findById(artistsId)
				.orElseThrow(() -> new NoSuchElementException
					("Artist with ID= " + artistsId + " does not exist."));
	}
	
	private Album findAlbumById(Long artistsId, Long albumId) {
		Album album = albumDao.findById(albumId)
				.orElseThrow(() -> new NoSuchElementException
					("Album with ID= " + albumId + " does not exist."));
		
		if (album.getArtists().getArtistsId() != artistsId) {
			throw new IllegalArgumentException("The album with ID= " + albumId + 
					" is not a registered album for the artist with ID= " + artistsId + ".");
		}
		return album;
	}
	
	private Song findSongById(Long artistsId, Long albumId, Long songId) {
		Song song = songDao.findById(songId)
				.orElseThrow(() -> new NoSuchElementException
					("Song with ID= " + songId + " does not exist."));
		
		if (song.getAlbum().getAlbumId() != albumId) {
			throw new IllegalArgumentException("The song with ID= " + songId +
					" is not a registered song for the album with ID= " + albumId + ".");
		}
		return song;
	}
	
	private Member findMemberById(Long artistsId, Long memberId) {
		Member member = memberDao.findById(memberId)
				.orElseThrow(() -> new NoSuchElementException
					("Member with ID= " + memberId + " does not exist."));
		
		if (member.getArtists().getArtistsId() != artistsId) {
			throw new IllegalArgumentException("The member with ID= " + memberId +
					" is not a registered member for the artist with ID= " + artistsId + ".");
		}
		return member;
	}
	
	private Instrument findInstrumentById(Long artistsId, Long memberId, Long instrumentId) {
		Instrument instrument = instrumentDao.findById(instrumentId)
				.orElseThrow(() -> new NoSuchElementException
					("Instrument with ID= " + instrumentId + " does not exist."));
		
		boolean found = false;
		
		for(Member member : instrument.getMember()) {
			if(member.getMemberId() == memberId) {
				found = true;
				break;
			}
		}
		
		if(!found) {
			throw new IllegalArgumentException(
					"The instrument with ID= " + instrumentId +
					" is not an instrument used by the member with ID= " + memberId + ".");
		}
		return instrument;
	}
	
	private Artists locateOrCreateArtists(Long artistsId) {
		Artists artists;
		
		if (Objects.isNull(artistsId) || artistsId == 0) {
			artists = new Artists();
		} else {
			artists = findArtistsById(artistsId);
		}
		return artists;
	}
	
	private Album locateOrCreateAlbum(Long artistsId, Long albumId) {
		Album album;
		
		if (Objects.isNull(albumId) || albumId == 0) {
			album = new Album();
		} else {
			album = findAlbumById(artistsId, albumId);
		}
		return album;
	}
	
	private Song locateOrCreateSong(Long artistsId, Long albumId, Long songId) {
		Song song;

		if (Objects.isNull(songId) || songId == 0) {
			song = new Song();
		} else {
			song = findSongById(artistsId, albumId, songId);
		}
		return song;
	}
	
	private Member locateOrCreateMember(Long artistsId, Long memberId) {
		Member member;
		
		if (Objects.isNull(memberId) || memberId == 0) {
			member = new Member();
		} else {
			member = findMemberById(artistsId, memberId);
		}
		return member;
	}
	
	@Transactional(readOnly = false)
	public ArtistsData saveArtistsData(ArtistsData artistsData) {
		
		Long artistsId = artistsData.getArtistsId();
		Artists artists = locateOrCreateArtists(artistsId);
		
		copyArtistsFields(artists, artistsData);
		
		return new ArtistsData(artistsDao.save(artists));
	}

	@Transactional(readOnly = false)
	public ArtistsAlbum saveArtistsAlbum(Long artistsId, ArtistsAlbum artistsAlbum) {
		
		Artists artists = locateOrCreateArtists(artistsId);
		Long albumId = artistsAlbum.getAlbumId();
		Album album = locateOrCreateAlbum(artistsId, albumId);
		
		copyAlbumFields(album, artistsAlbum);
		
		album.setArtists(artists);
		artists.getAlbum().add(album);
		
		Album dbAlbum = albumDao.save(album);
		
		return new ArtistsAlbum(dbAlbum);
	}

	@Transactional(readOnly = false)
	public ArtistsSong saveArtistsSong(Long artistsId, Long albumId, ArtistsSong artistsSong) {
		
		Album album = locateOrCreateAlbum(artistsId, albumId);
		Long songId = artistsSong.getSongId();
		Song song = locateOrCreateSong(artistsId, albumId, songId);
		
		copySongFields(song, artistsSong);
		
		song.setAlbum(album);
		album.getSong().add(song);
		
		Song dbSong = songDao.save(song);
		
		return new ArtistsSong(dbSong);
	}
	
	@Transactional(readOnly = false)
	public ArtistsMember saveArtistsMember(Long artistsId, Long memberId, ArtistsMember artistsMember) {
		
		Artists artists = locateOrCreateArtists(artistsId);
		Member member = locateOrCreateMember(artistsId, memberId);
		
		copyMemberFields(member, artistsMember);
		
		member.setArtists(artists);
		artists.getMember().add(member);
		
		Member dbMember = memberDao.save(member);
		
		return new ArtistsMember(dbMember);
	}

	@Transactional(readOnly = false)
	public ArtistsInstrument saveArtistsInstrument(Long artistsId, Long memberId, ArtistsInstrument artistsInstrument) {
		
		Member member = locateOrCreateMember(artistsId, memberId);
		Instrument instrument = new Instrument();
		
		copyInstrumentFields(instrument, artistsInstrument);
		
		member.getInstrument().add(instrument);
		
		Instrument dbInstrument = instrumentDao.save(instrument);
		
		return new ArtistsInstrument(dbInstrument);
	}
	
	public void deleteArtistById(Long artistsId) {
		
		Artists artists = findArtistsById(artistsId);
		artistsDao.delete(artists);
	}
	
	public void deleteAlbumById(Long artistsId, Long albumId) {
		
		Album album = findAlbumById(artistsId, albumId);
		albumDao.delete(album);
	}
	
	public void deleteSongById(Long artistsId, Long albumId, Long songId) {
		
		Song song = findSongById(artistsId, albumId, songId);
		songDao.delete(song);
	}
	
	public void deleteMemberById(Long artistsId, Long memberId) {
		
		Member member = findMemberById(artistsId, memberId);
		memberDao.delete(member);
	}
	
	public void deleteInstrumentById(Long artistsId, Long memberId, Long instrumentId) {
		
		Instrument instrument = findInstrumentById(artistsId, memberId, instrumentId);
		instrumentDao.delete(instrument);
	}
	
	private void copyArtistsFields(Artists artists, ArtistsData artistsData) {
		artists.setArtistsName(artistsData.getArtistsName());
		artists.setGenre(artistsData.getGenre());
		artists.setYearFormed(artistsData.getYearFormed());
	}
	
	private void copyAlbumFields(Album album, ArtistsAlbum artistsAlbum) {
		album.setAlbumTitle(artistsAlbum.getAlbumTitle());
		album.setReleaseDate(artistsAlbum.getReleaseDate());
	}
	
	private void copySongFields(Song song, ArtistsSong artistsSong) {
		song.setSongTitle(artistsSong.getSongTitle());
		song.setLength(artistsSong.getLength());
	}
	
	private void copyMemberFields(Member member, ArtistsMember artistsMember) {
		member.setMemberName(artistsMember.getMemberName());
	}
	
	private void copyInstrumentFields(Instrument instrument, ArtistsInstrument artistsInstrument) {
		instrument.setInstrumentName(artistsInstrument.getInstrumentName());
	}
	
}

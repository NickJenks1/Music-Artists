package artists.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import artists.controller.model.ArtistsData;
import artists.controller.model.ArtistsData.ArtistsAlbum;
import artists.controller.model.ArtistsData.ArtistsInstrument;
import artists.controller.model.ArtistsData.ArtistsMember;
import artists.controller.model.ArtistsData.ArtistsSong;
import artists.service.ArtistsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping
@Slf4j
public class ArtistsController {

	/**
	 * 
	 * Instance of ArtistsService with Autowired annotation to inject a managed bean into it
	 * 
	 **/
	@Autowired
	private ArtistsService artistsService;

	/**
	 * 
	 * Methods for retrieving data by id from the "artists", "album", "song", "member", and "instrument" tables
	 * using the ArtistsService class with GetMapping annotation to let Spring Boot know these methods are for GET
	 *
	 **/
	@GetMapping("/artists")
	public List<ArtistsData> retrieveAllArtists() {

		log.info("Retrieving all artists");
		return artistsService.retrieveAllArtists();
	}

	@GetMapping("/artists/{artistsId}")
	public ArtistsData retrieveArtistsById(@PathVariable Long artistsId) {

		log.info("Retrieving artist with ID={}", artistsId);
		return artistsService.retrieveArtistsById(artistsId);
	}

	@GetMapping("/artists/{artistsId}/album/{albumId}")
	public ArtistsAlbum retrieveAlbumById(@PathVariable Long artistsId, @PathVariable Long albumId) {

		log.info("Retrieving album with ID={}", albumId, " from artist with ID={}", artistsId);
		return artistsService.retrieveAlbumById(artistsId, albumId);
	}

	@GetMapping("/artists/{artistsId}/album/{albumId}/song/{songId}")
	public ArtistsSong retrieveSongById(@PathVariable Long artistsId, @PathVariable Long albumId,
			@PathVariable Long songId) {

		log.info("Retrieving song with ID={}", songId, " from the album with ID={}", albumId,
				" belonging to the artist with ID={}", artistsId);
		return artistsService.retrieveSongById(artistsId, albumId, songId);
	}

	@GetMapping("/artists/{artistsId}/member/{memberId}")
	public ArtistsMember retrieveMemberById(@PathVariable Long artistsId, @PathVariable Long memberId) {
		log.info("Retrieving member with ID={}", memberId, " from the artist with ID={}", artistsId);
		return artistsService.retrieveMemberById(artistsId, memberId);
	}

	@GetMapping("/artists/{artistsId}/member/{memberId}/instrument/{instrumentId}")
	public ArtistsInstrument retrieveInstrumentById(@PathVariable Long artistsId, @PathVariable Long memberId,
			@PathVariable Long instrumentId) {

		log.info("Retrieving instrument with ID={}", instrumentId, " that is used by the member with ID={}", memberId,
				" who is in the group with the ID={}", artistsId);
		return artistsService.retrieveInstrumentById(artistsId, memberId, instrumentId);
	}

	/**
	 * 
	 * Methods for adding data by id to the "artists", "album", "song", "member", and "instrument" tables
	 * using the ArtistsService class with PostMapping annotation to let Spring Boot know these methods are for POST
	 *
	 **/
	@PostMapping("/artists")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistsData addArtists(@RequestBody ArtistsData artistsData) {

		log.info("Adding an artist {}", artistsData);
		return artistsService.saveArtistsData(artistsData);
	}

	@PostMapping("/artists/{artistsId}/album")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistsAlbum addAlbum(@PathVariable Long artistsId, @RequestBody ArtistsAlbum artistsAlbum) {

		log.info("Adding an album {}", artistsAlbum);
		return artistsService.saveArtistsAlbum(artistsId, artistsAlbum);
	}

	@PostMapping("/artists/{artistsId}/album/{albumId}/song")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistsSong addSong(@PathVariable Long artistsId, @PathVariable Long albumId,
			@RequestBody ArtistsSong artistsSong) {

		log.info("Adding a song {}", artistsSong);
		return artistsService.saveArtistsSong(artistsId, albumId, artistsSong);
	}

	@PostMapping("/artists/{artistsId}/member")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistsMember addMember(@PathVariable Long artistsId, Long memberId,
			@RequestBody ArtistsMember artistsMember) {

		log.info("Adding a member {}", artistsMember);
		return artistsService.saveArtistsMember(artistsId, memberId, artistsMember);
	}

	@PostMapping("/artists/{artistsId}/member/{memberId}/instrument")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ArtistsInstrument addInstrument(@PathVariable Long artistsId, @PathVariable Long memberId,
			@RequestBody ArtistsInstrument artistsInstrument) {

		log.info("Adding an instrument {}", artistsInstrument);
		return artistsService.saveArtistsInstrument(artistsId, memberId, artistsInstrument);
	}

	/**
	 * 
	 * Methods for updating data by id in the "artists", "album", "song", "member", and "instrument" tables
	 * using the ArtistsService class with PutMapping annotation to let Spring Boot know these methods are for PUT
	 *
	 **/
	@PutMapping("/artists/{artistsId}")
	public ArtistsData updateArtists(@PathVariable Long artistsId, @RequestBody ArtistsData artistsData) {

		artistsData.setArtistsId(artistsId);
		log.info("Updating the artist {}", artistsData);
		return artistsService.saveArtistsData(artistsData);
	}

	@PutMapping("/artists/{artistsId}/album/{albumId}")
	public ArtistsAlbum updateAlbum(@PathVariable Long artistsId, @PathVariable Long albumId,
			@RequestBody ArtistsAlbum artistsAlbum) {

		artistsAlbum.setAlbumId(albumId);
		log.info("Updating the album {}", artistsAlbum);
		return artistsService.saveArtistsAlbum(artistsId, artistsAlbum);
	}

	@PutMapping("/artists/{artistsId}/album/{albumId}/song/{songId}")
	public ArtistsSong updateSong(@PathVariable Long artistsId, @PathVariable Long albumId, @PathVariable Long songId,
			@RequestBody ArtistsSong artistsSong) {

		artistsSong.setSongId(songId);
		log.info("Updating the song {}", artistsSong);
		return artistsService.saveArtistsSong(artistsId, albumId, artistsSong);
	}

	@PutMapping("/artists/{artistsId}/member/{memberId}")
	public ArtistsMember updateMember(@PathVariable Long artistsId, @PathVariable Long memberId,
			@RequestBody ArtistsMember artistsMember) {

		artistsMember.setMemberId(memberId);
		log.info("Updating the member {}", artistsMember);
		return artistsService.saveArtistsMember(artistsId, memberId, artistsMember);
	}

	@PutMapping("/artists/{artistsId}/member/{memberId}/instrument/{instrumentId}")
	public ArtistsInstrument artistsInstrument(@PathVariable Long artistsId, @PathVariable Long memberId,
			@PathVariable Long instrumentId, @RequestBody ArtistsInstrument artistsInstrument) {

		artistsInstrument.setInstrumentId(instrumentId);
		log.info("Updating the instrument {}", artistsInstrument);
		return artistsService.saveArtistsInstrument(artistsId, memberId, artistsInstrument);
	}

	/**
	 * 
	 * Methods for removing data by id in the "artists", "album", "song", "member", and "instrument" tables
	 * using the ArtistsService class with DeleteMapping annotation to let Spring Boot know these methods are for DELETE
	 *
	 **/
	@DeleteMapping("/artists/{artistsId}")
	public Map<String, String> deleteArtistById(@PathVariable Long artistsId) {

		log.info("Deleting artist with ID={}", artistsId);
		artistsService.deleteArtistById(artistsId);
		return Map.of("Deleting", "Artist with ID= " + artistsId + " has been deleted.");
	}

	@DeleteMapping("/artists/{artistsId}/album/{albumId}")
	public Map<String, String> deleteAlbumById(@PathVariable Long artistsId, @PathVariable Long albumId) {

		log.info("Deleting album with ID={}", albumId);
		artistsService.deleteAlbumById(artistsId, albumId);
		return Map.of("Deleting", "Album with ID= " + albumId + " has been deleted.");
	}

	@DeleteMapping("/artists/{artistsId}/album/{albumId}/song/{songId}")
	public Map<String, String> deleteSongById(@PathVariable Long artistsId, @PathVariable Long albumId,
			@PathVariable Long songId) {

		log.info("Deleting song with ID ={}", songId);
		artistsService.deleteSongById(artistsId, albumId, songId);
		return Map.of("Deleting", "Song with ID= " + songId + " has been deleted.");
	}

	@DeleteMapping("/artists/{artistsId}/member/{memberId}")
	public Map<String, String> deleteMemberById(@PathVariable Long artistsId, @PathVariable Long memberId) {

		log.info("Deleting member with ID ={}", memberId);
		artistsService.deleteMemberById(artistsId, memberId);
		return Map.of("Deleting", "Member with ID= " + memberId + " has been deleted.");
	}

	@DeleteMapping("/artists/{artistsId}/member/{memberId}/instrument/{instrumentId}")
	public Map<String, String> deleteInstrumentById(@PathVariable Long artistsId, @PathVariable Long memberId,
			@PathVariable Long instrumentId) {

		log.info("Deleting instrument with ID ={}", instrumentId);
		artistsService.deleteInstrumentById(artistsId, memberId, instrumentId);
		return Map.of("Deleting", "Instrument with ID= " + instrumentId + " has been deleted.");
	}

}

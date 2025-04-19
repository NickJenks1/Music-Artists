package artists.controller.model;

import java.util.HashSet;
import java.util.Set;

import artists.entity.Album;
import artists.entity.Artists;
import artists.entity.Instrument;
import artists.entity.Member;
import artists.entity.Song;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * Classes that take the data from the associated entity classes to form a constructor via the 
 * NoArgsConstructor annotation, and they receive getters and setter from the Data annotation, as well as
 * ToString, EqualsAndHashCode, etc.
 * 
 **/
@Data
@NoArgsConstructor
public class ArtistsData {

	private long artistsId;
	private String artistsName;
	private String genre;
	private String yearFormed;
	private Set<ArtistsAlbum> albums = new HashSet<>();
	private Set<ArtistsMember> members = new HashSet<>();
	
	public ArtistsData(Artists artists) {
		artistsId = artists.getArtistsId();
		artistsName = artists.getArtistsName();
		genre = artists.getGenre();
		yearFormed = artists.getYearFormed();
		
		for (Album album : artists.getAlbum()) {
			ArtistsAlbum artistsAlbum = new ArtistsAlbum(album);
			albums.add(artistsAlbum);
		}
		
		for (Member member : artists.getMember()) {
			ArtistsMember artistsMember = new ArtistsMember(member);
			members.add(artistsMember);
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class ArtistsAlbum {
	
		private long albumId;
		private String albumTitle;
		private String releaseDate;
		private Set<ArtistsSong> songs = new HashSet<>();
	
		
		public ArtistsAlbum(Album album) {
			albumId = album.getAlbumId();
			albumTitle = album.getAlbumTitle();
			releaseDate = album.getReleaseDate();
			
			for (Song song : album.getSong()) {
				ArtistsSong artistsSong = new ArtistsSong(song);
				songs.add(artistsSong);
			}
		}
	}

	@Data
	@NoArgsConstructor
	public static class ArtistsSong {
		
		private long songId;
		private String songTitle;
		private String length;
		
		public ArtistsSong(Song song) {
			songId = song.getSongId();
			songTitle = song.getSongTitle();
			length = song.getLength();
		}
	}
	
	@Data
	@NoArgsConstructor
	public static class ArtistsMember {
		
		private long memberId;
		private String memberName;
		private Set<ArtistsInstrument> instruments = new HashSet<>();
		
		public ArtistsMember(Member member) {
			memberId = member.getMemberId();
			memberName = member.getMemberName();
			
			for (Instrument instrument : member.getInstrument()) {
				ArtistsInstrument artistsInstrument = new ArtistsInstrument(instrument);
				instruments.add(artistsInstrument);
			}
		}
	}
		
	@Data
	@NoArgsConstructor
public static class ArtistsInstrument {
		
		private long instrumentId;
		private String instrumentName;
		private Set<ArtistsMember> members = new HashSet<>();
		
		public ArtistsInstrument(Instrument instrument) {
			instrumentId = instrument.getInstrumentId();
			instrumentName = instrument.getInstrumentName();
		}
	}
}

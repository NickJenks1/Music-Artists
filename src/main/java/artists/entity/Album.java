package artists.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Album {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long albumId;
	
	private String albumTitle;
	private String releaseDate;
	
	@ManyToOne
	@JoinColumn(name = "artists_id")
	private Artists artists;
	
	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Song> song = new HashSet<>();

}

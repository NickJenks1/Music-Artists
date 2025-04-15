package artists.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Song {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long songId;
	
	private String songTitle;
	private String length;
	
	@ManyToOne
	@JoinColumn(name = "album_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Album album;

}

package artists.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 
 * Class for the "Artists" entity that has field for a generated id
 * as well as a name, genre, and the year they formed
 * "Artists" has a OneToMany relationship with album, as well as member
 * 
 **/
@Entity
@Data
public class Artists {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long artistsId;
	
	private String artistsName;
	private String genre;
	private String yearFormed;

	@OneToMany(mappedBy = "artists", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Album> album = new HashSet<>();
	
	@OneToMany(mappedBy = "artists", cascade = CascadeType.ALL, orphanRemoval = true)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Member> member = new HashSet<>();
	
}

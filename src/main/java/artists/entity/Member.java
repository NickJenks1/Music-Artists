package artists.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long memberId;
	
	private String memberName;
	
	@ManyToMany
	@JoinTable(name = "member_instrument", joinColumns = @JoinColumn(name = "member_id"), 
	inverseJoinColumns = @JoinColumn(name = "instrument_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Instrument> instrument = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "artists_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Artists artists;
	
}

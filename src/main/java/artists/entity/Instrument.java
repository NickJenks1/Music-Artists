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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Instrument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long instrumentId;
	
	private String instrumentName;
	private String memberName;
	
	@ManyToMany
	@JoinTable(name = "member_instrument", joinColumns = @JoinColumn(name = "instrument_id"), 
	inverseJoinColumns = @JoinColumn(name = "member_id"))
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Member> member = new HashSet<>();

}

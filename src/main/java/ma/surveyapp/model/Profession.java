package ma.surveyapp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Profession {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProfession;
	@Column(nullable=false,unique=true)
	private String libelleProfession;
	private String descProfession;
	@OneToMany(mappedBy="profession",fetch=FetchType.LAZY)
	@JsonBackReference
    private Set<Participant> participants;

}

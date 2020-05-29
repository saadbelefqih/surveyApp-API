package ma.surveyapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Ville{
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVille;
	@Column(nullable=false,unique=true)
	private String libelleVille;
	private Float latitudeVille;
	private Float longitudeVille;
	@OneToMany(mappedBy="ville",fetch=FetchType.LAZY)
    private Set<Participant> participants=new HashSet<>();

}

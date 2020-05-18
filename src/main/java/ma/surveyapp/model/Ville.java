package ma.surveyapp.model;

import java.io.Serializable;
import java.util.HashSet;
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
public class Ville implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVille;
	@Column(nullable=false,unique=true)
	private String libelleVille;
	private Float latitudeVille;
	private Float longitudeVille;
	@OneToMany(mappedBy="ville",fetch=FetchType.LAZY)
	@JsonBackReference
    private Set<Participant> participants=new HashSet<>();

}

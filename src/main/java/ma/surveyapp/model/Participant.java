package ma.surveyapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
@Entity
@Data
public class Participant {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idParticipant;
	//true pour Homme False pour Femme
	private Boolean genreParticipant;
	private String nomParticipant;
	private String prenomParticipant;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateNaissance;
	private String imageURLParticipant;
	private String emailParticipant;
	private String telParticipant;
	private String adresseParticipant;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "ville_id",nullable = false)
	private Ville ville;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "profession_id",nullable = false)
    private Profession profession;
	@OneToMany(mappedBy = "participant",fetch=FetchType.LAZY, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Demande> demandes=new HashSet<Demande>();
	
	

	
}

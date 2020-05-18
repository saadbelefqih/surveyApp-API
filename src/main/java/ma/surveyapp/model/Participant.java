package ma.surveyapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Participant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private Ville ville;
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(nullable = false)
	@JsonManagedReference
    private Profession travail;
	@OneToMany(mappedBy = "participant",fetch=FetchType.LAZY)
	@JsonBackReference
    private Set<Demande> demandes;


}

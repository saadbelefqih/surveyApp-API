package ma.surveyapp.model;

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Groupe {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idGroupe;
	private String intituleGroupe;
	private String descGroupe;
	private Integer nbParticipant;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDebuteTravailGroupe;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFinTravailGroupe;
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(name = "annoce_id",nullable = false)
	private Annonce annonce;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="groupe")
	private Set<Demande> demandes;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="groupe")
	private Set<LigneQuestionnaire> ligneQuestionnaires;

}

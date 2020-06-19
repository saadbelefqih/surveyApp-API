package ma.surveyapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Annonce {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idAnnonce;
	private String libelleAnnonce;
	private String detailAnnonce;
	private Integer nbParticipantDemander;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDebutPublication;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFinPublication;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDebutTravail;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateFinTravail;
	@ManyToMany(mappedBy = "annonces")
    private Set<PublicCible> publics;
	@OneToMany(mappedBy = "annonce",fetch=FetchType.LAZY)
    private Set<Demande> demandes=new HashSet<>();
	@OneToMany(mappedBy = "annonce",fetch=FetchType.LAZY)
    private Set<Groupe> groupes=new HashSet<>();
}

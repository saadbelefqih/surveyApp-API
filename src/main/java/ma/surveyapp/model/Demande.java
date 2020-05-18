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

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Demande {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idDemande;
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private Participant participant;
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(nullable = false)
	@JsonManagedReference
	private Annonce annonce;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date dateDemande;
	private Boolean isPending=true;
	private Boolean isValide=false;
	private Boolean isRefuse=false;
	@ManyToOne(fetch=FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private Groupe groupe;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="demande")
	private Set<Reponse> reponses;

}

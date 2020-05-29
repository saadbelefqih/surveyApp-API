package ma.surveyapp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
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
public class LigneQuestionnaire {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idLQ;
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name = "questionnaire_id",nullable = false)
	private Questionnaire questionnaire;
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name = "groupe_id",nullable = false)
	private Groupe groupe;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(nullable=false)
	private Date dateDebutReponse;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	@Column(nullable=false)
	private Date dateFinReponse;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="ligneQuestionnaire")
	private Set<Reponse> reponses;

}

package ma.surveyapp.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Reponse {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idReponse;
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name = "ligne_questionnaire_id",nullable = false)
	private LigneQuestionnaire ligneQuestionnaire;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "demande_id",nullable=false)
	private Demande demande;
	private Boolean hasRespense;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="reponse")
	private Set<ReponseDetails> reponsesDetails;

}

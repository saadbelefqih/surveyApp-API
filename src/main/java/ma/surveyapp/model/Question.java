package ma.surveyapp.model;

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

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Question {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idQuestion;
	@Column(nullable=false)
	private String textQuestion;
	@Column(nullable=false)
	private String option1;
	@Column(nullable=false)
	private String option2;
	private String option3;
	private String option4;
	private Boolean isMultichoice=false;
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name = "questionnaire_id",nullable = false)
	private Questionnaire questionnaire;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="question")
	private Set<ReponseDetails> reponses;
}

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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Setter
@Getter
public class Questionnaire {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idQuestionnaire;
	@Column(nullable=false)
	private String intituleQuestionnaire;
	private String descQuestionnaire;
	private Date DateAjoutQuestionnaire;
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(nullable = false)
	private Theme theme;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="questionnaire")
	private Set<Question>questions;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="questionnaire")
	private Set<LigneQuestionnaire>lignesQuestionnaire;
	
	

}

package ma.surveyapp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@NoArgsConstructor
@Getter
@Setter
public class Theme {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idTheme;
	@Column(unique=true,nullable=false)
	private String intituleTheme;
	private String descTheme;
	@OneToMany(mappedBy="theme",fetch=FetchType.LAZY)
	private Set<Questionnaire> questionnaires;
	

}

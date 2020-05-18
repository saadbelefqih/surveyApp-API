package ma.surveyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Questionnaire;
import ma.surveyapp.model.Theme;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long>{
	
	Questionnaire findByIntituleQuestionnaireIgnoreCase(String intituleQuestionnaire);
	List<Questionnaire> findByTheme(Theme theme);
	
}

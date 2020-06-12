package ma.surveyapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import ma.surveyapp.model.Questionnaire;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long>{
	

	
	@Query(value="SELECT q FROM Questionnaire q WHERE  q.theme.idTheme=:themeId")
	List<Questionnaire> findByThemeId(@Param("themeId")Long themeId);
	
	@Query(value="SELECT q FROM Questionnaire q WHERE  UPPER(q.intituleQuestionnaire) LIKE CONCAT('%',?1,'%')")
	List<Questionnaire> findByInIntitule(@Param("intitule")String intitule,Pageable pageable);
	
	Questionnaire findByIntituleQuestionnaireIgnoreCase(String intituleQuestionnaire);
	
}

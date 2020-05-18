package ma.surveyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Groupe;
import ma.surveyapp.model.LigneQuestionnaire;
import ma.surveyapp.model.Questionnaire;

public interface LigneQuestionnaireRepository extends JpaRepository<LigneQuestionnaire, Long>{
	
	LigneQuestionnaire findByQuestionnaireAndGroupe(Questionnaire q,Groupe g);
	List<LigneQuestionnaire> findByQuestionnaire(Questionnaire q);
	List<LigneQuestionnaire> findByGroupe(Groupe g);

}

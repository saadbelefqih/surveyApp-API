package ma.surveyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.surveyapp.model.Groupe;
import ma.surveyapp.model.LigneQuestionnaire;
import ma.surveyapp.model.Questionnaire;

public interface LigneQuestionnaireRepository extends JpaRepository<LigneQuestionnaire, Long>{
	 @Query("SELECT l FROM LigneQuestionnaire l WHERE l.questionnaire.idQuestionnaire = :questionnaireId AND l.groupe.idGroupe = :groupeId")
	 LigneQuestionnaire findLigneQuestionnaireByQuestionnaireIdAndGroupeId(
					@Param("questionnaireId") Long questionnaireId,@Param("groupeId") Long groupeId);
	 
	 
	 @Modifying
	 @Query("DELETE FROM LigneQuestionnaire l WHERE l.questionnaire.idQuestionnaire = :questionnaireId AND l.groupe.idGroupe = :groupeId")
	 void deleteLigneQuestionnaireByQuestionnaireIdAndGroupeId(@Param("questionnaireId") Long questionnaireId,
			 @Param("groupeId") Long groupeId);
	 
	List<LigneQuestionnaire> findByQuestionnaire(Questionnaire q);
	List<LigneQuestionnaire> findByGroupe(Groupe g);

}

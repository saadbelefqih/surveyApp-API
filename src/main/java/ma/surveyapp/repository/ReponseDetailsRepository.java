package ma.surveyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.surveyapp.model.QuestionTotalReponse;
import ma.surveyapp.model.ReponseDetails;

public interface ReponseDetailsRepository extends JpaRepository<ReponseDetails, Long> {
	
	
    @Query("SELECT NEW ma.surveyapp.model.QuestionTotalReponse(r.question.idQuestion,COUNT(r.idRd)) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption1 = true GROUP BY r.question.idQuestion")
    List<QuestionTotalReponse> countOption1ByLigneQuestionnaireIdInGroupByidQuestion(@Param("lqId") Long lqId);
    
    @Query("SELECT NEW ma.surveyapp.model.QuestionTotalReponse(r.question.idQuestion,COUNT(r.idRd)) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption2 = true GROUP BY r.question.idQuestion")
    List<QuestionTotalReponse> countOption2ByLigneQuestionnaireIdInGroupByidQuestion(@Param("lqId") Long lqId);
    
    @Query("SELECT NEW ma.surveyapp.model.QuestionTotalReponse(r.question.idQuestion,COUNT(r.idRd)) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption3 = true GROUP BY r.question.idQuestion")
    List<QuestionTotalReponse> countOption3ByLigneQuestionnaireIdInGroupByidQuestion(@Param("lqId") Long lqId);
    
    @Query("SELECT NEW ma.surveyapp.model.QuestionTotalReponse(r.question.idQuestion,COUNT(r.idRd)) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption4 = true GROUP BY r.question.idQuestion")
    List<QuestionTotalReponse> countOption4ByLigneQuestionnaireIdInGroupByidQuestion(@Param("lqId") Long lqId);

  

}

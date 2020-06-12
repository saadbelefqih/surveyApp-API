package ma.surveyapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.surveyapp.model.Question;
import ma.surveyapp.model.Reponse;
import ma.surveyapp.model.ReponseDetails;

public interface ReponseDetailsRepository extends JpaRepository<ReponseDetails, Long> {
	
	@Query("SELECT rd FROM ReponseDetails rd WHERE rd.question.idQuestion=:questionId AND rd.reponse.idReponse=:reponseId")
	ReponseDetails getReponseDetailsByQuestionIdAndReponseId(@Param("questionId") Long questionId,@Param("reponseId")Long reponseId);
	
	ReponseDetails findByReponseAndQuestion(Reponse reponse,Question question);
    
/*	@Query("SELECT NEW ma.surveyapp.model.QuestionTotalReponse(r.question.idQuestion,COUNT(r.idRd)) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption1 = true GROUP BY r.question.idQuestion")
    List<QuestionTotalReponse> countOption1ByLigneQuestionnaireIdInGroupByidQuestion(@Param("lqId") Long lqId);*/
    
	@Query("SELECT COUNT(r.idRd) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption1 = true AND r.question.idQuestion = :quesId ")
    Integer countOption1ByLigneQuestionnaireIdAndidQuestion(@Param("lqId") Long lqId,@Param("quesId")Long quesId);
	
	@Query("SELECT COUNT(r.idRd) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption2 = true AND r.question.idQuestion = :quesId ")
    Integer countOption2ByLigneQuestionnaireIdAndidQuestion(@Param("lqId") Long lqId,@Param("quesId")Long quesId);
	@Query("SELECT COUNT(r.idRd) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption3 = true AND r.question.idQuestion = :quesId ")
    Integer countOption3ByLigneQuestionnaireIdAndidQuestion(@Param("lqId") Long lqId,@Param("quesId")Long quesId);
	@Query("SELECT COUNT(r.idRd) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption4 = true AND r.question.idQuestion = :quesId ")
    Integer countOption4ByLigneQuestionnaireIdAndidQuestion(@Param("lqId") Long lqId,@Param("quesId")Long quesId);
    /*@Query("SELECT NEW ma.surveyapp.model.QuestionTotalReponse(r.question.idQuestion,COUNT(r.idRd)) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption2 = true GROUP BY r.question.idQuestion")
    List<QuestionTotalReponse> countOption2ByLigneQuestionnaireIdInGroupByidQuestion(@Param("lqId") Long lqId);
    
    @Query("SELECT NEW ma.surveyapp.model.QuestionTotalReponse(r.question.idQuestion,COUNT(r.idRd)) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption3 = true GROUP BY r.question.idQuestion")
    List<QuestionTotalReponse> countOption3ByLigneQuestionnaireIdInGroupByidQuestion(@Param("lqId") Long lqId);
    
    @Query("SELECT NEW ma.surveyapp.model.QuestionTotalReponse(r.question.idQuestion,COUNT(r.idRd)) from ReponseDetails r WHERE r.reponse.ligneQuestionnaire.idLQ = :lqId AND r.isOption4 = true GROUP BY r.question.idQuestion")
    List<QuestionTotalReponse> countOption4ByLigneQuestionnaireIdInGroupByidQuestion(@Param("lqId") Long lqId);*/

  

}

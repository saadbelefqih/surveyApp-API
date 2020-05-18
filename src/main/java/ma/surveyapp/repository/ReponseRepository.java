package ma.surveyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Demande;
import ma.surveyapp.model.LigneQuestionnaire;
import ma.surveyapp.model.Reponse;

public interface ReponseRepository extends JpaRepository<Reponse, Long>{
	List<Reponse>findByLigneQuestionnaireAndDemande(LigneQuestionnaire lq,Demande demande);
	
	List<Reponse> findByLigneQuestionnaire(LigneQuestionnaire lq);
	List<Reponse> findByLigneQuestionnaireAndHasRespenseTrue(LigneQuestionnaire lq);
	List<Reponse> findByLigneQuestionnaireAndHasRespenseFalse(LigneQuestionnaire lq);
	
	
	List<Reponse> findByDemande (Demande demande);
	List<Reponse> findByDemandeAndHasRespenseTrue(Demande demande);
	List<Reponse> findByDemandeAndHasRespenseFalse(Demande demande);

}

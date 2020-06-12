package ma.surveyapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.surveyapp.model.Demande;
import ma.surveyapp.model.LigneQuestionnaire;
import ma.surveyapp.model.Reponse;

public interface ReponseRepository extends JpaRepository<Reponse, Long>{
	
	
	@Query("SELECT r FROM Reponse r WHERE r.demande.idDemande = :demandeId AND r.hasRespense=false AND :dateNow BETWEEN  r.ligneQuestionnaire.dateDebutReponse AND r.ligneQuestionnaire.dateFinReponse ")
	List<Reponse> findResponseByDemandeIdAndDate(
					@Param("demandeId") Long demandeId,@Param("dateNow") Date dateNow);
	
	List<Reponse>findByLigneQuestionnaireAndDemande(LigneQuestionnaire lq,Demande demande);
	
	List<Reponse> findByLigneQuestionnaire(LigneQuestionnaire lq);
	List<Reponse> findByLigneQuestionnaireAndHasRespenseTrue(LigneQuestionnaire lq);
	List<Reponse> findByLigneQuestionnaireAndHasRespenseFalse(LigneQuestionnaire lq);
	
	
	List<Reponse> findByDemande (Demande demande);
	List<Reponse> findByDemandeAndHasRespenseTrue(Demande demande);
	List<Reponse> findByDemandeAndHasRespenseFalse(Demande demande);

}

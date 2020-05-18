package ma.surveyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Annonce;
import ma.surveyapp.model.Demande;
import ma.surveyapp.model.Participant;

public interface DemandeRepository extends JpaRepository<Demande, Long>{

	List<Demande> findByAnnonceAndParticipant(Annonce annonce,Participant participant);
	
	
	List<Demande> findByAnnonce(Annonce annonce);
	List<Demande> findByAnnonceAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(Annonce annonce);
	List<Demande> findByAnnonceAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(Annonce annonce);
	List<Demande> findByAnnonceAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(Annonce annonce);
	
	List<Demande> findByParticipant(Participant participant);
	List<Demande> findByParticipantAndIsPendingTrueAndIsValideFalseAndIsRefuseFalse(Participant participant);
	List<Demande> findByParticipantAndIsPendingFalseAndIsRefuseFalseAndIsValideTrue(Participant participant);
	List<Demande> findByParticipantAndIsPendingFalseAndIsValideFalseAndIsRefuseTrue(Participant participant);

}

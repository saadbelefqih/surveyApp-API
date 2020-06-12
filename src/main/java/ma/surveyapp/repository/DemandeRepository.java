package ma.surveyapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.surveyapp.model.Annonce;
import ma.surveyapp.model.Demande;
import ma.surveyapp.model.Participant;

public interface DemandeRepository extends JpaRepository<Demande, Long>{

	 @Query("SELECT d FROM Demande d WHERE d.annonce.idAnnonce = :anId AND d.participant.idParticipant = :parId")
	Demande getAnnonceAndParticipant(
				@Param("anId") Long annnonceId,@Param("parId") Long participantId);
	 
	 @Query("SELECT d FROM Demande d WHERE d.annonce.idAnnonce=:anId AND d.idDemande=:dmdId")
		Demande getByIdAnnonceAndIdDemande(
					@Param("anId") Long annnonceId,@Param("dmdId") Long demandeId);
	 
	 @Query("SELECT d FROM Demande d WHERE d.participant.idParticipant = :parId AND d.idDemande = :dmdId")
		Demande getByIdParticipantAndIdDemande(
					@Param("parId") Long participantId,@Param("dmdId") Long demandeId);
	 
	 @Query("SELECT d FROM Demande d WHERE d.participant.idParticipant = :parId")
	 List<Demande> getByIdParticipant(
					@Param("parId") Long participantId);
	 
	 @Query("SELECT d FROM Demande d WHERE d.participant.idParticipant = :parId AND d.isValide = true AND d.isRefuse = false AND d.isPending = false")
	 List<Demande> getByValideDemandesByIdParticipant(
					@Param("parId") Long participantId);
	 
	 @Query("SELECT d FROM Demande d WHERE d.participant.idParticipant = :parId AND d.isValide = false AND d.isRefuse = true AND d.isPending = false")
	 List<Demande> getByRefuseDemandesByIdParticipant(
					@Param("parId") Long participantId);
	 
	 @Query("SELECT d from Demande d WHERE d.participant.idParticipant = :parId AND d.isValide = false AND d.isRefuse = false AND d.isPending = true")
	 List<Demande> getByPendingDemandesByIdParticipant(
					@Param("parId") Long participantId);
	 
	 
	 @Modifying
	 @Query("update Demande d set d.isValide = true , d.isPending = false , d.isRefuse = false where d.idDemande =:dmdId")
	 void validerDemande(@Param("dmdId") Long demandeId);
	 
	 @Modifying
	 @Query("update Demande d set d.isRefuse = true , d.isPending = false,d.isValide = false where d.idDemande =:dmdId")
	 void refuserDemande(@Param("dmdId") Long demandeId);
	 
	 @Modifying
	 @Query("update Demande d set d.isPending = true,d.isValide=false,d.isRefuse=false where d.idDemande =:dmdId")
	 void pendingDemande(@Param("dmdId") Long demandeId);
	 
	 
	 
	 @Modifying
	   @Query("DELETE Demande d WHERE d.isPending=true AND d.isValide=false AND d.isRefuse=false AND d.idDemande =:dmdId")
	   public void deleteInPendingDemande(@Param("dmdId") Long demandeId);
	 
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

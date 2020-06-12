package ma.surveyapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ma.surveyapp.dto.DemandeRequestDTO;
import ma.surveyapp.dto.DemandeResponseDTO;
import ma.surveyapp.dto.GroupeDTO;


public interface DemandeService {
	/*
	List<Demande> getAllByAnnonce(Long idAnnonce);
	List<Demande> getValidateByAnnonce(Long idAnnonce);
	List<Demande> getPendingByAnnonce(Long idAnnonce);
	List<Demande> getRefuseByAnnonce(Long idAnnonce);

	List<Demande> getAllByVolontaire(Long idVolontaire);
	List<Demande> getRefuseByVolontaire(Long idVolontaire);
	List<Demande> getValidateByVolontaire(Long idVolontaire);
	List<Demande> getPendingByVolontaire(Long idVolontaire);
	*/
	DemandeResponseDTO getByID(Long idDemande);
	DemandeResponseDTO getByDemandeIdAndAnnonceId(Long idDemande,Long idAnnonce);
	DemandeResponseDTO getByDemandeIdAndParticipantId(Long idDemande,Long idparticipant);
	Page<DemandeResponseDTO> getByParticipantId(Long idparticipant,int page, int size);
	Page<DemandeResponseDTO> getByValideDemandeByParticipantId(Long idparticipant,int page, int size);
	Page<DemandeResponseDTO> getByRefuseDemandeByParticipantId(Long idparticipant,int page, int size);
	Page<DemandeResponseDTO> getByPendingDemandeByParticipantId(Long idparticipant,int page, int size);
	DemandeResponseDTO save(DemandeRequestDTO demandeRequestDTO);
	void setValidate(List<DemandeRequestDTO> demandes);
	void setPending(List<DemandeRequestDTO> demandes);
	void setRefuse(List<DemandeRequestDTO> demandes);
	Boolean supprimerDemande(Long idDemande);

	GroupeDTO addDemandesToGroupe(Long idAnnonce,Long idgroupe,List<DemandeRequestDTO> demandesRequestDTO);
	DemandeResponseDTO removeDemandeFromGroupe(DemandeRequestDTO demandeRequestDTO);

	
}

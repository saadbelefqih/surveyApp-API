package ma.surveyapp.util.modelmapper;

import ma.surveyapp.dto.DemandeResponseDTO;
import ma.surveyapp.model.Demande;

public class DemandeMapper {
	public static DemandeResponseDTO demandeToDemandeDTO(Demande demande){
		DemandeResponseDTO demandeRequestDTO = new DemandeResponseDTO();
		demandeRequestDTO.setIdDemande(demande.getIdDemande());
		demandeRequestDTO.setAnnonce(AnnonceMapper.annonceToAnnonceDTO(demande.getAnnonce()));
		demandeRequestDTO.setDateDemande(demande.getDateDemande());
		demandeRequestDTO.setParticipant(ParticipantMapper.ParticipantToParticipantResponseDto(demande.getParticipant()));
		demandeRequestDTO.setIsPending(demande.getIsPending());
		demandeRequestDTO.setIsRefuse(demande.getIsRefuse());
		demandeRequestDTO.setIsValide(demande.getIsRefuse());
		if(demande.getGroupe()!=null){
		demandeRequestDTO.setGroupe(GroupeMapper.groupeToGroupeDTO(demande.getGroupe()));
		}
		return demandeRequestDTO;
		
	}

}

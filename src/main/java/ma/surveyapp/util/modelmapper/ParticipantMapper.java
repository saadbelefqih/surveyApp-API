package ma.surveyapp.util.modelmapper;

import ma.surveyapp.dto.ParticipantResponseDTO;
import ma.surveyapp.model.Participant;

public class ParticipantMapper {
	
	public static ParticipantResponseDTO ParticipantToParticipantResponseDto(Participant participant){
		ParticipantResponseDTO participantResponseDTO = new ParticipantResponseDTO();
		participantResponseDTO.setId(participant.getIdpers());
		participantResponseDTO.setGenre(participant.getGenre());
		participantResponseDTO.setAdresse(participant.getAdresse());
		participantResponseDTO.setDateNaissance(participant.getDatenais());
		participantResponseDTO.setEmail(participant.getEmail());
		participantResponseDTO.setImageURL(participant.getPhoto());
		participantResponseDTO.setNom(participant.getNom());
		participantResponseDTO.setPrenom(participant.getPrenom());
		participantResponseDTO.setUsername(participant.getUsername());
		//participantResponseDTO.setPassword(participant.getPassword());
		participantResponseDTO.setProfession(ProfessionMapper.ProfessionToProfessionDTO(participant.getProfession()));
		participantResponseDTO.setTel(participant.getNtel());
		participantResponseDTO.setVille(VilleMapper.villeToVilleDTO(participant.getVille()));
		return participantResponseDTO;
	}
	

	

	


}

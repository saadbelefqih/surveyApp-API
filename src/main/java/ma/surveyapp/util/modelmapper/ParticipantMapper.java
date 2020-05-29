package ma.surveyapp.util.modelmapper;

import ma.surveyapp.dto.ParticipantResponseDTO;
import ma.surveyapp.model.Participant;

public class ParticipantMapper {
	
	public static ParticipantResponseDTO ParticipantToParticipantResponseDto(Participant participant){
		ParticipantResponseDTO participantResponseDTO = new ParticipantResponseDTO();
		participantResponseDTO.setId(participant.getIdParticipant());
		participantResponseDTO.setGenre(participant.getGenreParticipant());
		participantResponseDTO.setAdresse(participant.getAdresseParticipant());
		participantResponseDTO.setDateNaissance(participant.getDateNaissance());
		participantResponseDTO.setEmail(participant.getEmailParticipant());
		participantResponseDTO.setImageURL(participant.getImageURLParticipant());
		participantResponseDTO.setNom(participant.getNomParticipant());
		participantResponseDTO.setPrenom(participant.getPrenomParticipant());
		participantResponseDTO.setProfession(ProfessionMapper.ProfessionToProfessionDTO(participant.getProfession()));
		participantResponseDTO.setTel(participant.getTelParticipant());
		participantResponseDTO.setVille(VilleMapper.villeToVilleDTO(participant.getVille()));
		return participantResponseDTO;
	}
	

	

	


}

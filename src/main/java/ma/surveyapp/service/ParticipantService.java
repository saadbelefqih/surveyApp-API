package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.dto.ParticipantRequestDTO;
import ma.surveyapp.dto.ParticipantResponseDTO;

public interface ParticipantService {
	
	List<ParticipantResponseDTO> getAll(int page,int size);
	ParticipantResponseDTO getById(Long idParticipant);
	ParticipantResponseDTO save(ParticipantRequestDTO participantRequestDTO);
	ParticipantResponseDTO update(ParticipantRequestDTO participantRequestDTO);
	void delete(Long idParticipant);
	

}

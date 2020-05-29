package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.dto.ParticipantRequestDTO;
import ma.surveyapp.dto.ParticipantResponseDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;

public interface ParticipantService {
	
	List<ParticipantResponseDTO> getAll() throws ApiInternalServerErrorExeption,ApiNoContentException;
	ParticipantResponseDTO getById(Long idParticipant)throws ApiNoContentException,ApiInternalServerErrorExeption;
	ParticipantResponseDTO save(ParticipantRequestDTO participantRequestDTO)throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	ParticipantResponseDTO update(ParticipantRequestDTO participantRequestDTO)throws ApiNotFoundException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	void delete(Long idParticipant)throws ApiInternalServerErrorExeption;
	

}

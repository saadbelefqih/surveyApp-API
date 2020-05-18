package ma.surveyapp.service;

import java.util.List;


import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Participant;

public interface ParticipantService {
	
	List<Participant> getAll() throws ApiInternalServerErrorExeption,ApiNoContentException;
	Participant getById(Long idParticipant)throws ApiNoContentException,ApiInternalServerErrorExeption;
	Participant save(Participant participant)throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	Participant update(Participant participant)throws ApiNotFoundException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	void delete(Long idParticipant)throws ApiInternalServerErrorExeption;
	

}

package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Reponse;

public interface ReponseService {
	
	
	List<Reponse> getAllByLigneQuestionnaireAndVolontaire(Long idLigneQuestionnaire,Long idDemande)throws ApiNotFoundException,ApiNoContentException,ApiInternalServerErrorExeption;
	
	List<Reponse> getAllByLigneQuestionnaire(Long idLigneQuestionnaire) throws ApiNotFoundException,ApiNoContentException,ApiInternalServerErrorExeption;
	List<Reponse>getAllByToResponseByLigneQuestionnaire(Long idLigneQuestionnaire)throws ApiNotFoundException,ApiNoContentException,ApiInternalServerErrorExeption;
	List<Reponse>getAllByRespondedByLigneQuestionnaire(Long idLigneQuestionnaire)throws ApiNotFoundException,ApiNoContentException,ApiInternalServerErrorExeption;
	
	List<Reponse> getAllByDemande(Long idDemande)throws ApiNotFoundException,ApiNoContentException,ApiInternalServerErrorExeption;
	List<Reponse>getAllByToResponseByDemande(Long idDemande)throws ApiNotFoundException,ApiNoContentException,ApiInternalServerErrorExeption;
	List<Reponse>getAllByRespondedByDemande(Long idDemande)throws ApiNotFoundException,ApiNoContentException,ApiInternalServerErrorExeption;
	
	Reponse getById(Long id)throws ApiNoContentException, ApiInternalServerErrorExeption;
	Reponse save(Reponse reponse)throws ApiNotFoundException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	void delete(Long id)throws ApiNotModifiedException,ApiInternalServerErrorExeption;
	
	

}

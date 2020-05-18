package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.LigneQuestionnaire;

public interface LigneQuestionnaireService {
	
	List<LigneQuestionnaire> getAll() throws ApiInternalServerErrorExeption,ApiNoContentException;
	List<LigneQuestionnaire> getByGroupe(Long idGroupe) throws ApiInternalServerErrorExeption,ApiNotFoundException,ApiNoContentException;
	List<LigneQuestionnaire> getByQuestionnaire(Long idQuestionnaire) throws ApiInternalServerErrorExeption,ApiNotFoundException,ApiNoContentException;
	LigneQuestionnaire getbyID(Long idLigneQuestionnaire)throws ApiNoContentException, ApiInternalServerErrorExeption;
	LigneQuestionnaire save(LigneQuestionnaire LigneQuestionnaire)throws ApiNotFoundException,ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	void delete(Long idLigneQuestionnaire)throws ApiNotModifiedException,ApiInternalServerErrorExeption;

}

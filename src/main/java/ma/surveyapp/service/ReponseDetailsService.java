package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.model.ReponseDetails;

public interface ReponseDetailsService {
	List<ReponseDetails> getAll()throws ApiInternalServerErrorExeption;
	ReponseDetails getByID(Long idReponse)throws ApiInternalServerErrorExeption;
	ReponseDetails save(ReponseDetails Reponse)throws ApiInternalServerErrorExeption;

}

package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.model.PublicCible;

public interface PublicCibleService {
	
	List<PublicCible> getAll() throws ApiInternalServerErrorExeption;
	PublicCible getByID(Long idPublic)throws ApiInternalServerErrorExeption;
	PublicCible save(PublicCible Public)throws ApiInternalServerErrorExeption;
	PublicCible update(PublicCible Public)throws ApiInternalServerErrorExeption;
	void delete(Long idPublic)throws ApiInternalServerErrorExeption;

}

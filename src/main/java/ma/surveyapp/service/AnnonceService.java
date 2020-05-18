package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Annonce;

public interface AnnonceService {
	
	List<Annonce> getAll()throws ApiInternalServerErrorExeption,ApiNoContentException;
	Annonce getByID(Long idAnnonce)throws ApiNoContentException,ApiInternalServerErrorExeption;
	Annonce save(Annonce annonce)throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	void addPublicCible(Long idAnnonce,Long idpublic)throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	void deletePublic(Long idAnnonce,Long idpublic)throws ApiNoContentException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	Annonce update(Annonce annonce)throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	void delete(Long idAnnonce)throws ApiInternalServerErrorExeption ;

}

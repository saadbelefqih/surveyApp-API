package ma.surveyapp.service;


import org.springframework.data.domain.Page;

import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Ville;

public interface VilleService {
	Page<Ville> getAll(int page,int size)throws ApiInternalServerErrorExeption;
	Page<Ville> getByLibelle(String libelle,int page,int size)throws ApiInternalServerErrorExeption;
	Ville getByID(Long idville)throws ApiNoContentException,ApiInternalServerErrorExeption;
	Ville save(Ville ville) throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	Ville update(Ville ville)throws ApiNotFoundException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	void delete(Long idville)throws ApiInternalServerErrorExeption;

}

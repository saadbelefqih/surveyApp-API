package ma.surveyapp.service;

import java.util.List;
import java.util.Set;

import ma.surveyapp.dto.AnnonceDTO;
import ma.surveyapp.dto.PublicCibleDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotModifiedException;

public interface AnnonceService {
	
	List<AnnonceDTO> getAll()throws ApiInternalServerErrorExeption;
	AnnonceDTO getByID(Long idAnnonce)throws ApiNoContentException,ApiInternalServerErrorExeption;
	AnnonceDTO save(AnnonceDTO annonceDTO)throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	AnnonceDTO addPublicCibleToAnnonce(Long idAnnonce,Set<PublicCibleDTO> publicsCible)throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	AnnonceDTO deletePublicPublicCibleFromAnnonce(Long idAnnonce,Set<PublicCibleDTO> publicsCible)throws ApiNoContentException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	AnnonceDTO update(AnnonceDTO annonceDTO)throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	void delete(Long idAnnonce)throws ApiInternalServerErrorExeption ;

}

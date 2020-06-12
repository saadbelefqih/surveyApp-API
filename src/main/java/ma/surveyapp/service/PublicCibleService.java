package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.dto.AnnonceDTO;
import ma.surveyapp.dto.PublicCibleDTO;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;

public interface PublicCibleService {
	
	List<PublicCibleDTO> getAll();
	PublicCibleDTO getByID(Long idPublic)throws ApiInternalServerErrorExeption;
	List<AnnonceDTO> getAnnoncesByPublicCible(Long idPublic)throws ApiInternalServerErrorExeption;
	PublicCibleDTO save(PublicCibleDTO Public)throws ApiInternalServerErrorExeption;
	PublicCibleDTO update(PublicCibleDTO Public)throws ApiInternalServerErrorExeption;
	void delete(Long idPublic)throws ApiInternalServerErrorExeption;

}

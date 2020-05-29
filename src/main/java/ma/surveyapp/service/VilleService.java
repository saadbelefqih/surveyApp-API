package ma.surveyapp.service;


import java.util.List;


import ma.surveyapp.dto.VilleDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;

public interface VilleService {
	List<VilleDTO> getAll(int page,int size)throws ApiInternalServerErrorExeption;
	List<VilleDTO> getByLibelle(String libelle,int page,int size)throws ApiInternalServerErrorExeption;
	VilleDTO getByID(Long idville)throws ApiNoContentException,ApiInternalServerErrorExeption;
	VilleDTO save(VilleDTO ville) throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption;
	VilleDTO update(VilleDTO ville)throws ApiNotFoundException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	void delete(Long idville)throws ApiInternalServerErrorExeption;

}

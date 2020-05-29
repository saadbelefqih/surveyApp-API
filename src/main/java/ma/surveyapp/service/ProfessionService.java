package ma.surveyapp.service;



import java.util.List;

import ma.surveyapp.dto.ProfessionDTO;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;

public interface ProfessionService {
	List<ProfessionDTO> getAll(String name,int page,int size) throws ApiInternalServerErrorExeption;
	ProfessionDTO getByID(Long idprofession) throws ApiInternalServerErrorExeption;
	ProfessionDTO save(ProfessionDTO profession) throws ApiInternalServerErrorExeption;
	ProfessionDTO update(ProfessionDTO profession) throws ApiInternalServerErrorExeption;
	void delete(Long idprofession)throws ApiInternalServerErrorExeption;

}

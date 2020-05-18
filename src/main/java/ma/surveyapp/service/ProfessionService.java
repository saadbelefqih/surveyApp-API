package ma.surveyapp.service;



import org.springframework.data.domain.Page;

import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.model.Profession;

public interface ProfessionService {
	Page<Profession> getAll(String name,int page,int size) throws ApiInternalServerErrorExeption;
	Profession getByID(Long idprofession) throws ApiInternalServerErrorExeption;
	Profession save(Profession profession) throws ApiInternalServerErrorExeption;
	Profession update(Profession profession) throws ApiInternalServerErrorExeption;
	void delete(Long idprofession)throws ApiInternalServerErrorExeption;

}

package ma.surveyapp.service.impl;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Profession;
import ma.surveyapp.repository.ProfessionRepository;
import ma.surveyapp.service.ProfessionService;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProfessionServiceImpl implements ProfessionService{
	private final ProfessionRepository professionRepository;

	@Override
	public Page<Profession> getAll(String name,int page,int size) throws ApiInternalServerErrorExeption {
		try {
			return professionRepository.findByFullTextSearch(name,(PageRequest.of(page, size)));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Profession getByID(Long idTravail) throws ApiInternalServerErrorExeption {
		try{
			if(professionRepository.existsById(idTravail)){
				return professionRepository.getOne(idTravail);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Profession save(Profession profession) throws ApiInternalServerErrorExeption {
		try {
			if(professionRepository.findByLibelleProfessionIgnoreCase(profession.getLibelleProfession())!=null){
				throw new ApiConflictException("Profession already exist");
			}
			Profession professionSaved = professionRepository.save(profession);
			if(professionSaved!=null){
				return professionSaved;
			}
			throw new ApiNotModifiedException("Profession is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public Profession update(Profession profession) throws ApiInternalServerErrorExeption {
		try {
			if(!professionRepository.existsById(profession.getIdProfession())){
				throw new ApiNotFoundException("Profession does not exist");
			}
			Profession professionSaved = professionRepository.save(profession);
			if(professionSaved!=null){
				return professionSaved;
			}
			throw new ApiNotModifiedException("Profession is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idTravail) throws ApiInternalServerErrorExeption {
		try {
			professionRepository.deleteById(idTravail);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}
	

}

package ma.surveyapp.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.ProfessionDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Profession;
import ma.surveyapp.repository.ProfessionRepository;
import ma.surveyapp.service.ProfessionService;
import ma.surveyapp.util.modelmapper.ProfessionMapper;
@Service
@RequiredArgsConstructor
@Slf4j
public class ProfessionServiceImpl implements ProfessionService{
	private final ProfessionRepository professionRepository;

	@Override
	public List<ProfessionDTO> getAll(String name,int page,int size) throws ApiInternalServerErrorExeption {
		try {
			List<ProfessionDTO> lists=professionRepository.findByFullTextSearch(name,(PageRequest.of(page, size)))
					.stream().map(p->{
						return ProfessionMapper.ProfessionToProfessionDTO(p);
					}).collect(Collectors.toList());
			return lists;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public ProfessionDTO getByID(Long idTravail) throws ApiInternalServerErrorExeption {
		try{
			if(professionRepository.existsById(idTravail)){
				return ProfessionMapper.ProfessionToProfessionDTO(professionRepository.getOne(idTravail));
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public ProfessionDTO save(ProfessionDTO profession) throws ApiInternalServerErrorExeption {
		try {
			if(professionRepository.findByLibelleProfessionIgnoreCase(profession.getLibelleProfession())!=null){
				throw new ApiConflictException("Profession already exist");
			}
			Profession professionSaved = professionRepository.save(ProfessionMapper.ProfessionDtoToProfession(profession));
			if(professionSaved!=null){
				return ProfessionMapper.ProfessionToProfessionDTO(professionSaved);
			}
			throw new ApiNotModifiedException("Profession is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public ProfessionDTO update(ProfessionDTO profession) throws ApiInternalServerErrorExeption {
		try {
			if(!professionRepository.existsById(profession.getIdProfession())){
				throw new ApiNotFoundException("Profession does not exist");
			}
			Profession professionSaved = professionRepository.save(ProfessionMapper.ProfessionDtoToProfession(profession));
			if(professionSaved!=null){
				return ProfessionMapper.ProfessionToProfessionDTO(professionSaved);
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

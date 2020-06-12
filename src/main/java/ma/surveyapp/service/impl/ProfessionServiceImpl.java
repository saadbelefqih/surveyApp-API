package ma.surveyapp.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.ProfessionDTO;
import ma.surveyapp.exception.ApiConflictException;
//import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Profession;
import ma.surveyapp.repository.ProfessionRepository;
import ma.surveyapp.service.ProfessionService;
import ma.surveyapp.util.modelmapper.ProfessionMapper;
@Service
@RequiredArgsConstructor
//@Slf4j
public class ProfessionServiceImpl implements ProfessionService{
	private final ProfessionRepository professionRepository;

	@Override
	public List<ProfessionDTO> getAll(String name,int page,int size) {
			List<ProfessionDTO> lists= new ArrayList<ProfessionDTO>();
			lists=professionRepository.findByFullTextSearch(name,(PageRequest.of(page, size)))
					.stream().map(p->{
						return ProfessionMapper.ProfessionToProfessionDTO(p);
					}).collect(Collectors.toList());
			if(!lists.isEmpty()){
			return lists;}
			throw new ApiNotFoundException("No result founded");
		
	}

	@Override
	public ProfessionDTO getByID(Long idTravail){
			if(professionRepository.existsById(idTravail)){
				return ProfessionMapper.ProfessionToProfessionDTO(professionRepository.getOne(idTravail));
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public ProfessionDTO save(ProfessionDTO profession){
			if(professionRepository.findByLibelleProfessionIgnoreCase(profession.getLibelleProfession())!=null){
				throw new ApiConflictException("Profession already exist");
			}
			profession.setIdProfession(null);
			Profession professionSaved = professionRepository.save(ProfessionMapper.ProfessionDtoToProfession(profession));
			if(professionSaved!=null){
				return ProfessionMapper.ProfessionToProfessionDTO(professionSaved);
			}
			throw new ApiNotModifiedException("Profession is unsuccessfully inserted");
	}

	@Override
	public ProfessionDTO update(ProfessionDTO profession){
			if(!professionRepository.existsById(profession.getIdProfession())){
				throw new ApiNotFoundException("Profession does not exist");
			}
			Profession professionSaved = professionRepository.save(ProfessionMapper.ProfessionDtoToProfession(profession));
			if(professionSaved!=null){
				return ProfessionMapper.ProfessionToProfessionDTO(professionSaved);
			}
			throw new ApiNotModifiedException("Profession is unsuccessfully updated");
	}

	@Override
	public void delete(Long idTravail){
			professionRepository.deleteById(idTravail);
	}
	

}

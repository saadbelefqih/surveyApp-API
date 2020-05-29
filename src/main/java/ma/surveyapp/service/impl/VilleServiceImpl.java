package ma.surveyapp.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.VilleDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Ville;
import ma.surveyapp.repository.VilleRepository;
import ma.surveyapp.service.VilleService;
import ma.surveyapp.util.modelmapper.VilleMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class VilleServiceImpl implements VilleService{
	
	private final VilleRepository villeRepository;

	@Override
	public List<VilleDTO> getAll(int page,int size) throws ApiInternalServerErrorExeption{
		try {
			List<VilleDTO> lists=villeRepository.findAll(PageRequest.of(page, size))
					.stream().map(ville->{
					return VilleMapper.villeToVilleDTO(ville);
					}).collect(Collectors.toList());
			return lists;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public VilleDTO getByID(Long idville) throws ApiNoContentException,ApiInternalServerErrorExeption  {
		try{
			if(villeRepository.existsById(idville)){
				return VilleMapper.villeToVilleDTO(villeRepository.getOne(idville));
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
		
		
	}

	@Override
	public VilleDTO save(VilleDTO ville) throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(villeRepository.findByLibelleVilleIgnoreCase(ville.getLibelleVille())!=null){
				throw new ApiConflictException("Ville already exist");
			}
			Ville v = villeRepository.save(VilleMapper.VilleDtoToVille(ville));
			if(v!=null){
				return VilleMapper.villeToVilleDTO(v);
			}
			throw new ApiNotModifiedException("Ville is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
		
		
	}

	@Override
	public VilleDTO update(VilleDTO ville)throws ApiNotFoundException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try {
			if(!villeRepository.existsById(ville.getIdVille())){
				throw new ApiNotFoundException("Ville does not exist");
			}
			Ville v = villeRepository.save(VilleMapper.VilleDtoToVille(ville));
			if(v!=null){
				return VilleMapper.villeToVilleDTO(v);
			}
			throw new ApiNotModifiedException("Ville is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idville) throws ApiInternalServerErrorExeption{
		try {
			villeRepository.deleteById(idville);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public List<VilleDTO> getByLibelle(String libelle,int page, int size) throws ApiInternalServerErrorExeption {
		try {
			List<VilleDTO> lists=villeRepository.findByLibelleVilleIgnoreCaseContaining(libelle,PageRequest.of(page, size))
					.stream().map(ville->{
					return VilleMapper.villeToVilleDTO(ville);
					}).collect(Collectors.toList());
			return lists;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}


	

}

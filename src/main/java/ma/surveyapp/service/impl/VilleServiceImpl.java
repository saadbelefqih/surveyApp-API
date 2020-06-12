package ma.surveyapp.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.VilleDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Ville;
import ma.surveyapp.repository.VilleRepository;
import ma.surveyapp.service.VilleService;
import ma.surveyapp.util.modelmapper.VilleMapper;

@Service
@RequiredArgsConstructor
//@Slf4j
public class VilleServiceImpl implements VilleService{
	
	private final VilleRepository villeRepository;
	
	@Override
	public Page<VilleDTO> getByLibelle(String libelle,int page, int size) {
		List<VilleDTO> lists= new ArrayList<>();
		lists=villeRepository.findByLibelleVilleIgnoreCaseContaining(libelle,PageRequest.of(page, size))
					.stream().map(ville->{
					return VilleMapper.villeToVilleDTO(ville);
					}).collect(Collectors.toList());
			if(!lists.isEmpty()){
				return  new PageImpl<>(lists, PageRequest.of(page, size), lists.size());
			}
			throw new ApiNotFoundException("No result founded");
	}
	/*
	@Override
	public List<VilleDTO> getAll(int page,int size){
			List<VilleDTO> lists=villeRepository.findAll(PageRequest.of(page, size))
					.stream().map(ville->{
					return VilleMapper.villeToVilleDTO(ville);
					}).collect(Collectors.toList());
			if(!lists.isEmpty()){
				return lists;
			}
			throw new ApiNotFoundException("No result founded");
		
	}*/

	@Override
	public VilleDTO getByID(Long idville){
			if(villeRepository.existsById(idville)){
				return VilleMapper.villeToVilleDTO(villeRepository.getOne(idville));
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public VilleDTO save(VilleDTO ville)  {
			if(villeRepository.findByLibelleVilleIgnoreCase(ville.getLibelleVille())!=null){
				throw new ApiConflictException("Ville already exist");
			}
			ville.setIdVille(null);
			Ville v = villeRepository.save(VilleMapper.VilleDtoToVille(ville));
			if(v!=null){
				return VilleMapper.villeToVilleDTO(v);
			}
			throw new ApiNotModifiedException("Ville is unsuccessfully inserted");	
	}

	@Override
	public VilleDTO update(Long idExistVille,VilleDTO ville){
			if(ville.getIdVille()!=idExistVille){
				throw new ApiConflictException("Error Ville");
			}
			if(!villeRepository.existsById(ville.getIdVille())){
				throw new ApiNotFoundException("Ville does not exist");
			}
			Ville v = villeRepository.save(VilleMapper.VilleDtoToVille(ville));
			if(v!=null){
				return VilleMapper.villeToVilleDTO(v);
			}
			throw new ApiNotModifiedException("Ville is unsuccessfully inserted");
	}

	@Override
	public void delete(Long idville){
			villeRepository.deleteById(idville);
		
	}

}

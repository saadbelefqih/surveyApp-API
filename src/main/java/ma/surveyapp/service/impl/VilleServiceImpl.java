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
import ma.surveyapp.model.Ville;
import ma.surveyapp.repository.VilleRepository;
import ma.surveyapp.service.VilleService;

@Service
@RequiredArgsConstructor
@Slf4j
public class VilleServiceImpl implements VilleService{
	
	private final VilleRepository villeRepository;

	@Override
	public Page<Ville> getAll(int page,int size) throws ApiInternalServerErrorExeption{
		try {
			
			return villeRepository.findAll(PageRequest.of(page, size));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Ville getByID(Long idville) throws ApiNoContentException,ApiInternalServerErrorExeption  {
		try{
			if(villeRepository.existsById(idville)){
				return villeRepository.getOne(idville);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
		
		
	}

	@Override
	public Ville save(Ville ville) throws ApiConflictException,ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(villeRepository.findByLibelleVilleIgnoreCase(ville.getLibelleVille())!=null){
				throw new ApiConflictException("Ville already exist");
			}
			Ville v = villeRepository.save(ville);
			if(v!=null){
				return v;
			}
			throw new ApiNotModifiedException("Ville is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
		
		
	}

	@Override
	public Ville update(Ville ville)throws ApiNotFoundException,ApiNotModifiedException,ApiInternalServerErrorExeption {
		try {
			if(!villeRepository.existsById(ville.getIdVille())){
				throw new ApiNotFoundException("Ville does not exist");
			}
			Ville v = villeRepository.save(ville);
			if(v!=null){
				return v;
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
	public Page<Ville> getByLibelle(String libelle,int page, int size) throws ApiInternalServerErrorExeption {
		try {
			return villeRepository.findByLibelleVilleIgnoreCaseContaining(libelle,PageRequest.of(page, size));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}


	

}

package ma.surveyapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.PublicCible;
import ma.surveyapp.repository.PublicCibleReposirory;
import ma.surveyapp.service.PublicCibleService;
@Service
@RequiredArgsConstructor
@Slf4j
public class PublicCibleServiceImpl implements PublicCibleService{
	
	private final PublicCibleReposirory publicCibleReposirory;

	@Override
	public List<PublicCible> getAll() throws ApiInternalServerErrorExeption {
		try {
			if(!CollectionUtils.isEmpty(publicCibleReposirory.findAll())){
				throw new ApiNoContentException("No result founded");
			}
			return publicCibleReposirory.findAll();
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public PublicCible getByID(Long idPublic) throws ApiInternalServerErrorExeption {
		try{
			if(publicCibleReposirory.existsById(idPublic)){
				return publicCibleReposirory.getOne(idPublic);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public PublicCible save(PublicCible p) throws ApiInternalServerErrorExeption {
		try {
			if(publicCibleReposirory.findByLibellePublicIgnoreCase(p.getLibellePublic())!=null){
				throw new ApiConflictException("Public already exist");
			}
			PublicCible publicSaved = publicCibleReposirory.save(p);
			if(publicSaved!=null){
				return publicSaved;
			}
			throw new ApiNotModifiedException("Public is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public PublicCible update(PublicCible p) throws ApiInternalServerErrorExeption {
		try {
			if(!publicCibleReposirory.existsById(p.getIdPublic())){
				throw new ApiNotFoundException("Public does not exist");
			}
			PublicCible publicSaved = publicCibleReposirory.save(p);
			if(publicSaved!=null){
				return publicSaved;
			}
			throw new ApiNotModifiedException("Public is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idPublic) throws ApiInternalServerErrorExeption {
		try {
			publicCibleReposirory.deleteById(idPublic);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

}

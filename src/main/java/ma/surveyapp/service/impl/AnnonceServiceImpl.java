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
import ma.surveyapp.model.Annonce;
import ma.surveyapp.repository.AnnonceRepository;
import ma.surveyapp.repository.PublicCibleReposirory;
import ma.surveyapp.service.AnnonceService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnnonceServiceImpl implements AnnonceService{
	private final AnnonceRepository annonceRepository;
	private final PublicCibleReposirory publicCibleReposirory;

	@Override
	public List<Annonce> getAll() throws ApiInternalServerErrorExeption, ApiNoContentException {
		try {
			if(!CollectionUtils.isEmpty(annonceRepository.findAll())){
				throw new ApiNoContentException("No result founded");
			}
			return annonceRepository.findAll();
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Annonce getByID(Long idAnnonce) throws ApiNoContentException, ApiInternalServerErrorExeption {
		try{
			if(annonceRepository.existsById(idAnnonce)){
				return annonceRepository.getOne(idAnnonce);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Annonce save(Annonce annonce)
			throws ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(annonceRepository.findByLibelleAnnonceAndDateAjout(annonce.getLibelleAnnonce(),annonce.getDateAjout())!=null){
				throw new ApiConflictException("Annonce already exist");
			}
			Annonce annonceSaved = annonceRepository.save(annonce);
			if(annonceSaved!=null){
				return annonceSaved;
			}
			throw new ApiNotModifiedException("Annonce is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public void addPublicCible(Long idAnnonce,Long idpublic)
			throws ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiConflictException("Annonce already exist");
			}
			if(!publicCibleReposirory.existsById(idpublic)){
				throw new ApiConflictException("Public Cible already exist");
			}
			Annonce annonceFinded = annonceRepository.getOne(idAnnonce);
			annonceFinded.getPublics().add(publicCibleReposirory.getOne(idAnnonce));
			Annonce annonceSaved=annonceRepository.save(annonceFinded);
			if(annonceSaved==null){
			throw new ApiNotModifiedException("Annonce is unsuccessfully inserted");}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public void deletePublic(Long idAnnonce,Long idpublic) throws ApiNoContentException,ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiConflictException("Annonce already exist");
			}
			if(!publicCibleReposirory.existsById(idpublic)){
				throw new ApiConflictException("Public Cible already exist");
			}
			Annonce annonceFinded = annonceRepository.getOne(idAnnonce);
			annonceFinded.getPublics().remove(publicCibleReposirory.getOne(idAnnonce));
			Annonce annonceSaved=annonceRepository.save(annonceFinded);
			if(annonceSaved==null){
			throw new ApiNotModifiedException("Annonce is unsuccessfully inserted");}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
		
	}

	@Override
	public Annonce update(Annonce annonce)
			throws ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(!annonceRepository.existsById(annonce.getIdAnnonce())){
				throw new ApiNotFoundException("Annonce does not exist");
			}
			Annonce annonceSaved = annonceRepository.save(annonce);
			if(annonceSaved!=null){
				return annonceSaved;
			}
			throw new ApiNotModifiedException("Annonce is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idAnnonce) throws ApiInternalServerErrorExeption {
		try {
			annonceRepository.deleteById(idAnnonce);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}
	

}

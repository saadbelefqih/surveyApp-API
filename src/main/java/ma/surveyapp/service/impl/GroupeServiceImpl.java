package ma.surveyapp.service.impl;

//import java.util.List;
//
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import ma.surveyapp.exception.ApiBadRequestException;
//import ma.surveyapp.exception.ApiConflictException;
//import ma.surveyapp.exception.ApiInternalServerErrorExeption;
//import ma.surveyapp.exception.ApiNoContentException;
//import ma.surveyapp.exception.ApiNotFoundException;
//import ma.surveyapp.exception.ApiNotModifiedException;
//import ma.surveyapp.model.Groupe;
//import ma.surveyapp.repository.GroupeRepository;
import ma.surveyapp.service.GroupeService;
//@Service
//@RequiredArgsConstructor
//@Slf4j
public class GroupeServiceImpl implements GroupeService{
	/*
	private final GroupeRepository groupeRepository;

	@Override
	public List<Groupe> getAll() throws ApiInternalServerErrorExeption {
		try {
			if(!CollectionUtils.isEmpty(groupeRepository.findAll())){
				throw new ApiNoContentException("No result founded");
			}
			return groupeRepository.findAll();
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Groupe getByID(Long idGroupe) throws ApiInternalServerErrorExeption {
		try{
			if(groupeRepository.existsById(idGroupe)){
				return groupeRepository.getOne(idGroupe);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Groupe save(Groupe groupe) throws ApiInternalServerErrorExeption {
		try {
			if(groupeRepository.findByIntituleGroupeIgnoreCase(groupe.getIntituleGroupe())!=null){
				throw new ApiConflictException("Groupe already exist");
			}
			Groupe groupeSaved = groupeRepository.save(groupe);
			if(groupeSaved!=null){
				return groupeSaved;
			}
			throw new ApiNotModifiedException("Groupe is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}



	@Override
	public Groupe update(Groupe groupe) throws ApiInternalServerErrorExeption {
		try {
			if(!groupeRepository.existsById(groupe.getIdGroupe())){
				throw new ApiNotFoundException("Groupe does not exist");
			}
			Groupe groupeSaved = groupeRepository.save(groupe);
			if(groupeSaved!=null){
				return groupeSaved;
			}
			throw new ApiNotModifiedException("Groupe is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idGroupe) throws ApiInternalServerErrorExeption {
		try {
			if(groupeRepository.existsById(idGroupe) && !CollectionUtils.isEmpty(groupeRepository.findById(idGroupe).get().getDemandes())){
				throw  new ApiBadRequestException("erreur de suppression ");
			}
			groupeRepository.deleteById(idGroupe);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
		
	}

*/

}

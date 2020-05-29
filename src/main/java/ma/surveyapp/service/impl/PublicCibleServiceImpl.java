package ma.surveyapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.AnnonceDTO;
import ma.surveyapp.dto.PublicCibleDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.PublicCible;
import ma.surveyapp.repository.PublicCibleReposirory;
import ma.surveyapp.service.PublicCibleService;
import ma.surveyapp.util.modelmapper.AnnonceMapper;
import ma.surveyapp.util.modelmapper.PublicCibleMapper;
@Service
@RequiredArgsConstructor
@Slf4j
public class PublicCibleServiceImpl implements PublicCibleService{
	
	private final PublicCibleReposirory publicCibleReposirory;

	@Override
	public List<PublicCibleDTO> getAll() throws ApiInternalServerErrorExeption {
		try {
			
			return publicCibleReposirory.findAll().stream().map(pc->{return PublicCibleMapper.publicCibleToPublicCibleDTO(pc);}).collect(Collectors.toList());
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public PublicCibleDTO getByID(Long idPublic) throws ApiInternalServerErrorExeption {
		try{
			if(publicCibleReposirory.existsById(idPublic)){
				return PublicCibleMapper.publicCibleToPublicCibleDTO(publicCibleReposirory.getOne(idPublic));
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public PublicCibleDTO save(PublicCibleDTO pCDTO) throws ApiInternalServerErrorExeption {
		try {
			if(publicCibleReposirory.findByLibellePublicIgnoreCase(pCDTO.getLibelle())!=null){
				throw new ApiConflictException("Public Cible already exist");
			}
			pCDTO.setId(null);
			PublicCible publicSaved = publicCibleReposirory.save(PublicCibleMapper.publicCibleDtoToPublicCible(pCDTO));
			if(publicSaved!=null){
				return PublicCibleMapper.publicCibleToPublicCibleDTO(publicSaved);
			}
			throw new ApiNotModifiedException("Public is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public PublicCibleDTO update(PublicCibleDTO pCDTO) throws ApiInternalServerErrorExeption {
		try {
			if(!publicCibleReposirory.existsById(pCDTO.getId())){
				throw new ApiNotFoundException("Public does not exist");
			}
			PublicCible  pc = PublicCibleMapper.publicCibleDtoToPublicCible(pCDTO);
			pc.setAnnonces(publicCibleReposirory.findById(pCDTO.getId()).get().getAnnonces());
			PublicCible publicSaved = publicCibleReposirory.save(pc);
			if(publicSaved!=null){
				return PublicCibleMapper.publicCibleToPublicCibleDTO(publicSaved);
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

	@Override
	public List<AnnonceDTO> getAnnoncesByPublicCible(Long idPublic) throws ApiInternalServerErrorExeption {
		try{
			if(publicCibleReposirory.existsById(idPublic)){
				return publicCibleReposirory.findById(idPublic).get().getAnnonces()
						.stream().map(an->{ return AnnonceMapper.annonceToAnnonceDTO(an);}).collect(Collectors.toList());}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

}

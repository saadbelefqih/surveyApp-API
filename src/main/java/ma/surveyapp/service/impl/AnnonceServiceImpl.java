package ma.surveyapp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.dto.AnnonceDTO;
import ma.surveyapp.dto.PublicCibleDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Annonce;
import ma.surveyapp.model.PublicCible;
import ma.surveyapp.repository.AnnonceRepository;
import ma.surveyapp.repository.PublicCibleReposirory;
import ma.surveyapp.service.AnnonceService;
import ma.surveyapp.util.modelmapper.AnnonceMapper;
import ma.surveyapp.util.modelmapper.PublicCibleMapper;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AnnonceServiceImpl implements AnnonceService{
	private final AnnonceRepository annonceRepository;
	private final PublicCibleReposirory publicCibleReposirory;

	@Override
	public List<AnnonceDTO> getAll() throws ApiInternalServerErrorExeption {
		try {
			List<AnnonceDTO> annonces=new ArrayList<AnnonceDTO>();
			annonces= annonceRepository.findAll().stream().
					map(annonce->{return AnnonceMapper.annonceToAnnonceDTO(annonce);}).collect(Collectors.toList());
			return annonces;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public AnnonceDTO getByID(Long idAnnonce) throws ApiNoContentException, ApiInternalServerErrorExeption {
		try{
			if(annonceRepository.existsById(idAnnonce)){
				return AnnonceMapper.annonceToAnnonceDTO(annonceRepository.getOne(idAnnonce));
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public AnnonceDTO save(AnnonceDTO annonceDTO)
			throws ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(annonceRepository.findByLibelleAnnonceAndDateDebutPublication(annonceDTO.getLibelle(),annonceDTO.getDateDebutTravail())!=null){
				throw new ApiConflictException("Annonce already exist");
			}
			Annonce annonce=AnnonceMapper.annonceDtoToAnnonce(annonceDTO);
			annonce.setIdAnnonce(null);
			Set<PublicCible> publicsCible=new HashSet<>();
			publicsCible.addAll(annonce.getPublics());
			annonce.setDemandes(new HashSet<>());
			annonce.setPublics(new HashSet<>());
			annonce.setGroupes(new HashSet<>());
			Annonce annonceSaved = annonceRepository.save(annonce);
			if(annonceSaved!=null){
				addPublicCible(annonceSaved,publicsCible);
				return AnnonceMapper.annonceToAnnonceDTO(annonceSaved);
			}
			throw new ApiNotModifiedException("Annonce is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}
	@Override
	public AnnonceDTO addPublicCibleToAnnonce(Long idAnnonce,Set<PublicCibleDTO> publicsCible) throws ApiInternalServerErrorExeption{	
		try {
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiNotFoundException("Annonce not existe");
			}
			Set<PublicCible> cibles = new HashSet<>();
			cibles= publicsCible.stream().map(p->{
				return PublicCibleMapper.publicCibleDtoToPublicCible(p);
			}).collect(Collectors.toSet());
			return AnnonceMapper.annonceToAnnonceDTO(addPublicCible(annonceRepository.findById(idAnnonce).get(),cibles));
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	
	private Annonce addPublicCible(Annonce annonce,Set<PublicCible> publicsCible)
			throws ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			publicsCible.forEach(p->{
				if(publicCibleReposirory.findByLibellePublicIgnoreCase(p.getLibellePublic())==null){
				p.setAnnonces(new HashSet<>());
				PublicCible pC=publicCibleReposirory.save(p);
				p=pC;}
				else{
					p=publicCibleReposirory.findByLibellePublicIgnoreCase(p.getLibellePublic());
				} 
				p.getAnnonces().add(annonce);
				annonce.getPublics().add(p);
			});
			return annonce;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public AnnonceDTO deletePublicPublicCibleFromAnnonce(Long idAnnonce,Set<PublicCibleDTO> publicsCible) throws ApiNoContentException,ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(!annonceRepository.existsById(idAnnonce)){
				throw new ApiConflictException("Annonce already exist");}
			final Set<PublicCible> cibles = publicsCible.stream().map(p->{
				return PublicCibleMapper.publicCibleDtoToPublicCible(p);
			}).collect(Collectors.toSet());
			
			Set<PublicCible> ciblesToDelete= new HashSet<>();
			Annonce annonceFinded = annonceRepository.findById(idAnnonce).get();
			
			System.out.println("****NON****");
			annonceFinded.getPublics().forEach(a->{
				System.out.println("****a=****");
				System.out.println(a.getIdPublic()+" "+a.getLibellePublic());
				cibles.forEach(c->{
					System.out.println("****c=****");
					System.out.println(c.getIdPublic()+" "+c.getLibellePublic());
					if(c.getIdPublic()==a.getIdPublic() && c.getIdPublic()==a.getIdPublic() && c.getIdPublic()!=null && a.getIdPublic()!=null){
						System.out.println("****OUI****");
						ciblesToDelete.add(a);	
					}
				});
				
			});
			if(!ciblesToDelete.isEmpty()){
				annonceFinded.getPublics().removeAll(ciblesToDelete);
				Annonce annonceSaved=annonceRepository.save(annonceFinded);
				if(annonceSaved!=null){
					return AnnonceMapper.annonceToAnnonceDTO(annonceSaved);
				}
			}
			
			throw new ApiNotModifiedException("Annonce is unsuccessfully inserted");
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
		
	}

	@Override
	public AnnonceDTO update(AnnonceDTO annonceDTO)
			throws ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(!annonceRepository.existsById(annonceDTO.getId())){
				throw new ApiNotFoundException("Annonce does not exist");
			}
			Annonce annonce=annonceRepository.findById(annonceDTO.getId()).get();
			annonce.setDateDebutPublication(annonceDTO.getDateDebutPublication());
			annonce.setDateDebutTravail(annonceDTO.getDateDebutTravail());
			annonce.setDateFinPublication(annonceDTO.getDateFinPublication());
			annonce.setDateFinTravail(annonceDTO.getDateFinTravail());
			annonce.setDetailAnnonce(annonceDTO.getDetail());
			annonce.setLibelleAnnonce(annonceDTO.getLibelle());
			annonce.setNbParticipantDemander(annonceDTO.getNbParticipant());
			annonce.setDemandes(annonce.getDemandes());
			annonce.setGroupes(annonce.getGroupes());
			annonce.setPublics(annonce.getPublics());
			Annonce annonceSaved = annonceRepository.save(annonce);
			if(annonceSaved!=null){
				return AnnonceMapper.annonceToAnnonceDTO(annonceSaved);
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

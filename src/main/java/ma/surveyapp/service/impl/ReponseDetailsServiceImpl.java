package ma.surveyapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.model.ReponseDetails;
import ma.surveyapp.repository.ReponseDetailsRepository;
import ma.surveyapp.service.ReponseDetailsService;
@Service
@RequiredArgsConstructor
@Slf4j
public class ReponseDetailsServiceImpl implements ReponseDetailsService{
	private final ReponseDetailsRepository reponseRepository;

	@Override
	public List<ReponseDetails> getAll() throws ApiInternalServerErrorExeption {
		try {
			if(!CollectionUtils.isEmpty(reponseRepository.findAll())){
				throw new ApiNoContentException("No result founded");
			}
			return reponseRepository.findAll();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public ReponseDetails getByID(Long idReponse) throws ApiInternalServerErrorExeption {
		try{
			if(reponseRepository.existsById(idReponse)){
				return reponseRepository.getOne(idReponse);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public ReponseDetails save(ReponseDetails reponse) throws ApiInternalServerErrorExeption {
		return null;
		/*try {
			if(reponse.getDateReponse().compareTo(reponse.getLigneQuestionnaire().getDateLQ())>0){
				throw new ApiNotModifiedException("The time has expired to answer this questionnaire");
			}
			if(reponse.getDateReponse().compareTo(reponse.getLigneQuestionnaire().getDateLQ())<0){
				throw new ApiNotModifiedException("please come back,"+reponse.getLigneQuestionnaire().getDateLQ());
			}
			 
			List<Boolean> reponses = Arrays.asList(reponse.getIsOption1(),reponse.getIsOption2(),reponse.getIsOption3(),reponse.getIsOption4());
			if(reponses.contains(true)){
				ReponseDetails reponseSaved = reponseRepository.save(reponse);
				if(reponseSaved!=null){
					return reponseSaved;
				}
			}
			throw new ApiNotModifiedException("Entity is unsuccessfully inserted");
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}*/
	}


}

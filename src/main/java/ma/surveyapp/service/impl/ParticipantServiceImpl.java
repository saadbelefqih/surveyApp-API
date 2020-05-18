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
import ma.surveyapp.model.Participant;
import ma.surveyapp.repository.ParticipantRepository;
import ma.surveyapp.service.ParticipantService;
@Service
@RequiredArgsConstructor
@Slf4j
public class ParticipantServiceImpl implements ParticipantService{
	private final ParticipantRepository participantRepository;

	@Override
	public List<Participant> getAll() throws ApiInternalServerErrorExeption, ApiNoContentException {
		try {
			if(CollectionUtils.isEmpty(participantRepository.findAll())){
				throw new ApiNoContentException("No result founded");
			}
			return participantRepository.findAll();
		} catch (Exception e){
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Participant getById(Long idParticipant) throws ApiNoContentException, ApiInternalServerErrorExeption {
		try{
			if(participantRepository.existsById(idParticipant)){
				return participantRepository.getOne(idParticipant);
			}
			throw new ApiNoContentException("No result founded");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public Participant save(Participant participant)
			throws ApiConflictException, ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(participantRepository.findByNomParticipantIgnoreCaseAndPrenomParticipantIgnoreCase(participant.getNomParticipant(),participant.getPrenomParticipant())!=null){
				throw new ApiConflictException("Participant already exist");
			}
			Participant participantSaved = participantRepository.save(participant);
			if(participantSaved!=null){
				return participantSaved;
			}
			throw new ApiNotModifiedException("Participant is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
			
		}
	}

	@Override
	public Participant update(Participant participant)
			throws ApiNotFoundException, ApiNotModifiedException, ApiInternalServerErrorExeption {
		try {
			if(!participantRepository.existsById(participant.getIdParticipant())){
				throw new ApiNotFoundException("Participant does not exist");
			}
			Participant participantSaved = participantRepository.save(participant);
			if(participantSaved!=null){
				return participantSaved;
			}
			throw new ApiNotModifiedException("Participant is unsuccessfully inserted");
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

	@Override
	public void delete(Long idParticipant) throws ApiInternalServerErrorExeption {
		try {
			participantRepository.deleteById(idParticipant);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new ApiInternalServerErrorExeption("Internal Server Error");
		}
	}

}

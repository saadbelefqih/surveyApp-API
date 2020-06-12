package ma.surveyapp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ma.surveyapp.dto.ParticipantRequestDTO;
import ma.surveyapp.dto.ParticipantResponseDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Participant;
import ma.surveyapp.repository.ParticipantRepository;
import ma.surveyapp.repository.ProfessionRepository;
import ma.surveyapp.repository.VilleRepository;
import ma.surveyapp.service.ParticipantService;
import ma.surveyapp.util.modelmapper.ParticipantMapper;
@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService{
	
	private final ParticipantRepository participantRepository;
	private final VilleRepository villeRepository;
	private final ProfessionRepository professionRepository;

	@Override
	public List<ParticipantResponseDTO> getAll(int page,int size){
			List<ParticipantResponseDTO> list = new ArrayList<ParticipantResponseDTO>();
			list= participantRepository.findAll(PageRequest.of(page, size))
					.stream().map(part->{
						return ParticipantMapper.ParticipantToParticipantResponseDto(part);
					}).collect(Collectors.toList());
			if(!list.isEmpty()){
				return list;
			}
			throw new ApiNotFoundException("No result founded");
	}

	@Override
	public ParticipantResponseDTO getById(Long idParticipant) {
			if(participantRepository.existsById(idParticipant)){
				return ParticipantMapper.ParticipantToParticipantResponseDto(participantRepository.getOne(idParticipant));}
			throw new ApiNotFoundException("No result founded");
		
	}

	@Override
	public ParticipantResponseDTO save(ParticipantRequestDTO participantRequestDTO){
			if(participantRepository.findByNomParticipantIgnoreCaseAndPrenomParticipantIgnoreCase(participantRequestDTO.getNom(),participantRequestDTO.getPrenom())!=null){
				throw new ApiConflictException("Participant already exist");
			}
			if(participantRequestDTO.getVille()==null){
				throw new ApiNotFoundException("Ville not founded"); 
			}
			if(!villeRepository.existsById(participantRequestDTO.getVille())){
				throw new ApiNotFoundException("Ville not founded"); 
			}
			if(participantRequestDTO.getProfession()==null){
				throw new ApiNotFoundException("Profession not founded"); 
			}
			if(!professionRepository.existsById(participantRequestDTO.getProfession())){
				throw new ApiNotFoundException("Profession not founded"); 
			}
			//convert Participant request DTO  to Participant persistant
			Participant p = new Participant();
			p.setDemandes(new HashSet<>());
			Participant participantSaved = participantRepository.save(participantDtoToParticipant(p,participantRequestDTO));
			if(participantSaved!=null){
				return ParticipantMapper.ParticipantToParticipantResponseDto(participantSaved);
			}
			throw new ApiNotModifiedException("Participant is unsuccessfully inserted");
		
	}

	@Override
	public ParticipantResponseDTO update(ParticipantRequestDTO participantRequestDTO){
			if(!participantRepository.existsById(participantRequestDTO.getId())){
				throw new ApiNotFoundException("Participant does not exist");
			}
			if(!villeRepository.existsById(participantRequestDTO.getVille())){
				throw new ApiNoContentException("Ville not founded"); 
			}
			if(!professionRepository.existsById(participantRequestDTO.getProfession())){
				throw new ApiNoContentException("Profession not founded"); 
			}
			Participant p = participantRepository.findById(participantRequestDTO.getId()).get();
			Participant participantSaved = participantRepository.save(participantDtoToParticipant(p, participantRequestDTO));
			if(participantSaved!=null){
				return ParticipantMapper.ParticipantToParticipantResponseDto(participantSaved);
			}
			throw new ApiNotModifiedException("Participant is unsuccessfully updated");
	}

	@Override
	public void delete(Long idParticipant){
			participantRepository.deleteById(idParticipant);
	}
	//convert Participant request DTO  to Participant persistant
	private Participant participantDtoToParticipant(Participant participant, ParticipantRequestDTO participantRequestDTO){
		participant.setGenreParticipant(participantRequestDTO.getGenre());
		participant.setAdresseParticipant(participantRequestDTO.getAdresse());
		participant.setDateNaissance(participantRequestDTO.getDateNaissance());
		participant.setEmailParticipant(participantRequestDTO.getEmail());
		participant.setImageURLParticipant(participantRequestDTO.getImageURL());
		participant.setNomParticipant(participantRequestDTO.getNom());
		participant.setPrenomParticipant(participantRequestDTO.getPrenom());
		participant.setTelParticipant(participantRequestDTO.getTel());
		participant.setVille(villeRepository.findById(participantRequestDTO.getVille()).get());
		participant.setProfession(professionRepository.findById(participantRequestDTO.getProfession()).get());
		return participant;
	}

}

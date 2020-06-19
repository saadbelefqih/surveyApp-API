package ma.surveyapp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ma.surveyapp.dto.ParticipantRequestDTO;
import ma.surveyapp.dto.ParticipantResponseDTO;
import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotFoundException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Participant;
import ma.surveyapp.model.Responsable;
import ma.surveyapp.model.RoleApp;
import ma.surveyapp.model.UserApp;
import ma.surveyapp.repository.ParticipantRepository;
import ma.surveyapp.repository.ProfessionRepository;
import ma.surveyapp.repository.ResponsableRepository;
import ma.surveyapp.repository.RoleAppRepository;
import ma.surveyapp.repository.VilleRepository;
import ma.surveyapp.service.AccountService;
import ma.surveyapp.util.modelmapper.ParticipantMapper;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{
	private final RoleAppRepository roleAppRepository;
	private final ParticipantRepository participantRepository;
	private final VilleRepository villeRepository;
	private final ProfessionRepository professionRepository;
	private final ResponsableRepository responsableRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Boolean singUpParticipant(ParticipantRequestDTO participantRequestDTO) {
		if(participantRepository.findByNomIgnoreCaseAndPrenomIgnoreCase(participantRequestDTO.getNom(),participantRequestDTO.getPrenom())!=null){
			throw new ApiConflictException("Participant already exist");}
		
		if(participantRepository.findByUsername(participantRequestDTO.getUsername()).isPresent()){
			throw new ApiConflictException("UserName already exist");}
		
		if(participantRequestDTO.getVille()==null){
			throw new ApiNotFoundException("Ville not founded");}
		
		if(!villeRepository.existsById(participantRequestDTO.getVille())){
			throw new ApiNotFoundException("Ville not founded");}
		
		if(participantRequestDTO.getProfession()==null){
			throw new ApiNotFoundException("Profession not founded");}
		
		if(!professionRepository.existsById(participantRequestDTO.getProfession())){
			throw new ApiNotFoundException("Profession not founded");}
		if( participantRequestDTO.getUsername()==null || participantRequestDTO.getPassword().equals(null) || participantRequestDTO.getRepassword()==null)
		{throw new ApiConflictException("Data Hasn't correct");}
		
		if(!participantRequestDTO.getPassword().equals(participantRequestDTO.getRepassword()))
		{throw new ApiConflictException("Please confirm your password");}
		//convert Participant request DTO  to Participant persistant
		Participant p = new Participant();
		p.setDemandes(new HashSet<>());
		p.setRoles(new ArrayList<>());
		p.setActive(true);
		Participant participantSaved = participantRepository.save(participantDtoToParticipant(p,participantRequestDTO));
		if(participantSaved!=null){
			addRoleToUser(participantSaved.getUsername(),"PARTICIPANT",true);
			return true;}
		
		return false;	
	}
	
	@Override
	public RoleApp saveRole(RoleApp role) {
		if(role.equals(null))
		{throw new ApiNotFoundException("Role not Found");}
		return roleAppRepository.save(role);
	}
	

	@Override
	public void addRoleToUser(String username, String rolename,Boolean isParticipant) {
		if((username.equals(null)) || (rolename.equals(null)) || (roleAppRepository.findByName(rolename).equals(null)))
		{
			throw new ApiNotFoundException("username not Found "+username+" Or Role "+rolename);
		}

		RoleApp role =roleAppRepository.findByName(rolename);
		if(isParticipant && participantRepository.findByUsername(username).isPresent()){
			Participant participant = participantRepository.findByUsername(username).get();
			participant.getRoles().add(role);
		}
		if(isParticipant && responsableRepository.findByUsername(username).isPresent()){
		Responsable responsable=responsableRepository.findByUsername(username).get();
		responsable.getRoles().add(role);
		}
	}

	@Override
	public UserApp saveResponsable(Responsable responsable, String confirmedPassword) {
		if(responsable.getUsername().equals(null) || responsable.getPassword().equals(null) || confirmedPassword.equals(null))
		{throw new ApiNoContentException("Username or Password or ConfirmedPassword null");}
		if(!responsable.getPassword().equals(confirmedPassword))
		{throw new ApiConflictException("Please confirm your password");}
		responsable.setPassword(bCryptPasswordEncoder.encode(responsable.getPassword()));
		responsable.setActive(true);

		if(!responsableRepository.save(responsable).equals(null))
		{
			addRoleToUser(responsable.getUsername(),"RESPONSABLE",false);
			return responsable;
		}
		throw new ApiNotFoundException("Erreur");
	}
	
	//convert Participant request DTO  to Participant persistant
	private Participant participantDtoToParticipant(Participant participant, ParticipantRequestDTO participantRequestDTO){
		participant.setGenre(participantRequestDTO.getGenre());
		participant.setAdresse(participantRequestDTO.getAdresse());
		participant.setDatenais(participantRequestDTO.getDateNaissance());
		participant.setUsername(participantRequestDTO.getUsername());
		participant.setPassword(participantRequestDTO.getPassword());
		participant.setEmail(participantRequestDTO.getEmail());
		participant.setPhoto(participantRequestDTO.getImageURL());
		participant.setNom(participantRequestDTO.getNom());
		participant.setPrenom(participantRequestDTO.getPrenom());
		participant.setNtel(participantRequestDTO.getTel());
		participant.setPassword(bCryptPasswordEncoder.encode(participantRequestDTO.getPassword()));
		participant.setVille(villeRepository.findById(participantRequestDTO.getVille()).get());
		participant.setProfession(professionRepository.findById(participantRequestDTO.getProfession()).get());
		return participant;
	}

	@Override
	public List<ParticipantResponseDTO> getParticipants(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParticipantResponseDTO getById(Long idParticipant) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParticipantResponseDTO updateParticipant(ParticipantRequestDTO participantRequestDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteParticipant(Long idParticipant) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}

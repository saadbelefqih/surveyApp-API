package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.dto.ParticipantRequestDTO;
import ma.surveyapp.dto.ParticipantResponseDTO;
import ma.surveyapp.model.Responsable;
import ma.surveyapp.model.RoleApp;
import ma.surveyapp.model.UserApp;

public interface AccountService {
	

	public Boolean singUpParticipant(ParticipantRequestDTO ParticipantRequestDTO);
	
	List<ParticipantResponseDTO> getParticipants(int page,int size);
	ParticipantResponseDTO getById(Long idParticipant);
	
	ParticipantResponseDTO updateParticipant(ParticipantRequestDTO participantRequestDTO);
	void deleteParticipant(Long idParticipant);
	
	
	public UserApp saveResponsable(Responsable responsable,String confirmedPassword);
	
	public RoleApp saveRole(RoleApp role);
	
	
	public void addRoleToUser(String username,String rolename,Boolean isParticipant);

}

package ma.surveyapp.util.modelmapper;

import ma.surveyapp.dto.RoleDTO;
import ma.surveyapp.model.RoleApp;

public class RoleMapper {
	
	public static RoleDTO roleToRoleDTO(RoleApp roleApp){
		RoleDTO roleDTO = new RoleDTO();
		roleDTO.setIdRole(roleApp.getId());
		roleDTO.setRole(roleApp.getName());
		return roleDTO;
	}
	
	public static RoleApp roleDtoToRole(RoleDTO roleDTO){
		RoleApp role = new RoleApp();
		role.setId(roleDTO.getIdRole());
		role.setName(roleDTO.getRole());
		return role;
	}

}

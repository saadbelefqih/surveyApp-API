package ma.surveyapp.dto;

import java.util.Date;
import java.util.Set;

import lombok.Data;
@Data
public class ParticipantResponseDTO {

	private Long id;
	//true pour Homme False pour Femme
	private Boolean genre;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String imageURL;
	private String email;
	private String tel;
	private String adresse;
	private String username;
	private String password;
	private VilleDTO ville;
    private ProfessionDTO profession;
    private Set<RoleDTO> roles;

}

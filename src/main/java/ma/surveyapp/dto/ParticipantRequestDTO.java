package ma.surveyapp.dto;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.Data;
@Data
public class ParticipantRequestDTO {
	private Long id;
	//true pour Homme False pour Femme
	private Boolean genre;
	@NotBlank
	@Size(min = 3, max = 40)
	private String nom;
	@NotBlank
    @Size(max = 40)
	private String prenom;
	private Date dateNaissance;
	private String imageURL;
	@NotBlank
    @Size(max = 40)
    @Email
	private String email;
	@NotBlank
	@Size(min = 10, max = 13,message="invalide Tel")
	private String tel;
	private String adresse;
	private Long ville;
    private Long profession;

}

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
    @Size(max = 40,min=4)
	private String nom;
	@NotBlank
    @Size(max = 40,min=4)
	private String prenom;
	private Date dateNaissance;
	private String imageURL;
    @Email
	private String email;
	@NotBlank
    @Size(max = 40,min=4)
	private String username;
	@NotBlank
    @Size(max = 20,min=8)
	private String password;
	private String repassword;
	private String tel;
	private String adresse;
	private Long ville;
    private Long profession;

}

package ma.surveyapp.dto;

import java.util.Date;

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
	private VilleDTO ville;
    private ProfessionDTO profession;

}

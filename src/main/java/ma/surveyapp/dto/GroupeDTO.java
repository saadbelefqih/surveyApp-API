package ma.surveyapp.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class GroupeDTO {
	private Long id;
	@NotBlank
    @Size(max = 40,min=4)
	private String intitule;
	private String desc;
	private Integer nbParticipantDemander;
	private Date dateDebuteTravail;
	private Date dateFinTravail;
	private int nbParticipantExist;
}

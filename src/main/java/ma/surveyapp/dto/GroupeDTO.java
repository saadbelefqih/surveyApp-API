package ma.surveyapp.dto;

import java.util.Date;

import lombok.Data;
@Data
public class GroupeDTO {
	private Long id;
	private String intitule;
	private String desc;
	private Integer nbParticipant;
	private Date dateDebuteTravail;
	private Date dateFinTravail;
}

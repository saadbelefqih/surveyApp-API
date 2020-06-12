package ma.surveyapp.dto;

import java.util.Date;
import java.util.Set;


import lombok.Data;
@Data
public class AnnonceDTO {
	private Long id;
	private String libelle;
	private String detail;
	private Integer nbParticipantTheorique;
	private Integer nbParticipantPresent;
	private Integer nbGroupe;
	private Date dateDebutPublication;
	private Date dateFinPublication;
	private Date dateDebutTravail;
	private Date dateFinTravail;
    private Set<PublicCibleDTO> publicsCible;
}

package ma.surveyapp.dto;


import lombok.Data;

@Data
public class DemandeRequestDTO {
	private Long idDemande;
	private Long idParticipant;
	private Long idAnnonce;
	private Long idGroupe;
}

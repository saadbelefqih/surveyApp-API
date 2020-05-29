package ma.surveyapp.dto;


import lombok.Data;

@Data
public class DemandeRequestDTO {
	private Long idParticipant;
	private Long idAnnonce;
	private Long idGroupe;
	private Boolean isPending;
	private Boolean isValide;
	private Boolean isRefuse;
	

}

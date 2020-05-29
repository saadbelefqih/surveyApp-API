package ma.surveyapp.dto;

import java.util.Date;


import lombok.Data;
@Data
public class DemandeResponseDTO {
	private Long idDemande;
	private ParticipantResponseDTO participant;
	private AnnonceDTO annonce;
	private Date dateDemande;
	private Boolean isPending;
	private Boolean isValide;
	private Boolean isRefuse;
	private GroupeDTO groupe;

}

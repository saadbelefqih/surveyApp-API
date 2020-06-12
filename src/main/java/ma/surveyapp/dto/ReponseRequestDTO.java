package ma.surveyapp.dto;

import java.util.Set;

import lombok.Data;
@Data
public class ReponseRequestDTO {
	private Long idReponse;
	private Set<ReponseDetailsDTO> reponsesDetails;
}

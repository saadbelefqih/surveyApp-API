package ma.surveyapp.dto;

import lombok.Data;

@Data
public class ReponseDetailsDTO {
	private Long idRd;
	private Boolean isOption1;
	private Boolean isOption2;
	private Boolean isOption3;
	private Boolean isOption4;
	private Long question;
	private Long reponse;
}

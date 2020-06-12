package ma.surveyapp.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReponseDetailsDTO {
	private Long idRd;
	private Boolean isOption1;
	private Boolean isOption2;
	private Boolean isOption3;
	private Boolean isOption4;
	@NotNull
	private Long question;
	@NotNull
	private Long reponse;
}

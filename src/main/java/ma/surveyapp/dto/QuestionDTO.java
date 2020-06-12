package ma.surveyapp.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class QuestionDTO {
	private Long idQuestion;
	@NotBlank
    @Size(max = 100,min=4)
	private String textQuestion;
	@NotBlank
    @Size(max = 40,min=3)
	private String option1;
	private int totalReponseOption1;
	@NotBlank
    @Size(max = 40,min=3)
	private String option2;
	private int totalReponseOption2;
	private String option3;
	private int totalReponseOption3;
	private String option4;
	private int totalReponseOption4;
}

package ma.surveyapp.dto;


import lombok.Data;
@Data
public class QuestionDTO {
	private Long idQuestion;
	private String textQuestion;
	private String option1;
	private Long totalReponseOption1;
	private String option2;
	private Long totalReponseOption2;
	private String option3;
	private Long totalReponseOption3;
	private String option4;
	private Long totalReponseOption4;
}

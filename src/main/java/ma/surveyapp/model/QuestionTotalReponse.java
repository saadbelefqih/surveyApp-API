package ma.surveyapp.model;

import lombok.Data;

@Data
public class QuestionTotalReponse {
	private Long questionId;
	private Long totalReponse;
	
	public QuestionTotalReponse(Long questionId, Long totalReponse) {
		super();
		this.questionId = questionId;
		this.totalReponse = totalReponse;
	}

	public QuestionTotalReponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

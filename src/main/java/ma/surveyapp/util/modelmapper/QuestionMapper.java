package ma.surveyapp.util.modelmapper;

import ma.surveyapp.dto.QuestionDTO;
import ma.surveyapp.model.Question;

public class QuestionMapper {
	
	public static QuestionDTO QuestionToQuestionDTO(Question question){
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setIdQuestion(question.getIdQuestion());
		questionDTO.setTextQuestion(question.getTextQuestion());
		questionDTO.setOption1(question.getOption1());
		questionDTO.setOption2(question.getOption2());
		questionDTO.setOption3(question.getOption3());
		questionDTO.setOption4(question.getOption4());

		return questionDTO;
	}
	
	
	public static Question QuestionDtoToQuestion(QuestionDTO questionDTO){
		Question question = new Question();
		question.setIdQuestion(null);
		question.setTextQuestion(questionDTO.getTextQuestion());
		question.setOption1(questionDTO.getOption1());
		question.setOption2(questionDTO.getOption2());
		question.setOption3(questionDTO.getOption3());
		question.setOption4(questionDTO.getOption4());
		return question;
	}

}

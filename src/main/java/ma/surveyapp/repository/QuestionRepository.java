package ma.surveyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.surveyapp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{
	Question findByTextQuestionIgnoreCase(String textQuestion);

}

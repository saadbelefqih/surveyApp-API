package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.model.LigneQuestionnaire;

public interface LigneQuestionnaireService {
	
	List<LigneQuestionnaire> getAll() ;
	List<LigneQuestionnaire> getByGroupe(Long idGroupe);
	List<LigneQuestionnaire> getByQuestionnaire(Long idQuestionnaire);
	LigneQuestionnaire getbyID(Long idLigneQuestionnaire);
	LigneQuestionnaire save(LigneQuestionnaire LigneQuestionnaire);
	void delete(Long idLigneQuestionnaire);

}

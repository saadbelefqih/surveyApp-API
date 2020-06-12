package ma.surveyapp.util.modelmapper;
import ma.surveyapp.dto.ReponseDetailsDTO;
import ma.surveyapp.model.ReponseDetails;

public class ReponseDetailsMapper {
	
	public static ReponseDetailsDTO reponseDetailsToReponseDetailsDTO(ReponseDetails reponseDetails){
		ReponseDetailsDTO reponseDetailsDTO = new ReponseDetailsDTO();
		reponseDetailsDTO.setIdRd(reponseDetails.getIdRd());
		reponseDetailsDTO.setIsOption1(reponseDetails.getIsOption1());
		reponseDetailsDTO.setIsOption2(reponseDetails.getIsOption2());
		reponseDetailsDTO.setIsOption3(reponseDetails.getIsOption3());
		reponseDetailsDTO.setIsOption4(reponseDetails.getIsOption4());
		reponseDetailsDTO.setQuestion(reponseDetails.getQuestion().getIdQuestion());
		reponseDetailsDTO.setReponse(reponseDetails.getReponse().getIdReponse());
		return reponseDetailsDTO;
	}

}

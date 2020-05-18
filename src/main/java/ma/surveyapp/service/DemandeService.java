package ma.surveyapp.service;

import java.util.List;

import ma.surveyapp.exception.ApiConflictException;
import ma.surveyapp.exception.ApiInternalServerErrorExeption;
import ma.surveyapp.exception.ApiNoContentException;
import ma.surveyapp.exception.ApiNotModifiedException;
import ma.surveyapp.model.Demande;


public interface DemandeService {
	
	List<Demande> getAllByAnnonce(Long idAnnonce) throws ApiNoContentException,ApiInternalServerErrorExeption;
	List<Demande> getValidateByAnnonce(Long idAnnonce) throws ApiNoContentException,ApiInternalServerErrorExeption;
	List<Demande> getPendingByAnnonce(Long idAnnonce) throws ApiNoContentException,ApiInternalServerErrorExeption;
	List<Demande> getRefuseByAnnonce(Long idAnnonce)throws ApiNoContentException,ApiInternalServerErrorExeption;

	List<Demande> getAllByVolontaire(Long idVolontaire) throws ApiNoContentException,ApiInternalServerErrorExeption;
	List<Demande> getRefuseByVolontaire(Long idVolontaire)throws ApiNoContentException,ApiInternalServerErrorExeption;
	List<Demande> getValidateByVolontaire(Long idVolontaire) throws ApiNoContentException,ApiInternalServerErrorExeption;
	List<Demande> getPendingByVolontaire(Long idVolontaire) throws ApiNoContentException,ApiInternalServerErrorExeption;
	
	Demande getByID(Long idDemande) throws ApiNoContentException,ApiInternalServerErrorExeption;
	Demande save(Demande demande) throws ApiConflictException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	Demande setValidate(Long idDemande) throws ApiNoContentException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	Demande setPending(Long idDemande) throws ApiNoContentException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	Demande setRefuse(Long demande) throws ApiNoContentException,ApiNotModifiedException,ApiInternalServerErrorExeption;
	Demande delete(Long idDemande) throws ApiInternalServerErrorExeption;


	void addDemandeToGroupe(Long idDemande,Long idGroupe) throws ApiNoContentException,ApiNotModifiedException,ApiInternalServerErrorExeption;

}

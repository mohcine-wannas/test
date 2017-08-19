package com.ayouris.tawassol.common.exception;

import javax.servlet.http.HttpServletResponse;

import lombok.Getter;
import com.ayouris.tawassol.common.model.enums.StatutFileAttente;
import com.ayouris.tawassol.common.model.enums.StatutOrdreLivraison;

@Getter
public enum ErrorMessageType {

    UNAUTHORIZED(
            HttpServletResponse.SC_UNAUTHORIZED, 1, "n/a", "Unauthorized: Authentication token was either missing or invalid."),
    UNAUTHORIZED_ORGANIZATION(
            HttpServletResponse.SC_UNAUTHORIZED, 2, "UNAUTHORIZED_ORGANIZATION", "Invalid authorization for organization"),
    REMOTE_USER_NOT_FOUND(
            HttpServletResponse.SC_UNAUTHORIZED, 2, "USER_REMOTE_NOT_FOUND", "REMOTE_USER Attribut not found in the http request"),
    USENAME_INVALIDE(
            HttpServletResponse.SC_UNAUTHORIZED, 2, "USENAME_INVALIDE", "REMOTE_USER Attribut not equals username attribut"),
    USER_NOT_FOUND(
            HttpServletResponse.SC_UNAUTHORIZED, 1, "user_not_found", "Unauthorized: User not found for login : %s."),
    INTERNAL_AUTHEN_ERROR(
            HttpServletResponse.SC_UNAUTHORIZED, 1, "Internal_authentication_error", "Internal authentication service exception"),
    TEMPORARY_TOKEN_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 3, "access_token", "Invalid temporary token"),
    TEMPORARY_TOKEN_NOT_VERIFIED(
            HttpServletResponse.SC_UNAUTHORIZED, 4, "access_token", "permission_denied"),
    ACCESS_TOKEN_MISSING_ENDPOINT(
            HttpServletResponse.SC_UNAUTHORIZED, 5, "access_token", "This endpoint should be signed with user's access token and secret"),
    ACCESS_TOKEN_EXPIRED(
            HttpServletResponse.SC_UNAUTHORIZED, 6, "access_token", "Invalid/expired user token: %s"),
    SIGNATURE_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 7, "signature", "Invalid signature: %s"),
    SIGNATURE_OR_TOKEN_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 8, "access_token", "Invalid signature or token '%s'"),
    TIMESTAMP_UNACCEPTABLE(
            HttpServletResponse.SC_UNAUTHORIZED, 9, "timestamp", "acceptable_timestamps=%s"),
    TIMESTAMP_REFUSED(
            HttpServletResponse.SC_UNAUTHORIZED, 10, "timestamp", "problem=timestamp_refused"),

    SIGNATURE_METHOD_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 12, "signature_method", "Invalid signature method: %s. Fitbit API currently supports: HMAC-SHA1"),
    BODY_HASH_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 13, "body_hash", "Invalid body hash."),
    BODY_HASH_MISSING(
            HttpServletResponse.SC_UNAUTHORIZED, 14, "body_hash", "This endpoint requires a body hash."),

    CLIENT_ID_MISSING(
            HttpServletResponse.SC_UNAUTHORIZED, 15, "client_id", "Empty client key"),
    CLIENT_ID_NOT_FOUND(
            HttpServletResponse.SC_UNAUTHORIZED, 16, "client_id", "Client key not found: %s"),
    CLIENT_ID_TOKEN_MISMATCH(
            HttpServletResponse.SC_UNAUTHORIZED, 17, "Unathorized", "Client authorization data does not match access token"),
    CLIENT_SECRET_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 18, "client_secret", "Client secret invalid"),
    HEADER_FORMAT_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 19, "Authorization", "Invalid authorization header format: %s"),
    ACCESS_TOKEN_MISSING(
            HttpServletResponse.SC_UNAUTHORIZED, 20, "access_token", "Access token required"),
    ACCESS_TOKEN_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 21, "access_token", "Access token invalid or expired: %s"),
    GRANT_TYPE_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 22, "grant_type", "Invalid Grant Type"),
    GRANT_TYPE_UNSUPPORTED(
            HttpServletResponse.SC_UNAUTHORIZED, 23, "grant_type", "Unsupported Grant Type: %s"),
    USER_CREDENTIALS_INVALID(
            HttpServletResponse.SC_UNAUTHORIZED, 24, "invalid user name/password", "Invalid username/password"),
    USER_INACTIF(
    		HttpServletResponse.SC_UNAUTHORIZED, 28, "inactif user", "inactif user"),
    UNAVAILABLE(
            HttpServletResponse.SC_UNAUTHORIZED, 25, "not available", "available only for partners"),
    SC_INTERNAL_SERVER_ERROR(
            HttpServletResponse.SC_INTERNAL_SERVER_ERROR, 26, "INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR"),
    
    TMP_EXCEPTION(
            HttpServletResponse.SC_BAD_REQUEST, 27, "n/a", "tmp exception"),
    USER_ACCOUNT_DISABLED(
            HttpServletResponse.SC_UNAUTHORIZED, 2, "USEr_DISABLED", "User account is disabled"),
    BAD_STATUS(
            HttpServletResponse.SC_BAD_REQUEST, 52, "status", "Unrecognized status value"),
   
    // Roles
    ROLE_RANK_NOT_VALID(
            HttpServletResponse.SC_BAD_REQUEST, 69, "role rank", "Role rank must be greater or equal than user rank"),
    ROLE_NAME_ALREADY_EXISTS(
            HttpServletResponse.SC_CONFLICT, 70, "role name", "Role name already exists"),
    ROLE_ALREADY_USED(
            HttpServletResponse.SC_CONFLICT, 71, "role enabled", "The given role can't be disabled, because it's already used"),
    ROLE_ORGANIZATION_TYPE(
            HttpServletResponse.SC_BAD_REQUEST, 72, "role organization type", "The organization type can't be changed"),

    // Organization
    ACRONYM_ALREADY_EXISTS(
            HttpServletResponse.SC_CONFLICT, 100, "Acronym", "Organisation acronym already exists"),
    AGREEMENT_NUMBER_ALREADY_EXISTS(
            HttpServletResponse.SC_CONFLICT, 101, "Agreement number", "Organisation agreement number already exists"),

	// Referentiel 
	//site
	SITE_CODE_ALREADY_EXISTS(
	            HttpServletResponse.SC_CONFLICT, 110, "Code", "Site code existe dejà"),
    SITE_MISSING_REQUIRED_VALUES(
            HttpServletResponse.SC_CONFLICT, 111, "Client", "un champ obligatoire ou plusieurs sont manquants"),

    //Banque
    BANQUE_MISSING_REQUIRED_VALUES(
            HttpServletResponse.SC_CONFLICT, 121, "Banque", "Un ou plusieurs champs obligatoires sont manquants"),
    //Client
    CLIENT_MISSING_REQUIRED_VALUES( HttpServletResponse.SC_CONFLICT, 131, "Client", "Un ou plusieurs champs obligatoires sont manquants"),
    CLIENT_CODE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 130, "Client", "code client existe dejà"),
    //Chauffeur
    CHAUFFEUR_CIN_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 140, "Chauffeur", "cin du chauffeur existe dejà"),
    CHAUFFEUR_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 141, "Chauffeur", "Un ou plusieurs champs obligatoires sont manquants"),
    //Concessionnaire
    CONCESSIONNAIRE_CODE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 150, "Concessionnaire", "Code concessionnaire existe dejà"),
    CONCESSIONNAIRE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 151, "Concessionnaire", "Un ou plusieurs champs obligatoires sont manquants"),
    CONCESSIONNAIRE_ALREADY_USED(HttpServletResponse.SC_CONFLICT, 150, "Concessionnaire", "Le concessionnaire ne peut pas être supprimer par ce que il est déjà utilisé"),
    //Signataire 
    SIGNATAIRE_CODE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 160, "Signataire", "Code signataire existe dejà"),
    SIGNATAIRE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 161, "Signataire", "Un ou plusieurs champs obligatoires sont manquants"),
    //Affectation
    AFFECTATION_CODE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 170, "Affectation", "Code affectation existe dejà"),
    AFFECTATION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 171, "Affectation", "Un ou plusieurs champs obligatoires sont manquants"),
	//Produit
    PRODUIT_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 180, "Produit", "Un ou plusieurs champs obligatoires sont manquants"),
    PRODUIT_CODE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 181, "Produit", "Code produit existe dejà"),
	 //Transporteur
    TRANSPORTEUR_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 191, "Transporteur", "Un ou plusieurs champs obligatoires sont manquants"),
    //Fournisseur
    FOURNISSEURS_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 211, "Fournisseur", "Un ou plusieurs champs obligatoires sont manquants"),
    //Fabriquant
    FABRIQUANT_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 221, "Fabriquant", "Le champs Libelle est obligatoires"),
    //Citerne
    CITERNE_CODE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 230, "Client", "code citerne existe dejà"),
    CITERNE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 231, "Citerne", "Un ou plusieurs champs obligatoires sont manquants"),
  //JOURNEE_ACTIVITE
   JOURNEE_ACTIVITE_CODE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 230, "JourneeActivite", "code Journée d'activité existe dejà"),
   JOURNEE_ACTIVITE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 231, "JourneeActivite", "Un ou plusieurs champs obligatoires sont manquants"),
   JOURNEE_ACTIVITE_ACTIVITE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 231, "JourneeActivite", "Une journée d'activité de méme type est déja ouverte"),
   JOURNEE_ACTIVITE_CLOTURE_INVALID(HttpServletResponse.SC_BAD_REQUEST, 304, "Journee Activite", "Vous ne pouvez pas cloturer la journée d'activité en question, il existe toujours des instances non validé"),
	 
   //Camion
    CAMION_NUMERO_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 230, "Camion", "Numero de camion existe dejà"),
    CAMION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 241, "Camion", "Un ou plusieurs champs obligatoires sont manquants"),
    //Marque
    MARQUE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 251, "Marque", "Un ou plusieurs champs obligatoires sont manquants"),
    //Destination
    DESTINATION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 251, "DestinationOL", "Un ou plusieurs champs obligatoires sont manquants"),
	 //Controle Camion
    CONTROLE_CAMION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 261, "Controle Camion", "Un ou plusieurs champs obligatoires sont manquants"),
    CONTROLE_CAMION_CODE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 260, "Controle Camion", "le champs controle mentioné existe dejà dans la base de donnée"),
    //Prestation
    PRESTATION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 201, "Prestation", "Un ou plusieurs champs obligatoires sont manquants") ,
	//TypeFileAttente
    TYPE_FILE_ATENTE_TYPE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 260, "Type File d'Attente", "le champs type mentioné existe dejà dans la base de donnée"),
    TYPE_FILE_ATTENTE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 201, "Type File d'Attente", "Un ou plusieurs champs obligatoires sont manquants") ,
    //AffectationProduitOperateur
    AFFECTATION_PRODUIT_OPERATEUR_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 201, "AffectationProduitOperateur", "Un ou plusieurs champs obligatoires sont manquants"),
    //ConditionCommerciale
    CONDITION_COMMERCIALE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 201, "Conditions Commerciales", "Un ou plusieurs champs obligatoires sont manquants"),
    CONDITION_COMMERCIALE_PERIODE_NOT_VALID(HttpServletResponse.SC_BAD_REQUEST, 202, "Conditions Commerciales", "Les periodes ne sont pas valide"),
    CONDITION_COMMERCIALE_PERIODE_NOT_COVERED(HttpServletResponse.SC_BAD_REQUEST, 203, "Conditions Commerciales", "Les périodes doivent couvrir toute la périodicité sélectioné"),
    //TypePieceReglement
    TYPE_PIECE_REGLEMENT_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 201, "Type de piece de Reglement", "Un ou plusieurs champs obligatoires sont manquants"),
    //Periode
    PERIODE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 211, "Piece de Reglement", "Periode : Un ou plusieurs champs obligatoires sont manquants"),
    //FileAttente
    FILE_ATTENTE_NUMBC_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 271, "File Attente", "N° Bon de commande existe dejà pour ce centre"),
    LIGNE_FILE_ATTENTE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 272, "File Attente", "Ligne File Attente : Un ou plusieurs champs obligatoires sont manquants"),
    FILE_ATTENTE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 273, "File Attente", "Un ou plusieurs champs obligatoires sont manquants"),
    FILE_ATTENTE_MISSING_LIGNE_FILE_ATTENTE(HttpServletResponse.SC_BAD_REQUEST, 27, "File Attente", "Veuillez entrer au moins une ligne file d'attente"),
    LIGNE_FILE_ATTENTE_QTE_COMMANDE_POSITIVE(HttpServletResponse.SC_BAD_REQUEST, 275, "File Attente", "Ligne File Attente : La Quantité commandé doit être supérieure à 0"),
    LIGNE_FILE_ATTENTE_QTE_DEFECTIEUSE_POSITIVE(HttpServletResponse.SC_BAD_REQUEST, 276, "File Attente", "Ligne File Attente : La Quantité défectieuse doit être supérieure ou égale à 0"),
    FILE_ATTENTE_MISSING_MOTIF(HttpServletResponse.SC_BAD_REQUEST, 277, "File Attente", "Le motif est obligatoir pour modifier un objet avec le statut '" + StatutFileAttente.A_RECTIFIER.getKey() + "'"),
    FILE_ATTENTE_ORDRE_LIVRAISON_LINK_INVALID(HttpServletResponse.SC_BAD_REQUEST, 303, "Ordre Livraison", "L'enregistrement ne correspond à aucun ordre de livraison"),
	
    //Ordre Livraison
    ORDRE_LIVRAISON_NUMBC_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 281, "Ordre Livraison", "N° Bon de commande existe dejà pour ce centre"),
    LIGNE_ORDRE_LIVRAISON_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 282, "Ordre Livraison", "Ligne Ordre Livraison Conditioné: Un ou plusieurs champs obligatoires sont manquants"),
    LIGNE_ORDRE_LIVRAISON_VRAC_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 283, "Ordre Livraison", "Ligne Ordre Livraison VRAC : Un ou plusieurs champs obligatoires sont manquants"),
    ORDRE_LIVRAISON_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 284, "Ordre Livraison", "Un ou plusieurs champs obligatoires sont manquants"),
    ORDRE_LIVRAISON_MISSING_LIGNE_ORDRE_LIVRAISON(HttpServletResponse.SC_BAD_REQUEST, 285, "Ordre Livraison", "Veuillez entrer au moins une ligne d'ordre de livraison"),
    LIGNE_ORDRE_LIVRAISON_QTE_COMMANDE_POSITIVE(HttpServletResponse.SC_BAD_REQUEST, 286, "Ordre Livraison", "Ligne Ordre Livraison Conditioné: La Quantité commandé doit être supérieure à 0"),
    LIGNE_ORDRE_LIVRAISON_VRAC_QTE_COMMANDE_POSITIVE(HttpServletResponse.SC_BAD_REQUEST, 287, "Ordre Livraison", "Ligne Ordre Livraison VRAC: La Quantité commandé doit être supérieure à 0"),
    LIGNE_ORDRE_LIVRAISON_QTE_DEFECTIEUSE_POSITIVE(HttpServletResponse.SC_BAD_REQUEST, 288, "Ordre Livraison", "Ligne Ordre Livraison : La Quantité défectieuse doit être supérieure ou égale à 0"),
	ORDRE_LIVRAISON_JOURNEE_ACTIVITE_LIVRAISON_NO_EXISTS(HttpServletResponse.SC_BAD_REQUEST, 289, "Ordre Livraison", "Aucune journée activité de type Livraison n'est ouverte"),
	ORDRE_LIVRAISON_EN_INSTANCE_ANNULER(HttpServletResponse.SC_BAD_REQUEST, 290, "Ordre Livraison", "Vous ne pouvez pas annuler que les ordres de liavraison en instance"),
	ORDRE_LIVRAISON_JOURNEE_ACTIVITE_DATE_COMMANDE_INVALID(HttpServletResponse.SC_BAD_REQUEST, 292, "Ordre Livraison", "La date de la journée activité est postérieure à la date Commande"),
	ORDRE_LIVRAISON_MISSING_CITERNE_VALUE(HttpServletResponse.SC_BAD_REQUEST, 293, "Ordre Livraison", "Le numero de citerne est obligatoire"),
	ORDRE_LIVRAISON_FILE_ATTENTE_LINK_INVALID(HttpServletResponse.SC_BAD_REQUEST, 294, "Ordre Livraison", "L'enregistrement ne correspond à aucune entrée dans la file d'attente"),
	ORDRE_LIVRAISON_FILE_ATTENTE_LINK_ERROR(HttpServletResponse.SC_BAD_REQUEST, 295, "Ordre Livraison", "link error"),
	ORDRE_LIVRAISON_MISSING_LIGNE_ORDRE_LIVRAISON_VRAC(HttpServletResponse.SC_BAD_REQUEST, 296, "Ordre Livraison", "Veuillez entrer une ligne d'ordre de livraison"),
	ORDRE_LIVRAISON_MISSING_MOTIF(HttpServletResponse.SC_BAD_REQUEST, 297, "Ordre Livraison", "Le motif est obligatoir pour modifier un objet avec le statut '" + StatutOrdreLivraison.A_RECTIFIER.getKey() + "'"),
	ORDRE_LIVRAISON_CANCEL_MISSING_MOTIF(HttpServletResponse.SC_BAD_REQUEST, 297, "Ordre Livraison", "vous devez mentionner le motif d'annulation"),
	ORDRE_LIVRAISON_NOT_FOUND(HttpServletResponse.SC_BAD_REQUEST, 298, "Ordre Livraison", "Aucun objet trouvé avec l'identifiant mentionné"),

	//Emplissage
	EMPLISSAGE_JOURNEE_ACTIVITE_EMPLISSAGE_NO_EXISTS(HttpServletResponse.SC_BAD_REQUEST, 300, "Emplissage", "Aucune journée activité de type Emplissage n'est ouverte"),
	EMPLISSAGE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 301, "Emplissage", "Un ou plusieurs champs obligatoires sont manquants"),
	EMPLISSAGE_QTE_EMPLISSAGE_POSITIVE(HttpServletResponse.SC_BAD_REQUEST, 302, "Emplissage", "La Quantité cidée à l'emplissage doit être supérieure strictement à 0"),
	EMPLISSAGE_QTE_AND_BD_POSITIVE(HttpServletResponse.SC_BAD_REQUEST, 305, "Emplissage", "Les Quantités et les BD doivent être supérieure ou égales à 0"),
	
	//FICHE MARCHE
	FICHE_MARCHE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 306, "FicheMarche", "Un ou plusieurs champs obligatoires sont manquants"),
	FICHE_MARCHE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 307, "FicheMarche", "Erreur d'enregistrement"),
    FICHE_MARCHE_NO_EXISTS(HttpServletResponse.SC_BAD_REQUEST, 308, "FicheMarche", "Aucune fiche journalière de marche d'emplissage n'est trouvée"),
    FICHE_MARCHE_NOT_FOUND(HttpServletResponse.SC_BAD_REQUEST, 309, "FicheMarche", "Aucun objet trouvé avec l'identifiant mentionné"),
    FICHE_MARCHE_CLOTURE_INVALID(HttpServletResponse.SC_BAD_REQUEST, 310, "FicheMarche", "Vous ne pouvez pas cloturer la fiche journalière de marche d'emplissage en question, il contient toujours des emplissages non validé"),
    FICHE_MARCHE_ALREADY_PRODUIT_EXISTS(HttpServletResponse.SC_CONFLICT, 315, "FicheMarche", "Une fiche journalière de marche d'emplissage de même type est déja ouverte"),

    //Circuit Dérogation
    COMPOSISION_SITE_OBJET_EXIST(HttpServletResponse.SC_BAD_REQUEST, 331, "circuitDerogation", "Circuit de dérogation déjà existant"),
    CIRCUIT_DEROGATION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 334, "délegation", "Un ou plusieurs champs obligatoires sont manquants"),

	//User
    USER_ID_NOT_FOUND(HttpServletResponse.SC_BAD_REQUEST, 330, "circuitDerogation", "Aucun Utilisateur trouvé avec l'identifiant mentionné"),
	
	//PrixVenteClient
	PRIX_VENTE_CLIENT_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 311, "Prix vente client", "Un ou plusieurs champs obligatoires sont manquants"),
	PRIX_VENTE_CLIENT_ALREADY_EXISTS(HttpServletResponse.SC_BAD_REQUEST, 312, "Prix vente client", "le prix de vente existe déja"),
	PRIX_VENTE_CLIENT_DATE_INVALID(HttpServletResponse.SC_BAD_REQUEST, 313, "Prix vente client", "la date fin doit être superieure à la date début"),
	PRIX_VENTE_CLIENT_OVERLAP(HttpServletResponse.SC_BAD_REQUEST, 314, "Prix vente client", "il y'a un chavechement des dates, merci de réctifier"),
    //User
    USER_LOGIN_ALREADY_EXIST(HttpServletResponse.SC_BAD_REQUEST, 334, "utilisateur", "le login existe déja"),
    USER_MATRICULE_ALREADY_EXIST(HttpServletResponse.SC_BAD_REQUEST, 335, "utilisateur", "la matricule existe déja"),
    UTILISATEUR_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 332, "utilisateur", "Un ou plusieurs champs obligatoires sont manquants"),
    //Délégation
    DELEGATION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 333, "délegation", "Un ou plusieurs champs obligatoires sont manquants"),

    //PieceReglement
    PIECE_REGLEMENT_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 335, "Piece de Reglement", "Un ou plusieurs champs obligatoires sont manquants"),
    PIECE_REGLEMENT_INVALID(HttpServletResponse.SC_BAD_REQUEST, 335, "Piece de Reglement", "Erreur d'enregistrement"),
    PIECE_REGLEMENT_NOT_FOUND(HttpServletResponse.SC_BAD_REQUEST, 320, "Piece de Reglement", "Aucun objet trouvé avec l'identifiant mentionné"),
    PIECE_REGLEMENT_INVALID_CANCEL(HttpServletResponse.SC_BAD_REQUEST, 320, "Piece de Reglement", "la pièce de réglement n'est pas en instance pour la clôturér"),
    PIECE_REGLEMENT_MONTANT_POSITIVE(HttpServletResponse.SC_BAD_REQUEST, 323, "Piece Reglemet", "Le montant doit être supérieure strictement à 0"),
	
	//Prix Vente Concessionnaire
	PRIX_VENTE_CLIENT_CONCESSIONNAIRE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_BAD_REQUEST, 316, "Prix vente concessionnaire", "Un ou plusieurs champs obligatoires sont manquants"),
	PRIX_VENTE_CLIENT_CONCESSIONNAIRE_ALREADY_EXISTS(HttpServletResponse.SC_BAD_REQUEST, 317, "Prix vente concessionnaire", "le prix de vente existe déja"),
	PRIX_VENTE_CLIENT_CONCESSIONNAIRE_DATE_INVALID(HttpServletResponse.SC_BAD_REQUEST, 318, "Prix vente concessionnaire", "la date fin doit être superieure à la date début"),
	PRIX_VENTE_CLIENT_CONCESSIONNAIRE_OVERLAP(HttpServletResponse.SC_BAD_REQUEST, 319, "Prix vente concessionnaire", "il y'a un chavechement des dates, merci de réctifier"),
	
	ORDRE_LIVRAISON_JOURNEE_ACTIVITE_LIVRAISON_NO_CONFORM(HttpServletResponse.SC_BAD_REQUEST, 321, "Ordre Livraison", "La journée activité actuelle de type Livraison n'est identique à la journée d'activité avec laquelle l'ordre de livraison est crée"),
	ORDRE_LIVRAISON_JOURNEE_ACTIVITE_EMPLISSAGE_NO_CONFORM(HttpServletResponse.SC_BAD_REQUEST, 322, "Ordre Livraison", "La journée activité actuelle de type Emplissage n'est identique à la journée d'activité avec laquelle l'ordre de livraison est crée");
	
    private final int httpResponseStatus;
    private final int code;
    private final String fieldName;
    private final String messagePattern;

    private ErrorMessageType(int httpResponseStatus, int code, String fieldName, String messagePattern) {
        this.fieldName = fieldName;
        this.code = code;
        this.messagePattern = messagePattern;
        this.httpResponseStatus = httpResponseStatus;
    }

    public String getMessage(String msgArg) {
        return String.format(messagePattern, msgArg);
    }

    public String getMessage(String msgArg1, String msgArg2) {
        return String.format(messagePattern, msgArg1, msgArg2);
    }

    public String getMessage(Object... msgArgs) {
        return String.format(messagePattern, msgArgs);
    }

}

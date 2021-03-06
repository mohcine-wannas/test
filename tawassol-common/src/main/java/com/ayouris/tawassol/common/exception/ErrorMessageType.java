package com.ayouris.tawassol.common.exception;

import javax.servlet.http.HttpServletResponse;

import lombok.Getter;

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

    WORNG_PASSWORD( HttpServletResponse.SC_UNAUTHORIZED, 101, "Mot de passe", "Le mot de passe que vous avez saisis est incorrect"),
    
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
    //Camion
    CAMION_NUMERO_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 230, "Camion", "Numero de camion existe dejà"),
    CAMION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 241, "Camion", "Un ou plusieurs champs obligatoires sont manquants"),
    //Marque
    MARQUE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 251, "Marque", "Un ou plusieurs champs obligatoires sont manquants"),
	 //Controle Camion
    CONTROLE_CAMION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 261, "Controle Camion", "Un ou plusieurs champs obligatoires sont manquants"),
    CONTROLE_CAMION_CODE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 260, "Controle Camion", "le champs controle mentioné existe dejà dans la base de donnée"),
    //Prestation
    PRESTATION_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 201, "Prestation", "Un ou plusieurs champs obligatoires sont manquants") ,
	//TypeFileAttente
    TYPE_FILE_ATENTE_TYPE_ALREADY_EXISTS(HttpServletResponse.SC_CONFLICT, 260, "Type File d'Attente", "le champs type mentioné existe dejà dans la base de donnée"),
    TYPE_FILE_ATTENTE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 201, "Type File d'Attente", "Un ou plusieurs champs obligatoires sont manquants") ,
    //AffectationProduitOperateur
    AFFECTATION_PRODUIT_OPERATEUR_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 201, "AffectationProduitOperateur", "Un ou plusieurs champs obligatoires sont manquants"),
    //ConditionCommerciale
    CONDITION_COMMERCIALE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 201, "Conditions Commerciales", "Un ou plusieurs champs obligatoires sont manquants"),
    CONDITION_COMMERCIALE_PERIODE_NOT_VALID(HttpServletResponse.SC_BAD_REQUEST, 202, "Conditions Commerciales", "Les periodes ne sont pas valide"),
    CONDITION_COMMERCIALE_PERIODE_NOT_COVERED(HttpServletResponse.SC_BAD_REQUEST, 203, "Conditions Commerciales", "Les périodes doivent couvrir toute la périodicité sélectioné"),
    //PieceReglement
    PIECE_REGLEMENT_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 201, "Piece de Reglement", "Un ou plusieurs champs obligatoires sont manquants"),
    //Periode
    PERIODE_MISSING_REQUIRED_VALUES(HttpServletResponse.SC_CONFLICT, 201, "Piece de Reglement", "Periode : Un ou plusieurs champs obligatoires sont manquants"),
    
    
    DELETE_ERROR(HttpServletResponse.SC_CONFLICT, 201, "Suppression", "Impossible de supprimer cet objet car il est déjà utilisé")
    
    ;
	
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

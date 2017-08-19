package com.ayouris.tawassol.admin.model.enums;

public enum ViewName {

	SITE("site"),
	ROLE("role"),
    BANQUE("banque"),
    CLIENT("client"),
    CHAUFFEUR("chauffeur"),
    CONCESSIONNAIRE("Concessionnaire"),
    SIGNATAIRE("Signataire"),
    AFFECTATION("Affectation"),
    PRODUIT("Produit"),
    TRANSPORTEUR("Transporteur"),
    FOURNISSEUR("Fournisseur"),
    FABRIQUANT("Fabriquant"),
    CITERNE("Citerne"),
    FILE_ATTENTE("File d'Attente"),
    CAMION("Camion"),
    MARQUE("Marque"),
    CONTROLE_CAMION("Contrôle Camion"),
    PRESTATION("Prestation"),
    TYPE_FILE_ATTENTE("Types de file d’attente")	,
    AFFECTATION_PRODUIT_OPERATEUR("Affectation des produits aux opérateurs chaine"),
    CONDITION_COMMERCIALE("Condition Commerciale"),
    TYPE_PIECE_REGLEMENT("Type de pièce de règlement"),
    PIECE_REGLEMENT("Pièce de règlement"),
    ORDRE_LIVRAISON("Ordre Livraison Conditionné"),
    ORDRE_LIVRAISON_VRAC("Ordre Livraison Vrac"),
    LIGNE_ORDRE_LIVRAISON("Ligne Ordre Livraison"),
    DESTINATION("Destination"),
    BON_LIVRAISON("Bon Livraison"),
    JOURNEE_ACTIVITE("Journée activité"),
    FICHE_MARCHE("fiche Marche"),
    DELEGATION("Délegation"),
    USERS("Users"),
    PRIX_VENTE_CLIENT("Prix de vente Client"),
    CIRCUIT_DEROGATION("Circuit de dérogation"),
	EMPLISSAGE("Emplissage"),
	PRIX_VENTE_CONCESSIONNAIRE("Prix de vente concessionnaire");
	
    private String key;

    private ViewName(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
	
}

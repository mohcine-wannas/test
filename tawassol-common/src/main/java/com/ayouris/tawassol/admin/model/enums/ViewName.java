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
    CAMION("Camion"),
    MARQUE("Marque"),
    CONTROLE_CAMION("Contrôle Camion"),
    PRESTATION("Prestation"),
    TYPE_FILE_ATTENTE("Types de file d’attente")	,
    AFFECTATION_PRODUIT_OPERATEUR("Affectation des produits aux opérateurs chaine"),
    CONDITION_COMMERCIALE("Condition Commerciale"),
    PIECE_REGLEMENT("Pièce de règlement");
	
    private String key;

    private ViewName(String value) {
        key = value;
    }

    public String getKey() {
        return key;
    }
	
}

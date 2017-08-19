package com.ayouris.tawassol.common.model.enums;

public enum StatutBonLivraison {

	EN_INSTANCE("En instance"),
	ANNULE("Annulé"),
	EN_TRAITEMENT("En Traitement"),
	A_VERIFIER("A Vérifier"),
	A_IMPRIMER("A imprimer"),
    EN_INSTANCE_ANNULATION("En instance d'annulation"),
	VALIDE("Validé");
	
    private String key;

    private StatutBonLivraison(String value) {
    	
        key = value;
    }

    public String getKey() {
        return key;
    }
}

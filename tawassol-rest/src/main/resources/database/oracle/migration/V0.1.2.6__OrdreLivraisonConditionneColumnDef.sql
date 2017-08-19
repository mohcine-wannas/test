INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'id', 'ID', '1', '1', null, '0', '1', 'NUMBER', 'ORDRE_LIVRAISON', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'numBC', 'N° BC / BCRG', null, '2', null, '0', '1', 'TEXT', 'ORDRE_LIVRAISON', null,null);
       INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'dateCommande', 'Date Commande', null, '3', null, '0', '1', 'DATE', 'ORDRE_LIVRAISON', null,null); 
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'clientLibelle', 'Client', null, '4', null, '0', '1', 'TEXT', 'ORDRE_LIVRAISON', null,'client.libelle');
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'concessionnaireLibelle', 'Concessionnaire', null, '5', null, '0', '1', 'TEXT', 'ORDRE_LIVRAISON', null,'concessionnaire.libelle');        
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'chauffeurNom', 'Chauffeur', null, '6', null, '0', '1', 'TEXT', 'ORDRE_LIVRAISON', null,'chauffeur.nom');

INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'statut', 'Etat', null, '7', null, '0', '1', 'ENUM', 'ORDRE_LIVRAISON', null,null); 

INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'typeBon', 'Type de Bon', '1', '8', null, '0', '1', 'ENUM', 'ORDRE_LIVRAISON', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'typeChargement', 'Type de Chargement', '1', '9', null, '0', '1', 'ENUM', 'ORDRE_LIVRAISON', null);
       

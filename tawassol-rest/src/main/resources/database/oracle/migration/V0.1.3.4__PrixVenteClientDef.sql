INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,UNIT)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'id', 'ID', '1', '1', null, '0', '1', 'NUMBER', 'PRIX_VENTE_CLIENT', null,'PERCENTAGE');
       
       INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD,UNIT) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'produitLibelle', 'Produit', null, '2', null, '0', '1', 'TEXT', 'PRIX_VENTE_CLIENT', '20','produit.libelle','PERCENTAGE');
       
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD,UNIT) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'siteLibelle', 'Centre', null, '3', null, '0', '1', 'TEXT', 'PRIX_VENTE_CLIENT', '15','site.libelle','PERCENTAGE');
  
       INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD,UNIT) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'clientLibelle', 'Client', null, '4', null, '0', '1', 'TEXT', 'PRIX_VENTE_CLIENT', '15','client.libelle','PERCENTAGE');
       
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD,UNIT) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'dateDebut', 'DU', null, '5', null, '0', '1', 'DATE', 'PRIX_VENTE_CLIENT', '20',null,'PERCENTAGE');
     
       INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD,UNIT) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'dateFin', 'Au', null, '6', null, '0', '1', 'DATE', 'PRIX_VENTE_CLIENT', '20',null,'PERCENTAGE');
       
        INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD,UNIT) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'prix', 'Prix', null, '7', null, '0', '1', 'TEXT', 'PRIX_VENTE_CLIENT', '10',null,'PERCENTAGE');
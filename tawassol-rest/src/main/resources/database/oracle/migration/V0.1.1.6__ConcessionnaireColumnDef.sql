INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'id', 'ID', '1', '1', null, '0', '1', 'NUMBER', 'CONCESSIONNAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'code', 'Code', null, '2', null, '0', '1', 'TEXT', 'CONCESSIONNAIRE', null,null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'libelle', 'Libellé', null, '3', null, '0', '1', 'TEXT', 'CONCESSIONNAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
		VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'abreviation', 'Abréviation', null, '4', null, '0', '1', 'TEXT', 'CONCESSIONNAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'ville', 'Ville', null, '5', null, '0', '1', 'TEXT', 'CONCESSIONNAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'tel', 'Tel', null, '6', null, '0', '1', 'TEXT', 'CONCESSIONNAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'fax', 'Fax', null, '7', null, '0', '1', 'TEXT', 'CONCESSIONNAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'mail', 'E-Mail', null, '8', null, '0', '1', 'TEXT', 'CONCESSIONNAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'active', 'Active', '1', '9', null, '0', '1', 'CHECKBOX', 'CONCESSIONNAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'affectationsSiteLibelle', 'Sites', '1', '10', null, '0', '1', 'TEXT', 'CONCESSIONNAIRE', null,'affectations.any().site.libelle');
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'affectationsClientLibelle', 'CLient', '1', '11', null, '0', '1', 'TEXT', 'CONCESSIONNAIRE', null,'affectations.any().client.libelle');
       
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'id', 'ID', '1', '1', null, '0', '1', 'NUMBER', 'SIGNATAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'nom', 'Nom', null, '2', null, '0', '1', 'TEXT', 'SIGNATAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'prenom', 'Prénom', null, '3', null, '0', '1', 'TEXT', 'SIGNATAIRE', null,null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
		VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'fonction', 'Fonction', null, '4', null, '0', '1', 'TEXT', 'SIGNATAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'tel', 'Tel', null, '5', null, '0', '1', 'TEXT', 'SIGNATAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'active', 'Active', '1', '6', null, '0', '1', 'CHECKBOX', 'SIGNATAIRE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'concessionnaireId', 'ConcessionnaireId', '1', '7', null, '0', '1', 'NUMBER', 'SIGNATAIRE', null,'concessionnaire.id');
 
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'id', 'ID', '1', '1', null, '0', '1', 'NUMBER', 'AFFECTATION', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'clientLibelle', 'Client', null, '2', null, '0', '1', 'TEXT', 'AFFECTATION', null,'client.libelle');
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD) 
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'siteLibelle', 'Centre', null, '3', null, '0', '1', 'TEXT', 'AFFECTATION', null,'site.libelle');
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD)
		VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'produitLibelle', 'Produit', null, '4', null, '0', '1', 'TEXT', 'AFFECTATION', null,'produit.libelle');
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'droitAvoir', 'Droit avoir', null, '5', null, '0', '1', 'CHECKBOX', 'AFFECTATION', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'droitBCRG', 'Droit BCRG', null, '6', null, '0', '1', 'CHECKBOX', 'AFFECTATION', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'limiter', 'Limiter à', null, '7', null, '0', '1', 'CHECKBOX', 'AFFECTATION', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'dateDebut', 'Du', null, '8', null, '0', '1', 'DATE', 'AFFECTATION', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
		VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'dateFin', 'Au', null, '9', null, '0', '1', 'DATE', 'AFFECTATION', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'active', 'Active', '1', '10', null, '0', '1', 'CHECKBOX', 'AFFECTATION', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'concessionnaireId', 'ConcessionnaireId', '1', '11', null, '0', '1', 'NUMBER', 'AFFECTATION', null,'concessionnaire.id');
       
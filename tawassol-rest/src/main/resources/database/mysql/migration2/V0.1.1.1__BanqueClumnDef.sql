
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'id', 'ID', '1', '1', null, '0', '1', 'NUMBER', 'BANQUE', null);
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
		VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'libelle', 'Libellé', null, '2', null, '0', '1', 'TEXT', 'BANQUE', null);
INSERT INTO "GTAWASSOL."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (GTAWASSOLREF_DATA_SEQ.nextval, '0', 'abreviation', 'Abréviation', null, '3', null, '0', '1', 'TEXT', 'BANQUE', null);
INSERT INTO "GWTAWASSOL"COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (GWTAWASSOLEF_DATA_SEQ.nextval, '0', 'adresse', 'Adresse', null, '5', null, '0', '1', 'TEXT', 'BANQUE', null);
INSERT INTO "GWTAWASSOL"COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (GWTAWASSOLEF_DATA_SEQ.nextval, '0', 'active', 'Active', '1', '4', null, '0', '1', 'CHECKBOX', 'BANQUE', null);

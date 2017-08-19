
INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'id', 'ID', '1', '1', null, '0', '1', 'NUMBER', 'CIRCUIT_DEROGATION', null);

INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD,UNIT)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'siteLibelle', 'Centre', null, '2', null, '0', '1', 'TEXT', 'CIRCUIT_DEROGATION', 25,'site.libelle','PERCENTAGE');

INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD,UNIT)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'objet', 'Objet', null, '3', null, '0', '1', 'ENUM', 'CIRCUIT_DEROGATION', 50,null,'PERCENTAGE');

INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH,NESTED_FIELD,UNIT)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'userUsername', 'Personne habilité', null, '4', null, '0', '1', 'TEXT', 'CIRCUIT_DEROGATION', 25,'user.username','PERCENTAGE');

INSERT INTO "TAWASSOL"."COLUMNDEF" (ID,EDITABLE,FIELD,HEADER_NAME,COLUMN_HIDE,COLUMN_ORDER,PINNED,SUPPRESS_SORTING,SUPRESS_MENU,COLUMN_TYPE,VIEW_NAME,WIDTH)
       VALUES (TAWASSOL.REF_DATA_SEQ.nextval, '0', 'active', 'Active', '1', '5', null, '0', '1', 'CHECKBOX', 'CIRCUIT_DEROGATION', null);
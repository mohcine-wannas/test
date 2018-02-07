
-- ----------------------------
-- Records of BANQUE
-- ----------------------------
INSERT INTO "BANQUE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "LIBELLE") VALUES ('32', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 17:55:09:633000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-17 17:57:43:810000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', null, 'BMCE', 'Adresse', 'Banque marocaine du commerce extérieur');
INSERT INTO "BANQUE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "LIBELLE") VALUES ('33', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 17:55:43:708000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', null, 'SG', ' Adresse', 'Société Générale');
INSERT INTO "BANQUE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "LIBELLE") VALUES ('34', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 17:56:26:315000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', null, 'BMCI', 'Adresse', 'Banque marocaine pour le commerce et l''industrie');

-- ----------------------------
-- Records of CONCESSIONNAIRE
-- ----------------------------
INSERT INTO "CONCESSIONNAIRE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "FAX", "ID_FISCAL", "INTERLOCUTEUR", "LIBELLE", "MAIL", "MOBILE", "PATENTE", "REGISTRE_COMMERCE", "TEL", "VILLE") VALUES ('1', null, null, null, null, null, null, 'abr', null, null, null, null, 'cons1', null, null, null, null, null, null);
INSERT INTO "CONCESSIONNAIRE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "FAX", "ID_FISCAL", "INTERLOCUTEUR", "LIBELLE", "MAIL", "MOBILE", "PATENTE", "REGISTRE_COMMERCE", "TEL", "VILLE") VALUES ('2', null, null, null, null, null, null, 'abr', null, null, null, null, 'cons2', null, null, null, null, null, null);
INSERT INTO "CONCESSIONNAIRE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "FAX", "ID_FISCAL", "INTERLOCUTEUR", "LIBELLE", "MAIL", "MOBILE", "PATENTE", "REGISTRE_COMMERCE", "TEL", "VILLE") VALUES ('3', null, null, null, null, null, null, 'abr', null, null, null, null, 'cons3', null, null, null, null, null, null);
INSERT INTO "CONCESSIONNAIRE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "FAX", "ID_FISCAL", "INTERLOCUTEUR", "LIBELLE", "MAIL", "MOBILE", "PATENTE", "REGISTRE_COMMERCE", "TEL", "VILLE") VALUES ('4', null, null, null, null, null, null, 'abr', null, null, null, null, 'cons4', null, null, null, null, null, null);

-- ----------------------------
-- Records of CHAUFFEUR
-- ----------------------------
INSERT INTO "CHAUFFEUR" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ADRESSE", "CIN", "NOM", "PERMIS", "PRENOM", "TEL", "VILLE", "CONCESSIONNAIRE_ID") VALUES ('17', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:59:16:229000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', null, 'adresse', 'BB108765', 'EL oufi', '03878', 'mohamed', '+21299887755', 'Rabat', '1');
INSERT INTO "CHAUFFEUR" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ADRESSE", "CIN", "NOM", "PERMIS", "PRENOM", "TEL", "VILLE", "CONCESSIONNAIRE_ID") VALUES ('18', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 16:04:13:701000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', null, 'Adresse', 'BJ765432', 'Ahmadi', '00878', 'adil', '+21299887755', 'Skhirat', '3');

-- ----------------------------
-- Records of FOURNISSEUR
-- ----------------------------
INSERT INTO "FOURNISSEUR" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "LIBELLE") VALUES ('28', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 16:23:23:298000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', null, 'z', 'z');
INSERT INTO "FOURNISSEUR" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "LIBELLE") VALUES ('29', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 16:23:31:429000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', null, 'r', 'r');

-- ----------------------------
-- Records of GROUPE
-- ----------------------------
INSERT INTO "GROUPE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "LIBELLE") VALUES ('1', null, null, null, null, null, null, 'TOTAL');
INSERT INTO "GROUPE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "LIBELLE") VALUES ('2', null, null, null, null, null, null, 'AFRIQUIA');


-- ----------------------------
-- Records of CLIENT
-- ----------------------------
INSERT INTO "CLIENT" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "EMAIL", "FAX", "GESTION_SOLDE", "ID_FISCAL", "INTERLOCUTEUR", "LIBELLE", "MOBILE", "PATENTE", "REGISTRE_COMMERCE", "TEL", "VILLE", "GROUPE_ID") VALUES ('13', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:49:34:153000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', 'Client_001', 'Total', 'Adresse', 'total@total.ma', '+21299887755', '1', '087E4874', 'Interlocuteur', 'Total Gaz', '+21299887755', 'Patente', 'Registre de commerce', '+21299887755', 'Ville', '1');
INSERT INTO "CLIENT" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "EMAIL", "FAX", "GESTION_SOLDE", "ID_FISCAL", "INTERLOCUTEUR", "LIBELLE", "MOBILE", "PATENTE", "REGISTRE_COMMERCE", "TEL", "VILLE", "GROUPE_ID") VALUES ('14', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:51:14:632000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-18 12:11:12:058000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', 'Afriquia_002', 'Afriquia', 'Adresse', 'afriquia@afriquia.ma', '0644594211F8', '1', 'Identifiant fiscal', 'Interlocuteur', 'Afriquia Gaz', '+21299887755', 'Patente', ' Registre de commerce', '0644594211F8', 'Ville', '2');
INSERT INTO "CLIENT" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "ADRESSE", "EMAIL", "FAX", "GESTION_SOLDE", "ID_FISCAL", "INTERLOCUTEUR", "LIBELLE", "MOBILE", "PATENTE", "REGISTRE_COMMERCE", "TEL", "VILLE", "GROUPE_ID") VALUES ('38', 'mchahdi', TO_TIMESTAMP(' 2017-05-18 11:41:02:990000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-18 11:58:18:972000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', 'Rt', 'RRR', 'rrrr', 'rrrrr', 'rrrr', '1', 'rrrr', 'rrrrrrr', 'RRRR', 'rrrr', 'rrrr', 'rrrrr', 'rrr', 'rrrrr', '1');

-- ----------------------------
-- Records of PRODUIT
-- ----------------------------
INSERT INTO "PRODUIT" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "LIBELLE", "NATURE_PRODUIT", "POIDS", "TYPE_CHARGEMENT", "UNITE") VALUES ('1', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:24:21:923000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:36:49:844000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', '001', 'Butane 03', 'BUTANE', '0.003', 'CONDITIONNE', 'kg');
INSERT INTO "PRODUIT" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "LIBELLE", "NATURE_PRODUIT", "POIDS", "TYPE_CHARGEMENT", "UNITE") VALUES ('7', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:28:43:733000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:37:41:601000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '002', 'Butane 12', 'BUTANE', '0.012', 'CONDITIONNE', 'kg');
INSERT INTO "PRODUIT" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "LIBELLE", "NATURE_PRODUIT", "POIDS", "TYPE_CHARGEMENT", "UNITE") VALUES ('9', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:29:38:041000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:37:16:758000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '003', 'Propane 34', 'PROPANE', '0.034', 'CONDITIONNE', null);
INSERT INTO "PRODUIT" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "LIBELLE", "NATURE_PRODUIT", "POIDS", "TYPE_CHARGEMENT", "UNITE") VALUES ('10', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:31:08:346000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:47:11:026000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', '004', 'Butane vrac', 'BUTANE', '0', 'VRAC', null);
INSERT INTO "PRODUIT" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "LIBELLE", "NATURE_PRODUIT", "POIDS", "TYPE_CHARGEMENT", "UNITE") VALUES ('11', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:33:04:337000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:38:15:734000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', '005', 'Butane 06', 'BUTANE', '0.006', 'CONDITIONNE', 'kg');
INSERT INTO "PRODUIT" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "LIBELLE", "NATURE_PRODUIT", "POIDS", "TYPE_CHARGEMENT", "UNITE") VALUES ('12', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 15:33:36:701000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', '006', 'Propane Vrac', 'PROPANE', '0', 'VRAC', null);


-- ----------------------------
-- Records of SITE
-- ----------------------------
INSERT INTO "SITE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "LIBELLE", "SITE_TYPE", "TAUX_TRANSPORT", "VILLE") VALUES ('46', 'mchahdi', TO_TIMESTAMP(' 2017-05-18 11:59:33:749000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', 'CE_001', 'CE.Skhirat', 'CE.Skhirat', 'CENTRE', '5.0', 'Skhirat');
INSERT INTO "SITE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "LIBELLE", "SITE_TYPE", "TAUX_TRANSPORT", "VILLE") VALUES ('47', 'mchahdi', TO_TIMESTAMP(' 2017-05-18 12:00:01:773000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', 'CE_002', 'CE.nador', 'CE.nador', 'CENTRE', '5.0', 'Nador');
INSERT INTO "SITE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "LIBELLE", "SITE_TYPE", "TAUX_TRANSPORT", "VILLE") VALUES ('48', 'mchahdi', TO_TIMESTAMP(' 2017-05-18 12:00:36:701000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-18 12:36:16:290000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', 'ee', 'Siege', 'Siege', 'SIEGE', '2.0', 'Rabat');

-- ----------------------------
-- Records of TRANSPORTEUR
-- ----------------------------
INSERT INTO "TRANSPORTEUR" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "ABREVIATION", "LIBELLE") VALUES ('26', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 16:22:15:328000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-17 16:23:06:360000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '0', null, 'a', 'Afriquia Gaz');

-- ----------------------------
-- Records of TYPE_FILE_ATTENTE
-- ----------------------------
INSERT INTO "TYPE_FILE_ATTENTE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "CATEGORIE", "TYPE", "TYPE_PARDEFAUT") VALUES ('30', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 16:24:51:422000', 'YYYY-MM-DD HH24:MI:SS:FF6'), 'mchahdi', TO_TIMESTAMP(' 2017-05-17 16:25:54:471000', 'YYYY-MM-DD HH24:MI:SS:FF6'), '1', null, 'RECEPTION_VRAC', 'Type 1', '1');
INSERT INTO "TYPE_FILE_ATTENTE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "CATEGORIE", "TYPE", "TYPE_PARDEFAUT") VALUES ('35', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 18:06:03:420000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', null, 'LIVRAISON_VRAC', 'Type 2', '1');
INSERT INTO "TYPE_FILE_ATTENTE" ("ID", "CREATED_BY", "CREATED_ON", "UPDATED_BY", "UPDATED_ON", "ACTIVE", "CODE", "CATEGORIE", "TYPE", "TYPE_PARDEFAUT") VALUES ('36', 'mchahdi', TO_TIMESTAMP(' 2017-05-17 18:08:27:838000', 'YYYY-MM-DD HH24:MI:SS:FF6'), null, null, '1', null, 'LIVRAISON_CONDITIONNE', 'Type 2', '1');

-- -

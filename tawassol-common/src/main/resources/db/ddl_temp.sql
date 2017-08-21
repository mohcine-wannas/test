
    create table adherent_seq (
        next_val bigint
    );

    insert into adherent_seq values ( 1 );

    create table categoriedocument_seq (
        next_val bigint
    );

    insert into categoriedocument_seq values ( 1 );

    create table categoriesport_seq (
        next_val bigint
    );

    insert into categoriesport_seq values ( 1 );

    create table club_seq (
        next_val bigint
    );

    insert into club_seq values ( 1 );

    create table cotisation_seq (
        next_val bigint
    );

    insert into cotisation_seq values ( 1 );

    create table document_seq (
        next_val bigint
    );

    insert into document_seq values ( 1 );

    create table hibernate_sequence (
        next_val bigint
    );

    insert into hibernate_sequence values ( 1 );

    create table TBL_ADHERENT (
        ID bigint not null,
        ADRESSE longtext,
        CIN varchar(255),
        DATE_NAISSANCE date,
        EMAIL varchar(255),
        NATIONALITE varchar(255),
        NOM varchar(255),
        PRENOM varchar(255),
        SEXE integer default 0,
        SITUATION_FAMILIALE integer default 0,
        TEL varchar(255),
        CATEGORIE_ID bigint,
        primary key (ID)
    );

    create table TBL_ADHERENT_CATEGORIE_SPORT (
        ADHERENT_ID bigint not null,
        CATEGORIESPORTLIST_ID bigint not null
    );

    create table TBL_CATEGORIE (
        ID bigint not null,
        DESCRIPTION varchar(255),
        LIBELLE varchar(255),
        primary key (ID)
    );

    create table TBL_CATEGORIE_DOCUMENT (
        ID bigint not null,
        DESCRIPTION longtext,
        LIBELLE varchar(255),
        primary key (ID)
    );

    create table TBL_CATEGORIE_SPORT (
        ID bigint not null,
        DESCRIPTION longtext,
        LIBELLE varchar(255),
        primary key (ID)
    );

    create table TBL_CLUB (
        ID bigint not null,
        ADRESSE longtext,
        AFFILIER boolean default true,
        EMAIL varchar(255),
        FAX varchar(255),
        LIBELLE varchar(255),
        NOMGERANT varchar(255),
        PRENOMGERANT varchar(255),
        SITEWEB varchar(255),
        TELEPHONE varchar(255),
        primary key (ID)
    );

    create table TBL_COTISATION (
        ID bigint not null,
        ANNEE varchar(255),
        DATE datetime,
        MONTANT double precision,
        CLUB_ID bigint,
        primary key (ID)
    );

    create table TBL_DOCUMENT (
        ID bigint not null,
        DATE datetime,
        DESCRIPTION longtext,
        FICHIER longtext,
        ADHERENT_ID bigint,
        CATEGORIEDOCUMENT_ID bigint,
        primary key (ID)
    );

    alter table TBL_ADHERENT 
        add constraint FK_TBL_ADHERENT_CATEGORIE_ID 
        foreign key (CATEGORIE_ID) 
        references TBL_CATEGORIE (ID);

    alter table TBL_ADHERENT_CATEGORIE_SPORT 
        add constraint FK_TBL_ADHERENT_CATEGORIE_SPORT_CATEGORIESPORTLIST_ID 
        foreign key (CATEGORIESPORTLIST_ID) 
        references TBL_CATEGORIE_SPORT (ID);

    alter table TBL_ADHERENT_CATEGORIE_SPORT 
        add constraint FK_TBL_ADHERENT_CATEGORIE_SPORT_ADHERENT_ID 
        foreign key (ADHERENT_ID) 
        references TBL_ADHERENT (ID);

    alter table TBL_COTISATION 
        add constraint FK_TBL_COTISATION_CLUB_ID 
        foreign key (CLUB_ID) 
        references TBL_CLUB (ID);

    alter table TBL_DOCUMENT 
        add constraint FK_TBL_DOCUMENT_ADHERENT_ID 
        foreign key (ADHERENT_ID) 
        references TBL_ADHERENT (ID);

    alter table TBL_DOCUMENT 
        add constraint FK_TBL_DOCUMENT_CATEGORIEDOCUMENT_ID 
        foreign key (CATEGORIEDOCUMENT_ID) 
        references TBL_CATEGORIE_DOCUMENT (ID);

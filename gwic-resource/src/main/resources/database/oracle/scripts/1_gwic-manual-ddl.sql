
--
-- Name: gwic_seq; Type: SEQUENCE; Schema: gwic; Owner: gwic
--
CREATE SEQUENCE "GWIC_SEQ"  
	MINVALUE 0
	MAXVALUE 9999999999999999999999999999 
	INCREMENT BY 1
	START WITH 1
	NOCACHE  NOORDER  NOCYCLE ;

--
-- Name: gwic_ref_data_seq; Type: SEQUENCE; Schema: gwic; Owner: gwic
--	
CREATE SEQUENCE "REF_DATA_SEQ"  
	MINVALUE 0
	MAXVALUE 9999999999999999999999999999 
	INCREMENT BY 1
	START WITH 1
	NOCACHE  NOORDER  NOCYCLE ;


---	
CREATE TABLE gwic.country_lang (
    id integer NOT NULL,
    lang_id integer NOT NULL,
    country_id integer NOT NULL,
    label character varying(256)
)
;

--
----
--
CREATE TABLE gwic.country_ref (
    id integer NOT NULL,
    code character varying(3),
    code_court character varying(2)
);
	
--
-- Name: lang; Type: TABLE; Schema: gwic; Owner: gwic; Tablespace: 
--
CREATE TABLE gwic.lang (
    id integer NOT NULL,
    "CODE" VARCHAR2(6 CHAR),
    "NAME" VARCHAR2(20 CHAR)
);

--
-- Name: member; Type: TABLE; Schema: gwic; Owner: gwic; Tablespace: 
--
CREATE TABLE  gwic."MEMBER" (	
    "ID" NUMBER(*,0) NOT NULL ENABLE, 
	"ORGANIZATION_ID" NUMBER(*,0) NOT NULL ENABLE, 
	"USERNAME" VARCHAR2(128) NOT NULL ENABLE, 
	"PASSWORD" VARCHAR2(128) NOT NULL ENABLE, 
	"ENABLED" NUMBER(*,0) NOT NULL ENABLE,
	"LASTNAME" VARCHAR2(128), 
	"FIRSTNAME" VARCHAR2(128), 
	"PHONE_NUMBER" VARCHAR2(50), 
	"EMAIL" VARCHAR2(50), 
	"FAX_NUMBER" VARCHAR2(50), 
	"CONTACT_TYPE" VARCHAR2(128), 
	"MOBILE_NUMBER" VARCHAR2(50), 
	"PREFERRED_CHANNEL" VARCHAR2(35), 
	"CREATED_BY" VARCHAR2(128), 
	"UPDATED_BY" VARCHAR2(128), 
	"CREATED_ON" TIMESTAMP (6) WITH TIME ZONE, 
	"UPDATED_ON" TIMESTAMP (6) WITH TIME ZONE, 
	"DESCRIPTION" VARCHAR2(4000)
   ) ;

--
-- Name: operation; Type: TABLE; Schema: gwic; Owner: gwic; Tablespace: 
--
CREATE TABLE gwic."OPERATION" (
	"ID" NUMBER(*,0) NOT NULL ENABLE,
    "NAME" VARCHAR2(120),
    "DESCRIPTION" VARCHAR2(4000)
);


--
-- Name: organization; Type: TABLE; Schema: gwic; Owner: gwic; Tablespace: 
--
CREATE TABLE gwic.organization (
    id integer NOT NULL,
    name character varying(200),
    acronym character varying(50),
    address character varying(128),
    additional_address character varying(128),
    zip_code character varying(10),
    city character varying(35),
    manager_lastname character varying(50),
    manager_firstname character varying(50),
    phone_number character varying(50),
    email character varying(50),
    fax_number character varying(50),
    mobile_number character varying(50),
    enabled NUMBER(1) DEFAULT 0 NOT NULL,
    validity_date date,
    organization_parent_id integer,
    country_id integer,
    created_by character varying(128),
    updated_by character varying(128),
    created_on TIMESTAMP (6) WITH TIME ZONE,
    updated_on TIMESTAMP (6) WITH TIME ZONE,
    manager_occupation character varying(200),
    manager_birth_date date,
    other_name character varying(200)
);

-- Name: permission_right; Type: TABLE; Schema: gwic; Owner: gwic; Tablespace: 
--
CREATE TABLE gwic.permission_right (
    id integer NOT NULL,
    operation_id integer NOT NULL,
    resource_id integer NOT NULL
);


-- Name: resource; Type: TABLE; Schema: gwic; Owner: gwic; Tablespace: 

CREATE TABLE gwic."RESOURCES"(	
    "ID" NUMBER(*,0) NOT NULL ENABLE, 
	"NAME" VARCHAR2(120 CHAR), 
	"DESCRIPTION" VARCHAR2(4000 CHAR)
) ;

--
-- Name: role; Type: TABLE; Schema: gwic; Owner: gwic; Tablespace: 
--
CREATE TABLE gwic.role (
    id integer NOT NULL,
    name character varying(128),
    created_by character varying(128),
    updated_by character varying(128),
    created_on timestamp(6) with time zone,
    updated_on timestamp(6) with time zone,
    description character varying(50),
	enabled NUMBER(*,0) NOT NULL,    
    organization_type character varying(128),
    rank integer
);

-- Name: role_permission; Type: TABLE; Schema: gwic; Owner: gwic; Tablespace: 
--
CREATE TABLE gwic.role_permission (
    role_id integer NOT NULL,
    permission_id integer NOT NULL,
    created_by character varying(128),
    updated_by character varying(128),
    created_on timestamp(6) with time zone,
    updated_on timestamp(6) with time zone
);


-- Name: user_role; Type: TABLE; Schema: gwic; Owner: gwic; Tablespace: 

CREATE TABLE gwic.user_role (
    member_id integer NOT NULL,
    role_id integer NOT NULL,
    created_by character varying(128),
    updated_by character varying(128),
    created_on timestamp(6) with time zone,
    updated_on timestamp(6) with time zone
);


-- Name: country_lang_pk; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 

ALTER TABLE gwic.country_lang
    ADD CONSTRAINT country_lang_pk PRIMARY KEY (id);


--
-- Name: country_ref_pk; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.country_ref
    ADD CONSTRAINT country_ref_pk PRIMARY KEY (id);

--
-- Name: lang_id; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.lang
    ADD CONSTRAINT lang_id PRIMARY KEY (id);


-- Name: member_pkey; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.member
    ADD CONSTRAINT member_pkey PRIMARY KEY (id);


-- Name: operation_pkey; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.operation
    ADD CONSTRAINT operation_pkey PRIMARY KEY (id);

--
-- Name: organization_pk; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.organization
    ADD CONSTRAINT organization_pk PRIMARY KEY (id);


-- Name: permission_right_pkey; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.permission_right
    ADD CONSTRAINT permission_right_pkey PRIMARY KEY (id);

--
-- Name: resource_pkey; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic."RESOURCES"
    ADD CONSTRAINT resource_pkey PRIMARY KEY (id);


-- Name: role_permission_pkey; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.role_permission
    ADD CONSTRAINT role_permission_pkey PRIMARY KEY (role_id, permission_id);


-- Name: role_pkey; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


-- Name: uk_lang_code; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.lang
    ADD CONSTRAINT uk_lang_code UNIQUE (code);

--
-- Name: uk_username; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.member
    ADD CONSTRAINT uk_username UNIQUE (username);


-- Name: user_role_pkey; Type: CONSTRAINT; Schema: gwic; Owner: gwic; Tablespace: 
--
ALTER TABLE gwic.user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (member_id, role_id);


-- Name: ix_label_country; Type: INDEX; Schema: gwic; Owner: gwic; Tablespace: 
--
CREATE INDEX ix_label_country ON gwic.country_lang (label);


-- Name: country_lang_country_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.country_lang
    ADD CONSTRAINT country_lang_country_fk FOREIGN KEY (country_id) REFERENCES country_ref(id);


-- Name: country_lang_lang_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.country_lang
    ADD CONSTRAINT country_lang_lang_fk FOREIGN KEY (lang_id) REFERENCES lang(id);


-- Name: country_organization_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.organization
    ADD CONSTRAINT country_organization_fk FOREIGN KEY (country_id) REFERENCES country_ref(id);


-- Name: member_organization_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE member
    ADD CONSTRAINT member_organization_fk FOREIGN KEY (organization_id) REFERENCES organization(id);

--
-- Name: parent_organization_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.organization
    ADD CONSTRAINT parent_organization_fk FOREIGN KEY (organization_parent_id) REFERENCES organization(id);

--
-- Name: permission_right_operation_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.permission_right
    ADD CONSTRAINT permission_right_operation_fk FOREIGN KEY (operation_id) REFERENCES operation(id);

--
-- Name: permission_right_resource_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.permission_right
    ADD CONSTRAINT permission_right_resource_fk FOREIGN KEY (resource_id) REFERENCES resources(id);


-- Name: role_permission_permission_right_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.role_permission
    ADD CONSTRAINT role_permission_right_fk FOREIGN KEY (permission_id) REFERENCES permission_right(id);


-- Name: role_permission_role_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.role_permission
    ADD CONSTRAINT role_permission_role_fk FOREIGN KEY (role_id) REFERENCES role(id);

--
-- Name: user_role_member_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.user_role
    ADD CONSTRAINT user_role_member_fk FOREIGN KEY (member_id) REFERENCES member(id);

--
-- Name: user_role_role_fk; Type: FK CONSTRAINT; Schema: gwic; Owner: gwic
--
ALTER TABLE gwic.user_role
    ADD CONSTRAINT user_role_role_fk FOREIGN KEY (role_id) REFERENCES role(id);
    

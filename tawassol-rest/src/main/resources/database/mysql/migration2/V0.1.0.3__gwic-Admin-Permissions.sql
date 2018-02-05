/***************************************************************************************************************************************************************/
/******************************************************************************** V4.9 *************************************************************************/
/***************************************************************************************************************************************************************/

DELETE FROM tawassol.role_permission;
DELETE FROM tawassol.permission_right;
DELETE FROM tawassol.resources;
DELETE FROM tawassol.operation;


/***************************************************************************************************************************************************************/
/***************************************************************************** OPERATIONS **********************************************************************/
/***************************************************************************************************************************************************************/

INSERT INTO tawassol.operation (id, name, description) VALUES ( tawassol.ref_data_seq.nextval, 'READ', 'READ');
INSERT INTO tawassol.operation (id, name, description) VALUES ( tawassol.ref_data_seq.nextval, 'WRITE', 'WRITE');
INSERT INTO tawassol.operation (id, name, description) VALUES ( tawassol.ref_data_seq.nextval, 'DELETE', 'DELETE');
INSERT INTO tawassol.operation (id, name, description) VALUES ( tawassol.ref_data_seq.nextval, 'UPDATE', 'UPDATE');
INSERT INTO tawassol.operation (id, name, description) VALUES ( tawassol.ref_data_seq.nextval, 'DOWNLOAD', 'DOWNLOAD');
INSERT INTO tawassol.operation (id, name, description) VALUES ( tawassol.ref_data_seq.nextval, 'UPLOAD', 'UPLOAD');
INSERT INTO tawassol.operation (id, name, description) VALUES ( tawassol.ref_data_seq.nextval, 'PRINT', 'PRINT');
INSERT INTO tawassol.operation (id, name, description) VALUES ( tawassol.ref_data_seq.nextval, 'ALL', 'ALL Operations');


/***************************************************************************************************************************************************************/
/************************************************************************* Permission Right ********************************************************************/
/***************************************************************************************************************************************************************/

INSERT INTO tawassol.resources(id, name, description) VALUES( tawassol.ref_data_seq.nextval, 'admin', 'Menu Administration');
INSERT INTO tawassol.permission_right(id, operation_id, resource_id) 
		select tawassol.ref_data_seq.nextval, op.id, rs.id from tawassol.operation op, tawassol.resources rs where op.name ='READ' and rs.name='admin';


INSERT INTO tawassol.resources(id, name, description) VALUES( tawassol.ref_data_seq.nextval, 'admin.users', 'Tableau utilisateurs');
INSERT INTO tawassol.permission_right (id, operation_id, resource_id) 
	SELECT tawassol.ref_data_seq.nextval, op.id, rs.id FROM tawassol.operation op, tawassol.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.users';
INSERT INTO tawassol.permission_right (id, operation_id, resource_id) 
	SELECT tawassol.ref_data_seq.nextval, op.id, rs.id FROM tawassol.operation op, tawassol.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.users';
	

INSERT INTO tawassol.resources(id, name, description) VALUES( tawassol.ref_data_seq.nextval, 'admin.user.view-edit', 'Icone oeil pour consulter/editer un utilisateur');	
INSERT INTO tawassol.permission_right(id, operation_id, resource_id) 
	SELECT tawassol.ref_data_seq.nextval, op.id, rs.id FROM tawassol.operation op, tawassol.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.user.view-edit';
INSERT INTO tawassol.permission_right(id, operation_id, resource_id) 
	SELECT tawassol.ref_data_seq.nextval, op.id, rs.id FROM tawassol.operation op, tawassol.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.user.view-edit';

	
INSERT INTO tawassol.resources(id, name, description)  VALUES( tawassol.ref_data_seq.nextval, 'admin.user.edit.status', 'Icone Editer pour Editer le status d''utilisateur');
INSERT INTO tawassol.permission_right(id, operation_id, resource_id) 
	SELECT tawassol.ref_data_seq.nextval, op.id, rs.id FROM tawassol.operation op, tawassol.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.user.edit.status';
INSERT INTO tawassol.permission_right(id, operation_id, resource_id) 
	SELECT tawassol.ref_data_seq.nextval, op.id, rs.id FROM tawassol.operation op, tawassol.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.user.edit.status';


INSERT INTO tawassol.resources(id, name, description)  VALUES( tawassol.ref_data_seq.nextval, 'admin.user.edit.auto.pdf.download', 'Téléchargement automatique du PDF');	
INSERT INTO gwtawassolermission_right(id, operation_id, resource_id) 
	SELECT gwtawassolef_data_seq.nextval, op.id, rs.id FROM gwtawassolperation op, gwtawassolesources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.user.edit.auto.pdf.download';
INSERT INTO gwtawassolermission_right(id, operation_id, resource_id) 
	SELECT gwtawassolef_data_seq.nextval, op.id, rs.id FROM gwtawassolperation op, gwtawassolesources rs WHERE op.name = 'READ' AND rs.name = 'admin.user.edit.auto.pdf.download';


INSERT INTO gwtawassolesources(id, name, description)  VALUES( gwtawassolef_data_seq.nextval, 'admin.user.add', 'Bouton Ajouter un utilisateur pour créer un utilisateur');	
INSERT INTO gwitawassolrmission_right(id, operation_id, resource_id) 
	SELECT gwitawassolf_data_seq.nextval, op.id, rs.id FROM gwitawassoleration op, gwitawassolsources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.user.add';
INSERT INTO gwitawassolrmission_right(id, operation_id, resource_id) 
	SELECT gwitawassolf_data_seq.nextval, op.id, rs.id FROM gwitawassoleration op, gwitawassolsources rs WHERE op.name = 'READ' AND rs.name = 'admin.user.add';


INSERT INTO gwitawassolsources(id, name, description) VALUES( gwitawassolf_data_seq.nextval, 'admin.users.download', 'Bouton Télécharger au format CSV');	
INSERT INTO gwic.tawassolission_right(id, operation_id, resource_id) 
	SELECT gwic.tawassoldata_seq.nextval, op.id, rs.id FROM gwic.tawassolation op, gwic.tawassolurces rs WHERE op.name = 'WRITE' AND rs.name = 'admin.users.download';
INSERT INTO gwic.tawassolission_right(id, operation_id, resource_id) 
	SELECT gwic.tawassoldata_seq.nextval, op.id, rs.id FROM gwic.tawassolation op, gwic.tawassolurces rs WHERE op.name = 'READ' AND rs.name = 'admin.users.download';

	
	
INSERT INTO gwic.tawassolurces(id, name, description)  VALUES( gwic.tawassoldata_seq.nextval, 'admin.organizations', 'Tableau organisations');
INSERT INTO gwic.tawassolission_right (id, operation_id, resource_id) 
	SELECT gwic.tawassoldata_seq.nextval, op.id, rs.id FROM gwic.tawassolation op, gwic.tawassolurces rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organizations';
INSERT INTO gwic.tawassolission_right (id, operation_id, resource_id) 
	SELECT gwic.tawassoldata_seq.nextval, op.id, rs.id FROM gwic.tawassolation op, gwic.tawassolurces rs WHERE op.name = 'READ' AND rs.name = 'admin.organizations';
	

INSERT INTO gwic.tawassolurces(id, name, description) VALUES( gwic.tawassoldata_seq.nextval, 'admin.organization.view-edit', 'Icone oeil pour consulter/editer une organization');		
INSERT INTO gwic.tawassolission_right(id, operation_id, resource_id) 
	SELECT gwic.tawassoldata_seq.nextval, op.id, rs.id FROM gwic.tawassolation op, gwic.tawassolurces rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organization.view-edit';
INSERT INTO gwic.tawassolission_right(id, operation_id, resource_id) 
	SELECT gwic.tawassoldata_seq.nextval, op.id, rs.id FROM gwic.tawassolation op, gwic.tawassolurces rs WHERE op.name = 'READ' AND rs.name = 'admin.organization.view-edit';


INSERT INTO gwic.tawassolurces(id, name, description)  VALUES( gwic.tawassoldata_seq.nextval, 'admin.organization.edit.status', 'Icone Editer pour Editer le status d''une organization');	
INSERT INTO gwic.tawassolission_right(id, operation_id, resource_id) 
	SELECT gwic.tawassoldata_seq.nextval, op.id, rs.id FROM gwic.tawassolation op, gwic.tawassolurces rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organization.edit.status';
INSERT INTO gwic.tawassolission_right(id, operation_id, resource_id) 
	SELECT gwic.tawassoldata_seq.nextval, op.id, rs.id FROM gwic.tawassolation op, gwic.tawassolurces rs WHERE op.name = 'READ' AND rs.name = 'admin.organization.edit.status';


INSERT INTO gwic.tawassolurces(id, name, description)  VALUES( gwic.tawassoldata_seq.nextval, 'admin.organization.add', 'Bouton Ajouter une organization pour créer une organization');	
INSERT INTO gwic.ptawassolssion_right(id, operation_id, resource_id) 
	SELECT gwic.rtawassolata_seq.nextval, op.id, rs.id FROM gwic.otawassoltion op, gwic.rtawassolrces rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organization.add';
INSERT INTO gwic.ptawassolssion_right(id, operation_id, resource_id) 
	SELECT gwic.rtawassolata_seq.nextval, op.id, rs.id FROM gwic.otawassoltion op, gwic.rtawassolrces rs WHERE op.name = 'READ' AND rs.name = 'admin.organization.add';


INSERT INTO gwic.rtawassolrces(id, name, description) VALUES( gwic.rtawassolata_seq.nextval, 'admin.organizations.download', 'Bouton Télécharger au format CSV');			
INSERT INTO gwic.pertawassolion_right(id, operation_id, resource_id) 
	SELECT gwic.reftawassola_seq.nextval, op.id, rs.id FROM gwic.opetawassolon op, gwic.restawassoles rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organizations.download';
INSERT INTO gwic.pertawassolion_right(id, operation_id, resource_id) 
	SELECT gwic.reftawassola_seq.nextval, op.id, rs.id FROM gwic.opetawassolon op, gwic.restawassoles rs WHERE op.name = 'READ' AND rs.name = 'admin.organizations.download';


/***************************************************************************************************************************************************************/
/************************************************************************* Role Permission *********************************************************************/
/***************************************************************************************************************************************************************/
INSERT INTO gwic.roltawassolrmission(role_id, permission_id) select role.id, pr.id from gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	where op.name='READ'  and r.name='admin'  and pr.resource_id=r.id  and pr.operation_id=op.id and role.name='FILLING_CENTER';

INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.users' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.organizations' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';

INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';

INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.users' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organizations' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';


INSERT INTO gwic.roltawassolrmission(role_id, permission_id) select role.id, pr.id from gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name='READ'  and r.name='admin'  and pr.resource_id=r.id  and pr.operation_id=op.id and role.name='SIEGE';	
	
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.users' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.organizations' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';

INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.edit.auto.pdf.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r
	WHERE op.name = 'READ' AND r.name = 'admin.user.add' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.users.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.add' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'READ' AND r.name = 'admin.organizations.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';

	
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.users' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organizations' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';

INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.user.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.user.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.user.edit.auto.pdf.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r
	WHERE op.name = 'WRITE' AND r.name = 'admin.user.add' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.users.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organization.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organization.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organization.add' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.roltawassolrmission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.roltawassolle, gwic.pertawassolion_right pr, gwic.opetawassolon op, gwic.restawassoles r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organizations.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';


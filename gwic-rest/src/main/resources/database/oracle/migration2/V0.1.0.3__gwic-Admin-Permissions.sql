/***************************************************************************************************************************************************************/
/******************************************************************************** V4.9 *************************************************************************/
/***************************************************************************************************************************************************************/

DELETE FROM gwic.role_permission;
DELETE FROM gwic.permission_right;
DELETE FROM gwic.resources;
DELETE FROM gwic.operation;


/***************************************************************************************************************************************************************/
/***************************************************************************** OPERATIONS **********************************************************************/
/***************************************************************************************************************************************************************/

INSERT INTO gwic.operation (id, name, description) VALUES ( gwic.ref_data_seq.nextval, 'READ', 'READ');
INSERT INTO gwic.operation (id, name, description) VALUES ( gwic.ref_data_seq.nextval, 'WRITE', 'WRITE');
INSERT INTO gwic.operation (id, name, description) VALUES ( gwic.ref_data_seq.nextval, 'DELETE', 'DELETE');
INSERT INTO gwic.operation (id, name, description) VALUES ( gwic.ref_data_seq.nextval, 'UPDATE', 'UPDATE');
INSERT INTO gwic.operation (id, name, description) VALUES ( gwic.ref_data_seq.nextval, 'DOWNLOAD', 'DOWNLOAD');
INSERT INTO gwic.operation (id, name, description) VALUES ( gwic.ref_data_seq.nextval, 'UPLOAD', 'UPLOAD');
INSERT INTO gwic.operation (id, name, description) VALUES ( gwic.ref_data_seq.nextval, 'PRINT', 'PRINT');
INSERT INTO gwic.operation (id, name, description) VALUES ( gwic.ref_data_seq.nextval, 'ALL', 'ALL Operations');


/***************************************************************************************************************************************************************/
/************************************************************************* Permission Right ********************************************************************/
/***************************************************************************************************************************************************************/

INSERT INTO gwic.resources(id, name, description) VALUES( gwic.ref_data_seq.nextval, 'admin', 'Menu Administration');
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
		select gwic.ref_data_seq.nextval, op.id, rs.id from gwic.operation op, gwic.resources rs where op.name ='READ' and rs.name='admin';


INSERT INTO gwic.resources(id, name, description) VALUES( gwic.ref_data_seq.nextval, 'admin.users', 'Tableau utilisateurs');
INSERT INTO gwic.permission_right (id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.users';
INSERT INTO gwic.permission_right (id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.users';
	

INSERT INTO gwic.resources(id, name, description) VALUES( gwic.ref_data_seq.nextval, 'admin.user.view-edit', 'Icone oeil pour consulter/editer un utilisateur');	
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.user.view-edit';
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.user.view-edit';

	
INSERT INTO gwic.resources(id, name, description)  VALUES( gwic.ref_data_seq.nextval, 'admin.user.edit.status', 'Icone Editer pour Editer le status d''utilisateur');
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.user.edit.status';
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.user.edit.status';


INSERT INTO gwic.resources(id, name, description)  VALUES( gwic.ref_data_seq.nextval, 'admin.user.edit.auto.pdf.download', 'Téléchargement automatique du PDF');	
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.user.edit.auto.pdf.download';
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.user.edit.auto.pdf.download';


INSERT INTO gwic.resources(id, name, description)  VALUES( gwic.ref_data_seq.nextval, 'admin.user.add', 'Bouton Ajouter un utilisateur pour créer un utilisateur');	
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.user.add';
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.user.add';


INSERT INTO gwic.resources(id, name, description) VALUES( gwic.ref_data_seq.nextval, 'admin.users.download', 'Bouton Télécharger au format CSV');	
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.users.download';
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.users.download';

	
	
INSERT INTO gwic.resources(id, name, description)  VALUES( gwic.ref_data_seq.nextval, 'admin.organizations', 'Tableau organisations');
INSERT INTO gwic.permission_right (id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organizations';
INSERT INTO gwic.permission_right (id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.organizations';
	

INSERT INTO gwic.resources(id, name, description) VALUES( gwic.ref_data_seq.nextval, 'admin.organization.view-edit', 'Icone oeil pour consulter/editer une organization');		
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organization.view-edit';
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.organization.view-edit';


INSERT INTO gwic.resources(id, name, description)  VALUES( gwic.ref_data_seq.nextval, 'admin.organization.edit.status', 'Icone Editer pour Editer le status d''une organization');	
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organization.edit.status';
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.organization.edit.status';


INSERT INTO gwic.resources(id, name, description)  VALUES( gwic.ref_data_seq.nextval, 'admin.organization.add', 'Bouton Ajouter une organization pour créer une organization');	
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organization.add';
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.organization.add';


INSERT INTO gwic.resources(id, name, description) VALUES( gwic.ref_data_seq.nextval, 'admin.organizations.download', 'Bouton Télécharger au format CSV');			
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'WRITE' AND rs.name = 'admin.organizations.download';
INSERT INTO gwic.permission_right(id, operation_id, resource_id) 
	SELECT gwic.ref_data_seq.nextval, op.id, rs.id FROM gwic.operation op, gwic.resources rs WHERE op.name = 'READ' AND rs.name = 'admin.organizations.download';


/***************************************************************************************************************************************************************/
/************************************************************************* Role Permission *********************************************************************/
/***************************************************************************************************************************************************************/
INSERT INTO gwic.role_permission(role_id, permission_id) select role.id, pr.id from gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	where op.name='READ'  and r.name='admin'  and pr.resource_id=r.id  and pr.operation_id=op.id and role.name='FILLING_CENTER';

INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.users' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.organizations' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';

INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';

INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.users' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organizations' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'FILLING_CENTER';


INSERT INTO gwic.role_permission(role_id, permission_id) select role.id, pr.id from gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name='READ'  and r.name='admin'  and pr.resource_id=r.id  and pr.operation_id=op.id and role.name='SIEGE';	
	
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.users' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.organizations' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';

INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.user.edit.auto.pdf.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r
	WHERE op.name = 'READ' AND r.name = 'admin.user.add' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.users.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.organization.add' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'READ' AND r.name = 'admin.organizations.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';

	
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.users' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organizations' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';

INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.user.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.user.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.user.edit.auto.pdf.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r
	WHERE op.name = 'WRITE' AND r.name = 'admin.user.add' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.users.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organization.view-edit' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organization.edit.status' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organization.add' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';
INSERT INTO gwic.role_permission (role_id, permission_id) SELECT role.id, pr.id FROM gwic.role role, gwic.permission_right pr, gwic.operation op, gwic.resources r 
	WHERE op.name = 'WRITE' AND r.name = 'admin.organizations.download' AND pr.resource_id = r.id  AND pr.operation_id = op.id AND role.name = 'SIEGE';


package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.AffectationParentEleveBean;
import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.bean.SchoolBean;
import com.ayouris.tawassol.common.model.entity.*;
import com.ayouris.tawassol.common.model.enums.ParentingRelationship;
import com.ayouris.tawassol.repository.AffectationParentEleveRepository;
import com.ayouris.tawassol.repository.EleveRepository;
import com.ayouris.tawassol.security.service.PasswordService;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.*;
import com.github.fluent.hibernate.internal.util.InternalUtils;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPAExpressions;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author m.wannas
 */

@Service
public class EleveServiceImpl extends GenericServiceImpl2<Eleve, Long, EleveBean> implements EleveService {

    private static final int FILE_MASSAR_FIRST_LINE = 11;
    @Autowired
    private CustomModelMapper mapper;
    @Autowired
    private EleveRepository eleveRepository;
    @Autowired
    private AffectationParentEleveRepository affectationParentEleveRepository;
    @Autowired
    private ParentService parentService;
    @Autowired
    private AffectationParentEleveService affectationParentEleveService;
    @Autowired
    private ClasseService classeService;
    @Autowired
    private AffectationEleveClasseService affectationEleveClasseService;
    @Autowired
    private PasswordService passwordService;

    @Override
    public List<EleveBean> getAllByClasseId(Long classeId) {

        Iterable<Eleve> list = getElevesByClasseId(classeId);
        return mapper.map(list, EleveBean.LIST_BEAN_TYPE);
    }

    @Override
    public List<Eleve> getElevesByClasseId(Long classeId) {
        QEleve eleve = QEleve.eleve;
        QAffectationEleveClasse affectationEleveClasse = QAffectationEleveClasse.affectationEleveClasse;
        return (List<Eleve>) eleveRepository.findAll(eleve.id.in(JPAExpressions.selectFrom(affectationEleveClasse)
                .where(affectationEleveClasse.classe.id.eq(classeId)
                        .and(affectationEleveClasse.classe.active.isTrue()))
                .select(affectationEleveClasse.eleve.id)),
                new OrderSpecifier<>(Order.ASC, eleve.enabled),
                new OrderSpecifier<>(Order.DESC, eleve.createdOn));

    }


    @Override
    public List<EleveBean> getAllByParentId(Long parentId) {

        QEleve eleve = QEleve.eleve;
        QAffectationParentEleve affectationParentEleve = QAffectationParentEleve.affectationParentEleve;
        Iterable<Eleve> list = eleveRepository.findAll(eleve.enabled.isTrue().and(eleve.id.in(JPAExpressions.selectFrom(affectationParentEleve)
                .where(affectationParentEleve.parent.id.eq(parentId)
                        .and(affectationParentEleve.parent.enabled.isTrue()))
                .select(affectationParentEleve.eleve.id))));
        return mapper.map(list, EleveBean.LIST_BEAN_TYPE);
    }

    @Override
    public List<AffectationParentEleveBean> getAllByCurrentParent() throws Exception {
        User currentUser = SecurityUtils.getCurrentUser();
        if(!(currentUser instanceof Parent)) {
            throw new Exception("Forbidden");
        }
        QAffectationParentEleve affectationParentEleve = QAffectationParentEleve.affectationParentEleve;
        Iterable<AffectationParentEleve> list = affectationParentEleveRepository.findAll(affectationParentEleve.parent.id.eq(currentUser.getId())
                        .and(affectationParentEleve.eleve.enabled.isTrue()));
        return mapper.map(list, AffectationParentEleveBean.LIST_BEAN_TYPE);
    }

    private Eleve getEleveByCodeMassar(String codeMassar) {
        List<Eleve> list = eleveRepository.findByCodeMassarAndEnabledTrue(codeMassar);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public Long setParent(String codeMassar, ParentBean parentBean, ParentingRelationship parentingRelationship) {
        //TODO validators
        Eleve eleve = getEleveByCodeMassar(codeMassar);
        School school = eleve.getSchool();
        parentBean.setSchool(mapper.map(eleve.getSchool(), SchoolBean.class));
        Parent parent = parentService.createOrUpdate(parentBean);
        AffectationParentEleve affectation = new AffectationParentEleve(eleve, parent, parentingRelationship);

        affectationParentEleveService.save(affectation);
        return parent.getId();
    }

    @Override
    public boolean addStudent(String codeMassar, ParentingRelationship parentingRelationship) throws Exception {
        Boolean exist = verifierCodeMassar(codeMassar);

        if(!exist) {
            return false;
        }
        Eleve eleve = getEleveByCodeMassar(codeMassar);
        School school = eleve.getSchool();

        User currentUser = SecurityUtils.getCurrentUser();
        if(!(currentUser instanceof Parent)) {
            throw new Exception("Forbidden");
        }
        Parent parent = (Parent) currentUser;
        AffectationParentEleve affectation = new AffectationParentEleve(eleve, parent, parentingRelationship);

        affectationParentEleveService.save(affectation);
        return true;
    }

    @Override
    public void enableParent(Long id, Boolean enable) {
        //TODO validators
        AffectationParentEleve affectation = affectationParentEleveService.findOne(id);
        affectation.setEnabled(enable);
        affectationParentEleveService.save(affectation);
        Parent parent = affectation.getParent();
        parent.setEnabled(true);
        parentService.save(parent);
    }

    @Override
    public Boolean verifierCodeMassar(String codeMassar) {
        return getEleveByCodeMassar(codeMassar) != null;
    }


    @Override
    public EleveBean getEleve(Long id) {

        Eleve eleve = findOne(id);
        if (eleve == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }
        // QAffectationEleveClasse qAffectationEleveClasse = QAffectationEleveClasse.affectationEleveClasse;

        //AffectationEleveClasse affectation = findOne(qAffectationEleveClasse.eleve.id.eq(eleve.getId()));

        EleveBean elevebean = mapper.mapStrict(eleve, EleveBean.class);

        return elevebean;
    }

    @Override
    public Long create(EleveBean eleveBean) {

        validationRequiredValue(eleveBean);
        if (verifierCodeMassar(eleveBean.getCodeMassar())) {
            throw new ServiceException(ErrorMessageType.CODE_MASSAR_EXIST);
        }

        Eleve eleve = mapper.mapStrict(eleveBean, Eleve.class);
        Classe classe = mapper.mapStrict(eleveBean.getClasse(), Classe.class);

        eleve.setSchool(SecurityUtils.getCurrentSchool());
        eleve.setCurrentCycle(SecurityUtils.getCurrentCycle());
        eleve.setEnabled(false);
        eleve.setPassword(passwordService.encodePassword(eleve.getCodeMassar()));
        eleve.setUsername(eleve.getCodeMassar());
        save(eleve);

        AffectationEleveClasse affectation = new AffectationEleveClasse();
        affectation.setClasse(classe);
        affectation.setEleve(eleve);
        affectationEleveClasseService.save(affectation);

        return eleve.getId();
    }

    @Override
    public Long update(Long id, EleveBean eleveBean) {

        Eleve oldEntity = findOne(id);

        if (oldEntity == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }
        validationRequiredValue(eleveBean);

        Eleve eleveCheck = getEleveByCodeMassar(eleveBean.getCodeMassar());
        if (eleveCheck.getId() != id) {
            throw new ServiceException(ErrorMessageType.CODE_MASSAR_EXIST);
        }

        Eleve eleve = mapper.mapStrict(eleveBean, Eleve.class);
        Classe classe = mapper.mapStrict(eleveBean.getClasse(), Classe.class);

        oldEntity.setCodeMassar(eleve.getCodeMassar());
        oldEntity.setFirstname(eleve.getFirstname());
        oldEntity.setLastname(eleve.getLastname());
        oldEntity.setEtatSante(eleve.getEtatSante());
        oldEntity.setRemarque(eleve.getRemarque());
        save(eleve);

        AffectationEleveClasse affectation = affectationEleveClasseService.findByEleveIdAndClasseId(eleve.getId(), classe.getId());
        if (affectation != null) {
            affectation.setClasse(classe);
            affectationEleveClasseService.save(affectation);
        }
        return eleve.getId();
    }

    @Override
    public void delete(Long id) {
        Eleve oldEntity = findOne(id);

        if (oldEntity == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }

        delete(oldEntity);
    }

    @Override
    public 	Boolean deleteAffectation(Long idAffectation) throws Exception {
        AffectationParentEleve affectation = affectationParentEleveService.findOne(idAffectation);
        if (affectation == null) {
            throw new Exception("not found");
        }
        Parent parent = affectation.getParent();
        if (parent == null || parent.getId() != SecurityUtils.getCurrentUser().getId()) {
            throw new Exception("forbidden");
        }
        affectationParentEleveService.delete(idAffectation);
        return true;
    }

    @Override
    public int importFromMassarFileUpload(Long idClasse, InputStream is) throws Exception {


        int counter = 0;
        Workbook myWorkBook = null;
        try {
            Classe classe = classeService.findOne(idClasse);

            if (classe == null) {
                throw new Exception("Classe Not Found");
            }

            myWorkBook = WorkbookFactory.create(is);

            Sheet sheet = myWorkBook.getSheetAt(0);

            //TODO Validation if (validateMassarFile(sheet, fileUploadedName) == false) {


            for (int rowIndex = FILE_MASSAR_FIRST_LINE; rowIndex < 160; rowIndex++) {
                try {


                    Row row = sheet.getRow(rowIndex);

                    if (InternalUtils.StringUtils.isEmpty(row.getCell(3).getStringCellValue().trim())) {
                        break;
                    }
                    Eleve eleve = new Eleve();

                    eleve.setCodeMassar(row.getCell(3).getStringCellValue().trim());

                    eleve.setLastname(row.getCell(5).getStringCellValue());
                    eleve.setFirstname(row.getCell(6).getStringCellValue());
                    eleve.setSexe(getEleveSexe(row.getCell(9).getStringCellValue().trim()));
                    eleve.setDateNaissance(LocalDate.parse(row.getCell(10).getStringCellValue().trim(), DateTimeFormatter.ofPattern("d/MM/yyyy")));
                    eleve.setSchool(SecurityUtils.getCurrentSchool());
                    eleve.setCurrentCycle(SecurityUtils.getCurrentCycle());
                    eleve.setEnabled(true);
                    eleve.setUsername(eleve.getCodeMassar());
                    eleve.setPassword(eleve.getCodeMassar());


                    if (!verifierCodeMassar(eleve.getCodeMassar())) {
                        save(eleve);

                        AffectationEleveClasse affectation = new AffectationEleveClasse();
                        affectation.setClasse(classe);
                        affectation.setEleve(eleve);

                        affectationEleveClasseService.save(affectation);
                        counter++;
                    } else {
                        System.out.println(eleve.getCodeMassar() + " already exist");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

            myWorkBook.close();
        }
        return counter;
    }


    private String getEleveSexe(String value) {
        if (value.equals("أنثى")) {
            return "Fille";
        } else if (value.equals("ذكر")) {
            return "Garçon";
        } else {
            return value;
        }
    }

    void validationRequiredValue(EleveBean eleveBean) {
        if (StringUtils.isEmpty(eleveBean.getFirstname())
                || StringUtils.isEmpty(eleveBean.getLastname())
                || StringUtils.isEmpty(eleveBean.getCodeMassar())
                || eleveBean.getClasse() == null
                || eleveBean.getNiveau() == null) {
            throw new ServiceException(ErrorMessageType.MISSING_REQUIRED_FIELDS);
        }
    }

    @Override
    public void enableEleve(Long id, Boolean enable) {
        Eleve eleve = findOne(id);
        if (eleve == null) {
            throw new ServiceException(ErrorMessageType.OBJECT_NOT_EXSIST);
        }

        eleve.setEnabled(enable);
        save(eleve);

    }


    @Override
    public void enableAllEleve(Boolean enable) {

        QEleve qEleve = QEleve.eleve;
        List<Eleve> eleves = (List<Eleve>) eleveRepository.findAll(qEleve.school.id.eq(SecurityUtils.getCurrentSchool().getId()));
        for (Eleve eleve : eleves) {
            eleve.setEnabled(enable);
            save(eleve);
        }
    }
}

package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.bean.SchoolBean;
import com.ayouris.tawassol.common.model.entity.*;
import com.ayouris.tawassol.common.model.enums.ParentingRelationship;
import com.ayouris.tawassol.repository.EleveRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.*;
import com.github.fluent.hibernate.internal.util.InternalUtils;
import com.querydsl.jpa.JPAExpressions;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
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
    private ParentService parentService;
    @Autowired
    private AffectationParentEleveService affectationParentEleveService;
    @Autowired
    private ClasseService classeService;
    @Autowired
    private AffectationEleveClasseService affectationEleveClasseService;

    @Override
    public List<EleveBean> getAllByClasseId(Long classeId) {

        Iterable<Eleve> list = getElevesByClasseId(classeId);
        return mapper.map(list, EleveBean.LIST_BEAN_TYPE);
    }

    @Override
    public List<Eleve> getElevesByClasseId(Long classeId) {
        QEleve eleve = QEleve.eleve;
        QAffectationEleveClasse affectationEleveClasse = QAffectationEleveClasse.affectationEleveClasse;
        return (List<Eleve>) eleveRepository.findAll(eleve.enabled.isTrue().and(eleve.id.in(JPAExpressions.selectFrom(affectationEleveClasse)
                .where(affectationEleveClasse.classe.id.eq(classeId)
                        .and(affectationEleveClasse.classe.active.isTrue()))
                .select(affectationEleveClasse.eleve.id))));

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
        Eleve eleve = getEleveByCodeMassar(codeMassar);
        if (eleve == null) {
            return false;
        } else {
            return true;
        }
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
}

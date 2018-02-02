package ma.salamgaz.tawassol.reporting.helper;

import lombok.Getter;
import lombok.Setter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Getter
@Setter
public class BuildReport implements Serializable {
    private static final long serialVersionUID = -711818362357424456L;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // ||||||||||||||||||||||||||||||||||||||||||||||||||||| Constantes ||||||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public static final String REQUEST_KEY = "reportPath";

    public static final int PDF_REPORT = 1;
    public static final int XLS_REPORT = 2;
    public static final int DOCX_REPORT = 3;
    public static final int CSV_REPORT = 4;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // ||||||||||||||||||||||||||||||||||||||||||||||||||||| Attributs |||||||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    private String nomReport;
    private int typeReport = 1;
    private Map<String, Object> parameters = new HashMap<String, Object>();
    private List<?> listeBean;
    private boolean logo;
    private boolean corssTab = false;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // ||||||||||||||||||||||||||||||||||||||||||||||||||| Méthodes utiles |||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
//    public String buidAndGetReportPath() throws Exception {
//
//        String reportPath = null;
//        JasperPrint jasperPrint = null;
//
//        ClassLoader classLoader = getClass().getClassLoader();
//        URL url = classLoader.getResource("reports/" + nomReport + ".jasper");
//
//        if (null != url) {
//
//            File reportFile = new File(url.getFile());
//            jasperPrint = JasperFillManager.fillReport(url.getFile(), parameters, new JRBeanCollectionDataSource(listeBean));
//
//        } else {
//            url = classLoader.getResource("reports/" + nomReport + ".jrxml");
//            if (null == url) {
//                throw new FileNotFoundException();
//            }
//            String jasperPath = url.getFile();
//            File reportFile = new File(jasperPath);
//            JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
//            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(listeBean));
//        }
//
//        if (jasperPrint != null) {
//            switch (typeReport) {
//
//                case PDF_REPORT:
//                    reportPath = exportPdfReport(jasperPrint);
//                    break;
//
//                case XLS_REPORT:
//                    exportXlsReport(jasperPrint);
//                    break;
//
//                case DOCX_REPORT:
//                    // exportDocxReport(jasperPrint);
//                    break;
//
//                case CSV_REPORT:
//                    // exportCsvReport(jasperPrint);
//                    break;
//
//                default:
//                    break;
//            }
//        }
//
//        return reportPath;
//    }

    public byte[] exportToPdf() throws Exception {
        return JasperExportManager.exportReportToPdf(buidAsJasperPrint());
    }

    private JasperPrint buidAsJasperPrint()  throws Exception {
        String reportPath = null;
        JasperPrint jasperPrint = null;

        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource("reports/" + nomReport + ".jasper");

//        StringBuffer javascript = new StringBuffer();
//        javascript.append("var params= his.getPrintParams();");
//        javascript.append("params.interactive=params.constants.interactionLevel.silent;");
//        javascript.append("params.pageHandling =params.constants.handling.shrink;");
//        javascript.append("this.print(params);");
//        parameters.put(PdfExporterConfiguration.PROPERTY_PDF_JAVASCRIPT, javascript.toString());


        if (null != url) {

            File reportFile = new File(url.getFile());
            jasperPrint = JasperFillManager.fillReport(url.getFile(), parameters, new JRBeanCollectionDataSource(listeBean));

        } else {
            url = classLoader.getResource("reports/" + nomReport + ".jrxml");
            if (null == url) {
                throw new FileNotFoundException();
            }
            String jasperPath = url.getFile();
            File reportFile = new File(jasperPath);
            JasperDesign jasperDesign = JRXmlLoader.load(reportFile);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint=   JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(listeBean));
        }

        return  jasperPrint;
    }

    private String exportPdfReport(JasperPrint jasperPrint) throws Exception {

        byte[] binaryData = JasperExportManager.exportReportToPdf(jasperPrint);

        String filePath = System.getProperty("java.io.tmpdir") + UUID.randomUUID().toString();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(binaryData);
        fileOutputStream.close();

        return filePath;
    }

    private void exportXlsReport(JasperPrint jasperPrint) throws Exception {

        jasperPrint.setProperty("net.sf.jasperreports.export.xls.exclude.origin.band.1", "pageHeader");
        jasperPrint.setProperty("net.sf.jasperreports.export.xls.exclude.origin.band.2", "pageFooter");

        JRXlsExporter jrXlsExporter = new JRXlsExporter();

        jrXlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setDetectCellType(true);
        configuration.setWhitePageBackground(false);
        configuration.setRemoveEmptySpaceBetweenRows(true);
        configuration.setRemoveEmptySpaceBetweenColumns(true);
        configuration.setIgnoreCellBackground(true);
        configuration.setCollapseRowSpan(true);
        configuration.setIgnoreAnchors(true);
        configuration.setIgnoreGraphics(true);

        jrXlsExporter.setConfiguration(configuration);

        String filePath = System.getProperty("java.io.tmpdir") + UUID.randomUUID().toString();
        File destFile = new File(filePath);
        SimpleOutputStreamExporterOutput simpleOutputStreamExporterOutput = new SimpleOutputStreamExporterOutput(destFile);
        jrXlsExporter.setExporterOutput(simpleOutputStreamExporterOutput);

        jrXlsExporter.exportReport();

    }

}

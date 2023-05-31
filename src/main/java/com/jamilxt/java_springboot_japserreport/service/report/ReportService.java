package com.jamilxt.java_springboot_japserreport.service.report;

import com.jamilxt.java_springboot_japserreport.domain.report.ExportType;

import com.jamilxt.java_springboot_japserreport.model.SoapPasienModel;
import com.jamilxt.java_springboot_japserreport.repository.SoapPasienRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;

@Service
public class ReportService {

    @Autowired
    private SoapPasienRepository soapPasienRepository;

  public void downloadSkriningReport( HttpServletResponse response) throws JRException, IOException {
    exportReportSkrining( response);
  }


  public void downloadRM01Report( HttpServletResponse response, String noreg, String nama, String tglLahir, String norm, String jenisKelamin) throws JRException, IOException {
    exportRM01(response, noreg, nama, tglLahir, norm, jenisKelamin);
  }

  public  void  downloadReportAsesmentRawatJalan(HttpServletResponse response, String noreg, String nama, String tglLahir, String norm, String jenisKelamin) throws JRException, IOException {
      exportReportPasienRawatjalan(response, noreg, nama, tglLahir, norm, jenisKelamin);
  }


  private void exportReportSkrining(HttpServletResponse response)throws JRException, IOException{

    InputStream transactionReportStream =
    getClass()
        .getResourceAsStream(
                "/report/skrining_pasien_rawat_jalan.jrxml");

    String titleTransactionBy = "Skrining Pasien Rawat Jalan";
    JasperReport jasperReport = JasperCompileManager.compileReport(transactionReportStream);

  
        HashMap<String, Object> parameters = new HashMap();
        parameters.put("title", titleTransactionBy);

        JasperPrint jasperPrint =
            JasperFillManager.fillReport(jasperReport, parameters);

        JRPdfExporter exporter = new JRPdfExporter();


        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setContentType("application/pdf");
        response.setHeader(
            HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "skrining_pasien_rawat_jalan" + ".pdf;");
        exporter.exportReport();

  }


  private void exportReportPasienRawatjalan(HttpServletResponse response, String noreg, String nama, String tglLahir, String norm, String jenisKelamin)throws JRException, IOException{

      InputStream pasienReportStream = getClass().getResourceAsStream("/report/RM_RJ02F.jrxml");

      String title = "Assement_Awal_Pasien_Rawat_Jalan";
      JasperReport jasperReport = JasperCompileManager.compileReport(pasienReportStream);

      HashMap<String, Object> parameters = new HashMap();
      parameters.put("title", title);
      parameters.put("c_rm", norm);
      parameters.put("c_nama", nama);
      parameters.put("c_gender", jenisKelamin);
      parameters.put("c_tgllahir", tglLahir);



      System.out.println(parameters);

      JasperPrint jasperPrint =
              JasperFillManager.fillReport(jasperReport, parameters);

      JRPdfExporter exporter = new JRPdfExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      response.setContentType("application/pdf");
      response.setHeader(
              HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + title + ".pdf;");
      exporter.exportReport();


  }
    

  
  private void exportRM01(HttpServletResponse response, String noreg, String nama, String tglLahir, String norm, String jenisKelamin)throws JRException, IOException{
    InputStream transactionReportStream =
    getClass()
        .getResourceAsStream(
                "/report/rm01.jrxml");

        SoapPasienModel soapPasienModel = soapPasienRepository.findByNoreg(noreg);


        String title = "Skrining Pasien Rawat Jalan";
        JasperReport jasperReport = JasperCompileManager.compileReport(transactionReportStream);

        HashMap<String, Object> parameters = new HashMap();
        parameters.put("title", title);
        parameters.put("c_rm", norm);
        parameters.put("c_nama", nama);
        parameters.put("c_gender", jenisKelamin);
        parameters.put("c_tgllahir", tglLahir);
        parameters.put("c_gawat_tidaksadar", soapPasienModel.getSkriningk1());
        parameters.put("c_gawat_jari", soapPasienModel.getSkriningk2());
        parameters.put("c_gawat_sesak", soapPasienModel.getSkriningk3());
        parameters.put("c_gawat_muntah", soapPasienModel.getSkriningk4());
        parameters.put("c_gawat_kejang", soapPasienModel.getSkriningk5());
        parameters.put("c_gawat_bayi", soapPasienModel.getSkriningk6());
        parameters.put("c_fisik_kesulitan", soapPasienModel.getF1());
        parameters.put("c_fisik_kesakitan", soapPasienModel.getF2());
        parameters.put("c_fisik_gangguan", soapPasienModel.getF3());
        parameters.put("c_fisik_kelemahan", soapPasienModel.getF4());
        parameters.put("c_batuk_panjang", soapPasienModel.getB1());
        parameters.put("c_batuk_berdarah", soapPasienModel.getB2());
        parameters.put("c_resiko_jatuh", soapPasienModel.getSkriningRj());
        parameters.put("c_inter1_ya", soapPasienModel.getR1());
        parameters.put("c_inter3_ya", soapPasienModel.getR2());
        parameters.put("c_inter4_ya", soapPasienModel.getR3());
        parameters.put("c_inter2_ya", soapPasienModel.getR4());



        System.out.println(parameters);

        JasperPrint jasperPrint =
            JasperFillManager.fillReport(jasperReport, parameters);

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
        response.setContentType("application/pdf");
        response.setHeader(
            HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "rm_01" + ".pdf;");
        exporter.exportReport();

  }
    


  private void exportReport(Collection<?> beanCollection, ExportType exportType, HttpServletResponse response) throws JRException, IOException {
    InputStream transactionReportStream =
        getClass()
            .getResourceAsStream(
                "/transaction_report_" + exportType.toString().toLowerCase() + ".jrxml");
    String titleTransactionBy = "Transactions Report";

    JasperReport jasperReport = JasperCompileManager.compileReport(transactionReportStream);
    JRBeanCollectionDataSource beanColDataSource =
        new JRBeanCollectionDataSource(beanCollection);
    HashMap<String, Object> parameters = new HashMap();
    parameters.put("title", titleTransactionBy);

    JasperPrint jasperPrint =
        JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    var dateTimeNow = LocalDateTime.now().format(formatter);
    var fileName = titleTransactionBy.replace(" ", "") + dateTimeNow;

    if (exportType == ExportType.PDF) {

      JRPdfExporter exporter = new JRPdfExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      response.setContentType("application/pdf");
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".pdf;");
      exporter.exportReport();

    } else if (exportType == ExportType.EXCEL) {

      JRXlsxExporter exporter = new JRXlsxExporter();
      SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
      reportConfigXLS.setSheetNames(new String[]{titleTransactionBy});
      reportConfigXLS.setDetectCellType(true);
      reportConfigXLS.setCollapseRowSpan(false);
      exporter.setConfiguration(reportConfigXLS);
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".xlsx;");
      response.setContentType("application/octet-stream");
      exporter.exportReport();

    } else if (exportType == ExportType.CSV) {

      JRCsvExporter exporter = new JRCsvExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      var outputStream = response.getOutputStream();
      exporter.setExporterOutput((new SimpleWriterExporterOutput(outputStream)));
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".csv;");
      response.setContentType("text/csv");
      exporter.exportReport();

    } else if (exportType == ExportType.DOCX) {

      JRDocxExporter exporter = new JRDocxExporter();
      exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
      exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".docx;");
      response.setContentType("application/octet-stream");
      exporter.exportReport();

    } else {
      throw new RuntimeException("File Format isn't supported!");
    }
  }
}

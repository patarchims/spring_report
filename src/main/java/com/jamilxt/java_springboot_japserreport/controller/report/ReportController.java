package com.jamilxt.java_springboot_japserreport.controller.report;

import com.jamilxt.java_springboot_japserreport.domain.report.ExportType;
import com.jamilxt.java_springboot_japserreport.service.report.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class ReportController implements ReportApi {

  private final ReportService reportService;

  public ReportController(ReportService reportService) {
    this.reportService = reportService;
  }
  @Override
  public ResponseEntity<Void> downloadSkrining(HttpServletResponse response) throws IOException, JRException {
    reportService.downloadSkriningReport(response);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> downloadRMJ(HttpServletResponse response, String noreg, String nama, String tglLahir, String norm, String jenisKelamin) throws IOException, JRException {
    reportService.downloadRM01Report(response, noreg,  nama, tglLahir, norm,jenisKelamin);
    return ResponseEntity.ok().build();
  }

  @Override
  public ResponseEntity<Void> reportAssementRawat(HttpServletResponse response, String noreg, String nama, String tglLahir, String norm, String jenisKelamin) throws IOException, JRException {
    reportService.downloadReportAsesmentRawatJalan(response, noreg, nama, tglLahir, norm, jenisKelamin);
    return  ResponseEntity.ok().build();
  }


}

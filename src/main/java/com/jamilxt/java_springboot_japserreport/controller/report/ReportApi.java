package com.jamilxt.java_springboot_japserreport.controller.report;

import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(path = "report")
public interface ReportApi {

  @GetMapping(value ="skrining/download")
  ResponseEntity<Void> downloadSkrining(HttpServletResponse response)throws IOException, JRException;  

  @GetMapping(value ="rmj")
  ResponseEntity<Void> downloadRMJ(HttpServletResponse response, @RequestParam("noreg") String noreg,
                                   @RequestParam("nama") String nama,   @RequestParam("tglLahir") String tglLahir,
                                   @RequestParam("norm") String norm, @RequestParam("jenisKelamin") String jenisKelamin
                                   )throws IOException, JRException;

  @GetMapping(value ="rawat-jalan")
    ResponseEntity<Void>reportAssementRawat(HttpServletResponse response, @RequestParam("noreg") String noreg,
                                            @RequestParam("nama") String nama,   @RequestParam("tglLahir") String tglLahir,
                                            @RequestParam("norm") String norm, @RequestParam("jenisKelamin") String jenisKelamin
  )throws IOException, JRException;


}

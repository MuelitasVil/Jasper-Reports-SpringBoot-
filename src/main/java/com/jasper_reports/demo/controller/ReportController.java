package com.jasper_reports.demo.controller;

import com.jasper_reports.demo.model.Company;
import com.jasper_reports.demo.model.Employee;
import com.jasper_reports.demo.model.PaidEmploye;
import com.jasper_reports.demo.service.ReportService;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.jasper_reports.demo.model.ReportRequest;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/employees")
    public ResponseEntity<InputStreamResource> generateEmployeeReport(
            @RequestBody ReportRequest reportRequest) {

        try {

            
            // Extrae la información de empresa y empleados del request
            Company company = reportRequest.getCompany();
            List<Employee> employees = reportRequest.getEmployees();
            
            Employee e = new Employee();
            employees.add(0, e);

            // Prepara los parámetros para el reporte
            JRBeanArrayDataSource dataSource = new JRBeanArrayDataSource(employees.toArray());

            System.out.println(dataSource.toString());

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("DataSource", dataSource);
            parameters.put("P_empresa", company.getName());
            parameters.put("P_correo", company.getAddress());
            // Agrega otros parámetros si es necesario

            byte[] reportBytes = reportService.generateEmployeeReport(dataSource, parameters);

            System.out.println("4");
            //Convert the byte array to an InputStreamResource
            ByteArrayInputStream bis = new ByteArrayInputStream(reportBytes);
            System.out.println("5");
            InputStreamResource resource = new InputStreamResource(bis);
            System.out.println("6");

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=ReportPaid.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(resource);

        } catch (Exception e) {
            // Manejo de errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/paidEmploye")
    public ResponseEntity<InputStreamResource> generatePaid(@RequestBody PaidEmploye reportRequest) {
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("voucher_id", reportRequest.getVoucher_id());
            parameters.put("current_date", formatter.format(localDateTime));
            parameters.put("AmountPaid", reportRequest.getAmount_Paid());
            parameters.put("payment_method", reportRequest.getPayment_method());
            parameters.put("parent_name", reportRequest.getParent_name());
            parameters.put("child_name", reportRequest.getChild_name());
            parameters.put("imageDir", "classpath:/static/images/");

            System.out.println(reportRequest.getAmount_Paid());
            System.out.println(parameters.toString());

            byte[] reportBytes = reportService.generatePaid(parameters);

            // Convert the byte array to an InputStreamResource
            ByteArrayInputStream bis = new ByteArrayInputStream(reportBytes);
            InputStreamResource resource = new InputStreamResource(bis);

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=ReportPaid.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(org.springframework.http.MediaType.APPLICATION_PDF)
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

package com.jasper_reports.demo.service;

import com.jasper_reports.demo.model.Employee;

import net.sf.jasperreports.engine.*;

import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generateEmployeeReport(List<Employee> employees, Map<String, Object> parameters) throws Exception {

        String destinationPath = "/home/manuel/Desktop/JasperReports/demo/src/main/resources/static/ReportGenerated.pdf";
		String filePath = "/home/manuel/Desktop/JasperReports/demo/src/main/resources/templates/reports/Prueba1.jrxml";

        try{
            System.out.println("1-");
            JasperReport report = JasperCompileManager.compileReport(filePath);
			System.out.println("2-");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			System.out.println("3-");
            JasperExportManager.exportReportToPdfFile(print, destinationPath);

        return null;
        
        }  catch (Exception e) {
            System.out.println(e);
        }
    return null;
    }

    public byte[] generatePaid(Map<String, Object> parameters) throws Exception {
        String destinationPath = "/home/manuel/Desktop/JasperReports/demo/src/main/resources/static/ReportPaid.pdf";
		String filePath = "/home/manuel/Desktop/JasperReports/demo/src/main/resources/templates/reports/Report.jrxml";

        try{
            System.out.println("1-");
            JasperReport report = JasperCompileManager.compileReport(filePath);
			System.out.println("2-");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			System.out.println("3-");
            JasperExportManager.exportReportToPdfFile(print, destinationPath);
            return JasperExportManager.exportReportToPdf(print);
        
            
        
        }  catch (JRException e) {
            e.printStackTrace();
            throw new Exception("Error generating report", e);
        }

    }
}
/* 
System.out.println("1-");
JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
System.out.println("2-");
FileInputStream fis = new FileInputStream("/home/manuel/JaspersoftWorkspace/MyReports/Prueba1.jasper");
System.out.println("3-");
BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
System.out.println("4-");
JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream); 
System.out.println("5-");
JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
System.out.println("6-");
ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
JasperExportManager.exportReportToPdfFile(jasperPrint, "src/main/resources/reports/report.pdf");
*/

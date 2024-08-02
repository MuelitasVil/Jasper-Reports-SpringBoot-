package com.jasper_reports.demo.service;

import com.jasper_reports.demo.model.Employee;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public byte[] generateEmployeeReport(JRBeanArrayDataSource employees, Map<String, Object> parameters) throws Exception {

        String destinationPath = "/home/manuel/Desktop/JasperReports/demo/src/main/resources/static/ReportPaid.pdf";
		String filePath = "/home/manuel/Desktop/JasperReports/demo/src/main/resources/templates/reports/Prueba1.jrxml";

        try{
            System.out.println("1-");
            JasperReport report = JasperCompileManager.compileReport(filePath);
            //this.PrintParametersOfReport(report);
			System.out.println("2-");
            JasperPrint print = JasperFillManager.fillReport(report, parameters, employees);
			System.out.println("3-");
            return JasperExportManager.exportReportToPdf(print);

        }  catch (Exception e) {
            System.out.println(e);
        }
    return null;
    }

    public byte[] generatePaid(Map<String, Object> parameters) throws Exception {
        String destinationPath = "/home/manuel/Desktop/JasperReports/demo/src/main/resources/static/ReportPaid.pdf";
		String filePath = "/home/manuel/Desktop/JasperReports/demo/src/main/resources/templates/reports/Prueba2.jrxml";

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

    public void PrintParametersOfReport(JasperReport report) {
        JRParameter[] reportParameters = report.getParameters();
            System.out.println("Report Parameters:");
            for (JRParameter param : reportParameters) {
                System.out.println("Name: " + param.getName() + ", Type: " + param.getValueClassName());
            }
    }
}


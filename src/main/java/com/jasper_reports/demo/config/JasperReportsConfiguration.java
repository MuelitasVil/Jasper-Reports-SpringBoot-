/*package com.jasper_reports.demo.config;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;

@Configuration
public class JasperReportsConfiguration {

    @Bean
    public JasperReport employeeReport() throws Exception {
        try (InputStream inputStream = getClass().getResourceAsStream("/Prueba1.jasper")) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: /Prueba1.jasper");
            }
            return (JasperReport) JRLoader.loadObject(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("Error loading object from InputStream", e);
        }
    }
}*/
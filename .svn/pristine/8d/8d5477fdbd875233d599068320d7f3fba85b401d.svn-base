package de.ww.openweather.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.ww.openweather.controllers.param.OrtFilterParam;
import de.ww.openweather.controllers.param.PageParam;
import de.ww.openweather.controllers.param.SortParam;
import de.ww.openweather.export.ExportFile;
import de.ww.openweather.jasperreports.ReportGenerator;
import de.ww.openweather.utils.persistence.Ort;
import de.ww.openweather.utils.persistence.OrtSmall;
import de.ww.openweather.utils.repositories.OrtRepository;
import de.ww.openweather.utils.repositories.OrtSmallRepository;
/**
 * REST-Controller f&uuml;r de Erzeugung von JasperReports
 * 
 * @author Wolfram Welschinger
 *
 */
@RestController
@RequestMapping("/restservices")
public class ReportRestController {

	@Autowired
	private OrtRepository ortRepo;

	@Autowired
	private OrtSmallRepository ortSmallRepo;	
	
//	@Value(value = "classpath:jasperreports/ort_liste.jrxml")
	@Value(value = "classpath:jasperreports/ort_liste_mit_logo.jrxml")	
	private Resource reportJrxml;	
	
	//Property aus application.properties lesen
	@Value("${spring.datasource.url}")
	private String springDatasourceUrl;	
	
	@Value("${spring.datasource.username}")
	private String springDatasourceUsername;		
	
	@Value("${exportpath}")
	private String EXPORT_PATH;	
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	ReportGenerator reportGenerator;
	
	private Logger log = LogManager.getLogger(this.getClass().getName());
	
    /**
     * Erzeugt einen jasperReport im Ausgabeformat PDF.
     * 
     * URL: http://127.0.0.1:9999/restservices/report
     * 
     * @return ExportFile-Objekt
     */
	@RequestMapping("/report")
	public ExportFile createReport() {	
		ExportFile exportFile = null;
		try {
			log.debug(reportJrxml == null ? ">>>> reportJrxml ist null" : ">>>> reportJrxml ist nicht null!");
			if (reportJrxml != null){
				log.debug(">>>>> Resource: " + reportJrxml.getFilename());	
//				reportJrxml.getInputStream();
//				List<OrtSmall> ortList = (List<OrtSmall>) ortSmallRepo.findAll();
//				reportGenerator.generatePdfReport(ortList, reportJrxml.getInputStream());
				exportFile = reportGenerator.generatePdfReportFromJdbcConn(dataSource.getConnection(), reportJrxml.getInputStream());
				
				log.info("\n\n\n\n>>>>> Report fertig!");
				log.info("spring.datasource.url     : " + springDatasourceUrl);
				log.info("spring.datasource.username: " + springDatasourceUsername + ">>>>\n\n\n\n");					
			}
			log.debug(">>>> Erzeuge Report...........");
			
		} catch (Exception e) {
			log.error("\n\n\n\n>>>> Fehler beim Ausfuehren des Reports: " + e.getMessage() + "\n\n\n\n");
			e.printStackTrace();
		}
		
		return exportFile;
	}
	
}

package de.ww.openweather.jasperreports;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
//import com.zetcode.bean.Car;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import de.ww.openweather.export.ExportFile;
import de.ww.openweather.utils.persistence.OrtSmall;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Erzeugt einen Jasperreport aus einer Resource-Datei
 * @author Wolfram Welschinger
 *
 */
@Component
public class ReportGenerator {

	// https://stackoverflow.com/questions/36407575/how-to-get-files-from-resources-folder-spring-framework
	@Value(value = "classpath:orte_list.jrxml")
	private Resource reportJrxml;	
	
	@Autowired
	ExportFile exportFile;
	
	private Logger log = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * Erzeugt einen Jasperreport aus einer JRBeanCollectionDataSource
	 * @param orte JRBeanCollectionDataSource aus einem List-Objekt (List&lt;Ort&gt;) 
	 * @param in Inputstream (die JRXML-Datei)
	 * @throws JRException Exeption, wenn der Report nicht generiert werden konnte
	 */
    public void generatePdfReport(List<OrtSmall> orte, InputStream in) throws JRException {

//        String report = "src/main/resources/orte_list.jrxml";
//      JasperReport jreport = JasperCompileManager.compileReport(report);

        String report = "orte_list.jrxml";
        JasperReport jreport = JasperCompileManager.compileReport(in);
        
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(orte);

        HashMap params = new HashMap();

        JasperPrint jprint = JasperFillManager.fillReport(jreport, params, ds);
        
        JasperExportManager.exportReportToPdfFile(jprint,
                "ort_liste.pdf");
    }
    
	/**
	 * Erzeugt einen Jasperreport aus einer JRBeanCollectionDataSource
	 * @param jdbcConnection JRBeanCollectionDataSource aus einem List-Objekt (List&lt;Ort&gt;) 
	 * @param in Inputstream (die JRXML-Datei)
	 * @throws JRException Exeption, wenn der Report nicht erzeugt werden konnte
	 * @throws IOException I/O-Fehler
	 * @return ExportFile (Jasperreport)
	 */
    public ExportFile generatePdfReportFromJdbcConn(Connection jdbcConnection, InputStream in) throws JRException, IOException {

    		log.debug(">>>>>>>>>>> Report from Datasource...");
    		exportFile.initPdf();
    		log.debug(">>>>>>>>>>> ... exportFile: " + exportFile.getFile().getAbsolutePath());
    		
        JasperReport jreport = JasperCompileManager.compileReport(in);
        
         HashMap params = new HashMap();

 		JasperPrint jasperPrint = JasperFillManager.fillReport(
				jreport, params, jdbcConnection);
        
        JasperExportManager.exportReportToPdfFile(jasperPrint,
                exportFile.getFile().getAbsolutePath());
        
		log.debug(">>>>>>>>>>>Report " + exportFile.getFile().getAbsolutePath() + " geschrieben.");
        
		return exportFile;
		
    }    
    
}
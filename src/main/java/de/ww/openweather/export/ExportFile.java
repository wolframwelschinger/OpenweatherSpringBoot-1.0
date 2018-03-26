package de.ww.openweather.export;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExportFile {

	private File file;
	private String url;
	
	@Value("${exportpath}")
	private String EXPORT_PATH;
	
	private Logger log = LogManager.getLogger(this.getClass().getName());
	
	public ExportFile() {
		super();
	}
	
	public void initPdf() throws IOException {
		log.debug(">>>>>>>>>> EXPORT_PATH: " + EXPORT_PATH);
		File dir = new File (EXPORT_PATH);
		if (!dir.exists()) {
			boolean success = dir.mkdirs();
			if (!success) {
				throw new IOException("Das Verzeichnis " + dir.getAbsolutePath() + " konnte nicht angelegt werden!");
			}
		}
		File tempFile = File.createTempFile("report", ".pdf", new File(EXPORT_PATH));
		setFile(tempFile);
		setUrl(tempFile.getAbsolutePath());
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

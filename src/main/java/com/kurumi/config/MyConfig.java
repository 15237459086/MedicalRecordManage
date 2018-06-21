package com.kurumi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 配置文件读取
 * @author lyh
 *
 */
@Configuration
@PropertySource(value="classpath:application.properties") 
public class MyConfig {

	
	@Value("${config.remote.login.url}")
	private String remoteLoginUrl;
	
	@Value("${config.image.resource.path}")
	private String imageRecourcePath;
	
	@Value("${config.pdf.resource.path}")
	private String pdfRecourcePath;
	
	@Value("${config.json.resource.path}")
	private String jsonRecourcePath;
	
	@Value("${config.current.version}")
	private String currentVersion;
	
	@Value("${config.borrow.limit.day}")
	private String borrowLimitDay;

	@Value("${config.page.index.pdf.template.path}")
	private String pageIndexpPdfTemplatePath;
	
	@Value("${config.worker.signature.path}")
	private String workerSignaturePath;

	
	public String getRemoteLoginUrl() {
		return remoteLoginUrl;
	}

	public void setRemoteLoginUrl(String remoteLoginUrl) {
		this.remoteLoginUrl = remoteLoginUrl;
	}

	public String getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}

	public String getImageRecourcePath() {
		return imageRecourcePath;
	}

	public void setImageRecourcePath(String imageRecourcePath) {
		this.imageRecourcePath = imageRecourcePath;
	}

	public String getPdfRecourcePath() {
		return pdfRecourcePath;
	}

	public void setPdfRecourcePath(String pdfRecourcePath) {
		this.pdfRecourcePath = pdfRecourcePath;
	}
	
	
	
	
	public String getJsonRecourcePath() {
		return jsonRecourcePath;
	}

	public void setJsonRecourcePath(String jsonRecourcePath) {
		this.jsonRecourcePath = jsonRecourcePath;
	}

	public int getBorrowLimitDay(){
		int defaultLimitDay = 7;
		if(this.borrowLimitDay != null){
			try {
				return Integer.parseInt(borrowLimitDay);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return defaultLimitDay;
	}

	public String getPageIndexpPdfTemplatePath() {
		return pageIndexpPdfTemplatePath;
	}

	public String getWorkerSignaturePath() {
		return workerSignaturePath;
	}

	
	
	
}

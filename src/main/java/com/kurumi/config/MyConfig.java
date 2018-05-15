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
	
	@Value("${config.current.version}")
	private String currentVersion;

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
	
	
	
	
}

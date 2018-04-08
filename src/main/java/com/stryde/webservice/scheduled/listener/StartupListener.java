package com.stryde.webservice.scheduled.listener;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartupListener implements ApplicationListener<ApplicationReadyEvent>{
	
//	private static final Logger logger = LoggerFactory.getLogger(StartupListener.class);
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
//		logger.info("========== StartupListener ==============");
//		cleanOAuthService.cleanClientDetails();
//		logger.info("========== StartupListener end ==========");
	}

}

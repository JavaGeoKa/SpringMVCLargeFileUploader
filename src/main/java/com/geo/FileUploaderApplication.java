package com.geo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class FileUploaderApplication {

	static Logger log = LoggerFactory.getLogger(FileUploaderApplication.class);

	public static void main(String[] args) {


//		SpringApplication.run(FileUploaderApplication.class, args);
		new SpringApplicationBuilder()
				.bannerMode(Banner.Mode.OFF)
				.logStartupInfo(false)
				.profiles("prod","cloud")
				.listeners(new ApplicationListener<ApplicationEvent>() {
					@Override
					public void onApplicationEvent(ApplicationEvent event) {
						log.info("#### > " + event.getClass().getCanonicalName());
					}
				})
				.sources(FileUploaderApplication.class)
				.run(args);


	}

}

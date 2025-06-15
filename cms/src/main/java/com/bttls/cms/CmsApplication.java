package com.bttls.cms;

import com.bttls.config.CommonPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CommonPropertiesConfig.class)
@SpringBootApplication(scanBasePackages = "com.bttls")
public class CmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsApplication.class, args);
	}

}

package com.bttls.whs1;

import com.bttls.config.CommonPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CommonPropertiesConfig.class)
@SpringBootApplication(scanBasePackages = {"com.bttls"})
public class Whs1Application {

	public static void main(String[] args) {
		SpringApplication.run(Whs1Application.class, args);
	}

}

package qv.com.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class PhoneShopWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneShopWebApplication.class, args);
	}

}

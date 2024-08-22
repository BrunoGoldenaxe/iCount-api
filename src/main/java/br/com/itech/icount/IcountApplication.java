package br.com.itech.icount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class IcountApplication {

	public static void main(String[] args) {
		SpringApplication.run(IcountApplication.class, args);
	}

}

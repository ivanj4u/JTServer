package co.id.aribanilia.jtserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ivan_j4u
 * Feb 9, 2017
 */
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class JTServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JTServerApplication.class, args);
	}
}

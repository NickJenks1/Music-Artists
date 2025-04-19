package artists;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Annotation that tells Spring Boot that this is the application
 */
@SpringBootApplication
public class ArtistsApplication {

	/*
	 * Method that starts Spring Boot
	 */
	public static void main(String[] args) {
		SpringApplication.run(ArtistsApplication.class, args);

	}

}

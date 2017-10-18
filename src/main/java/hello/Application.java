package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner demo(ApplicantRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Applicant("Jack", "Bauer", "Jack@Bauer.com"));
			repository.save(new Applicant("Chloe", "O'Brian", "Chloe@Obrian.com"));
			repository.save(new Applicant("Kim", "Bauer", "kim@Bauer.com"));
			repository.save(new Applicant("David", "Palmer", "david@Palmer.com"));
			repository.save(new Applicant("Michelle", "Dessler", "Michaelle@Dessler.com"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Applicant customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Applicant customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			for (Applicant bauer : repository.findByLastName("Bauer")) {
				log.info(bauer.toString());
			}
			log.info("");
		};
	}

}

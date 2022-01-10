package de.evoila.datajpa;

import de.evoila.datajpa.models.User;
import de.evoila.datajpa.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DataJpaApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(DataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		userRepository.save(new User("Hans", "Flick"));
		userRepository.save(new User("Joachim", "Löw"));
		userRepository.save(new User("Dirk", "Nowitzki"));
		userRepository.save(new User("Sebastian", "Vettel"));

		log.info("All Users");
		List<User> listOfUsers = userRepository.findAll();

		for (User user : listOfUsers) {
			log.info(user.toString());
		}

		log.info("Find by id = 3");
		User foundUser = userRepository.findById(3);
		log.info(foundUser.toString());


		log.info("Find by lastName = Löw");
		List<User> foundUsers = userRepository.findByLastName("Löw");

		for (User user : foundUsers) {
			log.info(user.toString());
		}

	}
}

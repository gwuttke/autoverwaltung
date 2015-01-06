package de.gw.auto.hibernate.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import de.gw.auto.SpringApplicationContext;
import de.gw.auto.domain.Benutzer;
import de.gw.auto.repository.UserRepository;

public class User {
	

	
	
	private UserRepository userRepository = (UserRepository) SpringApplicationContext.getBean(UserRepository.class);

	public void test() {
		Benutzer test = new Benutzer("Wuttke", "Georg", "georg.wuttke", "123",
				"georg1wuttke@gmail.com");

		List<Benutzer> users = new ArrayList<Benutzer>();

		users.add(test);

		System.out.println("before insert (no id):");
		for (final Benutzer u : users) {
			System.out.format("  -> %s\n", u);
		}
		System.out.println();

		// persist users
		userRepository.save(users);
		System.out.println("after insert (with id):");
		for (final Benutzer u : users) {
			System.out.format("  -> %s\n", u);
		}
		System.out.println();

		// find users by username
		final String pattern = "%Ge%";
		System.out.format("users with username like %s\n", pattern);
		/*
		 * final List<Benutzer> foundUsers = userRepository
		 * .findByVornameLike(pattern);
		 * 
		 * for (final Benutzer u : foundUsers) { System.out.format("  -> %s\n",
		 * u); } System.out.println();
		 * 
		 * // update user if (!foundUsers.isEmpty()) { final Benutzer dbUser =
		 * foundUsers.get(1); dbUser.setBenutzername(dbUser.getName() + "." +
		 * dbUser.getVorname()); userRepository.save(dbUser);
		 * 
		 * final Benutzer updatedDbUser = userRepository.findOne(dbUser
		 * .getId()); System.out.format(" after update:\n  -> %s\n\n",
		 * updatedDbUser); }
		 */
		// get all users from db
		System.out.println("all users:");
		for (final Benutzer u : userRepository.findAll()) {
			System.out.format("  -> %s\n", u);
		}
		System.out.println();
	}

}

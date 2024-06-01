package com.trainin.controller;

import com.trainin.persist.model.User;
import com.trainin.persist.repo.UserRepository;
import jakarta.annotation.Nonnull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The UserController class is a REST controller that handles HTTP requests related to the User entity.
 * It is responsible for handling CRUD operations for the User entity.
 * The class uses the UserRepository and UserService classes to interact with the database and perform business logic.
 * The class defines several endpoints for retrieving, adding, updating, and deleting users.
 * The class also performs validation on the email format before adding a new user.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserRepository userRepository;
//	private final UserService userService;

	/**
	 * The constructor of the UserController class takes a UserRepository object as a parameter.
	 * @param userRepository
	 */
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
//		this.userService = new UserService(userRepository);
    }
	/**
	 * The getUsers method is a GET endpoint that returns a list of all users in the database.
	 * @return a list of User objects
	 */
	@GetMapping
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	/**
	 * The NewUserRequest record is a data class that represents the request body for adding a new user.
	 * It contains the first name, last name, and email of the user.
	 */
	record NewUserRequest(
			String firstName,
			String lastName,
			String email
			) {}

	/**
	 * The addUser method is a POST endpoint that adds a new user to the database.
	 * @param newUserRequest
	 */
	@PostMapping
	public void addUser(@Nonnull @RequestBody NewUserRequest newUserRequest) {
//		if (userService.emailFormatValidation(newUserRequest.email())) {
//			throw new IllegalArgumentException("Invalid email format");
//		}
		User user = new User();
		user.setFirstName(newUserRequest.firstName());
		user.setLastName(newUserRequest.lastName());
		user.setEmail(newUserRequest.email());
		userRepository.save(user);
	}
	/**
	 * The deleteUser method is a DELETE endpoint that deletes a user from the database by their ID.
	 * @param id the ID of the user to delete
	 */
	@DeleteMapping("{id}")
	public void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
	}
	/**
	 *
	 */
	@DeleteMapping("all")
	public void deletUsersAll() {
		userRepository.deleteAll();
	}
	/**
	 * The updateUser method is a PUT endpoint that updates the details of a user in the database.
	 * @param id the ID of the user to update
	 * @param newUserRequest the updated details of the user
	 */
	@PutMapping("{id}")
	public void updateUser(@PathVariable Integer id, @RequestBody NewUserRequest newUserRequest) {
		User user = userRepository.findById(id).orElseThrow();
		user.setFirstName(newUserRequest.firstName());
		user.setLastName(newUserRequest.lastName());
		user.setEmail(newUserRequest.email());
		userRepository.save(user);
	}
}

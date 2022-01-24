package net.codejava;

import static org.assertj.core.api.Assertions.assertThat;

//import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)


public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
//	
//	@Autowired
//	private HttpServletRequest request;
	
	
	@Test
	public void testCreateUser() {
	    User user = new User();
	    user.setEmail("Tarun@gmail.com");
	    user.setPassword("Ray*&^");
	    user.setFirstname("Tarun");
	    user.setLastname("Jai");
	    user.setUsername("TJai");
//	    user.setIPaddress(request.getRemoteHost());
	     
	    User savedUser = repo.save(user);   
	    User existUser = entityManager.find(User.class, savedUser.getIdUser());	     
	    assertThat(user.getEmail()).isEqualTo(existUser.getEmail());
	     
	}
	
	@Test
	public void testFindUserByEmail() {
		String email = "Tarun@gmail.com";
		
		User user = repo.findByEmail(email);
		assertThat(user).isNotNull();
	}


}

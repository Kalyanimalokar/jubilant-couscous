package net.codejava;


import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.bytebuddy.utility.RandomString;



@Controller
public class AppController {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@GetMapping("")
	public String viewHomePage() {
		return "index";
	}
	
	@GetMapping("/register")
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		String randomcode = RandomString.make(64);
		user.setVerificationCode(randomcode);
		repo.save(user);
		String siteURL = Utility.getSiteURL(request);
		sendVerificationEmail(user, siteURL);
		
		
		return "register_success";
	}
	
	
	public void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {
		String subject = "Please verify your email";
		String sendername = "Project team";
		String mailContent = "<p> Dear " + user.getUsername() +", </p>";
		mailContent += "<p> Please click the link below to verify your email address: </p>";
		
		String verifyURL = siteURL + "/verify?code" + user.getVerificationCode();
		mailContent += "<h3><a href=\"" + verifyURL +"\">VERIFY</a></h3>";
		mailContent += "<p>Thank you!</p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("sashinvk975@gmail.com", sendername);
		helper.setTo(user.getEmail());
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		mailSender.send(message);
	}

	@GetMapping("/list_users")
	public String viewUserList(Model model) {
		List<User> listUsers = repo.findAll();
		model.addAttribute("listUsers", listUsers);
		return "users";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "redirect:/";
	}
}



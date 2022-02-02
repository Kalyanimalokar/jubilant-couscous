package net.codejava;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;






@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	@Column(nullable = false,unique=true,length = 45)
	private String email;
	
	@Column(nullable = false,unique=true,length = 45)
	private String firstname;
	
	@Column(nullable = false,unique=true,length = 45)
	private String lastname;
	
	@Column(nullable = false,unique=true,length = 45)
	private String username;
	
	@Column(nullable = true)
	private String IPaddress;
	
	@Column(nullable = false,length = 64)
	private String password;
	
	@Column(name = "verification_code", updatable = false)
	private String verificationCode;
	
	public Long getIdUser() {
		return idUser;
	}
	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIPaddress() {
		return IPaddress;
	}
	public void setIPaddress(String iPaddress) {
		IPaddress = iPaddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}	
	
	
}

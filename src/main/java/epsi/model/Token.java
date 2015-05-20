package epsi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class Token {

	@Id
	@GeneratedValue
	private long id;

	private String value;

	@Column(name = "expiration_date")
	private Date expirationDate;
	
	@ManyToOne
	@JoinColumn(name = "user_fk")
	private User user;
	
	public Token(){
		
	}
	
	public Token(String value, User user, Date expirationDate){
		this.value = value;
		this.user  = user;
		this.expirationDate = expirationDate;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

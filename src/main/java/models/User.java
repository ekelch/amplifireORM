package models;

import java.util.Objects;

import annotations.GeneratedId;
import annotations.NonId;
import annotations.NonIdGetter;

public class User {
	
	@GeneratedId
	private long userId;
	
	@NonId
	private String username;
	
	@NonId
	private String password;
	
	@NonId
	private String email;
	
	
	public User(long userId, String username, String password, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	
	

	
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@NonIdGetter
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@NonIdGetter
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NonIdGetter
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "user [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}
	
	
	
}
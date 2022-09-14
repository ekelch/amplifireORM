package models;

import java.util.Objects;

import annotations.GeneratedId;
import annotations.GeneratedIdSetter;
import annotations.NonId;
import annotations.NonIdGetter;
import annotations.NonIdSetter;

public class User {
	
	@GeneratedId(column=1)
	private long userId;
	
	@NonId(column=2)
	private String username;
	
	@NonId(column=3)
	private String password;
	
	@NonId(column=4)
	private String email;
	
	
	public User(long userId, String username, String password, String email) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	
	public User() {
		this.userId = -1;
	}


	public long getUserId() {
		return userId;
	}
	
	@GeneratedIdSetter(column=1)
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@NonIdGetter(column=2)
	public String getUsername() {
		return username;
	}
	@NonIdSetter(column=2)
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NonIdGetter(column=3)
	public String getPassword() {
		return password;
	}

	@NonIdSetter(column=3)
	public void setPassword(String password) {
		this.password = password;
	}

	@NonIdGetter(column=4)
	public String getEmail() {
		return email;
	}

	@NonIdSetter(column=4)
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

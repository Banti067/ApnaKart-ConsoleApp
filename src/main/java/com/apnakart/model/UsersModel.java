package com.apnakart.model;

public class UsersModel {

	// User Required fields with Encapsulation

	private int userId; 
	private String name;
	private String email;
	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = normalizeName(name);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = normalizeEmail(email);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = normalizePassword(password);
	}

	
    private String normalizeName(String value) {
        if (value == null) return "";
        return value.trim().replaceAll("\\s+", " ");
    }

    
    private String normalizeEmail(String value) {
        if (value == null) return "";
        return value.trim().replaceAll("\\s+", "").toLowerCase();
    }

   
    private String normalizePassword(String value) {
        if (value == null) return "";
        return value.trim();
    }
	

}

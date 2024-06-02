package com.openbook.Dao;

public class user {
	
private Long id;
private String username;
private String password;
private byte[] salt;
private byte[] passwordHash;
private String email;
private Long dp_id;


public Long getId() {
    return id;
}

public void setId(Long id) {
    this.id = id;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public void setPassword(String password) {
    this.password = password;
}
public String getPassword() {
    return password;
}

public byte[] getSalt() {
    return salt;
}

public void setSalt(byte[] salt) {
    this.salt = salt;
}

public byte[] getPasswordHash() {
    return passwordHash;
}

public void setPasswordHash(byte[] passwordHash) {
    this.passwordHash = passwordHash;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public Long getDp_id() {
	return dp_id;
}

public void setDp_id(Long dp_id) {
	this.dp_id = dp_id;
}

}

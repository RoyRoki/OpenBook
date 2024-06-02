package com.openbook.quary;

public class myQuary {
 public static String addUsers = "INSERT INTO users (username, email, salt, passwordHash, dp_id) VALUES (?, ?, UNHEX(?), UNHEX(?), ?)";
 public static String findUser = "SELECT username, email, salt, passwordHash FROM users WHERE username = ?";
 public static String userId = "SELECT id FROM users WHERE username = ?";
 public static String insertTextPost = "INSERT INTO posts (user_id, content) VALUES (?, ?)";
 public static String retrieveposts = "SELECT posts.id, users.username, posts.content, posts.created_at FROM posts JOIN users ON posts.user_id = users.id WHERE posts.user_id = ? ORDER BY posts.created_at DESC";
 public static String userName = "SELECT username FROM users WHERE id = ?";
 public static String allUsers ="SELECT posts.id, posts.user_id, posts.content, posts.created_at FROM posts ORDER BY posts.created_at DESC";
public static String isPresent="SELECT COUNT(*) from users where username = ?";
public static String findAccount = "SELECT id, username, email, dp_id FROM users WHERE username = ?";
public static String upname = "UPDATE users SET username=? WHERE username=?";
public static String isEmailPresent="SELECT COUNT(*) from users where email = ?";
public static String upemail="UPDATE users SET email=? WHERE username=?";
public static String upPass="UPDATE users SET salt=UNHEX(?), passwordHash=UNHEX(?) where username=?";

public static String addPhoto ="INSERT INTO user_photos (user_id, photo) values (?, ?)";
public static String upDP = "UPDATE users SET dp_id = ? WHERE id=?";
public static String getPhotos = "SELECT ph_id, user_id, photo, created_at FROM user_photos ORDER BY ph_id DESC";
public static String getPhotosById = "SELECT photo FROM user_photos WHERE ph_id=?";
public static String ph_id= "SELECT dp_id FROM users WHERE username = ?";
}

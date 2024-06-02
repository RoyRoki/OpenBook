package com.openbook.Dao;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface userDao {
	boolean isValidUser(String username, String password);
    boolean addUser(user user);
    boolean deleteUser(String username, String password);
	long getId(String username);
	long getPh_Id(String username);
	boolean insertTextPost(long userId, String content);
	String getName(long id);
	boolean isPresent(String username);
	String[] getuser(String username);
	int upname(String name, String pass, String newname);
	int upemail(String username, String pass, String newemail);
	boolean upPassWord(String username, String oldpass, String newpass);
	Long addPhoto(Long user_id, InputStream file);
	boolean updateDP(Long user_id, Long ph_id);
	List<media> getPhotos() throws SQLException;
	byte[] getPhotoById(Long ph_id);
}

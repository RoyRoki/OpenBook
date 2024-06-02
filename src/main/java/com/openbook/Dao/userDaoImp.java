package com.openbook.Dao;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.openbook.quary.myQuary;
import com.openbook.util.dataBaseConn;
import com.openbook.util.passwords;
public class userDaoImp implements userDao {

	@Override
	public boolean isValidUser(String username, String password) {
		
		try (Connection conn = dataBaseConn.getConn();
	             PreparedStatement pstmt = conn.prepareStatement(myQuary.findUser)) {
	            
	            pstmt.setString(1, username);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    byte[] salt = rs.getBytes("salt");
	                    byte[] storedHash = rs.getBytes("passwordHash");
	                    return passwords.isExpectedPassword(password.toCharArray(), salt, storedHash);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	

	@Override
	public boolean addUser(user user) {
		
		byte[] salt = passwords.getNextSalt();
		byte[] passwordHash = passwords.hash(user.getPassword().toCharArray(), salt);
		
        String saltHex = bytesToHex(salt);
        String passwordHashHex = bytesToHex(passwordHash);
        
        try (
        		Connection conn = dataBaseConn.getConn();
                PreparedStatement pstmt = conn.prepareStatement(myQuary.addUsers)) {
               
               pstmt.setString(1, user.getUsername());
               pstmt.setString(2, user.getEmail());
               pstmt.setString(3, saltHex);
               pstmt.setString(4, passwordHashHex);
               pstmt.setLong(5,-1);
               
               pstmt.executeUpdate();
           } catch (SQLException e) {
               e.printStackTrace();
           }
        
		return true;
	}

	@Override
	public boolean deleteUser(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }



	@Override
	public long getId(String username) {
		try (Connection conn = dataBaseConn.getConn();
	             PreparedStatement pstmt = conn.prepareStatement(myQuary.userId)) {
	            pstmt.setString(1, username);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getLong("id");
	                } 
	              } catch (SQLException e) {
	  	            e.printStackTrace();
	  	        }
	                return -1; // User not found  
	            }
		catch (SQLException e) {
            e.printStackTrace();
        }
		return -1;
	        }
	
	
	 public  boolean insertTextPost(long userId, String content) {
	        try (Connection conn = dataBaseConn.getConn();
	             PreparedStatement pstmt = conn.prepareStatement(myQuary.insertTextPost)) {
	            pstmt.setLong(1, userId);
	            pstmt.setString(2, content);
	            pstmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }



	@Override
	public String getName(long id) {
		try (Connection conn = dataBaseConn.getConn();
	             PreparedStatement pstmt = conn.prepareStatement(myQuary.userName)) {
	            pstmt.setLong(1, id);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getString("username");
	                } 
	              } catch (SQLException e) {
	  	            e.printStackTrace();
	  	        }
	                return "1"; // User not found  
	            }
		catch (SQLException e) {
           e.printStackTrace();
       }
		return "1";
	}



	@Override
	public boolean isPresent(String username) {
		try (Connection conn = dataBaseConn.getConn();
				PreparedStatement pstm = conn.prepareStatement(myQuary.isPresent);) {
			pstm.setString(1, username);

			 try (ResultSet rs = pstm.executeQuery()) {
	                if (rs.next()) {
	                	if (rs.getInt(1) != 0) {
	        	            return true;
	        	        }
	                } 
	              } catch (SQLException e) {
	  	            e.printStackTrace();
	  	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public String[] getuser(String username) {
		try (Connection conn = dataBaseConn.getConn();
				PreparedStatement pstm = conn.prepareStatement(myQuary.findAccount);) {
			pstm.setString(1, username);
			
			try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                   String[] str = new String[4];
                   str[0] =String.format("%05d", rs.getLong(1));
                   str[1] = rs.getString(2);
                   str[2] = rs.getString(3);
                   str[3] =String.format("%d", rs.getLong(4));
                   return str;
                } 
              } catch (SQLException e) {
  	            e.printStackTrace();
  	        }
                return null; // User not found  
            }
	catch (SQLException e) {
       e.printStackTrace();
   }
	return null;
}



	@Override
	public int upname(String oldname, String pass, String newname) {
		boolean present = isPresent(newname);
		if(isValidUser(oldname, pass) && !present) {
			try (Connection conn = dataBaseConn.getConn();
					PreparedStatement pstm = conn.prepareStatement(myQuary.upname);){
				pstm.setString(1, newname);
				pstm.setString(2, oldname);
				boolean bl = pstm.execute();//false if successful
				if(!bl) return 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (present) return 2;
		
			return 1;
		}



	@Override
	public int upemail(String username, String pass, String newemail) {
		boolean present = isEmailPresent(newemail);
		if(isValidUser(username, pass) && !present) {
			try (Connection conn = dataBaseConn.getConn();
					PreparedStatement pstm = conn.prepareStatement(myQuary.upemail);){
				pstm.setString(1, newemail);
				pstm.setString(2, username);
				boolean bl = pstm.execute();//false if successful
				if(!bl) return 0;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (present) return 2;
		
			return 1;
	}



	private boolean isEmailPresent(String newemail) {
		try (Connection conn = dataBaseConn.getConn();
				PreparedStatement pstm = conn.prepareStatement(myQuary.isEmailPresent);) {
			pstm.setString(1, newemail);

			 try (ResultSet rs = pstm.executeQuery()) {
	                if (rs.next()) {
	                	if (rs.getInt(1) != 0) {
	        	            return true;
	        	        }
	                } 
	              } catch (SQLException e) {
	  	            e.printStackTrace();
	  	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public boolean upPassWord(String username, String oldpass, String newpass) {
		if(isValidUser(username, oldpass)) {
			
			user user = new user();
	        user.setUsername(username);
	        user.setPassword(newpass);
	        
			byte[] salt = passwords.getNextSalt();
			byte[] passwordHash = passwords.hash(user.getPassword().toCharArray(), salt);
			
			String saltHex = bytesToHex(salt);
	        String passwordHashHex = bytesToHex(passwordHash);

	        
	        try (
	        		Connection conn = dataBaseConn.getConn();
	                PreparedStatement pstmt = conn.prepareStatement(myQuary.upPass)) {
	               
	               pstmt.setString(1, saltHex);
	               pstmt.setString(2, passwordHashHex);
	               pstmt.setString(3, username);
	               
	               boolean bl = pstmt.execute();
	               if(!bl) {
	            	   return true;
	               }
	           } catch (SQLException e) {
	               e.printStackTrace();
	           }
	        
		}
		return false;
	}



	@Override
	public Long addPhoto(Long user_id, InputStream file) {
		Long ph_id = null;

		try (Connection connection = dataBaseConn.getConn();
				PreparedStatement pstm = connection.prepareStatement(myQuary.addPhoto, Statement.RETURN_GENERATED_KEYS)) {

			pstm.setLong(1, user_id);
			if (file != null) {
				pstm.setBlob(2, file);
			}

			if (pstm.executeUpdate() > 0) {
	            // Retrieve the generated keys
	            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    ph_id = generatedKeys.getLong(1); 
	                }
	            }
	        }
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return ph_id;
	}



	@Override
	public boolean updateDP(Long user_id, Long ph_id) {
		try(Connection conn = dataBaseConn.getConn();
				PreparedStatement pstm = conn.prepareStatement(myQuary.upDP);) {
			pstm.setLong(1, ph_id);
			pstm.setLong(2, user_id);
			
			boolean bl = pstm.execute(); //false if successful
			return !bl;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}



	@Override
	public List<media> getPhotos() throws SQLException {
		
		List<media> mediaList = new ArrayList<>();
		
		try(Connection conn = dataBaseConn.getConn();
				PreparedStatement pstm = conn.prepareStatement(myQuary.getPhotos);) {
			ResultSet rs = pstm.executeQuery();
			
			
			while (rs.next()) {
	            Long user_id = rs.getLong("user_id");
	            String name = getName(user_id);
	            byte[] photo = rs.getBytes("photo");
	            String createdAt = rs.getString("created_at");
	            mediaList.add(new media(name, photo, createdAt));
	        }
			return mediaList;
		} 
	}



	@Override
	public byte[] getPhotoById(Long ph_id) {
		byte[] photo = null;
		try(Connection conn = dataBaseConn.getConn();
				PreparedStatement pstm = conn.prepareStatement(myQuary.getPhotosById);) {
			
			pstm.setLong(1, ph_id);
			ResultSet rs = pstm.executeQuery();
			
		    while (rs.next()) {

	            photo = rs.getBytes("photo");
	            return photo;
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	 return null;
	}



	@Override
	public long getPh_Id(String username) {
		try (Connection conn = dataBaseConn.getConn();
	             PreparedStatement pstmt = conn.prepareStatement(myQuary.ph_id)) {
	            pstmt.setString(1, username);
	            try (ResultSet rs = pstmt.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getLong("dp_id");
	                } 
	              } catch (SQLException e) {
	  	            e.printStackTrace();
	  	        }
	                return -1; // User not found  
	            }
		catch (SQLException e) {
           e.printStackTrace();
       }
		return -1;
	}
}



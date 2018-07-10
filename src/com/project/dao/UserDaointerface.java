package com.project.dao;

import java.util.ArrayList;

import com.project.reversepojos.*;

public interface UserDaointerface {
 	public Long adduser(TableUser ref);
 	public TableUser getLoginUserDetail(String user_email,String user_password);
	public Long checkUser(String useremail);
	public TableUser checkUserById(long id);
	public String forgetpassword(String useremail);
	public TableUserImage saveimage(TableUserImage userimage);
	public void updateUser(TableUser getuserdetailbylogin);
	public TableUserImage restoreimage(TableUser userref);
	public ArrayList<TableUser> getallusers();
	public void deleteUser(TableUser olddataofuser);
	public void changeUserPasswordByHashCode(TableUser tbuser);
	public void AddUserImage(TableUserImage imageref);
	
}

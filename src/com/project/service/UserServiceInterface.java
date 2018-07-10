package com.project.service;
import java.util.ArrayList;

import com.project.reversepojos.*;

public interface UserServiceInterface {
	public Long AddUserService(TableUser ref);
	public TableUser getLoginUserDetail(String useremail,String password);
	public Long checkUser(String useremail);
	public TableUser checkUserById(long userid);
	public String forgetpassword(String useremail);
	public TableUserImage restoreimage(TableUser ref);
	public void updateUser(TableUser getuserdetailbylogin);
	public TableUserImage saveimage(TableUserImage userimageref);
	public ArrayList<TableUser> getallusers();
	public void deleteUser(TableUser olddataofuser);
	public void changeUserPasswordByHashCode(TableUser tbuser);
	public void AddUserImage(TableUserImage imageref);
}

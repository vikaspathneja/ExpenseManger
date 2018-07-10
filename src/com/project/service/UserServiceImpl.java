package com.project.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.reversepojos.TableUser;
import com.project.reversepojos.TableUserImage;
import com.project.dao.*;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInterface {
	@Autowired
	UserDaointerface ref;
	
	@Override
	public Long AddUserService(TableUser tableuserref) {
		return ref.adduser(tableuserref);
	}

	@Override
	public TableUser getLoginUserDetail(String user_email, String user_password) {
		System.out.println("service:" + user_email+" "+ user_password);
		return ref.getLoginUserDetail(user_email, user_password);
	}

	@Override
	public Long checkUser(String useremail) {
		return ref.checkUser(useremail);
		
	}

	@Override
	public String forgetpassword(String useremail) {
		return ref.forgetpassword(useremail);
		
	}
	@Override
	public TableUser checkUserById(long id) {
		return ref.checkUserById(id);
	}

	@Override
	public void updateUser(TableUser getuserdetailbylogin) {
		ref.updateUser(getuserdetailbylogin);
		
	}

	@Override
	public  TableUserImage saveimage(TableUserImage userimageref) {
		return ref.saveimage(userimageref);

	}

	@Override
	public TableUserImage restoreimage(TableUser userref) {
		return ref.restoreimage(userref);
	}

	@Override
	public ArrayList<TableUser> getallusers() {
		return ref.getallusers();
	}

	@Override
	public void deleteUser(TableUser olddataofuser) {
		ref.deleteUser(olddataofuser);
	}

	@Override
	public void changeUserPasswordByHashCode(TableUser tbuser) {
		ref.changeUserPasswordByHashCode(tbuser);
	}

	@Override
	public void AddUserImage(TableUserImage imageref) {
		ref.AddUserImage(imageref);
	}

	
}

package com.project.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.reversepojos.TableUser;
import com.project.reversepojos.TableUserImage;
import static com.project.email.ForgetPasswordMail.ForgetPasswordMailMethod;

@Repository
public class UserDaoImpl implements UserDaointerface {
	private Long uid=(long) 0;
	
	@Autowired
	private SessionFactory sf;

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
	
	@Override
	public Long adduser(TableUser ref) {
		Long uid=(Long) sf.getCurrentSession().save(ref);
		String saltedPassword = ref.getUserEmail() + ref.getUserPassword();
		String hashedPassword = generateHash(saltedPassword);
		ref.setUserStatus("active");
		ref.setUserPassword(hashedPassword);
		sf.getCurrentSession().update(ref);
		System.out.println("registered user with hashed password----------"+ref.toString());
		return uid;
	}

	
	@Override
	public TableUser getLoginUserDetail(String user_email,String user_password) {
	System.out.println("dao:"+user_email+" "+ user_password);
	String saltedPassword = user_email+user_password;
	String hashedPassword = generateHash(saltedPassword);
	
	String hql="select tb FROM TableUser tb where tb.userEmail=:user_email and tb.userPassword=:user_password";
		TableUser loginuserobj=(TableUser)sf.getCurrentSession().createQuery(hql).setParameter("user_email",user_email).setParameter("user_password", hashedPassword).uniqueResult();
		if(loginuserobj!=null)
		return loginuserobj;
		else{
		failure_login_attempt();
		return null;	
		}
	}

	public void failure_login_attempt(){
		System.out.println("before adding counter------------------------");
		TableUser user=(TableUser)sf.getCurrentSession().get(TableUser.class, uid);
		int previous_counter_value=user.getLoginCounter();
		if(previous_counter_value<4){
			previous_counter_value+=1;
			user.setLoginCounter(previous_counter_value);
			user.setUserStatus("active");
			
		}
		else if(previous_counter_value==4){
			previous_counter_value+=1;
			user.setLoginCounter(previous_counter_value);
			user.setUserStatus("last_login_attempt");
			} 
		else {
			user.setUserStatus("blocked");
			
		}
		
		
	}
	
	@Override
	public Long checkUser(String useremail) {
		System.out.println("dao:"+useremail);
		String checkuserhql="select tb FROM TableUser tb where tb.userEmail=:user_email";
		try{
			TableUser user=(TableUser)sf.getCurrentSession().createQuery(checkuserhql).setParameter("user_email",useremail).uniqueResult();
			uid=user.getUserId();
			System.out.println("executed select query for getting result form email------------checkuser id "+uid+"user-------"+user.toString());
			return uid;
			}catch(NullPointerException e){
			return (long) 0;
			}catch (Exception e) {
				return (long) -1;
			}
	}

	@Override
	public TableUser checkUserById(long id) {
			try{
			TableUser user=(TableUser)sf.getCurrentSession().get(TableUser.class, id);
			return user;
			}catch(Exception e){
				return null;
			}
	}

	@Override
	public String forgetpassword(String useremail) {
		Long user=checkUser(useremail);
		System.out.println("-------user id-----"+user);
		if(user==0 || user==-1){
			System.out.println("reached in forget password dao:"+useremail);
			return "usernotfoundinforgetpassword";	
		}
		try{
				TableUser loginuserobj=(TableUser)sf.getCurrentSession().get(TableUser .class, user);
				System.out.println("after executing get------------------"+loginuserobj.toString());
				String password=loginuserobj.getUserPassword();
				System.out.println("transient obj"+loginuserobj.toString());	
				String emailstatus=ForgetPasswordMailMethod(useremail,password);
				return emailstatus;
			}
			catch(Exception e)
			{
				return "someerroroccurredinsendingemail";
			}
		}

	@Override
	public TableUserImage saveimage(TableUserImage ref) {
		int id= (Integer) sf.getCurrentSession().save(ref);
		System.out.println("image table row id:-"+id);
			@SuppressWarnings("unused")
			String foldername="resources/images/userIcon.png";
		System.out.println("------------image post------");
			String storeimagesql="insert into table_user_image(image_name,user_id) values(?,?)";
			return (TableUserImage)sf.getCurrentSession().createSQLQuery(storeimagesql).setParameter(1, ref.getImageName()).setParameter(2,id).uniqueResult();
	}

	public TableUserImage saveUserImage(TableUserImage ref)
	{
		Session session = sf.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(ref);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ref;
	}
	public TableUserImage getUserImage(Long bookId)
	{
		Session session = sf.getCurrentSession();
		try {
			TableUserImage userimage = (TableUserImage) session.get(TableUserImage.class, bookId);
			return userimage ;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	@Override
	public void updateUser(TableUser getuserdetailbylogin) {
		sf.getCurrentSession().saveOrUpdate(getuserdetailbylogin);
		 
	}
	@Override
	public TableUserImage restoreimage(TableUser userref) {
		String useridsql="select tbui from TableUserImage where tbui.userId=:uid";
		TableUserImage image=(TableUserImage) sf.getCurrentSession().createQuery(useridsql).setParameter("uid", userref.getUserId()).setMaxResults(1);
		return image;
	}

	@Override
	public ArrayList<TableUser> getallusers() {
		String alluserhql="from TableUser";
		return (ArrayList<TableUser>)sf.getCurrentSession().createQuery(alluserhql).list();
	}

	@Override
	public void deleteUser(TableUser olddataofuser) {
		sf.getCurrentSession().delete(olddataofuser);
		}

	@Override
	public void changeUserPasswordByHashCode(TableUser tbuser) {
		System.out.println("reached in change user password by hashcode");
		String hashedpassword=tbuser.getUserExtra();
		System.out.println("------hashed password enter by user-------------"+hashedpassword);
		Long uid=checkUser(tbuser.getUserEmail());
		if(uid!=0 && uid!=-1)
		{
			TableUser tbuserdb=(TableUser)sf.getCurrentSession().get(TableUser.class, uid);
		String userhashedpasswordfromdb=tbuserdb.getUserPassword();
		if(hashedpassword.equals(userhashedpasswordfromdb)==true){
			String saltedPassword = tbuser.getUserEmail() + tbuser.getUserPassword();
			String hashedPassword = generateHash(saltedPassword);
			tbuserdb.setUserPassword(hashedPassword);
			sf.getCurrentSession().update(tbuserdb);
		}}
	}

	@Override
	public void AddUserImage(TableUserImage imageref) {
		sf.getCurrentSession().save(imageref);		
		
	}

}

package com.project.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.annotations.common.reflection.java.generics.TypeEnvironmentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.project.reversepojos.TableUser;
import com.google.api.client.json.Json;
import com.google.gson.JsonObject;
import com.project.reversepojos.TableUserImage;
import com.project.service.UserServiceInterface;
import com.project.thirdpartylogin.LoginByGoogle;

@Controller
public class UserController {
	/* private HashMap hashmap=new HashMap<>(); */

	@Autowired
	private UserServiceInterface userServiceInterfaceRef;


	@RequestMapping(value = "/chatting", method = RequestMethod.GET)
	public String chatting(HttpSession hs) {
		TableUser user = (TableUser) hs.getAttribute("valid_user");
		if (user != null) {
			// map.addAttribute("userImagePojo", new TableUserImage());
			return "chatting";
		} else
			return "redirect:/login";

	}
	
	@RequestMapping(value = "/splitbill", method = RequestMethod.GET)
	public String splitbill(Model map, HttpSession hs) {
		TableUser user = (TableUser) hs.getAttribute("valid_user");
		if (user != null) {
			// map.addAttribute("userImagePojo", new TableUserImage());
			return "splitbill";
		} else
			return "redirect:/login";

	}

	@RequestMapping(value = "/uploadtocloud", method = RequestMethod.GET)
	public String uploadtocloud(Model map, HttpSession hs) {
		TableUser user = (TableUser) hs.getAttribute("valid_user");
		if (user != null) {
			map.addAttribute("userImagePojo", new TableUserImage());
			return "upload";
		} else
			return "redirect:/login";

	}

	@RequestMapping(value = "/uploadtocloud", method = RequestMethod.POST)
	public String uploadtocloud(TableUserImage ref, HttpSession hs) {
		System.out.println("picture_url" + ref.getImageAbsolutePath());
		userServiceInterfaceRef.AddUserImage(ref);
		hs.setAttribute("profileurl", ref.getImageAbsolutePath());
		return "redirect:/dashboard";
	}

	// @RequestMapping(value =
	// "/loginbyfacebook/{responseruserid}/{responseemail}/{fbpicture}",method =
	// RequestMethod.GET)
	@RequestMapping(value = "/loginbyfacebook", method = RequestMethod.GET)
	// var
	// url="loginbyfacebook/"+fbtoken+"/"+responseruserid+"/"+responseemail+"/"+fbpicture+"/"+resonpsefullname;
	/*
	 * public String loginbyFacebook(
	 * 
	 * @PathVariable("responseruserid") String responseruserid,
	 * 
	 * @PathVariable("responseemail")String responseemail,
	 * 
	 * @PathVariable("fbpicture")String fbpicture,HttpServletRequest
	 * request,HttpSession hs) {
	 */
	public String loginbyFacebook(HttpServletRequest request, HttpSession hs) {
		String returnstatus = "";
		System.out.println("--------login by facebook-----------");
		String fb_user_id = request.getParameter("fb_userid");
		String fb_email = request.getParameter("fb_email");
		String fb_full_name = request.getParameter("fb_fullname");
		String fb_picture_url = request.getParameter("fb_picture_url");
		System.out.println(
				fb_email + "=======" + fb_full_name + "========" + fb_user_id + "============" + fb_picture_url);

		try {
			long user_id = userServiceInterfaceRef.checkUser(fb_email);
			System.out.println(
					"check user id from email received from -----------facebook------------user_id:===" + user_id);

			// if some other error happend while checkig
			if (user_id == (long) -1) {
				hs.setAttribute("checkuserstatus", "someerrorwhilelogin");
				returnstatus = "failure";
			}
			// if user doesnot not exist & first time signing through facebook
			else if (user_id == (long) 0) {
				System.out.println("if user doesnot not exist  & first time signing through facebook");
				System.out.println("---------reached in new user from facebook sign in button--------------");
				TableUser user = new TableUser();
				user.setFacebookFullName(fb_full_name);
				user.setFacebookUserId(fb_user_id);
				user.setFacebookprofilePictureUrl(fb_picture_url);
				user.setUserEmail(fb_email);
				user.setRole("user");
				user.setUserStatus("active");

				long newuserid = userServiceInterfaceRef.AddUserService(user);
				// TableUser
				// newuser=userServiceInterfaceRef.checkUserById(newuserid);
				user.setUserId(newuserid);
				System.out.println("===============new user by google" + user.toString());
				hs.setAttribute("valid_user", user);
				hs.setAttribute("currentuserid", newuserid);
				hs.setAttribute("profileurl", fb_picture_url);
				returnstatus = "redirect:/dashboard";

			}

			else {
				// if user exist but first time login via google
				System.out.println("if user exist &  facebook user id also exist");
				TableUser user = userServiceInterfaceRef.checkUserById(user_id);
				System.out.println("after checkuser by id in already existing facebook person" + user.toString());
				String facebookidbydb = user.getFacebookUserId();
				// user exist but signing first time
				if (facebookidbydb == null || facebookidbydb == "" || facebookidbydb.equals("null")) {
					System.out.println("user exist but signing first time through facebook");
					user.setFacebookFullName(fb_full_name);
					user.setFacebookUserId(fb_user_id);
					user.setFacebookprofilePictureUrl(fb_picture_url);
					user.setUserEmail(fb_email);
					user.setRole("user");
					user.setUserStatus("active");
					userServiceInterfaceRef.updateUser(user);
					System.out.println("user updated");
					hs.setAttribute("valid_user", user);
					hs.setAttribute("currentuserid", user.getUserId());
					hs.setAttribute("profileurl", fb_picture_url);
					returnstatus = "redirect:/dashboard";

				} else {
					// user exist && user already login through facebook
					// previously
					System.out.println("google user id match with existing db user google id");
					hs.setAttribute("valid_user", user);
					hs.setAttribute("currentuserid", user.getUserId());
					hs.setAttribute("profileurl", fb_picture_url);
					System.out.println("session data" + hs.toString());
					returnstatus = "redirect:/dashboard";
				}

			}
		} catch (Exception e) {
			hs.setAttribute("checkuserstatus", "erroringooglelogin");
			returnstatus = "failure";
		}
		return returnstatus;

	}

	@RequestMapping(value = "/loginbygoogle", method = RequestMethod.GET)
	public String loginbyGoogle(HttpServletRequest request, HttpSession hs) {
		String returnstatus="";
		try {
			String id_token=request.getParameter("id_token");
			TableUser user=LoginByGoogle.returnuserobjectFromToken(id_token);
			
			System.out.println("user with google data before updating in db:::::"+user.toString());
			long user_id = userServiceInterfaceRef.checkUser(user.getUserEmail());
			System.out.println(
					"check user id from email received from -----------google------------user_id:===" + user_id);
				if (user_id == (long) -1) {
				hs.setAttribute("checkuserstatus", "someerrorwhilelogin");
				returnstatus = "failure";
			}
				
			// if user doesnot not exist & first time signing through google
			else if (user_id == (long) 0) {
				System.out.println("if user doesnot not exist  & first time signing through google");
				System.out.println("---------reached in new user from google sign in button--------------");
				long newuserid = userServiceInterfaceRef.AddUserService(user);
				user.setUserId(newuserid);
				System.out.println("===============new user by google" + user.toString());
				hs.setAttribute("valid_user", user);
				hs.setAttribute("currentuserid", newuserid);
				hs.setAttribute("profileurl", user.getGoogleprofilePictureUrl());
				returnstatus = "redirect:/dashboard";
			}
			else {
				// if user exist but first time login via google
				System.out.println("if user exist &  googleuserid also exist");
				TableUser userwithemailalreadyexist = userServiceInterfaceRef.checkUserById(user_id);
				System.out.println("after checkuser by id in already existing google person" + userwithemailalreadyexist.toString());
				String googleidbydb = userwithemailalreadyexist.getGoogleUserId();
				// user exist but signing first time
				if (googleidbydb == null || googleidbydb == "" || googleidbydb.equals("null")) {
					System.out.println("user exist but signing first time through google");
					userwithemailalreadyexist.setFirstName(user.getFirstName());
					userwithemailalreadyexist.setLastName(user.getLastName());
					userwithemailalreadyexist.setGoogleUserId(user.getGoogleUserId());
					userwithemailalreadyexist.setGoogleprofilePictureUrl(user.getGoogleprofilePictureUrl());
					userwithemailalreadyexist.setUserEmail(user.getUserEmail());
					userwithemailalreadyexist.setGoogleFullName(user.getGoogleFullName());
					userwithemailalreadyexist.setRole(user.getRole());
					userwithemailalreadyexist.setUserStatus(user.getUserStatus());
					
					userServiceInterfaceRef.updateUser(userwithemailalreadyexist);
					System.out.println("user updated");
					hs.setAttribute("valid_user", userwithemailalreadyexist);
					hs.setAttribute("currentuserid", userwithemailalreadyexist.getUserId());
					hs.setAttribute("profileurl", userwithemailalreadyexist.getGoogleprofilePictureUrl());
					returnstatus = "redirect:/dashboard";

				} else {
					// user exist && user already login through google
					// previously
					System.out.println("google user id match with existing db user google id");
					hs.setAttribute("valid_user", userwithemailalreadyexist);
					hs.setAttribute("currentuserid", userwithemailalreadyexist.getUserId());
					hs.setAttribute("profileurl", userwithemailalreadyexist.getGoogleprofilePictureUrl());
					//System.out.println("session data" + hs.toString());
					returnstatus = "redirect:/dashboard";
				}

			}
		} catch (Exception e) {
			hs.setAttribute("checkuserstatus", "erroringooglelogin");
			returnstatus = "failure";
		}
		return returnstatus;
		
	}

	@RequestMapping(value = "/myprofiledetails")
	public String getProfileDetails(HttpSession hs, Model map) {
		String resultstatus = "";
		try {
			TableUser tbuser = (TableUser) hs.getAttribute("valid_user");
			if (tbuser != null) {
				resultstatus = "ProfilePage";
			}
		} catch (Exception e) {
			resultstatus = "failure";
		}
		return resultstatus;
	}

	@RequestMapping(value = "user/edituser/{id}", method = RequestMethod.GET)
	public String editUserForAdmin(@PathVariable("id") long userid, HttpSession hs, Model map) {
		String checkuserstatus = "";
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			System.out.println("userid in edit user" + userid);
			TableUser olddataofuser = userServiceInterfaceRef.checkUserById(userid);
			map.addAttribute("userpojo", olddataofuser);
			return "editUser";
		} catch (Exception err) {
			checkuserstatus = "session_out";
			hs.setAttribute("checkuserstatus", checkuserstatus);
			return "failure";
		}

	}

	@RequestMapping(value = "user/edituser/{id}", method = RequestMethod.POST)
	public String editUserForAdmin(@PathVariable("id") long userid, TableUser tbuserref, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			TableUser olddataofuser = userServiceInterfaceRef.checkUserById(userid);
			System.out.println("old data of user" + olddataofuser.toString());
			String email = tbuserref.getUserEmail();
			String mobile = tbuserref.getUserMobileNumber();
			String role = tbuserref.getRole();
			String status = tbuserref.getUserStatus();

			olddataofuser.setUserMobileNumber(mobile);
			olddataofuser.setUserEmail(email);
			olddataofuser.setRole(role);
			olddataofuser.setUserStatus(status);

			System.out.println("changed data of user" + olddataofuser.toString());
			userServiceInterfaceRef.updateUser(olddataofuser);
			return "redirect:/showallusers";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/user/deleteuser/{id}", method = RequestMethod.GET)
	public String deleteUserForAdmin(@PathVariable("id") long id, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			TableUser olddataofuser = userServiceInterfaceRef.checkUserById(id);
			userServiceInterfaceRef.deleteUser(olddataofuser);
			return "redirect:/showallusers";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/showallusers")
	public String ShowAllTablesForAdmin(HttpSession hs) {
		try {
			TableUser tbuser = (TableUser) hs.getAttribute("valid_user");
			ArrayList<TableUser> userslist = userServiceInterfaceRef.getallusers();
			/* added for ajax to be tested */
			/*
			 * String returnText = ""; for (TableUser t : userslist) returnText
			 * += "<tr><td>" + t.getUserId() + "</td><td>" + t.getUserName() +
			 * "</td><td>" + t.getUserEmail() + "</td><td>" +
			 * t.getUserMobileNumber() + "</td><td>" +
			 * t.getRole()+"</td><td>"+t.getUserStatus()+"</td><td>" +
			 * "<a data-toggle='modal' data-target='#modalExpenseEdit' class='btn mini blue-stripe' href='/ExpenseManager/user/edituser/"
			 * + t.getUserId() + "'>Edit</a></td><td>" +
			 * "<a href='/ExpenseManager/user/deleteuser/" + t.getUserId() +
			 * "' class='confirm-delete btn mini red-stripe'>Delete</a>" +
			 * "<td></tr>" +
			 * "<div id='modalExpenseEdit' class='modal fade' role='dialog' data-backdrop='static' data-keyboard='false'> <div class='modal-dialog'><!-- Modal content--><div class='modal-content'></div></div></div>;"
			 * ; return returnText;
			 */
			hs.setAttribute("users_list", userslist);
			return "showUsersForAdmin";
		} catch (NullPointerException err) {

			return "failure";
		}
	}

	@RequestMapping(value = "/registerUser")
	public String controlleradd(Model map) {
		map.addAttribute("registerUserPojo", new TableUser());
		return "registerPage";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String postcontrolleradd(TableUser ref, Model map, HttpSession hs) {
		String returnstatus = "";
		long checkuser = userServiceInterfaceRef.checkUser(ref.getUserEmail());
		System.out.println("user exit or not desc:::" + checkuser);
		if (checkuser == 0) {
			// new user
			userServiceInterfaceRef.AddUserService(ref);
			// default expense/income category added
			hs.setAttribute("checkuserstatus", "registersuccessfully");
			returnstatus = "failure";
		} else if (checkuser != 0 && checkuser != -1) {
			hs.setAttribute("checkuserstatus", "useralreadyregisterwiththisemail");
			returnstatus = "failure";
		} else {
			hs.setAttribute("checkuserstatus", "registeruserfailed");
			returnstatus = "failure";
		}
		return returnstatus;
	}

	@RequestMapping(value = "/loginbyuniquenumber", method = RequestMethod.GET)
	public String loginbyuniquenumberget(Model map, HttpSession hs) {
		map.addAttribute("userPojo", new TableUser());
		return "firstloginbyguest";
	}

	@RequestMapping(value = "/loginbyuniquenumber", method = RequestMethod.POST)
	public String loginbyuniquenumberpost(TableUser ref, HttpSession hs) {
		try {// checking user exist in session
			TableUser tbuser = (TableUser) hs.getAttribute("valid_user");
			System.out.println("reached in checking user in session for guest login");
			long userid = tbuser.getUserId();
			hs.setAttribute("currentuserid", userid);
			return "redirect:/dashboard";
		} catch (NullPointerException nullpointerexception) {
			// user not exist in session
			System.out.println("reached in checking user in database for guest login");
			long useridbydefaultuser = ref.getUserId();
			try {// checking user exist in db
				TableUser tbexistuser = userServiceInterfaceRef.checkUserById(useridbydefaultuser);
				hs.setAttribute("valid_user", tbexistuser);
				hs.setAttribute("currentuserid", tbexistuser.getUserId());
				return "redirect:/dashboard";
			} catch (Exception usernotexistindb) {
				hs.setAttribute("checkuserstatus", "uniquenumbernotfound");
				return "failure";
			}
		}
	}

	@RequestMapping(value = "/loginbyguest", method = RequestMethod.GET)
	public String loginasguest(HttpSession hs) {
		try {// checking user exist in session
			TableUser tbuser = (TableUser) hs.getAttribute("valid_user");
			System.out.println("reached in checking user in session for guest login");
			long userid = tbuser.getUserId();
			hs.setAttribute("currentuserid", userid);
			return "redirect:/dashboard";
		} catch (NullPointerException nullpointerexception) {
			// user not exist in session
			TableUser tbuser = new TableUser();
			tbuser.setUserEmail("default");
			tbuser.setUserPassword("default");
			tbuser.setRole("user");
			long uid = userServiceInterfaceRef.AddUserService(tbuser);
			TableUser tbexistuser = userServiceInterfaceRef.checkUserById(uid);
			hs.setAttribute("valid_user", tbexistuser);
			hs.setAttribute("currentuserid", tbexistuser.getUserId());
			return "redirect:/dashboard";
		}
	}

	@RequestMapping(value = "/login")
	public String controllerlogin(Model map, HttpSession hs) {
		System.out.println("----------reached in login get method-------");
		String resultstatus = "";
		try {
			TableUser user = (TableUser) hs.getAttribute("valid_user");
			System.out.println(user.toString());
			resultstatus = "redirect:/dashboard";
		} catch (Exception e) {
			map.addAttribute("userPojo", new TableUser());
			resultstatus = "loginPage";
		}
		return resultstatus;
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String controllerlogin(TableUser ref, Model map, HttpSession hs) {
		String returnstatus = "";
		String userresultstatus = "";
		long userstatusfirstcheck = userServiceInterfaceRef.checkUser(ref.getUserEmail());
		System.out.println("user status---------" + userstatusfirstcheck);
		if (userstatusfirstcheck == 0) {
			System.out.println("reached in if part----------------------");
			hs.setAttribute("checkuserstatus", "newuser");
			System.out.println("----------new user-------");
			returnstatus = "failure";
		}

		else if (userstatusfirstcheck == -1) {
			System.out.println("reached in else if part-------------------");
			hs.setAttribute("checkuserstatus", "exception");
			returnstatus = "failure";
		} else {
			System.out.println("reached in else part----------------uid---" + userstatusfirstcheck);
			try {

				TableUser getuserdetailbylogin = userServiceInterfaceRef.getLoginUserDetail(ref.getUserEmail(),
						ref.getUserPassword());
				System.out.println(
						"user from details" + getuserdetailbylogin.getUserStatus() + getuserdetailbylogin.toString());
				userresultstatus = getuserdetailbylogin.getUserStatus();
				if (userresultstatus.equals("active") == true) {
					System.out.println("reached in else subpart active--------------");
					hs.setAttribute("valid_user", getuserdetailbylogin);
					hs.setAttribute("currentuserid", (long) getuserdetailbylogin.getUserId());
					System.out.println("reached in active user------------------");
					returnstatus = "redirect:/dashboard";
				} else if (userresultstatus.equals("last_login_attempt") == true) {
					System.out.println("reached in else subpart elfe if----------------");
					getuserdetailbylogin.setUserStatus("active");
					getuserdetailbylogin.setLoginCounter(0);
					userServiceInterfaceRef.updateUser(getuserdetailbylogin);
					System.out.println("updated user:-" + getuserdetailbylogin.toString());
					hs.setAttribute("valid_user", getuserdetailbylogin);
					hs.setAttribute("currentuserid", (long) getuserdetailbylogin.getUserId());
					returnstatus = "redirect:/dashboard";
				} else if (userresultstatus.equals("blocked") == true) {
					System.out.println("reached in else subpart else if2----------------");
					hs.setAttribute("currentuserid", (long) getuserdetailbylogin.getUserId());
					hs.setAttribute("checkuserstatus", userresultstatus);
					returnstatus = "failure";
				}

			} catch (Exception e) {
				// pending login cunter status to user
				TableUser tbuser = (TableUser) userServiceInterfaceRef.checkUserById(userstatusfirstcheck);
				int logincounter = tbuser.getLoginCounter();
				String status = tbuser.getUserStatus();
				if (logincounter < 5) {
					hs.setAttribute("checkuserstatus", "passwordnotmatch");
					System.out.println("----------wrong password-------");
					returnstatus = "failure";
				} else if (logincounter == 5) {
					hs.setAttribute("checkuserstatus", status);
					System.out.println("----------last warning-------");
					returnstatus = "failure";
				} else {
					hs.setAttribute("checkuserstatus", status);
					returnstatus = "failure";
				}
				returnstatus = "failure";
			}
		}
		return returnstatus;
	}

	@RequestMapping(value = "/forgotPasswordEmail", method = RequestMethod.GET)
	public String forgotLoginPassword(Model map, HttpSession hs) {
		System.out.println("forgotPasswordEmail get method");
		map.addAttribute("userPojo", new TableUser());
		return "forgotPasswordPage";
	}

	@RequestMapping(value = "/forgotPasswordEmail", method = RequestMethod.POST)
	public String forgotLoginPassword(TableUser newtbuser, Model map, HttpSession hs) {
		System.out.println("forgotPasswordEmail post method" + newtbuser.toString());
		System.out.println("forget user pwd -----------------" + newtbuser.getUserEmail());
		String emailstatus = userServiceInterfaceRef.forgetpassword(newtbuser.getUserEmail());
		System.out.println("emailstatus++++++++++++" + emailstatus);
		if (emailstatus == null || emailstatus == "" || emailstatus.equals("null")) {
			hs.setAttribute("checkuserstatus", "otttt");
			return "failure";
		} else {
			hs.setAttribute("checkuserstatus", emailstatus);
			return "failure";
		}

	}

	// @RequestMapping(value = "/forgetbyHashCode",method=RequestMethod.GET)
	@RequestMapping(value = "/forgetbyHashCode/{email}/{hashcode}", method = RequestMethod.GET)
	public String matchHashCode(@PathVariable("email") String email, @PathVariable("hashcode") String hashcode,
			Model map, HttpSession hs) {
		// public String matchHashCode(Model map,HttpSession hs) {
		try {
			System.out.println("reached in forget by hashcode post method");
			TableUser user = (TableUser) hs.getAttribute("valid_user");
			System.out.println("reached in match Hash Code email--" + email);
			System.out.println("hashcode from forget password link" + hashcode);
			TableUser tbuser = new TableUser();
			tbuser.setUserExtra(hashcode);
			tbuser.setUserEmail(email);
			System.out.println("tbuser before password change-------------" + tbuser.toString());
			map.addAttribute("user_pojo", tbuser);
			return "forgetsuccessfully";
		} catch (Exception e) {
			hs.setAttribute("checkuserstatus", "HashCodeMethodProblem");
			return "failure";
		}

	}

	@RequestMapping(value = "/forgetbyHashCode/{email}/" + "{hashcode}", method = RequestMethod.POST)
	public String matchHashCode(@PathVariable("email") String email, @PathVariable("hashcode") String hashcode,
			TableUser userref, HttpSession hs) {
		try {
			System.out.println("hashcode by url" + hashcode + "email by url" + email);
			System.out.println(
					"hashcode by userref" + userref.getUserExtra() + "email by userref" + userref.getUserEmail());
			System.out.println("reached in forget by hashcode post method");
			userref.setUserExtra(hashcode);
			userref.setUserEmail(email);
			userServiceInterfaceRef.changeUserPasswordByHashCode(userref);
			return "redirect:/login";
		} catch (Exception e) {
			return "failure";
		}
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession hs) {
		hs.invalidate();

		return "redirect:/login";
	}

	@RequestMapping(value = "/changeuserimage", method = RequestMethod.GET)
	public String changeimage(Model map, HttpSession hs) {
		TableUser user = (TableUser) hs.getAttribute("valid_user");
		if (user != null) {
			map.addAttribute("userImagePojo", new TableUserImage());
			return "updateimage";
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/changeuserimage", method = RequestMethod.POST)
	public String changeimagepost(@RequestParam("textFile") CommonsMultipartFile file, TableUserImage userimagedetails,
			HttpSession hs) {
		String userstatus = "";
		BufferedOutputStream bout = null;
		OutputStream out = null;
		File filepath = null;
		byte barr[] = null;
		System.out.println("reached here--------------changeuserimage post method-");
		String returnstatus = "";
		TableUser checksessionuser = (TableUser) hs.getAttribute("valid_user");
		if (checksessionuser != null) {
			/*
			 * String property = System.getProperty("jboss.server.data.dir"); if
			 * (property != null) { boolean exists = new
			 * File(property).exists(); System.out.println(
			 * "envirnment exists: OPENSHIFT_DATA_DIR " +
			 * property+"----"+exists); }
			 * 
			 * openshift file path String
			 * filepathdirectory=System.getenv("OPENSHIFT_DATA_DIR"); filepath =
			 * new File(filepathdirectory+"/images/userIcon.png"); filepath =
			 * new File("resources/images/userIcon.png");
			 * 
			 * /*local file path F:\cdac\cdac project\MY_LIVE_PROJECT\source
			 * backups\ExpenseManager\WebContent\static_contents\images\
			 * userIcon.png
			 */

			filepath = new File(
					"C:/Users/pathneja/workspace/ExpenseManager/WebContent/static_contents/images/userIcon.png");
			System.out.println("filepath:-----" + filepath.getPath());
			System.out.println("get absolute path:------" + filepath.getAbsolutePath());
			barr = file.getFileItem().get();
			if (!filepath.exists()) {
				try {
					// BufferedOutputStream bout = new BufferedOutputStream(new
					// FileOutputStream("C:/Users/pathneja/Desktop"+"/"+file.getOriginalFilename()));
					bout = new BufferedOutputStream(new FileOutputStream(filepath));

					bout.write(barr);
					System.out.println("after writing-------------");
					bout.flush();
					System.out.println("after flushing-------------");
					/*
					 * userimagedetails.setImageName(file.getOriginalFilename())
					 * ; userimagedetails.setImageAbsolutePath(filepath.
					 * getAbsolutePath());
					 * userimagedetails.setUserId(checksessionuser.getUserId());
					 * System.out.println("before image blog set in pojo object"
					 * ); userimagedetails.setImage(barr); System.out.println(
					 * "after setting value for table");
					 * //userServiceInterfaceRef.saveimage(userimagedetails);
					 * System.out.println("after image saved in db");
					 */
					bout.close();
					System.out.println("after closing------------");
					returnstatus = "redirect:/dashboard";

				} catch (Exception e) {
					hs.setAttribute("checkuserstatus", "imageuploadfailedinifpart");
					returnstatus = "failure";
				}
			} else if (filepath.exists()) {
				try {
					System.out.println("filepath exist or not" + filepath.exists());
					System.out.println("temp path" + filepath.getPath());
					System.out.println("permanent path" + filepath.getAbsolutePath());
					filepath.delete();
					System.out.println("deleted file path now exist or not" + filepath.exists());
					bout = new BufferedOutputStream(new FileOutputStream(filepath));
					bout.write(barr);
					bout.flush();
					bout.close();
					returnstatus = "redirect:/dashboard";
				} catch (Exception e) {
					hs.setAttribute("checkuserstatus", "imageuploadfailedinelsepart");
					returnstatus = "failure";
				}
			}
		}
		return returnstatus;
	}
}
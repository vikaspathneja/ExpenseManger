package com.project.reversepojos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="table_user")
public class TableUser implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private long userId;

	@Column(name="user_login_first")
	private boolean userLoginFirst;
	
	@Column(name="user_budget")
	private int userBudget;

	@Temporal(TemporalType.DATE)
	@Column(name="user_dob")
	private Date userDob;

	@Column(name="user_email")
	private String userEmail;

	@Column(name="user_status")
	private String userStatus;
	
	@Column(name="google_user_id")
	private String googleUserId;

	@Column(name="facebook_user_id")
	private String facebookUserId;

	@Column(name="user_extra")
	private String userExtra;

	@Column(name="user_mobile_number")
	private String userMobileNumber;

	@Column(name="role")
	private String role;
	
	@Column(name="user_name")
	private String userName;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="google_full_name")
	private String googleFullName;
	
	@Column(name="user_password")
	private String userPassword;

	@Column(name="login_counter")
	private int loginCounter;
	
	@Column(name="google_profile_picture_url")
	private String googleprofilePictureUrl;

	@Column(name="facebook_profile_picture_url")
	private String facebookprofilePictureUrl;
	
	public String getFacebookUserId() {
		return facebookUserId;
	}

	public void setFacebookUserId(String facebookUserId) {
		this.facebookUserId = facebookUserId;
	}

	public String getGoogleprofilePictureUrl() {
		return googleprofilePictureUrl;
	}

	public void setGoogleprofilePictureUrl(String googleprofilePictureUrl) {
		this.googleprofilePictureUrl = googleprofilePictureUrl;
	}

	public String getFacebookprofilePictureUrl() {
		return facebookprofilePictureUrl;
	}

	public void setFacebookprofilePictureUrl(String facebookprofilePictureUrl) {
		this.facebookprofilePictureUrl = facebookprofilePictureUrl;
	}

	public String getFacebookFullName() {
		return facebookFullName;
	}

	public void setFacebookFullName(String facebookFullName) {
		this.facebookFullName = facebookFullName;
	}

	public String getFacebookToken() {
		return facebookToken;
	}

	public void setFacebookToken(String facebookToken) {
		this.facebookToken = facebookToken;
	}

	@Column(name="facebook_full_name")
	private String facebookFullName;
	
	@Column(name="facebook_token")
	private String facebookToken;
	
	
	public String getGoogleUserId() {
		return googleUserId;
	}

	public void setGoogleUserId(String googleUserId) {
		this.googleUserId = googleUserId;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

		

	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getUserBudget() {
		return this.userBudget;
	}

	public void setUserBudget(int userBudget) {
		this.userBudget = userBudget;
	}

	public Date getUserDob() {
		return this.userDob;
	}

	public void setUserDob(Date userDob) {
		this.userDob = userDob;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserExtra() {
		return this.userExtra;
	}

	public int getLoginCounter() {
		return loginCounter;
	}

	public void setLoginCounter(int loginCounter) {
		this.loginCounter = loginCounter;
	}

	public void setUserExtra(String userExtra) {
		this.userExtra = userExtra;
	}

	public String getUserMobileNumber() {
		return this.userMobileNumber;
	}

	public void setUserMobileNumber(String userMobileNumber) {
		this.userMobileNumber = userMobileNumber;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isUserLoginFirst() {
		return userLoginFirst;
	}

	public void setUserLoginFirst(boolean userLoginFirst) {
		this.userLoginFirst = userLoginFirst;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getGoogleFullName() {
		return googleFullName;
	}

	public void setGoogleFullName(String googleFullName) {
		this.googleFullName = googleFullName;
	}

	@Override
	public String toString() {
		return "\nTableUser [userId=" + userId + ", userLoginFirst=" + userLoginFirst + ", userBudget=" + userBudget
				+ ", userDob=" + userDob + ", userEmail=" + userEmail + ", userStatus=" + userStatus + ", googleUserId="
				+ googleUserId + ", facebookUserId=" + facebookUserId + ", userExtra=" + userExtra
				+ ", userMobileNumber=" + userMobileNumber + ", role=" + role + ", userName=" + userName
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", googleFullName=" + googleFullName
				+ ", userPassword=" + userPassword + ", loginCounter=" + loginCounter + ", googleprofilePictureUrl="
				+ googleprofilePictureUrl + ", facebookprofilePictureUrl=" + facebookprofilePictureUrl
				+ ", facebookFullName=" + facebookFullName + ", facebookToken=" + facebookToken + "]";
	}





	
}
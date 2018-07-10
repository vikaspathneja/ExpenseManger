package com.project.email;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgetPasswordMail {
	static Properties props = new Properties();
    
    public static  String ForgetPasswordMailMethod(String emailofreceiver,String HashPassword){
    	System.out.println("receiver email---------"+emailofreceiver+"hashpassword:------"+HashPassword);
    	final String username = "expensemanager0@gmail.com";
        final String password = "Welcome@9200";
        
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication("expensemanager0@gmail.com", "Welcome@9200");
                  }
                });


      try {
    	  //DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
          //Date dateobj = new Date();
          //System.out.println(df.format(dateobj));
          
    	  Transport transport=session.getTransport();
          Message message = new MimeMessage(session);
          message.setFrom(new InternetAddress("expensemanager0@gmail.com"));//formBean.getString("fromEmail")
          message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailofreceiver));
          message.setSubject("Forget Password Expense Manager");//formBean.getString(
          System.out.println("receiver email---------"+emailofreceiver+"hashpassword:------"+HashPassword);
          message.setText("http://my-expensemanger.rhcloud.com/forgetbyHashCode/"+emailofreceiver+"/"+HashPassword);
          //message.setText("http://localhost:9090/ExpenseManager/forgetbyHashCode/"+HashPassword);
          System.out.println("receiver email---------"+emailofreceiver+"hashpassword:------"+HashPassword);
          transport.connect();
          transport.send(message, InternetAddress.parse(emailofreceiver));//(message);
          System.out.println("Email Sent Successfully");
          return "EmailSentSuccessfully";

      } catch (MessagingException e) {
          System.out.println("Some Error Occured While Sending Password Reset Link");
          //e.printStackTrace();
          //throw new RuntimeException(e);
          return "EmailSentFailed";

      }


	}
    
}

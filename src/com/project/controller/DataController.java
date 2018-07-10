package com.project.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.reversepojos.TableExpense;
import com.project.reversepojos.TableIncome;
import com.project.reversepojos.TableUser;
import com.project.service.ExpenseServiceInterface;
import com.project.service.IncomeServiceInterface;

@Controller
public class DataController {

	@Autowired
	ExpenseServiceInterface expenseserviceinterfaceref;
	
	@Autowired
	IncomeServiceInterface incomeserviceinterfaceref;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/dashboard/graph1")
	public @ResponseBody String getGraph1(HttpSession hs){
		ObjectMapper objMapper=new ObjectMapper();
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
		ArrayList<TableExpense> expenseDataList=expenseserviceinterfaceref
				.getExpensesofParticularUser(userobj.getUserId());
		ArrayList<TableIncome> incomeDataList=incomeserviceinterfaceref
				.getIncomeofParticularUser(userobj.getUserId());
		int len = expenseDataList.size();
		if(len<incomeDataList.size())
			len = incomeDataList.size();
		JSONObject obj = new JSONObject();
		String jsonString = "[";
		for(int i=0;i<len-1;i++){
			if(i<expenseDataList.size())
			obj.put("expenseAmount", (expenseDataList.get(i)).getExpenseAmount());
			if(i<incomeDataList.size())
			obj.put("incomeAmount", (incomeDataList.get(i)).getIncomeAmount());
			try {
				jsonString += objMapper.writeValueAsString(obj)+",";
				System.out.println("jsonstring----------"+jsonString);
			} catch (IOException e) {
			e.printStackTrace();
			}
		}
		if(len==expenseDataList.size()&& expenseDataList.size()!=0)
		obj.put("expenseAmount", (expenseDataList.get(len-1)).getExpenseAmount());
		if(len==incomeDataList.size() && incomeDataList.size()!=0)
		obj.put("incomeAmount", (incomeDataList.get(len-1)).getIncomeAmount());
		try {
			jsonString += objMapper.writeValueAsString(obj)+"]";
		} catch (IOException e) {
		
			e.printStackTrace();
		}
//		try {
//			return objMapper.writeValueAsString(expenseDataList);
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
	return jsonString;
//		return "";
	}
	
	
	
}

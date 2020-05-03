
package com.project.controller;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.reversepojos.TableExpense;
import com.project.reversepojos.TableExpenseCategory;
import com.project.reversepojos.TableUser;
import com.project.service.ExpenseServiceInterface;
import com.project.service.MappingServiceInterface;

@Controller
public class DashboardController {
	@Autowired
	ExpenseServiceInterface expenseserviceinterfaceref;

	@Autowired
	MappingServiceInterface mapserint;

	
//	@Autowired
//	ExpenseServiceCategoryInterface expenseservicecategoryinterfaceref;

	HashMap finalgainloss(HttpSession hs){
		System.out.println("final gain lo");
		HashMap hm=new HashMap<>();
		hm=mapserint.finalgainloss(hs);
		return hm;
				
	}
	
	@RequestMapping(value="/dashboard")
	public String controllerlogin(Model map,HttpSession hs)
	{
		String userrole="";
		String resultstatus="";
		//Double lossgainamount=finalgainloss(hs);
		HashMap chartdata=finalgainloss(hs);
		try{
			TableUser obj=(TableUser)hs.getAttribute("valid_user");
			userrole=obj.getRole();
			if(userrole.equals("user")==true){
				hs.setAttribute("chartdata",chartdata);
				ArrayList<TableExpense> expenseDataList=expenseserviceinterfaceref
						.getExpensesofParticularUser(obj.getUserId());
				
				ArrayList<TableExpenseCategory> expenseCategoryList=expenseserviceinterfaceref
						.showExpenseCategory(obj.getUserId());
					
			
				map.addAttribute("expense_data", expenseDataList);
				map.addAttribute("exp_cat_list", expenseCategoryList);
				resultstatus="dashboardPage";
			}
			else if(userrole.equals("admin")==true){
				hs.setAttribute("chartdata",chartdata);
				ArrayList<TableExpense> expenseDataList=expenseserviceinterfaceref
						.getExpensesofParticularUser(obj.getUserId());
				
				ArrayList<TableExpenseCategory> expenseCategoryList=expenseserviceinterfaceref
						.showExpenseCategory(obj.getUserId());
			
				map.addAttribute("expense_data", expenseDataList);
				map.addAttribute("exp_cat_list", expenseCategoryList);
				resultstatus="adminDashboard";
			}
		}catch(NullPointerException e){
			return "redirect:/login";
		}catch(Exception ee){
			resultstatus="failure";
		}
		return resultstatus;
	}
	
	@RequestMapping(value = "/dashboard/deleteexpense/{id}", method = RequestMethod.GET)
	public String deleteExpense(@PathVariable("id") int id, Model map,HttpSession hs) {
		@SuppressWarnings("unused")
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
		expenseserviceinterfaceref.deleteExpensebyId(id);
		return "redirect:/dashboard";
	}
	
	@RequestMapping(value = "/payorsend", method = RequestMethod.GET)
	public String payorsend(HttpSession hs) {
		return "payuform";
	}
	
	@RequestMapping(value = "/payorsend", method = RequestMethod.POST)
	public String payorsendpost(HttpSession hs) {
		return "payuform";
	}
	
	
	
}

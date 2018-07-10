package com.project.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.reversepojos.TableIncome;
import com.project.reversepojos.TableIncomeCategory;
import com.project.reversepojos.TableUser;
import com.project.service.IncomeServiceCategoryInterface;
import com.project.service.IncomeServiceInterface;

@Controller
public class IncomeController {

	@Autowired
	IncomeServiceInterface incomeserviceinterfaceref;
	
	@Autowired
	IncomeServiceCategoryInterface incomeservicecategoryinterfaceref;

	@RequestMapping(value = "/showallincomecategories")
	public String ShowAllExpenseCategoryForAdmin(HttpSession hs) {
		try {
			TableUser tbuser = (TableUser) hs.getAttribute("valid_user");
			ArrayList<TableIncomeCategory> incomes_cat_list = incomeservicecategoryinterfaceref.getAllIncomeCategoryForAdmin();
			hs.setAttribute("incomes_cat_list", incomes_cat_list);
			return "showIncomeCategoriesForAdmin";
		} catch (NullPointerException err) {
			hs.setAttribute("checkuserstatus", "expensescategorieslisterror");
			return "failure";
		}
	}
	@RequestMapping(value = "incomecategory/editincomecategory/{id}", method = RequestMethod.GET)
	public String editIncomeCategory(@PathVariable("id") int incomecategoryid,Model map,HttpSession hs) {
		try{TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		map.addAttribute("incomecatpojo", new TableIncomeCategory());
		return "editincomeCategory";
		}catch(Exception err){
			hs.setAttribute("checkuserstatus", "session_out");
			return "failure";
		}
	}

	@RequestMapping(value = "incomecategory/editincomecategory/{id}", method = RequestMethod.POST)
	public String editIncomeCategoryPost(@PathVariable("id") int incomecategoryid, TableIncomeCategory tbexpcatref, HttpSession hs) {
		System.out.println("expensecategory id"+incomecategoryid);
		tbexpcatref.setIncomeCategoryId(incomecategoryid);
		try{TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		incomeservicecategoryinterfaceref.editIncomeCategory(tbexpcatref);
		System.out.println("reached after updation");
		return "redirect:/showallincomecategories";
		}catch(Exception err){
			hs.setAttribute("checkuserstatus", "expensescategoriesediterror");
			return "failure";
		}
	}
	
	@RequestMapping(value = "/updateincomecategory", method = RequestMethod.GET)
	public String updateIncomeCategory(TableIncomeCategory tbexpcatref, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			tbexpcatref.setUserId(userobj.getUserId());
			ArrayList<TableIncomeCategory> incomes_cat_list = incomeserviceinterfaceref.showIncomeCategory(userobj.getUserId());
			System.out.println("incomes_cat_list" + incomes_cat_list.toString());
			map.addAttribute("incomes_cat_list", incomes_cat_list);
			map.addAttribute("incomecatpojo", new TableIncomeCategory());
			return "updateIncomeCategoryForUser";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/updateincomecategory", method = RequestMethod.POST)
	public String updateExpenseCategory(TableIncomeCategory tbexpcatref, Model map, HttpSession hs, HttpServletRequest request) {
		System.out.println("in edit income category-------------------post method-----------------------");
		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		try {
			incomeservicecategoryinterfaceref.editIncomeCategory(tbexpcatref);
			return "redirect:/income";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}
	@RequestMapping(value = "/deleteincomecategory", method = RequestMethod.GET)
	public String deleteIncomeCategory(TableIncomeCategory tbexpcatref, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			tbexpcatref.setUserId(userobj.getUserId());
			ArrayList<TableIncomeCategory> incomes_cat_list = incomeserviceinterfaceref.showIncomeCategory(userobj.getUserId());
			System.out.println("incomes_cat_list" + incomes_cat_list .toString());
			map.addAttribute("incomes_cat_list", incomes_cat_list );
			map.addAttribute("incomecatpojo", new TableIncomeCategory());
			return "deleteIncomeCategoryForUser";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}
	@RequestMapping(value = "/deleteincomecategory", method = RequestMethod.POST)
	public String deleteIncomeCategory(TableIncomeCategory tbexpcatref, Model map, HttpSession hs, HttpServletRequest request) {
		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		try {
			incomeservicecategoryinterfaceref.deleteIncomeCategory(tbexpcatref.getIncomeCategoryId());
			return "redirect:/income";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}
	

	@RequestMapping(value = "/incomecategory/deleteincomecategory/{id}", method = RequestMethod.GET)
	public String deleteIncomeCategory(@PathVariable("id") int id, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			incomeservicecategoryinterfaceref.deleteIncomeCategory(id);
			return "redirect:/showallincomecategories";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/showallincomes")
	public String ShowAllExpensesForAdmin(HttpSession hs) {
		try {
			TableUser tbuser = (TableUser) hs.getAttribute("valid_user");
			ArrayList<TableUser> incomeslist = incomeserviceinterfaceref.getallincomes();
			hs.setAttribute("incomes_list", incomeslist);
			return "showIncomesForAdmin";
		} catch (NullPointerException err) {
			hs.setAttribute("checkuserstatus", "incomeslisterror");
			return "failure";
		}
	}

	@RequestMapping("/income")
	public String incomePage(Model map,HttpSession hs)
	{
		TableUser obj=(TableUser)hs.getAttribute("valid_user");
		try{
		System.out.println("----income controller---------get method--------user id"+obj.getUserId());
		ArrayList<TableIncome> incomeDataList=incomeserviceinterfaceref
				.getIncomeofParticularUser(obj.getUserId());
		System.out.println("********"+incomeDataList);
		
		System.out.println("before adding default category list----------------");
		ArrayList<TableIncomeCategory> defaultIncomeCategory=incomeserviceinterfaceref.getdefaultIncomeCategory();
		System.out.println("defaultIncomeCategory----"+defaultIncomeCategory.toString());
	
		
		ArrayList<TableIncomeCategory> incomeCategoryList=incomeserviceinterfaceref
				.showIncomeCategory(obj.getUserId());
		System.out.println("********"+incomeCategoryList);
		map.addAttribute("income_data", incomeDataList);
		map.addAttribute("defaultIncomeCategory",defaultIncomeCategory);
		map.addAttribute("income_cat_list", incomeCategoryList);
		return "incomePage";
		}catch(NullPointerException e){
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/income/addincome")
	public String addExpense(Model map, HttpSession hs)
	{
		System.out.println("---------------------add income get method------------------------");
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
		try{
		
			System.out.println("before adding default category list----------------");
			//ArrayList<TableIncomeCategory> defaultIncomeCategory=incomeserviceinterfaceref.getdefaultIncomeCategory();
			//System.out.println("default_category----"+defaultIncomeCategory.toString());
		
		ArrayList<TableIncomeCategory>incomecatlist=incomeserviceinterfaceref.showIncomeCategory(userobj.getUserId());
		System.out.println(incomecatlist.toString());
		System.out.println("userid in add income:"+userobj.getUserId());
		
		map.addAttribute("incomepojo", new TableIncome(userobj.getUserId()));
		//map.addAttribute("default_category",defaultIncomeCategory);
		map.addAttribute("catlist",incomecatlist);
		
		return "addincome";
		}catch(NullPointerException e){
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/income/addincome", method = RequestMethod.POST)
	public String addIncome(TableIncome tbincref, Model map,HttpSession hs) {
		System.out.println("---------------------add income post method------------------------");
		incomeserviceinterfaceref.addIncome(tbincref);
		map.addAttribute("addedincome", tbincref);
		return "redirect:/income";
	}
	
	@RequestMapping(value = "/income/deleteincome/{id}", method = RequestMethod.GET)
	public String deleteIncome(@PathVariable("id") int id,Model map,HttpSession hs) {
		System.out.println("-----------delete income------------------");
		try{
			@SuppressWarnings("unused")
			TableUser userobj=(TableUser)hs.getAttribute("valid_user");
			incomeserviceinterfaceref.deleteIncomeebyId(id);
			return "redirect:/income";
		}catch(NullPointerException e)
		{
			return "redirect:/login";
		}
		
		/*tbincomeref.setUserId(userobj.getUserId());
		System.out.println("value from addincome ref to delete income controller method"+tbincomeref.toString());
		System.out.println("value from obj"+userobj.toString());
		ArrayList<TableIncome>Incomeslist=incomeserviceinterfaceref.getIncomeofParticularUser(userobj.getUserId());
		System.out.println("Incomelist"+ Incomeslist.toString());
		map.addAttribute("allincome", Incomeslist);
		return "allincomefordelete";*/
		
		
	}
	
	/*@RequestMapping(value = "/deleteincome", method = RequestMethod.POST)
	public String deleteExpense(TableIncome tbincomeref,HttpSession hs, HttpServletRequest request,HttpServletResponse response) {
		try{
			int id=Integer.parseInt(request.getParameter("incomeid"));
			System.out.println("reached before deleting expense"+id);
			incomeserviceinterfaceref.deleteIncomeebyId(id);
			return "delincom" ;	
		}
		catch(Exception e)
		{
			return "redirect:" + "/income/deleteincome";
		}
	}*/
	
	@RequestMapping(value = "/updateincome", method = RequestMethod.GET)
	public String updateIncome(TableIncome tbincomeref, Model map,HttpSession hs) {
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
		tbincomeref.setUserId(userobj.getUserId());
		System.out.println("value from addincome ref to delete expense controller method"+tbincomeref.toString());
		System.out.println("value from obj"+userobj.toString());
		ArrayList<TableIncome>incomelist=incomeserviceinterfaceref.getIncomeofParticularUser(userobj.getUserId());
		System.out.println("incomelist"+ incomelist.toString());
		map.addAttribute("allincome", incomelist);
		map.addAttribute("updateIncomepojo", new TableIncome());
		return "allincomeforupdate";
	}
	
	@RequestMapping(value = "/updateincome", method = RequestMethod.POST)
	public String updateIncome(TableIncome tbexpref, Model map,HttpSession hs,HttpServletRequest request) {
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
		int id=Integer.parseInt(request.getParameter("incomeid"));
		//System.out.println("uuuuuiiiiiiddd:"+userobj.getUserId());
		map.addAttribute("newupdateincomepojo",new TableIncome());
		ArrayList<TableIncomeCategory>catlist=incomeserviceinterfaceref.showIncomeCategory(userobj.getUserId());
		map.addAttribute("userincomeidforupdate",id);
		map.addAttribute("catlist",catlist);
		return "newupdateincome";
	}
	@RequestMapping(value = "/finalupdateincome", method = RequestMethod.POST)
	public String finalUpdateIncome(TableIncome tbincomeref, Model map,HttpSession hs,HttpServletRequest request) {
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
		int incomeid=Integer.parseInt(request.getParameter("tempfieldincomeid"));
		System.out.println("user income id in finalupdate"+incomeid);
		tbincomeref.setUserId(userobj.getUserId());
		tbincomeref.setIncomeId(incomeid);
		System.out.println(tbincomeref.toString());
		incomeserviceinterfaceref.updateIncome(tbincomeref);
		return  "redirect:/income/addincome/";
	}
	@RequestMapping(value = "/income/editincome/{id}",method=RequestMethod.GET)
	public String editIncome(@PathVariable("id") int incomeid,TableIncome tbincomeref, Model map,HttpSession hs) {
		System.out.println("edit income get method---------------------------");
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
//		tbexpref.setUserId(userobj.getUserId());
//		tbexpref.setExpenseId(expenseid);
		map.addAttribute("incomepojo", tbincomeref);		
		ArrayList<TableIncomeCategory>incomecatlist=incomeserviceinterfaceref.showIncomeCategory(userobj.getUserId());
		map.addAttribute("catlist",incomecatlist);
		System.out.println("leaving edit income get method");
	//	expenseserviceinterfaceref.updateExpense(tbexpref);
	//	return  "redirect:/expense/addexpense/";
		return "editincome";
	}

	@RequestMapping(value = "income/editincome/{id}",method=RequestMethod.POST)
	public String editIncomePost(@PathVariable("id") int incomeid,TableIncome tbincomeref, Model map,HttpSession hs) {
		System.out.println("edit income post method---------------------------");
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
		tbincomeref.setUserId(userobj.getUserId());
		tbincomeref.setIncomeId(incomeid);
		incomeserviceinterfaceref.updateIncome(tbincomeref);
		System.out.println("in editincome post method");
		map.addAttribute("incomepojo", tbincomeref);		
//		ArrayList<TableExpenseCategory>expensecatlist=expenseserviceinterfaceref.showExpenseCategory(userobj.getUserId());
//		map.addAttribute("catlist",expensecatlist);
//		return  "redirect:/expense/addexpense/";
		return "redirect:/income";
	}

	
	@RequestMapping(value = "/addincome/addincomecategory", method = RequestMethod.GET)
	public String addincomecategory(Model map,HttpSession hs) {
		try{
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
		System.out.println("userid for income add income:"+userobj.getUserId());	
		System.out.println("in income category controller ");
		map.addAttribute("incomecategorypojo", new TableIncomeCategory(userobj.getUserId()));
		return "addincomecategory";
		}
		catch(Exception e)
		{
//			return "redirect:" + "/loginuser";
			return "failure";
		}
	}
	@RequestMapping(value = "/addincome/addincomecategory", method = RequestMethod.POST)
	public String addincomecategory(TableIncomeCategory tbinccatref, Model map,HttpSession hs) {
//		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
//		tbinccatref.setUserId(userobj.getUserId());
//		System.out.println(tbinccatref.toString());
		
		incomeservicecategoryinterfaceref.addIncomeCategory(tbinccatref);
		//map.addAttribute("addexpensecategory",tbexpcatref);
		return "redirect:" + "/income";
	}

	
	@RequestMapping(value="/income/all")
	public @ResponseBody String getAllFood(@RequestParam(value="category") String cat,HttpSession hs){
//		ObjectMapper objMapper=new ObjectMapper();
		
		TableUser userobj=(TableUser)hs.getAttribute("valid_user");
		System.out.println("category in ajax "+cat);
//		ArrayList<TableExpense> expenseDataList=expenseserviceinterfaceref
//				.getExpensesofParticularUser(2);
		ArrayList<TableIncome> incomeDataList=new ArrayList<>();
		if(Integer.parseInt(cat)==0)
			incomeDataList=incomeserviceinterfaceref
				.getIncomeofParticularUser(userobj.getUserId());
		else
		incomeDataList=
				incomeserviceinterfaceref
				.getIncomeByUserAndCategoryId(Integer.parseInt(cat), userobj.getUserId());

		
		
		String returnText="";
		for(TableIncome t:incomeDataList)
			returnText += "<tr><td>"+t.getIncomeId()+"</td><td>"+
						t.getIncomeAmount()+"</td><td>"+
						t.getIncomeCategoryId()+"</td><td>"+
						t.getIncomeDate()+"</td><td>"+
						t.getIncomeDescription()+"</td><td>"
						+ "<a data-toggle='modal' data-target='#modalIncomeEdit' class='btn mini blue-stripe' href='/ExpenseManager/income/editincome/"+t.getIncomeId()+"'>Edit</a></td><td>"
					+"<a href='/ExpenseManager/income/deleteincome/"+t.getIncomeId()+"' class='confirm-delete btn mini red-stripe'>Delete</a>"+"<td></tr>"
							+ "	<div id='modalIncomeEdit' class='modal fade' role='dialog' data-backdrop='static' data-keyboard='false'> <div class='modal-dialog'><!-- Modal content--><div class='modal-content'></div></div></div>;";
		return returnText;
	}

}

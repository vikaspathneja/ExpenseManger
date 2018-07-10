package com.project.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.project.reversepojos.TableExpense;
import com.project.reversepojos.TableExpenseCategory;
import com.project.reversepojos.TableUser;
import com.project.service.ExpenseServiceCategoryInterface;
import com.project.service.ExpenseServiceInterface;

@Controller
public class ExpenseController {
	@Autowired
	ExpenseServiceInterface expenseserviceinterfaceref;

	@Autowired
	ExpenseServiceCategoryInterface expenseservicecategoryinterfaceref;

	
	@RequestMapping(value = "/showallexpensecategories")
	public String ShowAllExpenseCategoryForAdmin(HttpSession hs) {
		try {
			TableUser tbuser = (TableUser) hs.getAttribute("valid_user");
			ArrayList<TableExpenseCategory> expenses_cat_list = expenseservicecategoryinterfaceref.getAllExpenseCategoryForAdmin();
			hs.setAttribute("expenses_cat_list", expenses_cat_list);
			return "showExpenseCategoriesForAdmin";
		} catch (NullPointerException err) {
			hs.setAttribute("checkuserstatus", "expensescategorieslisterror");
			return "failure";
		}
	}
	
	@RequestMapping(value = "expensecategory/editexpensecategory/{id}", method = RequestMethod.GET)
	public String editExpenseCategory(@PathVariable("id") int expensecategoryid,Model map,HttpSession hs) {
		try{TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		map.addAttribute("expensecatpojo", new TableExpenseCategory());
		return "editexpenseCategory";
		}catch(Exception err){
			hs.setAttribute("checkuserstatus", "session_out");
			return "failure";
		}
	}
	@RequestMapping(value = "expensecategory/editexpensecategory/{id}", method = RequestMethod.POST)
	public String editExpenseCategoryPost(@PathVariable("id") int expensecategoryid, TableExpenseCategory tbexpcatref, HttpSession hs) {
		System.out.println("expensecategory id"+expensecategoryid);
		tbexpcatref.setExpenseCategoryId(expensecategoryid);
		try{TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		System.out.println("reacher before updation");
		expenseservicecategoryinterfaceref.editExpenseCategory(tbexpcatref);
		System.out.println("reached after updation");
		return "redirect:/showallexpensecategories";
		}catch(Exception err){
			hs.setAttribute("checkuserstatus", "expensescategoriesediterror");
			return "failure";
		}
	}
	
	
	@RequestMapping(value = "/expensecategory/deleteexpensecategory/{id}", method = RequestMethod.GET)
	public String deleteExpenseCategory(@PathVariable("id") int id, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			expenseservicecategoryinterfaceref.deleteExpenseCategory(id);
			return "redirect:/showallexpensecategories";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/showallexpenses")
	public String ShowAllExpensesForAdmin(HttpSession hs) {
		try {
			TableUser tbuser = (TableUser) hs.getAttribute("valid_user");
			ArrayList<TableUser> expenseslist = expenseserviceinterfaceref.getallexpenses();
			hs.setAttribute("expenses_list", expenseslist);
			return "showExpensesForAdmin";
		} catch (NullPointerException err) {
			hs.setAttribute("checkuserstatus", "expenseslisterror");
			return "failure";
		}
	}

	@RequestMapping(value = "/expense")
	public String expensePage(Model map, HttpSession hs) {
		System.out.println("session id in expense controller" + hs.getId());
		TableUser obj = (TableUser) hs.getAttribute("valid_user");
		if (obj != null) {
			ArrayList<TableExpense> expenseDataList = expenseserviceinterfaceref
					.getExpensesofParticularUser(obj.getUserId());

			System.out.println("before adding default category list----------------");
			/*
			 * ArrayList<TableExpenseCategory>
			 * defaultExpenseCategory=expenseserviceinterfaceref
			 * .getdefaultExpenseCategory();
			 */
			ArrayList<TableExpenseCategory> expenseCategoryList = expenseserviceinterfaceref
					.showExpenseCategory(obj.getUserId());
			// map.addAttribute("default_category", defaultExpenseCategory);
			map.addAttribute("expense_data", expenseDataList);
			map.addAttribute("exp_cat_list", expenseCategoryList);
			return "expensePage";
		} else
			return "redirect:/login";

	}

	
	@RequestMapping(value = "/expense/addexpense", method = RequestMethod.GET)
	public String addExpense(Model map, HttpSession hs) {
		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		try {
			System.out.println("userid for expense in add expense:" + userobj.getUserId());
			// ArrayList<TableExpenseCategory>
			// defaultExpenseCategory=expenseserviceinterfaceref
			// .getdefaultExpenseCategory();

			ArrayList<TableExpenseCategory> expensecatlist = expenseserviceinterfaceref
					.showExpenseCategory(userobj.getUserId());
			System.out.println(expensecatlist.toString());
			// int newuserid=(int)hs.getAttribute("newuserid");
			// map.addAttribute("default_category", defaultExpenseCategory);
			map.addAttribute("expensepojo", new TableExpense(userobj.getUserId()));
			map.addAttribute("catlist", expensecatlist);
			return "addexpense";
		} catch (Exception e) {
			return "redirect:" + "/login";
		}
	}

	@RequestMapping(value = "/expense/addexpense", method = RequestMethod.POST)
	public String addExpense(TableExpense tbexpref, Model map) {
		expenseserviceinterfaceref.addExpense(tbexpref);
		map.addAttribute("addedexpense", tbexpref);
		// return "successafteraddingexpense";
		return "redirect:" + "/expense";
	}

	@RequestMapping(value = "/addexpense/addexpensecategory", method = RequestMethod.GET)
	public String addexpensecategory(Model map, HttpSession hs) {
		try {
			System.out.println("hiiiiiiii");
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			System.out.println("userid for expense add expense:" + userobj.getUserId());
			System.out.println("in expense category controller ");
			map.addAttribute("expensecategorypojo", new TableExpenseCategory(userobj.getUserId()));
			return "addexpensecategory";
		} catch (Exception e) {
			System.out.println("in exception controller");
			e.printStackTrace();
			// return "redirect:" + "/login";
			return "failure";
		}
	}

	@RequestMapping(value = "/addexpense/addexpensecategory", method = RequestMethod.POST)
	public String addexpensecategory(TableExpenseCategory tbexpcatref, Model map) {
		System.out.println("in add expense category");
		expenseservicecategoryinterfaceref.addExpenseCategory(tbexpcatref);
		// map.addAttribute("addexpensecategory",tbexpcatref);
		return "redirect:" + "/expense";
	}

	@RequestMapping(value = "/expense/deleteexpense/{id}", method = RequestMethod.GET)
	public String deleteExpense(@PathVariable("id") int id, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			// tbexpref.setUserId(userobj.getUserId());
			// System.out.println("value from addexpense ref to delete expense
			// controller method"+tbexpref.toString());
			// System.out.println("value from obj"+userobj.toString());
			// ArrayList<TableExpense>expenseslist=expenseserviceinterfaceref.getExpensesofParticularUser(userobj.getUserId());
			// System.out.println("expenseslist"+ expenseslist.toString());
			// map.addAttribute("allexpenses", expenseslist);
			// return "allexpensesfordelete";
			// System.out.println("in delete expense ********"+id);
			expenseserviceinterfaceref.deleteExpensebyId(id);
			return "redirect:/expense";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/expense/deleteexpense", method = RequestMethod.POST)
	public String deleteExpense(TableExpense tbexpref, HttpSession hs, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int id = Integer.parseInt(request.getParameter("expenseid"));
			System.out.println("reached before deleting expense" + id);
			expenseserviceinterfaceref.deleteExpensebyId(id);
			return "delexp";
		} catch (Exception e) {
			return "redirect:" + "/expense/deleteexpense";
		}

	}

	
	@RequestMapping(value = "/updateexpense", method = RequestMethod.GET)
	public String updateExpense(TableExpense tbexpref, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			tbexpref.setUserId(userobj.getUserId());
			System.out.println("value from addexpense ref to delete expense controller method" + tbexpref.toString());
			System.out.println("value from obj" + userobj.toString());
			ArrayList<TableExpense> expenseslist = expenseserviceinterfaceref
					.getExpensesofParticularUser(userobj.getUserId());
			System.out.println("expenseslist" + expenseslist.toString());
			map.addAttribute("allexpenses", expenseslist);
			map.addAttribute("updateExpensepojo", new TableExpense());
			return "allexpensesforupdate";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/updateexpense", method = RequestMethod.POST)
	public String updateExpense(TableExpense tbexpref, Model map, HttpSession hs, HttpServletRequest request) {
		System.out.println("in edit expense-------------------post method-----------------------");
		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		try {
			int id = Integer.parseInt(request.getParameter("expenseid"));
			map.addAttribute("newupdateexpensepojo", new TableExpense());
			ArrayList<TableExpenseCategory> catlist = expenseserviceinterfaceref
					.showExpenseCategory(userobj.getUserId());
			map.addAttribute("userexpenseidforupdate", id);
			map.addAttribute("catlist", catlist);
			return "newupdateexpense";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/updateexpensecategory", method = RequestMethod.GET)
	public String updateExpenseCategory(TableExpenseCategory tbexpcatref, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			tbexpcatref.setUserId(userobj.getUserId());
			ArrayList<TableExpenseCategory> expenses_cat_list = expenseserviceinterfaceref.showExpenseCategory(userobj.getUserId());
			System.out.println("expenses_cat_list" + expenses_cat_list.toString());
			map.addAttribute("expenses_cat_list", expenses_cat_list);
			map.addAttribute("expensecatpojo", new TableExpenseCategory());
			return "updateExpenseCategoryForUser";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/updateexpensecategory", method = RequestMethod.POST)
	public String updateExpenseCategory(TableExpenseCategory tbexpcatref, Model map, HttpSession hs, HttpServletRequest request) {
		System.out.println("in edit expense category-------------------post method-----------------------");
		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		try {
			expenseservicecategoryinterfaceref.editExpenseCategory(tbexpcatref);
			return "redirect:/expense";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/deleteexpensecategory", method = RequestMethod.GET)
	public String deleteExpenseCategory(TableExpenseCategory tbexpcatref, Model map, HttpSession hs) {
		try {
			TableUser userobj = (TableUser) hs.getAttribute("valid_user");
			tbexpcatref.setUserId(userobj.getUserId());
			ArrayList<TableExpenseCategory> expenses_cat_list = expenseserviceinterfaceref.showExpenseCategory(userobj.getUserId());
			System.out.println("expenses_cat_list" + expenses_cat_list.toString());
			map.addAttribute("expenses_cat_list", expenses_cat_list);
			map.addAttribute("expensecatpojo", new TableExpenseCategory());
			return "deleteExpenseCategoryForUser";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/deleteexpensecategory", method = RequestMethod.POST)
	public String deleteExpenseCategory(TableExpenseCategory tbexpcatref, Model map, HttpSession hs, HttpServletRequest request) {
		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		try {
			expenseservicecategoryinterfaceref.deleteExpenseCategory(tbexpcatref.getExpenseCategoryId());
			return "redirect:/expense";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(value = "/finalupdateexpense", method = RequestMethod.POST)
	public String finalUpdateExpense(TableExpense tbexpref, Model map, HttpSession hs, HttpServletRequest request) {
		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		try {
			int expenseid = Integer.parseInt(request.getParameter("tempfieldexpid"));

			System.out.println("user expense id in finalupdate" + expenseid);
			tbexpref.setUserId(userobj.getUserId());
			tbexpref.setExpenseId(expenseid);
			System.out.println(tbexpref.toString());
			expenseserviceinterfaceref.updateExpense(tbexpref);
			return "redirect:/expense/addexpense/";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "expense/editexpense/{id}", method = RequestMethod.GET)
	public String editExpense(@PathVariable("id") int expenseid, TableExpense tbexpref, Model map, HttpSession hs) {
		System.out.println("edit expense get method-------------------------------------called");
		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		// tbexpref.setUserId(userobj.getUserId());
		// tbexpref.setExpenseId(expenseid);
		// hs.setAttribute("currentexpenseid", expenseid);
		System.out.println("get tableexpense---------------" + tbexpref.toString());
		map.addAttribute("expensepojo", tbexpref);

		System.out.println("before adding default category list----------------");
		// ArrayList<TableExpenseCategory>
		// defaultExpenseCategory=expenseserviceinterfaceref
		// .getdefaultExpenseCategory();

		ArrayList<TableExpenseCategory> expensecatlist = expenseserviceinterfaceref
				.showExpenseCategory(userobj.getUserId());
		// map.addAttribute("default_category", defaultExpenseCategory);
		map.addAttribute("catlist", expensecatlist);

		// expenseserviceinterfaceref.updateExpense(tbexpref);
		// return "redirect:/expense/addexpense/";
		System.out.println("edit expense get method-------------------------------------leavingg");
		return "editexpense";
	}

	@RequestMapping(value = "expense/editexpense/{id}", method = RequestMethod.POST)
	public String editExpensePost(@PathVariable("id") int expenseid, TableExpense tbexpref, Model map, HttpSession hs) {
		System.out.println("reached in edit expense post method---------------------reached");
		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		try {
			// tbexpref.setUserId(userobj.getUserId());
			tbexpref.setExpenseId(expenseid);
			expenseserviceinterfaceref.updateExpense(tbexpref);
			map.addAttribute("expensepojo", tbexpref);
			// ArrayList<TableExpenseCategory>expensecatlist=expenseserviceinterfaceref.showExpenseCategory(userobj.getUserId());
			// map.addAttribute("catlist",expensecatlist);

			// return "redirect:/expense/addexpense/";
			System.out.println("reached in edit expense post---------------------leaving");
			return "redirect:/expense";
		} catch (Exception e) {
			return "redirect:/login";
		}
	}

	@RequestMapping(value = "/expense/food")
	public @ResponseBody String getAll(HttpSession hs) {
		ObjectMapper objMapper = new ObjectMapper();
		ArrayList<TableExpense> expenseDataList = expenseserviceinterfaceref
				.getExpensesofParticularUser(((TableUser) hs.getAttribute("valid_user")).getUserId());

		try {
			return objMapper.writeValueAsString(expenseDataList);
		} catch (JsonGenerationException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "empty";
	}

	@RequestMapping(value = "/expense/all")
	public @ResponseBody String getAllFood(@RequestParam(value = "category") String cat, HttpSession hs) {
		// ObjectMapper objMapper=new ObjectMapper();

		TableUser userobj = (TableUser) hs.getAttribute("valid_user");
		System.out.println("category in ajax " + cat);
		// ArrayList<TableExpense> expenseDataList=expenseserviceinterfaceref
		// .getExpensesofParticularUser(2);
		ArrayList<TableExpense> expenseDataList = new ArrayList<>();
		if (Integer.parseInt(cat) == 0)
			expenseDataList = expenseserviceinterfaceref.getExpensesofParticularUser(userobj.getUserId());
		else
			expenseDataList = expenseserviceinterfaceref.getExpensesByUserAndCategoryId(Integer.parseInt(cat),
					userobj.getUserId());

		String returnText = "";
		for (TableExpense t : expenseDataList)
			returnText += "<tr><td>" + t.getExpenseId() + "</td><td>" + t.getExpenseAmount() + "</td><td>" +
			/* t.getExpenseCategoryId()+"</td><td>"+ */
					t.getExpenseDate() + "</td><td>" + t.getExpenseDescription() + "</td><td>"
					+ "<a data-toggle='modal' data-target='#modalExpenseEdit' class='btn mini blue-stripe' href='/ExpenseManager/expense/editexpense/"
					+ t.getExpenseId() + "'>Edit</a></td><td>" + "<a href='/ExpenseManager/expense/deleteexpense/"
					+ t.getExpenseId() + "' class='confirm-delete btn mini red-stripe'>Delete</a>" + "<td></tr>"
					+ "	<div id='modalExpenseEdit' class='modal fade' role='dialog' data-backdrop='static' data-keyboard='false'> <div class='modal-dialog'><!-- Modal content--><div class='modal-content'></div></div></div>;";

		return returnText;
	}

}
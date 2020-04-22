<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Expense Manager</title>

<jsp:include page="scripts/scripts.jsp"></jsp:include>
<script type="text/javascript" src="resources/js/side_nav_dynamic.js"></script>
<jsp:include page="css/css.jsp"></jsp:include>
</head>
<body ng-app="expenseManager" onload="checkactivelink();">

	<%-- <jsp:include page="header/header_expensePage.jsp"></jsp:include> --%>
	<jsp:include page="header/header_dashboard.jsp"></jsp:include> 
	

	<div class="container-fluid ">
		<div class="row content">
			<div class="col-sm-2 sidenav hidden-xs ">
				<h2>Category</h2>
				<ul id="list" class="nav nav-pills nav-stacked">
					<li class="active" value="0"><a href="">All</a></li>
<%-- 
 					<form:form modelAttribute="default_category">
 					<c:forEach items="${default_category}" var="list">
 						<li value="${list.expenseCategoryId}"> ${list.expenseType}</li>
 					</c:forEach>
 					
 					</form:form>
 				
 --%> 				<form:form modelAttribute="exp_cat_list">
 					<c:forEach items="${exp_cat_list}" var="list" >
 						<li value="${list.expenseCategoryId}"> ${list.expenseType}</li>
 					</c:forEach>
 					
 					</form:form>
 					<li><a href="addexpense/addexpensecategory"
						data-toggle="modal" data-target="#modalExpenseCategory"> <span
							class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>Add
							Category
					</a></li>
					<li><a href="updateexpensecategory"
						data-toggle="modal" data-target="#modalExpenseCategory"> <span
							class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>Modify
							Category
					</a></li>
					<li><a href="deleteexpensecategory"
						data-toggle="modal" data-target="#modalExpenseCategory"> <span
							class="glyphicon glyphicon glyphicon-plus" aria-hidden="true"></span>Delete
							Category
					</a></li>
				</ul>
				<br>
			</div>

			<br>

			<div class="col-sm-10">

				<!--add expense  -->

				<a href="expense/addexpense" class="btn btn-info btn-lg pull-right"
					data-toggle="modal" data-target="#modalForm">Add Expense</a>

				<div id="modalForm" class="modal fade" role="dialog"
					data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content"></div>
					</div>
				</div>

				<br> <br>
				<div id="test_div">

					<table class="table table-striped table-hover table-users"
						id="dynamic_table">
						<thead>
							<tr>
								<th>Id</th>
								<th>Amount</th>
								<!-- <th>Category</th> -->
								<th>Date</th>
								<th>Description</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody id="tbody_id">
							<form:form modelAttribute="expense_data">
								<c:forEach items="${expense_data}" var="expense" varStatus="status">
									<tr>
										<td id="item">${expense.expenseId}</td>
										<td>${expense.expenseAmount}</td>
									<%-- 	<td>${expense.expenseCategoryId}</td> --%>
										<td>${expense.expenseDate}</td>
										<td>${expense.expenseDescription}</td>
										<td><a class="btn mini blue-stripe" 
										href="<c:url value='expense/editexpense/${expense_data[status.index].expenseId}' />"
										data-toggle="modal" data-target="#modalExpenseEdit">Edit</a></td>
										<td><a href="<c:url value='expense/deleteexpense/${expense_data[status.index].expenseId}' />"
											class="confirm-delete btn mini red-stripe" 
											role="button">Delete</a></td>
									</tr>
								</c:forEach>
						</form:form>
						</tbody>
					</table>


				</div>
			</div>

		</div>
	</div>
	<!-- expense category -->
	<div id="modalExpenseCategory" class="modal fade" role="dialog"
		data-backdrop="static" data-keyboard="false">

		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content"></div>
		</div>
	</div>
	
	
	<!-- expense edit -->
	<div id="modalExpenseEdit" class="modal fade" role="dialog"
		data-backdrop="static" data-keyboard="false">

		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content"></div>
		</div>
	</div>
	
	<jsp:include page="footer/footer.jsp"></jsp:include>

</body>
</html>
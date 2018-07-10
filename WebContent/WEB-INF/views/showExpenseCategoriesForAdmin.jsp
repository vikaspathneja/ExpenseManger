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
<!-- <script type="text/javascript" src="resources/js/side_nav_dynamic.js"></script> -->
<jsp:include page="css/css.jsp"></jsp:include>
<script type="text/javascript">
/* $(document).ready(function() {
	$("ul[id*=list] li").click(function() {
		$(".active").removeClass('active');
		var category = $(this).val();
		$(this).addClass('active');
		$.ajax({
			type : "GET",
			url : "expense/all",
			data : "category=" + category,
			success : function(response) {
				$("#tbody_id").html(response);
			},
			error : function(e) {
				alert('Error: ' + e);
			}
		});

	});
});
 */</script>



</head>

<body ng-app="expenseManager">

	<jsp:include page="header/header_dashboard.jsp"></jsp:include>
	<div class="container-fluid ">
		<div class="row content">
			<div class="col-sm-2 sidenav hidden-xs ">
				<h2>Category</h2>
				<ul id="list" class="nav nav-pills nav-stacked">
					<li ><a href="showallusers"> <span
							class="glyphicon glyphicon " aria-hidden="true"></span>All Users
						</a>
					</li>
					<li><a href="showallexpenses"> <span
							class="glyphicon glyphicon " aria-hidden="true"></span>All Expenses
						</a>
					</li>
					<li><a href="showallincomes"> <span
							class="glyphicon glyphicon " aria-hidden="true"></span>All Incomes
						</a>
					</li>
					<li><a href="showallexpensecategories"> <span
							class="glyphicon glyphicon " aria-hidden="true"></span>All Expense Categories
						</a>
					</li>
					<li><a href="showallincomecategories"> <span
							class="glyphicon glyphicon " aria-hidden="true"></span>All Income Categories
						</a>
					</li>
					<li><a href=""
						data-toggle="modal" data-target="#modalExpenseCategory"> <span
							class="glyphicon glyphicon " aria-hidden="true"></span>All Users Profile Images
						</a>
					</li>
				</ul>
				<br>
			</div>
			<br>
	<div class="col-sm-10">
			<a href=""  class="btn btn-info btn-lg pull-right">Admin DashBoard</a>
				<div id="test_div">
					<table class="table table-striped table-hover table-users"
						id="dynamic_table">
						<thead>
							<tr>
								<th>Expense Category Id</th>
								<th>Expense Type</th>
								 <th>Expense Category Description</th>
								<th>User Id</th>
								<th></th>
							</tr>
						</thead><tbody id="tbody_id">
							<form:form modelAttribute="expenses_cat_list">
								<c:forEach items="${expenses_cat_list}" var="list" varStatus="status">
									<tr>
										<td>${list.expenseCategoryId}</td>
										<td>${list.expenseType}</td>
										<td>${list.expenseCategoryDescription}</td>
										<td>${list.userId}</td>
										<td><a class="btn mini blue-stripe" 
										href="<c:url value='expensecategory/editexpensecategory/${expenses_cat_list[status.index].expenseCategoryId}' />"
										data-toggle="modal" data-target="#modalExpenseEdit">Edit</a></td>
										<td><a href="<c:url value='expensecategory/deleteexpensecategory/${expenses_cat_list[status.index].expenseCategoryId}' />"
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
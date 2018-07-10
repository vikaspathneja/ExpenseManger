<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head><title>Expense Manager</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<script type="text/javascript"
	src="resources/js/validation/expense_category_validation.js"></script>



<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">Add Category</h4>
</div>
<div class="modal-body">
	<div class="container">
		<div class="row">
			<div class='col-sm-6'>
				<div class="form-group"></div>
				<form:form method="post" modelAttribute="expensecategorypojo"
					style="display: block;" onsubmit="return validate()">

					<div class="form-group">
						<form:input tabindex="1" path="expenseType" id="type"
							class="form-control" placeholder="Expense type" value="" />
		<span id="type_result" style="color: red;"></span>
								
					</div>

					<div class="form-group">
						<form:input tabindex="2" path="expenseCategoryDescription"
							id="desc" class="form-control"
							placeholder="Expense Category Description" value="" />

					</div>
					<div class="form-group">
						<form:hidden tabindex="3" path="userId"
							id="userid" class="form-control"
							placeholder="User Id" value="${sessionScope.valid_user.userId}" />

					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-6 col-sm-offset-3">
								<input type="submit" tabindex="4"
									class="form-control btn btn-default" value="Save">
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

</div>

<!-- <div class="modal-footer form-group">
	<button type="button" class="btn btn-default" data-dismiss="modal">Save</button>
	<input type="submit" tabindex="3"
		class="btn btn-default form-control" data-dismiss="modal" value="Save">
	
	<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
</div> -->
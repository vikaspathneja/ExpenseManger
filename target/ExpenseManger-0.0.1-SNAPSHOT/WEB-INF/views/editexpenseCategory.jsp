<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <jsp:include page="scripts/scripts.jsp"></jsp:include>
<jsp:include page="css/css.jsp"></jsp:include>
 --%>
<!-- <script type="text/javascript"
	src="resources/js/validation/expense_validation.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
function GetOptions()
{
//alert("reahced");
var e = document.getElementById("myitem");
var strOptions = e.options[e.selectedIndex].value;
//alert("You have selected " + strOptions + "." );
document.getElementById("category").value=strOptions;
}
</script>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="resources/js/validation/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="resources/js/validation/jquery-ui-1.8.18.custom.min.js"></script>
	<link href="resources/css/normalize.css" rel="stylesheet" type="text/css"/>
	<link href="resources/css/datepicker.css" rel="stylesheet" type="text/css"/>	
</head><div class="modal-header">
	<button type="button" class="close" data-dismiss="modal">&times;</button>
	<h4 class="modal-title">Update Expense Category</h4>
</div>
<div class="modal-body">
	<div class="container">
		<div class="row">
			<div class='col-sm-6'>
				<div class="form-group"></div>
				<form:form method="post" modelAttribute="expensecatpojo" 
					style="display: block;" onsubmit="return validate()">

					<div class="form-group">
						<form:input tabindex="1" path="expenseType" id="expensetype"
							class="form-control" placeholder="Expense Type" value="" />
						<span id="expense_type_result" style="color: red;"></span>

					</div>

					<div class="form-group">
						<form:input tabindex="2" path="expenseCategoryDescription"
							id="expense_cat_desc" class="form-control"
							placeholder="Expense Category Description" value="" />

					</div>
					<div class="form-group">
						<form:hidden tabindex="5" path="userId" id="userid"
							class="form-control" placeholder="User Id"
							value="${sessionScope.valid_user.userId}" />
					</div>
					
					<div class="form-group">
						<div class="row">
							<div class="col-sm-6 col-sm-offset-3">
								<input type="submit" tabindex="3"
									class="form-control btn btn-default" value="Save">
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

</div>

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
//var strOptionsText = e.options[e.selectedIndex].text;
//alert(strOptionsText);
//alert("You have selected " + strOptions + "." );
document.getElementById("incomecategoryid").value=strOptions;
//document.getElementById("expensetype").value=strOptionsText;
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
	<h4 class="modal-title">Update Income Category</h4>
</div>
<div class="modal-body">
	<div class="container">
		<div class="row">
			<div class='col-sm-6'>
				<div class="form-group"></div>
				<form:form method="post" modelAttribute="incomecatpojo" 
					style="display: block;" onsubmit="return validate()">

					<div class="form-group">
						<select class="form-control" id="myitem" onchange="GetOptions();">
							<option value="">Select Old Category</option>
							<c:forEach items="${incomes_cat_list}" var="incomecatobj">
								<option value="${incomecatobj.incomeCategoryId}">${incomecatobj.incomeType}</option>
							</c:forEach>
						</select>
						<form:hidden id="incomecategoryid" path="incomeCategoryId"/>
						<span id="item_result" style="color: red;"></span>
					</div>
					
					<div class="form-group">
						<form:input tabindex="2" path="incomeType"
							id="incometype" class="form-control"
							placeholder="Enter New Income Type" value="" />
					</div>
										
					<div class="form-group">
						<form:input tabindex="2" path="incomeCategoryDescription"
							id="income_cat_desc" class="form-control"
							placeholder="Incoe Category Description" value="" />

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
									class="form-control btn btn-default" value="Update">
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

</div>

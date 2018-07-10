//
//$(document).ready(function() {
//	$("ul[id*=list] li").click(function() {
//		$(".active").removeClass('active');
//		var innerHtmlData = $("#dynamic_table").html();
//		alert(innerHtmlData);
//		$("#table_div").remove();
//		if ($(this).text() == "All") {
//			$(this).addClass('active');
//			$("#modalForm").after("<div id='table_div'>" +
//					"<table class='table table-striped table-hover table-users' " +
//							"id='dynamic_table'" +
//							"ng-controller='expenseAllController'></table></div>");
//			$("#dynamic_table").html(innerHtmlData);
//			
//		}
//		if ($(this).text() == "Food") {
//			$(this).addClass('active');
//			
//		}
//
//	});
//});
//

$(document).ready(function() {
	$("ul[id*=list] li").click(function() {
		$(".active").removeClass('active');
		var category = $(this).val();
		$(this).addClass('active');
//		if($(this).val()==0) $(this).addClass('active');
//		if($(this).val()==1) $(this).addClass('active');
//		if($(this).val()==2) $(this).addClass('active');
//		if($(this).val()==3) $(this).addClass('active');
//		if($(this).val()==4) $(this).addClass('active');
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

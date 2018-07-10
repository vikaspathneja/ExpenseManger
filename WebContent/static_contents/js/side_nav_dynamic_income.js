$(document).ready(function() {
	$("ul[id*=list] li").click(function() {
		$(".active").removeClass('active');
		var category = $(this).val();
		$(this).addClass('active');
		$.ajax({
			type : "GET",
			url : "income/all",
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

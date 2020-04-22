function validateDate(date) {
	var re = /^([0-9]{4})\/([0-9]{2})\/([0-9]{2})$/;
	return re.test(date);
}

function validateAmount(amount) {
//	var re = /^\d+$/;
	return !isNaN(parseFloat(amount)) && isFinite(amount);
}

function validate() {
	//for amount
	$("#amount_result").text("");
	var amount = $("#amount").val();
	
	//for date
	$("#date_result").text("");
	var date = $("#date").val();

	
	$("#item_result").text("");
	var item = $("#category").val();
	//for category
	if(item == null || item == "" || item==0){
		$("#item_result").text("* please fill out this field.");
		$("#item_result").css("color", "red");
		return false;
	}
	
	// for amount
	if (amount == null || amount == "") {
		$("#amount_result").text("* please fill out this field.");
		$("#amount_result").css("color", "red");
		return false;
	}
	if (!validateAmount(amount)) {
		$("#amount_result").text("* must be valid amount");
		$("#amount_result").css("color", "red");
		return false;
	}

	// for date
	if (date == null || date == "") {
		$("#date_result").text("* please fill out this field.");
		$("#date_result").css("color", "red");
		return false;
	}
	if (!validateDate(date)) {
		$("#date_result")
				.text(
						"* enter valid date as shown in hint.");
		$("#date_result").css("color", "red");
		return false;
	}
	if(document.getElementById("amount").value=="")
	{
		document.getElementById("amount").value=0;
		return true;
	}
	return true;
	
}

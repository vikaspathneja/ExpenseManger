function validateType(type) {
	var re = /^[a-zA-Z]+[a-zA-Z0-9]*$/;
	return re.test(type);
}


function validate() {
	//for type
	$("#type_result").text("");
	var type = $("#type").val();

	// for type
	if (type == null || type == "") {
		$("#type_result").text("* please fill out this field.");
		$("#type_result").css("color", "red");
		return false;
	}
	if (!validateType(type)) {
		$("#type_result").text("* must be valid expense type");
		$("#type_result").css("color", "red");
		return false;
	}

	return true;
}

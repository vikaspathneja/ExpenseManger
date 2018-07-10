function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function validatePassword(pass) {
	var re = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
	return re.test(pass);
}

function validateName(name) {
	var re = /^[A-z]+$/;
	return re.test(name);
}

function validateMobNum(mob) {
	var re = /^\d{10}$/;
	return re.test(mob);
}

function validate() {
	//for name
	$("#name_result").text("");
	var name = $("#name").val();
	// for email
	$("#email_result").text("");
	var email = $("#email").val();
	//for pass
	$("#pass_result").text("");
	var pass = $("#password").val();
	// for mob no
	$("#mob_result").text("");
	var mob = $("#mob_num").val();

	// for name
	if (name == null || name == "") {
		$("#name_result").text("* please fill out this field.");
		$("#name_result").css("color", "red");
		return false;
	}
	if (!validateName(name)) {
		$("#name_result").text("* must be valid name");
		$("#name_result").css("color", "red");
		return false;
	}

	// for email
	if (email == null || email == "") {
		$("#email_result").text("* please fill out this field.");
		$("#email_result").css("color", "red");
		return false;
	}
	if (!validateEmail(email)) {
		$("#email_result").text("* must be valid email");
		$("#email_result").css("color", "red");
		return false;
	}

	// for password
	if (pass == null || pass == "") {
		$("#pass_result").text("* please fill out this field.");
		$("#pass_result").css("color", "red");
		return false;
	}
	if (!validatePassword(pass)) {
		$("#pass_result")
				.text(
						"* Minimum 8 characters at least 1 Alphabet, 1 Number and 1 Special Character");
		$("#pass_result").css("color", "red");
		return false;
	}

	// for mob num
	if (mob == null || mob == "") {
		$("#mob_result").text("* please fill out this field.");
		$("#mob_result").css("color", "red");
		return false;
	}
	if (!validateMobNum(mob)) {
		$("#mob_result").text("* invalid number; must be ten digits");
		$("#mob_result").css("color", "red");
		return false;
	}
	return true;
}

function validate() {
	return true;
}

function GetOptionsForStatus()
{
//alert("reached");
var e = document.getElementById("selectstatus");
var strOptions = e.options[e.selectedIndex].value;
//alert("You have selected " + strOptions + "." );
document.getElementById("status").value=strOptions;
}

function GetOptionsForRole()
{
//alert("reached");
var e = document.getElementById("selectrole");
var strOptions = e.options[e.selectedIndex].value;
//alert("You have selected " + strOptions + "." );
document.getElementById("role").value=strOptions;
}

function validateY() {
    var y_val = document.getElementById("mainForm:Y").value;
    y_val = y_val.replace(",", ".");
    var y_valid = !((y_val == "") || !(!isNaN(parseFloat(y_val)) && isFinite(y_val)) || (y_val > 3) || (y_val < -3));
    if (y_valid) {
        var DisableArray = document.querySelectorAll('button[type="submit"]');
        for(var i=0;i<DisableArray.length;i++) {
            DisableArray[i].disabled = false;
            DisableArray[i].style.background = "#0078ae";
        }
        document.getElementById("mainForm:Y").style.borderColor = "#77d5f7";
    } else {
        var DisableArray = document.querySelectorAll('button[type="submit"]');
        for(var i=0;i<DisableArray.length;i++) {
            DisableArray[i].disabled = true;
            DisableArray[i].style.background = "#EEEEEE";
        }
        document.getElementById("mainForm:Y").style.borderColor = "red";
        alert("Input Y is out of range, Y set to 1");
        document.getElementById("mainForm:Y").value=1;
    }
}
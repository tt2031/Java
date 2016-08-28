/*var d = function (e) {
    var width = 600;
    var height = 600;
    var x = e.offsetX == undefined ? e.layerX : e.offsetX - width / 2;
    var y = e.offsetY == undefined ? e.layerY : e.offsetY;
    if (y <= height / 2)
        y = height / 2 - y;
    else
        y = -y + height / 2;
    var rad=0;
    rad = document.getElementById('mainForm:r').value;
    if (!isNaN(rad)) {
        var k = rad * 1.2;//Magic number! 1-длина отрезка R, 1.2 - длина от 0 до края (Вроде так)
        var l = 300;
        y = y * k / l;
        x = x * k / l;
    } else {
        alert("Set R first!");
        return;
    }
    document.getElementById('mainForm:xClick').value = x;
    document.getElementById('mainForm:yClick').value = y;
    document.getElementById('mainForm:areaClick').click();
};
function RadiusChange(rad){
    document.getElementById('mainForm:Radius').value=rad;
    document.getElementById('mainForm:radius').innerHTML=rad;
}
function areaClick(e) {
    var event = e||window.event;
    document.getElementById('mainForm:area').onclick = d(event);
}*/
var d = function (e) {
    var width = 600;
    var height = 600;
    var x = e.offsetX == undefined ? e.layerX : e.offsetX - width / 2;
    var y = e.offsetY == undefined ? e.layerY : e.offsetY;
    y = height / 2 - y;
    var rad;

    rad = document.getElementById('mainForm:r').value;
    if (!isNaN(rad)) {
        var k = rad * 1.2;
        var l = 300;
        y = y * k / l;
        x = x * k / l;
    } else {
        alert("Set R first!");
        return;
    }
    document.getElementById('mainForm:xClick').value = x;
    document.getElementById('mainForm:yClick').value = y;
    document.getElementById('mainForm:areaClick').click();
};
function areaClick(event) {
// document.getElementById('mainForm:area').onclick = d(event);
// document.getElementById('mainForm:area').onclick = function(e) {
    var width = 600;
    var height = 600;
    var x, y;
    if (event.offsetX == undefined) {
        x = event.layerX;
    } else {
        x = event.offsetX;
    }
    if (event.offsetY == undefined) {
        y = event.layerY;
    } else {
        y = event.offsetY;
    }
    x = x - 1503;
    x=x-92;
    y = -y + 420;
// var x = event.offsetX == undefined ? event.layerX : event.offsetX - width / 2;
// var y = event.offsetY == undefined ? event.layerY : event.offsetY;
// x = x - width / 2;
// y = height / 2 - y;
// console.log(x + " ; " + y);

    var rad;

    rad = document.getElementById('mainForm:r').value;
    if (!isNaN(rad)) {
        var k = rad * 1.2;
        var l = 300;
        y = y * k / l;
        x = x * k / l;
        x=x-0.12;
    } else {
        alert("Set R first!");
        return;
    }
    document.getElementById('mainForm:xClick').value = x;
    document.getElementById('mainForm:yClick').value = y;
    document.getElementById('mainForm:areaClick').click();
// }
}

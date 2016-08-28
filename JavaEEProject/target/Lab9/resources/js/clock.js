var timerId = setTimeout(function tick() {
    document.getElementById("clockForm:clockTimerBtn").click();
    timerId = setTimeout(tick, 5000);
}, 5000);
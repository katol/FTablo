var time = 0;
	var timerId = 0;
	var activate = 0;
	var redScope = 0;
    var blueScope = 0;
    var redPenalty = 0;
    var bluePenalty = 0;
	var blueRes = 0;
	var redRes = 0;
	var redName = "Боец номер-номер-номер раз";
	var blueName = "Боец два";
	document.getElementById("redName").innerHTML = redName;
	document.getElementById("blueName").innerHTML = blueName;
	function timer() {
		if (activate == 0){
		document.getElementById("startStop").innerHTML = "СТОП";
		activate = 1;
		timerId = setInterval(function () {
			time += 1;
			if(time < 0){
				time = 0;
			}
			document.getElementById("timer").innerHTML = Math.floor(time/600) + "" + Math.floor((time%600)/60) + ":" + Math.floor((time%60)/10) + time%10;
		}, 1000);
	}else{
	document.getElementById("startStop").innerHTML = "СТАРТ";
	activate = 0;
	stopTimer();
	}
	}

	function stopTimer(){
	clearInterval(timerId);
	}
	function plusTenSec(){
		time += 10;
		if(time < 0){
				time = 0;
			}
		document.getElementById("timer").innerHTML = Math.floor(time/600) + "" + Math.floor((time%600)/60) + ":" + Math.floor((time%60)/10) + time%10;
	}
	function minusTenSec(){
		time -= 10;
		if(time < 0){
				time = 0;
			}
		document.getElementById("timer").innerHTML = Math.floor(time/600) + "" + Math.floor((time%600)/60) + ":" + Math.floor((time%60)/10) + time%10;
	}
    function plusRedScope() {
        redScope += 1;
        document.getElementById("redScope").innerHTML = redScope;
    };
    function minusRedScope() {
        redScope -= 1;
        document.getElementById("redScope").innerHTML = redScope;
    };
    function plusBlueScope() {
        blueScope += 1;
        document.getElementById("blueScope").innerHTML = blueScope;
    };
    function minusBlueScope() {
        blueScope -= 1;
        document.getElementById("blueScope").innerHTML = blueScope;
    };

    function plusBluePenalty() {
        bluePenalty += 1;
        document.getElementById("bluePenalty").innerHTML = bluePenalty;
        if (bluePenalty == 0){
        	document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 white-card";
        }else if (bluePenalty == 1){
        	document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 yellow-card";
        }else if (bluePenalty == 2){
        	document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 orange-card";
        }else{
        	document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 red-card";
        }
    };
    function minusBluePenalty() {
        bluePenalty -= 1;
        document.getElementById("bluePenalty").innerHTML = bluePenalty;
        if (bluePenalty == 0){
        	document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 white-card";
        }else if (bluePenalty == 1){
        	document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 yellow-card";
        }else if (bluePenalty == 2){
        	document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 orange-card";
        }else{
        	document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 red-card";
        }
    };
    function plusRedPenalty() {
        redPenalty += 1;
        document.getElementById("redPenalty").innerHTML = redPenalty;
        if (redPenalty == 0){
        	document.getElementById("redPenaltyCard").className = "col-5 text-center border rounded mx-1 white-card";
        }else if (redPenalty == 1){
        	document.getElementById("redPenaltyCard").className = "col-5 text-center border rounded mx-1 yellow-card";
        }else if (redPenalty == 2){
        	document.getElementById("redPenaltyCard").className = "col-5 text-center border rounded mx-1 orange-card";
        }else{
        	document.getElementById("redPenaltyCard").className = "col-5 text-center border rounded mx-1 red-card";
        }
    };
    function minusRedPenalty() {
        redPenalty -= 1;
        document.getElementById("redPenalty").innerHTML = redPenalty;
        if (redPenalty == 0){
        	document.getElementById("redPenaltyCard").className = "col-5 text-center border rounded mx-1 white-card";
        }else if (redPenalty == 1){
        	document.getElementById("redPenaltyCard").className = "col-5 text-center border rounded mx-1 yellow-card";
        }else if (redPenalty == 2){
        	document.getElementById("redPenaltyCard").className = "col-5 text-center border rounded mx-1 orange-card";
        }else{
        	document.getElementById("redPenaltyCard").className = "col-5 text-center border rounded mx-1 red-card";
        }
	}
		
	function accept(){
		redRes += redScope;
		blueRes += blueScope;
		redScope = 0;
		blueScope = 0;
		document.getElementById("redScope").innerHTML = redScope;
		document.getElementById("blueScope").innerHTML = blueScope;
		document.getElementById("redRes").innerHTML = redRes;
		document.getElementById("blueRes").innerHTML = blueRes;
	}
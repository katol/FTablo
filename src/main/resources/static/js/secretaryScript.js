var time = 0;
var timerId = 0;
var activate = 0;
var redScope = 0;
var blueScope = 0;
var redPenalty = 0;
var bluePenalty = 0;
var blueRes = 0;
var redRes = 0;
var redName = "";
var blueName = "";
var roundNumber = 1;
var redVideoReplays = 0;
var blueVideoReplays = 0;
var fightId = 0;
document.getElementById("roundNumber").innerHTML = "Текущий сход №" + roundNumber;
var resp = new Array();
	function timer() {
		if (activate == 0){
		document.getElementById("startStop").innerHTML = "СТОП";
		activate = 1;
		timerId = setInterval(function () {
			time += 1;
			if(time < 0){
				time = 0;
			}
			updateHtml();
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
		updateHtml();
		}
	function minusTenSec(){
		time -= 10;
		if(time < 0){
				time = 0;
			}
		updateHtml();
	}
    function plusRedScope() {
        redScope += 1;
		updateHtml();
    };
    function minusRedScope() {
        redScope -= 1;
		updateHtml();
    };
    function plusBlueScope() {
        blueScope += 1;
		updateHtml();
    };
    function minusBlueScope() {
        blueScope -= 1;
		updateHtml();
    };

    function plusBluePenalty() {
        bluePenalty += 1;
		updateHtml();
    };
    function minusBluePenalty() {
        bluePenalty -= 1;
		updateHtml();

    };
    function plusRedPenalty() {
        redPenalty += 1;
		updateHtml();
    };
    function minusRedPenalty() {
        redPenalty -= 1;
		updateHtml();
	};
    function redVideoReplay(){
		redVideoReplays += 1;
		updateHtml();
	}
	function blueVideoReplay(){
		blueVideoReplays += 1;
		updateHtml();
	}
	function accept(){
		redRes += redScope;
		blueRes += blueScope;
		redScope = 0;
		blueScope = 0;
		roundNumber += 1;
		sendExchange();
		updateHtml()
	};
	
		
	function selectFight(){
		var xhr = new XMLHttpRequest();
		xhr.open('GET', 'http://localhost:8080/secretary/fights', false);
		xhr.send();
		if (xhr.status != 200) {
		alert( xhr.status + ': ' + xhr.statusText );
		} else {
			resp = JSON.parse( xhr.responseText).res;
			var fights = new Array();
			for (i = 0; i < resp.length; i++){
				fights.push("<option value =" + i +">" + resp[i].id_serial + " " + resp[i].red_name + " X " + resp[i].blue_name + "</option>");
			}
			var res = "<option selected>ВЫБЕРИТЕ БОЙ</option>";
			for(i = 0; i < fights.length; i++){
				res += fights[i];
			}
			document.getElementById("selectFight").innerHTML = res;
		}
	}
	function loadFight(){
		var fightChange = selectForm.fightSelect;
		var selectedOption = fightChange.options[fightChange.selectedIndex];
		redName = resp[selectedOption.value].red_name;
		blueName = resp[selectedOption.value].blue_name;
		redRes = Number(resp[selectedOption.value].red_scores);
		blueRes = Number(resp[selectedOption.value].blue_scores);
		redScope = 0;
		blueScope = 0;
		time = Number(resp[selectedOption.value].seconds_passed);
		updateHtml()
		fightId = resp[selectedOption.value].fight_id;
	}
	function newFight(){
		redName = document.getElementById("nameRed").value;
		blueName = document.getElementById("nameBlue").value;
		redRes = 0;
		blueRes = 0;
		redScope = 0;
		blueScope = 0;
		time = 0;
		roundNumber = 1;
		blueVideoReplays = 0;
		redVideoReplays = 0;
		updateHtml()
	}
	function sendExchange(){
		var xhr = new XMLHttpRequest();
		var json = JSON.stringify({
			id : 0,
			fight_id: fightId,
			seconds_passed: time,
			save_ts : new Date(),
			action_description : "",
			scores_to_red : redScope,
			scores_to_blue : blueScope
		});
		xhr.open("POST", '/exchange', true)
		xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
		xhr.send(json);
	}
	function updateHtml(){
		document.getElementById("redName").innerHTML = redName;
		document.getElementById("blueName").innerHTML = blueName;
		document.getElementById("timer").innerHTML = Math.floor(time/600) + "" + Math.floor((time%600)/60) + ":" + Math.floor((time%60)/10) + time%10;
		document.getElementById("redScope").innerHTML = redScope;
		document.getElementById("blueScope").innerHTML = blueScope;
		document.getElementById("redRes").innerHTML = redRes;
		document.getElementById("blueRes").innerHTML = blueRes;
		document.getElementById("bluePenalty").innerHTML = bluePenalty;
		document.getElementById("roundNumber").innerHTML = "Текущий сход №" + roundNumber;
		if (bluePenalty == 0){
			document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 white-card";
		}else if (bluePenalty == 1){
			document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 yellow-card";
		}else if (bluePenalty == 2){
			document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 orange-card";
		}else{
			document.getElementById("bluePenaltyCard").className = "col-5 text-center border rounded mx-1 red-card";
		}
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
		if (redVideoReplays > 0){
			document.getElementById("redVideoReplays").className = "btn btn-white border rounded bg-secondary px-1 mr-2";
		}else{
			document.getElementById("redVideoReplays").className = "btn btn-white border rounded bg-white px-1 mr-2";
		}
		if (blueVideoReplays > 0){
			document.getElementById("blueVideoReplays").className = "btn btn-white border rounded bg-secondary px-1 mr-2";
		}else{
			document.getElementById("blueVideoReplays").className = "btn btn-white border rounded bg-white px-1 mr-2";
		}
	}


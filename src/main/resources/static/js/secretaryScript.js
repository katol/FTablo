let time = 0;
let isTimerActivated = false;
let redScope = 0;
let blueScope = 0;
let redPenalty = 0;
let bluePenalty = 0;
let blueRes = 0;
let redRes = 0;
let redName = "";
let blueName = "";
let roundNumber = 1;
let redVideoReplays = 0;
let blueVideoReplays = 0;
let fightId = 0;
let stompClient = null;
document.getElementById("roundNumber").innerHTML = "Текущий сход №" + roundNumber;
let resp = [];
connectToTimer();

function plusTenSec() {
	time += 10;
	if(time < 0) {
		time = 0;
	}
	updateHtml();
}

function minusTenSec() {
	time -= 10;
	if(time < 0) {
		time = 0;
	}
	updateHtml();
}

function plusRedScope() {
	redScope += 1;
	updateHtml();
}

function minusRedScope() {
	redScope -= 1;
	updateHtml();
}

function plusBlueScope() {
	blueScope += 1;
	updateHtml();
}

function minusBlueScope() {
	blueScope -= 1;
	updateHtml();
}

function plusBluePenalty() {
	bluePenalty += 1;
	updateHtml();
	updatePenalty();
}

function minusBluePenalty() {
	bluePenalty -= 1;
	updateHtml();
	updatePenalty();
}

function plusRedPenalty() {
	redPenalty += 1;
	updateHtml();
	updatePenalty();
}

function minusRedPenalty() {
	redPenalty -= 1;
	updateHtml();
	updatePenalty();
}

function redVideoReplay() {
	redVideoReplays += 1;
	updateHtml();
	updateVideoReplay();
}

function blueVideoReplay() {
	blueVideoReplays += 1;
	updateHtml();
	updateVideoReplay();
}

function accept() {
	redRes += redScope;
	blueRes += blueScope;
	redScope = 0;
	blueScope = 0;
	roundNumber += 1;
	sendExchange();
	updateHtml()
}

function selectFight() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/fights', false);
	xhr.send();
	if (xhr.status !== 200) {
	alert(xhr.status + ': ' + xhr.statusText);
	} else {
		resp = JSON.parse( xhr.responseText).res;
		let fights = [];
		for (let i = 0; i < resp.length; i++) {
			fights.push(
				"<option value =" + i +">"
					+ resp[i].id_serial + " " + resp[i].red_name + " X " + resp[i].blue_name +
				"</option>"
			);
		}
		let res = "<option selected>ВЫБЕРИТЕ БОЙ</option>";
		for(let i = 0; i < fights.length; i++) {
			res += fights[i];
		}
		document.getElementById("selectFight").innerHTML = res;
	}
}

function loadFight() {
	let fightChange = selectForm.fightSelect;
	let selectedOption = fightChange.options[fightChange.selectedIndex];
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

function newFight() {
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
	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'http://localhost:8080/fights', false);
	xhr.setRequestHeader("Content-type", "application/json");
	xhr.send("{\"redName\":\"" + redName + "\",\"blueName\":\"" + blueName + "\"}");
	if (xhr.status !== 200) {
		alert(xhr.status + ': ' + xhr.statusText);
	} else {
		resp = JSON.parse(xhr.responseText).id;
		fightId = resp;
	}
	alert(fightId);
	updateHtml()
}

function sendExchange() {
	let xhr = new XMLHttpRequest();
	let json = JSON.stringify({
		id : 0,
		fight_id: fightId,
		seconds_passed: time,
		save_ts : new Date(),
		action_description : "",
		scores_to_red : redScope,
		scores_to_blue : blueScope
	});
	xhr.open("POST", '/exchanges', true)
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}

function updateHtml() {
	document.getElementById("redName").innerHTML = redName;
	document.getElementById("blueName").innerHTML = blueName;
	document.getElementById("timer").innerHTML
		= Math.floor(time/600) + "" + Math.floor((time%600)/60) + ":" + Math.floor((time%60)/10) + time%10;
	document.getElementById("redScope").innerHTML = redScope;
	document.getElementById("blueScope").innerHTML = blueScope;
	document.getElementById("redRes").innerHTML = redRes;
	document.getElementById("blueRes").innerHTML = blueRes;
	document.getElementById("bluePenalty").innerHTML = bluePenalty;
	document.getElementById("roundNumber").innerHTML = "Текущий сход №" + roundNumber;
	if (bluePenalty === 0) {
		document.getElementById("bluePenaltyCard").className
			= "col-5 text-center border rounded mx-1 white-card";
	} else if (bluePenalty === 1) {
		document.getElementById("bluePenaltyCard").className
			= "col-5 text-center border rounded mx-1 yellow-card";
	} else if (bluePenalty === 2) {
		document.getElementById("bluePenaltyCard").className
			= "col-5 text-center border rounded mx-1 orange-card";
	} else {
		document.getElementById("bluePenaltyCard").className
			= "col-5 text-center border rounded mx-1 red-card";
	}
	document.getElementById("redPenalty").innerHTML = redPenalty;
	if (redPenalty === 0) {
		document.getElementById("redPenaltyCard").className
			= "col-5 text-center border rounded mx-1 white-card";
	} else if (redPenalty === 1) {
		document.getElementById("redPenaltyCard").className
			= "col-5 text-center border rounded mx-1 yellow-card";
	} else if (redPenalty === 2) {
		document.getElementById("redPenaltyCard").className
			= "col-5 text-center border rounded mx-1 orange-card";
	} else {
		document.getElementById("redPenaltyCard").className = "col-5 text-center border rounded mx-1 red-card";
	}
	if (redVideoReplays > 0) {
		document.getElementById("redVideoReplays").className
			= "btn btn-white border rounded bg-secondary px-1 mr-2";
	} else {
		document.getElementById("redVideoReplays").className
			= "btn btn-white border rounded bg-white px-1 mr-2";
	}
	if (blueVideoReplays > 0) {
		document.getElementById("blueVideoReplays").className
			= "btn btn-white border rounded bg-secondary px-1 mr-2";
	} else {
		document.getElementById("blueVideoReplays").className
			= "btn btn-white border rounded bg-white px-1 mr-2";
	}
}

function updatePenalty() {
	let xhr = new XMLHttpRequest();
	let json = JSON.stringify({
		red_penalty : redPenalty,
		blue_penalty: bluePenalty
	});
	xhr.open("POST", '/penalty', true)
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}

function updateVideoReplay() {
	let xhr = new XMLHttpRequest();
	let json = JSON.stringify({
		red_video_replay : redVideoReplays,
		blue_video_replay: blueVideoReplays
	});
	xhr.open("POST", '/videoReplay', true)
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.send(json);
}

function connectToTimer() {
	let socket = new SockJS('/timer');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function() {
		stompClient.subscribe('/topic/time', function (seconds) {
			//TODO Do time display here instead of logging
			console.log("Received time in seconds from server = " + seconds.body);
		})
	});
}

function onTimerClick() {
	if (!isTimerActivated) {
		document.getElementById("startStop").innerHTML = "СТОП";
		isTimerActivated = true;
		stompClient.send("/app/timer", {}, "start");
	} else {
		document.getElementById("startStop").innerHTML = "СТАРТ";
		isTimerActivated = false;
		stompClient.send("/app/timer", {}, "stop");
	}
}
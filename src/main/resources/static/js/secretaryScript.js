let time = 0;
let id = 1;
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
let data;
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
	sendExchange();
	redScope = 0;
	blueScope = 0;
	roundNumber += 1;
	updateHtml()
}

function selectFight() {
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'http://localhost:8080/fights', false);
	xhr.send();
	if (xhr.status !== 200) {
		alert(xhr.status + ': ' + xhr.statusText);
	} else {
		resp = JSON.parse(xhr.responseText);
		accept(resp);
		data = resp;
		let fights = [];
		for (let i = 0; i < data.length; i++) {
			fights.push(
				"<option value =" + i + ">" + data[i].id
				+ data[i].redName + " X " + data[i].blueName +
				"</option>"
			);
		}
		let res = "<option selected>ВЫБЕРИТЕ БОЙ</option>";
		for (let i = 0; i < fights.length; i++) {
			res += fights[i];
		}
		document.getElementById("selectFight").innerHTML = res;
	}
}
	function nextStep() {
		let fightChange = selectForm.fightSelect;
		redName = data[fightChange.selectedIndex].redName;
		blueName = data[fightChange.selectedIndex].blueName;
		redRes = Number(resp[fightChange.selectedIndex].redScores);
		blueRes = Number(resp[fightChange.selectedIndex].blueScores);
		time = Number(resp[fightChange.selectedIndex].lastTs);
		fightId = resp[fightChange.selectedIndex].id;
		alert(fightId + " " + time);
		updateHtml();
	}

function loadFight() {
	updateHtml();
}

function newFight() {
	redName = document.getElementById("nameRed").value;
	blueName = document.getElementById("nameBlue").value;
	redRes = 0
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
		fightId : id,
		secondsPassed: time,
		saveTs :"2020-09-23 08:12:56.7747",
		actionDescription : "",
		scoresToRed : redScope,
		scoresToBlue : blueScope
	});
	id += 1;
	xhr.open("POST", '/exchanges', true)
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	alert(json);
	xhr.send(json);
}

function updateHtml() {
	document.getElementById("redName").innerHTML = redName;
	document.getElementById("blueName").innerHTML = blueName;
	updateTimer();
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
function updateTimer(){
	document.getElementById("timer").innerHTML
		= Math.floor(time/600) + "" + Math.floor((time%600)/60) + ":" + Math.floor((time%60)/10) + time%10;
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
			time = seconds.body;
			updateTimer();
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
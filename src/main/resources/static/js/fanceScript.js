let redName = "красный";
let blueName = "синий";
let redScope = 0;
let blueScope = 0;
let redPenalty = 0;
let bluePenalty = 0;
updateHtml();
connect();
function connect() {
    let socket = new SockJS('/fanseSoket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/fanseSoket', function (time) {
            console.log(JSON.parse(time.body));
            updateHtml();
        })
    });
}
function updateHtml(){
    document.getElementById("redName").innerHTML = redName;
    document.getElementById("blueName").innerHTML = blueName;
    document.getElementById("redScope").innerHTML = redScope;
    document.getElementById("blueScope").innerHTML = blueScope;
    document.getElementById("redRes").innerHTML = redRes;
    document.getElementById("blueRes").innerHTML = blueRes;
    document.getElementById("bluePenalty").innerHTML = "Предупреждения: " + bluePenalty;
    document.getElementById("redPenalty").innerHTML = "Предупреждения: " + redPenalty;
}

var stompClient = null;

function connect() {

    console.log("!!!connect");
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
		stompClient.connect({}, onConnected, onError);
}


function onConnected() {
console.log("!!! onConnected");

    stompClient.subscribe('/topic/public', onMessageReceived);
    sendMessage('int형을 모르겠어');
}

function onMessageReceived(payload) {
console.log("!!!!! onMessageReceived");
   // console.log("!!!!! payload:   "+payload);
}

function onError(error) {
    console.log("!!! onError"+error)
}

function sendMessage(content, event) {
console.log("!!! sendMessage");

    stompClient.send("/app/sendMessage", {}, JSON.stringify(content));
    //stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(content));
}

connect();
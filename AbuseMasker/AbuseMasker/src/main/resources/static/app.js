var stomClient = null;

var username = generateRandomUsername();

function generateRandomUsername() {
    var adjectives = ["Quick", "Lazy", "Happy", "Sad", "Angry"];
    var nouns = ["Fox", "Dog", "Cat", "Mouse", "Bear"];
    var adjective = adjectives[Math.floor(Math.random() * adjectives.length)];
    var noun = nouns[Math.floor(Math.random() * nouns.length)];
    return adjective + noun + Math.floor(Math.random() * 1000);
}


function connect(){
    var socket  = new SockJS('/ws');
    stomClient = Stomp.over(socket);

    stomClient.connect({}, function(frame) {
        console.log('connected ' + frame);
        stomClient.subscribe('/topic/messages', function(message) {
            showMessage(JSON.parse(message.body));
        })
    })
}


function sendMessage(){
    var messageContent = document.getElementById('message-input').value;
    stomClient.send("/app/chat", {}, JSON.stringify({
        'username' : username,
        'content' : messageContent
    }));

    document.getElementById('message-input').value = '';
}


function showMessage(message){
    var messageDiv = document.getElementById('messages');
    var messageElement = document.createElement('div');
    messageElement.appendChild(document.createTextNode(message.username + " : " + message.content));
    messageDiv.appendChild(messageElement);
}

connect();
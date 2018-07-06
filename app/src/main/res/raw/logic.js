var hostname = window.location.hostname;

function keepAlive() {
    var timeout = 500;

    $.getJSON(
        "http://" + hostname + ":8080/data",
        function (json) {
            console.log(json);

            document.getElementById("turn").innerText = json["turn"];

            document.getElementById("leftPlayerTime").innerText = json["left"]["time"];
            document.getElementById("rightPlayerTime").innerText = json["right"]["time"];

            document.getElementById("leftPlayerCP").innerText = json["left"]["cp"];
            document.getElementById("rightPlayerCP").innerText = json["right"]["cp"];
        }
    );

    timerId = setTimeout(keepAlive, timeout);
};

keepAlive();
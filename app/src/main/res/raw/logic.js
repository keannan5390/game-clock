var hostname = window.location.hostname;

function keepAlive() {
    var timeout = 500;

    $.getJSON(
        "http://" + hostname + ":8080/data",
        function (json) {
            console.log(json);

            var leftTurn = json["left"]["turn"];
            var rightTurn = json["right"]["turn"];
            var turn = Math.max(leftTurn, rightTurn);

            var turnText = "";
            if (leftTurn == rightTurn) {
                if (turn === 0)
                    turn = 1;

                turnText = turn + "B";
            }
            else
                turnText = turn + "A";

            document.getElementById("turn").innerText = turnText;

            document.getElementById("leftPlayerTime").innerText = json["left"]["time"];
            document.getElementById("rightPlayerTime").innerText = json["right"]["time"];

            document.getElementById("leftPlayerCP").innerText = json["left"]["cp"];
            document.getElementById("rightPlayerCP").innerText = json["right"]["cp"];
        }
    );

    timerId = setTimeout(keepAlive, timeout);
};

keepAlive();
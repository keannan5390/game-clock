package org.brutaldamage.chessclock.http;

import org.brutaldamage.chessclock.Global;
import org.brutaldamage.chessclock.models.GameStateModel;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class AndroidHttpServer extends NanoHTTPD {

    public AndroidHttpServer (int port) {
        super(port);
    }

    public AndroidHttpServer (String hostname, int port) {
        super(hostname, port);
    }

    @Override
    public Response serve(IHTTPSession session) {

        String player1Time = Global.GAME_STATE.leftPlayerTime();
        String player2Time = Global.GAME_STATE.rightPlayerTime();

        String html = "<html>\n" +
                " <meta http-equiv=\"refresh\" content=\"0.5\" />" +
                "<body>\n" +
                "   <h1 id=\"title\">Brutal Damage Game Clock</1>" +
                "    <div id=\"player1Info\">\n" +
                "        <h2>Player 1</h2>\n" +
                "        <p>Time: <span id=\"player1Time\">" + player1Time +"</span></p>\n" +
                "    </div>\n" +
                "    <div id=\"player2Info\">\n" +
                "        <h2>Player 2</h2>\n" +
                "        <p>Time: <span id=\"player2Time\">" + player2Time +"</span></p>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        return newFixedLengthResponse(html);
    }
}

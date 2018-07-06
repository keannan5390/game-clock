package org.brutaldamage.chessclock.http;

import android.content.Context;

import org.brutaldamage.chessclock.Global;
import org.brutaldamage.chessclock.R;
import org.brutaldamage.chessclock.models.GameStateModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class AndroidHttpServer extends NanoHTTPD {

    Context mContext;

    public AndroidHttpServer (Context context, int port) {
        super(port);

        mContext = context;
    }


    @Override
    public Response serve(IHTTPSession session) {

        String html = LoadHtmlFile(R.raw.web);

//        String player1Time = Global.GAME_STATE.leftPlayerTime();
//        String player2Time = Global.GAME_STATE.rightPlayerTime();
//        int leftPlayerTurn = Global.GAME_STATE.numLeftPlayerMoves;
//        int rightPlayerTurn = Global.GAME_STATE.numRightPlayerMoves;
//
//        String html = "<html>\n" +
//                " <meta http-equiv=\"refresh\" content=\"0.5\" />" +
//                "<body>\n" +
//                "   <h1 id=\"title\">Brutal Damage Game Clock</h1>" +
//                "    <div id=\"player1Info\">\n" +
//                "        <h2>Left Player</h2>\n" +
//                "        <p>Time: <span id=\"leftPlayerTime\">" + player1Time +"</span></p>\n" +
//                "        <p>Turn: <span id=\"leftPlayerTurn\">" + leftPlayerTurn +"</span></p>\n" +
//                "    </div>\n" +
//                "    <div id=\"player2Info\">\n" +
//                "        <h2>Right Player</h2>\n" +
//                "        <p>Time: <span id=\"rightPlayerTime\">" + player2Time +"</span></p>\n" +
//                "        <p>Turn: <span id=\"rightPlayerTurn\">" + rightPlayerTurn +"</span></p>\n" +
//                "    </div>\n" +
//                "</body>\n" +
//                "</html>";

        return newFixedLengthResponse(html);
    }

    private String LoadHtmlFile(int resourceId) {
        try {
            InputStream inputStream =  mContext.getResources().openRawResource(resourceId);
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                try {
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();
                } finally {
                    reader.close();
                }
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            // process exception
            return "<html><body><p>error loading html document</p></body></html>";
        }
    }
}

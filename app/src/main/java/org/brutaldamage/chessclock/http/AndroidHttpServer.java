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
    public Response serve(IHTTPSession session)
    {
        if(session.getUri().contains("data"))
        {
            String json = "{" +
                    "\"turn\": \""+  Global.GAME_STATE.getTurnDisplay() + "\"," +
                    "\"left\":{ \"time\": \"" + Global.GAME_STATE.leftPlayerTime() + "\", \"cp\": " + Global.GAME_STATE.numLeftPlayerCP + "}, " +
                    "\"right\": { \"time\": \" "+Global.GAME_STATE.rightPlayerTime() + "\", \"cp\": " + Global.GAME_STATE.numRightPlayerCP + "}"
                    + "}";

            return newFixedLengthResponse(Response.Status.OK, "application/json", json);
        }
        else if(session.getUri().contains("/files/logic.js"))
        {
            String html = LoadHtmlFile(R.raw.logic);
            return newFixedLengthResponse(Response.Status.OK, "application/javascript", html);
        }
        else
        {
            String html = LoadHtmlFile(R.raw.web);
            return newFixedLengthResponse(html);
        }
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
                        result.append("\n");
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

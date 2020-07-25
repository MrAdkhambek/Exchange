package r2.adam.exchange.models;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.github.nkzawa.emitter.Emitter.Listener;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SocketEvent extends LiveData<List<Exchange>> implements Listener {

    private Socket io;
    private String event;

    public SocketEvent(String event, Socket socket) {
        this.io = socket;
        this.event = event;

        io.on(this.event, this);
    }

    @Override
    public void call(Object... args) {
        Object first = args[0];
        ArrayList<Exchange> objList = new ArrayList<>();

        for (Object arg : args) Log.i("TTT", String.valueOf(arg));

        if (first instanceof JSONObject) {
            JSONObject obj = (JSONObject) first;
            objList.add(getExchange(obj));
        } else if (first instanceof JSONArray) {
            JSONArray arr = (JSONArray) first;
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.optJSONObject(i);
                if (obj == null) continue;
                objList.add(getExchange(obj));
            }
        }

        postValue(objList);
    }

    private Exchange getExchange(JSONObject object) {
        String id = object.optString("id", "");
        String currency = object.optString("currency", "");
        Double oldPrice = object.optDouble("oldPrice", 0.0);
        Double newPrice = object.optDouble("newPrice", 0.0);

        return new Exchange(id, currency, oldPrice, newPrice);
    }

    @Override
    protected void onInactive() {
        io.off(this.event, this);
        super.onInactive();
    }
}

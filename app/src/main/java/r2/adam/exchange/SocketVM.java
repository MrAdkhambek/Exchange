package r2.adam.exchange;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;
import java.util.List;

import r2.adam.exchange.models.Exchange;
import r2.adam.exchange.models.SocketEvent;

public class SocketVM extends ViewModel {

    private Socket io;
    public LiveData<List<Exchange>> exchangeLiveData;

    public SocketVM() {
        try {
            io = IO.socket("http://192.168.0.109:9999");
            exchangeLiveData = new SocketEvent("my_event", io);
            io.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCleared() {
        io.disconnect();
        super.onCleared();
    }
}

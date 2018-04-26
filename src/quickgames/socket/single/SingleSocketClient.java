package quickgames.socket.single;

import java.net.Socket;

public class SingleSocketClient extends SingleSocket implements OnSent {

    public SingleSocketClient(String host, int port) throws SingleSocketThrowable {
        super(host, port);
    }

    public void send(OnSent event, byte[] data) throws SingleSocketThrowable {

        // Send.
        mSend(data);

        // Receive.
        byte[] buffer = mReceive();

        // Answer.
        event.onSent(buffer);

        // Clearing socket.
        mClose();

    }

    public void send(byte[] data) throws SingleSocketThrowable {
        send(this, data);
    }

    @Override
    public void onSent(byte[] answer) {

    }

    //region DEPRECATED

    @Deprecated
    public SingleSocketClient(Socket socket) {
        super(socket);
    }

    @Deprecated
    public void Send(OnSent event, byte[] data) throws SingleSocketThrowable {
        send(event, data);
    }

    @Deprecated
    public void Send(byte[] data) throws SingleSocketThrowable {
        send(data);
    }

    //endregion

}

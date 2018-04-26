package quickgames.socket.single;

import java.net.Socket;

public class SingleSocketServerClient extends SingleSocket implements OnCreate, OnReceive {


    public SingleSocketServerClient(Socket socket) {
        super(socket);
        onCreate();
    }

    private void _waitingReceive() throws SingleSocketThrowable {
        // Receive.
        byte[] buffer = mReceive();

        // Answer.
        byte[] answer = onReceive(buffer);

        // Send.
        mSend(answer);

        // Clearing socket.
        mClose();

    }

    //region OVERRIDE

    @Override
    public void onCreate() {

    }

    @Override
    public byte[] onReceive(byte[] query) {
        return new byte[0];
    }

    void start() throws SingleSocketThrowable {
        _waitingReceive();
    }

    //endregion

}

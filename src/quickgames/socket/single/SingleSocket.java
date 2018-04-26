package quickgames.socket.single;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

abstract class SingleSocket {

    protected Socket mSocket;

    protected SingleSocket(String host, int port) throws SingleSocketThrowable {
        try {
            mSocket = new Socket(host, port);
        } catch (IOException e) {
            throw new SingleSocketThrowable(e.getMessage());
        }
    }

    protected SingleSocket(Socket socket) {
        mSocket = socket;
    }

    protected void mSend(byte[] data) throws SingleSocketThrowable {
        try {
            OutputStream os = mSocket.getOutputStream();
            os.write(data);
            os.flush();
        } catch (IOException e) {
            throw new SingleSocketThrowable(e.getMessage());
        }
    }

    protected byte[] mReceive() throws SingleSocketThrowable {
        try {
            InputStream is = mSocket.getInputStream();
            byte symbol = (byte) is.read();

            byte[] buffer = new byte[0];

            if (symbol >= 0) {
                int available = is.available();
                buffer = new byte[available + 1];
                is.read(buffer, 1, available);
                buffer[0] = symbol;
            }

            return buffer;
        } catch (IOException e) {
            throw new SingleSocketThrowable(e.getMessage());
        }
    }

    protected void mClose() throws SingleSocketThrowable {
        try {
            mSocket.close();
            mSocket = null;
        } catch (IOException e) {
            throw new SingleSocketThrowable(e.getMessage());
        }
    }

    //region OVERRIDE

    @Override
    public String toString() {
        return mSocket.toString();
    }

    //endregion

}

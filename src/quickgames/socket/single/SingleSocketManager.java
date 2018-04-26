package quickgames.socket.single;

import java.io.IOException;
import java.net.Socket;

public class SingleSocketManager {

    private static String _host;
    private static int _port;

    public static void setSocket(String host, int port) throws SingleSocketThrowable {
        _host = host;
        _port = port;
    }

    //region SEND

    public static void send(String host, int port, OnSent event, byte[] data) throws SingleSocketThrowable {
        setSocket(host, port);
        _getClient().send(event, data);
    }

    public static void send(OnSent event, byte[] data) throws SingleSocketThrowable {
        _getClient().send(event, data);
    }

    public static void send(byte[] data) throws SingleSocketThrowable {
        _getClient().send(data);
    }

    private static SingleSocketClient _getClient() throws SingleSocketThrowable {
        return new SingleSocketClient(_host, _port);
    }

    //endregion
}

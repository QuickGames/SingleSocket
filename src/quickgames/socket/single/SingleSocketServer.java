package quickgames.socket.single;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SingleSocketServer<TSingleSocketServerClient extends SingleSocketServerClient> {
    private ServerSocket _server;
    private boolean _isRunning;

    //region CONSTRUCTOR

    public SingleSocketServer(int port) throws SingleSocketThrowable {
        _constructor(port);
    }

    public SingleSocketServer(int port, boolean start) throws SingleSocketThrowable {
        _constructor(port);

        if (start) {
            start();
        }
    }

    private void _constructor(int port) throws SingleSocketThrowable {
        try {
            _server = new ServerSocket(port);
        } catch (IOException e) {
            throw new SingleSocketThrowable(e.getMessage());
        }
    }

    //endregion

    public void start() {
        if (_isRunning) {
            System.out.println("Server already started");
            return;
        }

        _isRunning = true;
        System.out.println("Server is started");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (_isRunning) {
                    try {
                        Socket socket = _server.accept();
                        final TSingleSocketServerClient client = getSocketClient(socket);
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("Connection accepted: " + client.toString());
                                onAccept(client);
                                try {
                                    client.start();
                                } catch (SingleSocketThrowable e) {
                                    onError(e);
                                }
                            }
                        }, "SocketServer.onAccept").start();
                    } catch (IOException e) {
                        onError(new SingleSocketThrowable(e.getMessage()));
                    }
                }
            }
        }, "SocketServer.accept").start();
    }

    public void close() throws SingleSocketThrowable {
        if (_isRunning) {
            _isRunning = false;
            try {
                _server.close();
            } catch (IOException e) {
                throw new SingleSocketThrowable(e.getMessage());
            }
            System.out.println("Server is stopped");
        } else
            System.out.println("Server already stopped");

    }

    protected void onError(SingleSocketThrowable e) {
        e.printStackTrace();
    }

    protected abstract TSingleSocketServerClient getSocketClient(Socket socket);

    public abstract void onAccept(TSingleSocketServerClient client);
}

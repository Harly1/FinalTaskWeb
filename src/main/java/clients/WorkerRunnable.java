package clients;


import java.io.*;
import java.net.Socket;

public class WorkerRunnable implements Runnable{

        protected Socket clientSocket = null;
        protected String serverText   = null;

        public WorkerRunnable(Socket clientSocket, String serverText) {
            this.clientSocket = clientSocket;
            this.serverText   = serverText;
        }

        public void run() {
            try(InputStream in  = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream();
                DataInputStream Din = new DataInputStream(in);
                DataOutputStream Dout = new DataOutputStream(out)
            ) {

                long time = System.currentTimeMillis();
                String line = Din.readUTF();
                Dout.writeUTF(line);
                System.out.println("Request processed: " + time);
            } catch (IOException e) {
                //report exception somewhere.
                e.printStackTrace();
            }
        }
}


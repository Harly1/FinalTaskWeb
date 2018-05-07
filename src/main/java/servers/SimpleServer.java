package servers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer implements Runnable {

    public synchronized void start(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        int port = 6666; // случайный порт (может быть любое число от 1025 до 65535)
        ServerSocket ss = null;
        Socket socket = null;
        String line = null;

        try {
            ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Waiting for a client...");
            socket = ss.accept();       // заставляем сервер ждать подключений и выводим сообщение когда кто-то связался с сервером
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Got a client :) ... Finally, someone saw me through all the cover!");
        System.out.println();
        try(    // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
                   InputStream sin = socket.getInputStream();
                   OutputStream sout = socket.getOutputStream();

                   // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения.
                   DataInputStream in = new DataInputStream(sin);
                   DataOutputStream out = new DataOutputStream(sout);
                ) {


            while(true) {
                line = in.readUTF(); // ожидаем пока клиент пришлет строку текста.
                System.out.println("The dumb client just sent me this line : " + line);
                System.out.println("I'm sending it back...");
                out.writeUTF(line); // отсылаем клиенту обратно ту самую строку текста.
                out.flush(); // заставляем поток закончить передачу данных.
                System.out.println("Waiting for the next line...");
                System.out.println();
            }
        } catch(Exception x) { x.printStackTrace(); }
    }
}

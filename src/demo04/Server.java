package demo04;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("服务器已启动，等待客户端连接...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端连接成功");

                ServerThread serverThread = new ServerThread(socket);
                serverThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerThread extends Thread {
        private Socket socket;
        private ObjectInputStream objectInputStream;
        private ObjectOutputStream objectOutputStream;

        public ServerThread(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

                while (true) {
                    String request = objectInputStream.readUTF();
                    String response;

                    if (request.equals("ADD")) {
                        Contact contact = (Contact) objectInputStream.readObject();
                        contacts.add(contact);
                        response = "联系人已添加";
                    } else if (request.equals("DELETE")) {
                        Contact contact = (Contact) objectInputStream.readObject();
                        if (contacts.contains(contact)) {
                            contacts.remove(contact);
                            response = "联系人已删除";
                        } else {
                            response = "联系人不存在";
                        }
                    } else if (request.equals("LIST")) {
                        response = contacts.toString();
                    } else if (request.equals("EXIT")) {
                        response = "已退出";
                        break;
                    } else {
                        response = "无效的请求";
                    }

                    objectOutputStream.writeUTF(response);
                    objectOutputStream.flush();
                }

                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

package demo04;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("请选择要执行的操作:");
                System.out.println("1. 添加联系人");
                System.out.println("2. 删除联系人");
                System.out.println("3. 显示通讯录列表");
                System.out.println("4. 退出");

                int choice = scanner.nextInt();
                scanner.nextLine();

                String request;
                if (choice == 1) {
                    System.out.print("请输入联系人姓名: ");
                    String name = scanner.nextLine();
                    System.out.print("请输入联系人电话: ");
                    String phone = scanner.nextLine();
                    Contact contact = new Contact(name, phone);
                    request = "ADD";
                    objectOutputStream.writeUTF(request);
                    objectOutputStream.flush();
                    objectOutputStream.writeObject(contact);
                    objectOutputStream.flush();
                } else if (choice == 2) {
                    System.out.print("请输入要删除的联系人姓名: ");
                    String name = scanner.nextLine();
                    System.out.print("请输入要删除的联系人电话: ");
                    String phone = scanner.nextLine();
                    Contact contact = new Contact(name, phone);
                    request = "DELETE";
                    objectOutputStream.writeUTF(request);
                    objectOutputStream.flush();
                    objectOutputStream.writeObject(contact);
                    objectOutputStream.flush();
                } else if (choice == 3) {
                    request = "LIST";
                    objectOutputStream.writeUTF(request);
                    objectOutputStream.flush();
                } else if (choice == 4) {
                    request = "EXIT";
                    objectOutputStream.writeUTF(request);
                    objectOutputStream.flush();
                    System.out.println("已退出");
                    break;
                } else {
                    System.out.println("无效的选择");
                    continue;
                }

                String response = objectInputStream.readUTF();
                System.out.println(response);
            }

            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

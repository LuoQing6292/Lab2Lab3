package demo03;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.io.Serializable;

public class Client {




    public class Contact implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private String phone;

        public Contact(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            return "Name: " + name + ", Phone: " + phone;
        }
    }

    public void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
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

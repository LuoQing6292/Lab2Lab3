package demo05;


import java.util.List;
import java.util.Scanner;

public class ContactSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请选择操作：");
            System.out.println("1. 添加联系人");
            System.out.println("2. 查看联系人");
            System.out.println("0. 退出");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 清空输入缓冲区

            switch (choice) {
                case 1:
                    System.out.println("请输入联系人姓名：");
                    String name = scanner.nextLine();

                    System.out.println("请输入联系人电话：");
                    String phoneNumber = scanner.nextLine();

                    Contact contact = new Contact(name, phoneNumber);
                    ContactDatabase.addContact(contact);

                    System.out.println("联系人添加成功！");
                    break;
                case 2:
                    List<Contact> contacts = ContactDatabase.getAllContacts();

                    if (contacts.isEmpty()) {
                        System.out.println("通讯录为空！");
                    } else {
                        System.out.println("联系人列表：");

                        for (Contact c : contacts) {
                            System.out.println("姓名：" + c.getName());
                            System.out.println("电话：" + c.getPhoneNumber());
                            System.out.println("--------------------");
                        }
                    }

                    break;
                case 0:
                    System.out.println("退出程序！");
                    return;
                default:
                    System.out.println("无效的选择！");
                    break;
            }
        }
    }
}


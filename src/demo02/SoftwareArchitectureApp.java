package demo02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SoftwareArchitectureApp extends JFrame {
    private JLabel fileLabel;
    private JButton browseButton;
    private JLabel methodLabel;
    private JComboBox<String> methodComboBox;
    private JButton processButton;
    private JTextArea resultTextArea;

    public SoftwareArchitectureApp() {
        setTitle("经典软件体系结构教学软件");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // 创建界面元素
        fileLabel = new JLabel("选择要处理的文件:");
        browseButton = new JButton("浏览");
        methodLabel = new JLabel("选择处理方法:");
        methodComboBox = new JComboBox<>(new String[]{"主程序-子程序", "面向对象", "事件系统", "管道-过滤器"});
        processButton = new JButton("处理");
        resultTextArea = new JTextArea(10, 30);

        // 布局界面元素
        add(fileLabel);
        add(browseButton);
        add(methodLabel);
        add(methodComboBox);
        add(processButton);
        add(resultTextArea);

        // 注册事件监听器
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile();
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processFile();
            }
        });
    }

    private void selectFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath();
            // 处理文件选择逻辑，例如将文件路径保存到变量中
        }
    }

    private void processFile() {
        String selectedMethod = (String) methodComboBox.getSelectedItem();
        // 根据选择的处理方法，编写相应的处理逻辑，并将结果显示在resultTextArea中
    }

    public static void main(String[] args) {
        SoftwareArchitectureApp app = new SoftwareArchitectureApp();
        app.setSize(400, 300);
        app.setVisible(true);
    }
}


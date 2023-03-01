package fileUI;

import javax.swing.*;
import java.awt.*;

public class FileUI extends JFrame {
    public  void showUI(){
        setTitle("文件管理");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container con = this.getContentPane();
        con.setLayout(new BorderLayout());
        JList<String> list = new JList<String>();//实例化列表框
        DefaultListModel<String> listModel = new DefaultListModel<String>();
        list.setModel(listModel);
        //实例化JScrollPane面板
        JScrollPane jp = new JScrollPane(list);

//        listModel.addElement("C:\\Users\\86191\\Pictures\\Saved Pictures\\QQ头像.jpg");
//        listModel.addElement("文件名2");

        jp.setPreferredSize(new Dimension(700, 600));                //设置JScrollPane大小
        Container container = this.getContentPane();
        container.setLayout(new FlowLayout());
        //单选框
        JButton jRadioButton1 = new JButton("上传");
        jRadioButton1.addActionListener(new SaveListener(this,listModel));
        JButton jRadioButton2 = new JButton("下载");
        jRadioButton2.addActionListener(new GetListener(this,list));
        JButton jRadioButton3 = new JButton("删除");
        jRadioButton3.addActionListener(new DeleteListener(this,listModel,list));

        //JButton jRadioButton4 = new JButton("分享");

        //由于单选框只能选择一个，分组，一个组中只能选择一个
        ButtonGroup group = new ButtonGroup();
        group.add(jRadioButton1);
        group.add(jRadioButton2);
        group.add(jRadioButton3);
        //group.add(jRadioButton4);

        container.add(jRadioButton1);
        container.add(jRadioButton2);
        container.add(jRadioButton3);
        //container.add(jRadioButton4);
        setVisible(true);
        con.add(BorderLayout.EAST,jp);
    }

    public static void main(String[] args) {
        new FileUI().showUI();

    }
}
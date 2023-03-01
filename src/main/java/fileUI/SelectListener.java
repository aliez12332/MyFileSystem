package fileUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectListener implements ActionListener {
    public static String curFile;
    private JFrame frame;
    private DefaultListModel<String> listModel;

    public SelectListener(FileUI frame , DefaultListModel<String> listModel) {
        this.frame = frame ;
        this.listModel = listModel ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        curFile = e.getActionCommand();
        System.out.println("当前选中问件："+ curFile);

    }
}

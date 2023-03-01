package fileUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteListener implements ActionListener {
    private JList<String> list;
    private JFrame frame;
    private DefaultListModel<String> listModel;

    public DeleteListener(FileUI frame , DefaultListModel<String> listModel, JList<String> list) {
        this.frame = frame ;
        this.listModel = listModel ;
        this.list= list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("删除")){
            String selectedValue = list.getSelectedValue();
            listModel.removeElement(selectedValue);

            JOptionPane.showMessageDialog(null, "删除成功！");

        }
    }
}

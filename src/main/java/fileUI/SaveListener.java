package fileUI;

import ServerFileSystem.RPCClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SaveListener implements ActionListener {
    private JFrame frame;
    DefaultListModel<String> listModel;

    public SaveListener(FileUI frame , DefaultListModel<String> listModel) {
        this.frame = frame ;
        this.listModel = listModel ;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("上传")){
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                System.out.println(path);
                listModel.addElement(path);
                try {
                    RPCClient.atomicInteger.set(0);
                    RPCClient.saveOneFile(path);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (ExecutionException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, "保存成功！");
            }
        }
    }
}

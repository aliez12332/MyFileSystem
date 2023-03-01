package fileUI;

import ServerFileSystem.RPCClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static ServerFileSystem.RPCClient.getOneFile;

public class GetListener implements ActionListener {
    private JList<String> list;
    private JFrame frame;

    public GetListener(FileUI frame,JList<String> list) {
        this.frame = frame;
        this.list=list;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("下载")){
            RPCClient.atomicInteger.set(0);
            try {
                String selectedValue = list.getSelectedValue();
                RPCClient.getOneFile(selectedValue);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ExecutionException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            JOptionPane.showMessageDialog(null, "下载成功！");

        }
    }
}

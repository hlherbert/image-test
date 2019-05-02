package com.hl;

import javax.swing.*;
import java.io.File;

/**
 * 对话框工具
 */
public class DialogUtils {
    /**
     * 弹出文件选择对话框选择文件
     * @return
     */
    public static File chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.showOpenDialog(null);
        return chooser.getSelectedFile();
    }
}

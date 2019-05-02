package com.hl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImageTestForm {
    private JButton btnProcess;
    private JLabel lblSelectImg;
    private JPanel pnlRoot;
    private JButton btnSelectImg;
    private JLabel lblFilePath;
    private JPanel pnlSrcImg;

    /**
     * 原始图片显示框 - 在pnlSrcImg内部
     */
    private GImagePanel pnlSrcImg1;

    /**
     * 原始图片地址
     */
    private String srcImgPath;

    /**
     * 图片显示控件
     */
    class GImagePanel extends JPanel {
        /**
         * 被显示的图片
         */
        private Image img;

        public void setImg(Image img) {
            this.img = img;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (img != null) {
                g.drawImage(img, 0, 0,this.getWidth(), this.getHeight(), null);
            }
        }
    }

    public ImageTestForm() {

        pnlSrcImg1 = new GImagePanel();

        btnSelectImg.addActionListener(new ActionListener() {
            /**
             * 选择图片
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = DialogUtils.chooseFile();
                lblFilePath.setText(file.getAbsolutePath());
                srcImgPath = file.getAbsolutePath();
            }
        });

        btnProcess.addActionListener(new ActionListener() {
            /**
             * 处理图片
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示原始图片
                showSrcImg();
            }
        });
    }

    /**
     * 获取原始图片路径
     *
     * @return
     */
    protected String getSrcImgPath() {
        return srcImgPath;
    }

    /**
     * 显示原始图片
     */
    protected void showSrcImg() {
        String srcImgPath = getSrcImgPath();
        if (srcImgPath == null) {
            return;
        }

        // pnlSrcImg1显示原始图片
        ImageIcon srcIcon = new ImageIcon(srcImgPath);
        Image srcImg = srcIcon.getImage();
        pnlSrcImg1.setImg(srcImg);

        // pnlSrcImg1作为pnlSrcImg子控件
        if (pnlSrcImg1.getParent() != pnlSrcImg) {
            pnlSrcImg.add(pnlSrcImg1, BorderLayout.CENTER);
            // add 控件后需要重绘
            pnlSrcImg.revalidate();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Image Test");
        frame.setContentPane(new ImageTestForm().pnlRoot);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);   //最大化
        frame.pack();
        frame.setVisible(true);
    }
}

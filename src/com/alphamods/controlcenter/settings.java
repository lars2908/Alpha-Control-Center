/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphamods.controlcenter;

import com.alphamods.controlcenter.utils.config;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JSpinner;

/**
 *
 * @author Manu
 */
public class settings extends javax.swing.JDialog {

    
        public final static String path = System.getProperty("user.dir"); 
List<JLabel> fanlabels;
List<JSpinner> fanmaxs;
List<JLabel> pumplabels;
List<JSpinner> pumpmaxs;
int vfan = Integer.parseInt(config.getValue("fans"));
int vpump = Integer.parseInt(config.getValue("pumps"));
    /**
     * Creates new form settings1
     */
    public settings(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        if(Boolean.parseBoolean(config.getValue("startupdate"))==true){updateatstart.setSelected(true);}else{updateatstart.setSelected(false);}
        fanlabels = Arrays.asList(fanlabel1, fanlabel2, fanlabel3,fanlabel4,fanlabel5,fanlabel6,fanlabel7,fanlabel8,fanlabel9,fanlabel10,fanlabel11);
        fanmaxs = Arrays.asList(fanmax1,fanmax2,fanmax3,fanmax4,fanmax5,fanmax6,fanmax7,fanmax8,fanmax9,fanmax10,fanmax11);
        pumplabels = Arrays.asList(pumplabel1, pumplabel2,pumplabel3,pumplabel4,pumplabel5,pumplabel6,pumplabel7,pumplabel8, pumplabel9, pumplabel10, pumplabel11);
        pumpmaxs = Arrays.asList(pumpmax1,pumpmax2,pumpmax3,pumpmax4,pumpmax5,pumpmax6,pumpmax7,pumpmax8,pumpmax9,pumpmax10,pumpmax11);
        
        for (JLabel fanlabel : fanlabels) {
        fanlabel.setVisible(false);
    }
        
    for (JLabel pumplabel : pumplabels) {
        pumplabel.setVisible(false);
    }
        
    for (JSpinner fanmax : fanmaxs) {
        fanmax.setVisible(false);
    }
    for (JSpinner pumpmax : pumpmaxs) {
        pumpmax.setVisible(false);
    }
    //make visible the things needed
        
        for(int i=0; i<vfan; i++){
            fanlabels.get(i).setVisible(true);
            fanmaxs.get(i).setVisible(true);
        }

        for(int i=0; i<vpump; i++){
            pumplabels.get(i).setVisible(true);
            pumpmaxs.get(i).setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generaltab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        updaterlabel = new javax.swing.JLabel();
        updateatstart = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        generallabel = new javax.swing.JLabel();
        bootstart = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        fansTitle = new javax.swing.JLabel();
        pumpsTitle = new javax.swing.JLabel();
        fanlabel1 = new javax.swing.JLabel();
        fanlabel2 = new javax.swing.JLabel();
        fanlabel3 = new javax.swing.JLabel();
        fanlabel4 = new javax.swing.JLabel();
        fanlabel5 = new javax.swing.JLabel();
        fanlabel6 = new javax.swing.JLabel();
        fanlabel7 = new javax.swing.JLabel();
        fanlabel8 = new javax.swing.JLabel();
        fanlabel9 = new javax.swing.JLabel();
        fanlabel10 = new javax.swing.JLabel();
        fanlabel11 = new javax.swing.JLabel();
        pumplabel1 = new javax.swing.JLabel();
        pumplabel2 = new javax.swing.JLabel();
        pumplabel3 = new javax.swing.JLabel();
        pumplabel4 = new javax.swing.JLabel();
        pumplabel5 = new javax.swing.JLabel();
        pumplabel6 = new javax.swing.JLabel();
        pumplabel7 = new javax.swing.JLabel();
        pumplabel8 = new javax.swing.JLabel();
        pumplabel9 = new javax.swing.JLabel();
        pumplabel10 = new javax.swing.JLabel();
        pumplabel11 = new javax.swing.JLabel();
        fanmax1 = new javax.swing.JSpinner();
        fanmax2 = new javax.swing.JSpinner();
        fanmax3 = new javax.swing.JSpinner();
        fanmax4 = new javax.swing.JSpinner();
        fanmax5 = new javax.swing.JSpinner();
        fanmax6 = new javax.swing.JSpinner();
        fanmax7 = new javax.swing.JSpinner();
        fanmax8 = new javax.swing.JSpinner();
        fanmax9 = new javax.swing.JSpinner();
        fanmax10 = new javax.swing.JSpinner();
        fanmax11 = new javax.swing.JSpinner();
        pumpmax1 = new javax.swing.JSpinner();
        pumpmax2 = new javax.swing.JSpinner();
        pumpmax3 = new javax.swing.JSpinner();
        pumpmax4 = new javax.swing.JSpinner();
        pumpmax5 = new javax.swing.JSpinner();
        pumpmax6 = new javax.swing.JSpinner();
        pumpmax7 = new javax.swing.JSpinner();
        pumpmax8 = new javax.swing.JSpinner();
        pumpmax9 = new javax.swing.JSpinner();
        pumpmax10 = new javax.swing.JSpinner();
        pumpmax11 = new javax.swing.JSpinner();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        updaterlabel.setText("Updater");

        updateatstart.setText("Check for updates at start");
        updateatstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateatstartActionPerformed(evt);
            }
        });

        generallabel.setText("General");

        bootstart.setText("Start Conctrol Center with system boot");
        bootstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bootstartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bootstart)
                    .addComponent(generallabel)
                    .addComponent(updaterlabel)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateatstart, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generallabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bootstart)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updaterlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateatstart)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        generaltab.addTab("General", jPanel1);

        fansTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fansTitle.setText("Fans");

        pumpsTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pumpsTitle.setText("Pumps");

        fanlabel1.setText("Max. RPM Channel 1");

        fanlabel2.setText("Max. RPM Channel 2");

        fanlabel3.setText("Max. RPM Channel 3");

        fanlabel4.setText("Max. RPM Channel 4");

        fanlabel5.setText("Max. RPM Channel 5");

        fanlabel6.setText("Max. RPM Channel 6");

        fanlabel7.setText("Max. RPM Channel 7");

        fanlabel8.setText("Max. RPM Channel 8");

        fanlabel9.setText("Max. RPM Channel 9");

        fanlabel10.setText("Max. RPM Channel 10");

        fanlabel11.setText("Max. RPM Channel 11");

        pumplabel1.setText("Max. RPM Channel 1");

        pumplabel2.setText("Max. RPM Channel 2");

        pumplabel3.setText("Max. RPM Channel 3");

        pumplabel4.setText("Max. RPM Channel 4");

        pumplabel5.setText("Max. RPM Channel 5");

        pumplabel6.setText("Max. RPM Channel 6");

        pumplabel7.setText("Max. RPM Channel 7");

        pumplabel8.setText("Max. RPM Channel 8");

        pumplabel9.setText("Max. RPM Channel 9");

        pumplabel10.setText("Max. RPM Channel 10");

        pumplabel11.setText("Max. RPM Channel 11");

        fanmax1.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax2.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax3.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax4.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax5.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax6.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax7.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax8.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax9.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax10.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        fanmax11.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        fanmax11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax1.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax2.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax3.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax4.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax5.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax6.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax7.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax8.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax9.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax9.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax10.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax10.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        pumpmax11.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        pumpmax11.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(filler1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fanlabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fanmax11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fanlabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fanmax1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fanlabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fanmax2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fanlabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fanmax3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fanlabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fanmax4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fanlabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fanmax5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fanlabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fanmax6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fanlabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fanmax7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(fanlabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fanmax8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(fanlabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fanmax10))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(fanlabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(fanmax9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(fansTitle))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(pumplabel10)
                                            .addComponent(pumplabel11))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(pumplabel9)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(pumplabel2)
                                                .addComponent(pumplabel1)
                                                .addComponent(pumplabel3)
                                                .addComponent(pumplabel4)
                                                .addComponent(pumplabel5)
                                                .addComponent(pumplabel6)
                                                .addComponent(pumplabel7)
                                                .addComponent(pumplabel8)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pumpmax11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pumpmax1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pumpmax2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pumpmax3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pumpmax4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pumpmax5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pumpmax6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pumpmax7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pumpmax8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(pumpmax10)
                                        .addComponent(pumpmax9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(pumpsTitle))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fansTitle)
                    .addComponent(pumpsTitle))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel1)
                            .addComponent(fanmax1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel2)
                            .addComponent(fanmax2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel3)
                            .addComponent(fanmax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel4)
                            .addComponent(fanmax4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel5)
                            .addComponent(fanmax5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel6)
                            .addComponent(fanmax6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel7)
                            .addComponent(fanmax7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel8)
                            .addComponent(fanmax8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fanlabel9)
                            .addComponent(fanmax9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel10)
                            .addComponent(fanmax10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fanlabel11)
                            .addComponent(fanmax11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel3))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel5))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel6))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel7))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel8))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pumpmax11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pumplabel11))))
                .addContainerGap())
        );

        generaltab.addTab("RPM max.", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generaltab)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generaltab))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void updateatstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateatstartActionPerformed
        if (updateatstart.isSelected()){
            config.setValue("startupdate", "true");

        }else{
            config.setValue("startupdate", "false");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_updateatstartActionPerformed

    private void bootstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bootstartActionPerformed
        try {

            String fpathunlockb =  path + "\\dr.vbs";
            String[] args = {"wscript", fpathunlockb};
            ProcessBuilder pb = new ProcessBuilder(args);

            Process pr = pb.start();

        } catch (IOException ex) {
            Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_bootstartActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
for(int i=0; i<vfan; i++){
            config.setValue("fanmax"+i, fanmaxs.get(i).getValue().toString());
        }

        for(int i=0; i<vpump; i++){
            config.setValue("pumpmax"+i, pumpmaxs.get(i).getValue().toString());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(settings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                settings dialog = new settings(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox bootstart;
    private javax.swing.JLabel fanlabel1;
    private javax.swing.JLabel fanlabel10;
    private javax.swing.JLabel fanlabel11;
    private javax.swing.JLabel fanlabel2;
    private javax.swing.JLabel fanlabel3;
    private javax.swing.JLabel fanlabel4;
    private javax.swing.JLabel fanlabel5;
    private javax.swing.JLabel fanlabel6;
    private javax.swing.JLabel fanlabel7;
    private javax.swing.JLabel fanlabel8;
    private javax.swing.JLabel fanlabel9;
    private javax.swing.JSpinner fanmax1;
    private javax.swing.JSpinner fanmax10;
    private javax.swing.JSpinner fanmax11;
    private javax.swing.JSpinner fanmax2;
    private javax.swing.JSpinner fanmax3;
    private javax.swing.JSpinner fanmax4;
    private javax.swing.JSpinner fanmax5;
    private javax.swing.JSpinner fanmax6;
    private javax.swing.JSpinner fanmax7;
    private javax.swing.JSpinner fanmax8;
    private javax.swing.JSpinner fanmax9;
    private javax.swing.JLabel fansTitle;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel generallabel;
    private javax.swing.JTabbedPane generaltab;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel pumplabel1;
    private javax.swing.JLabel pumplabel10;
    private javax.swing.JLabel pumplabel11;
    private javax.swing.JLabel pumplabel2;
    private javax.swing.JLabel pumplabel3;
    private javax.swing.JLabel pumplabel4;
    private javax.swing.JLabel pumplabel5;
    private javax.swing.JLabel pumplabel6;
    private javax.swing.JLabel pumplabel7;
    private javax.swing.JLabel pumplabel8;
    private javax.swing.JLabel pumplabel9;
    private javax.swing.JSpinner pumpmax1;
    private javax.swing.JSpinner pumpmax10;
    private javax.swing.JSpinner pumpmax11;
    private javax.swing.JSpinner pumpmax2;
    private javax.swing.JSpinner pumpmax3;
    private javax.swing.JSpinner pumpmax4;
    private javax.swing.JSpinner pumpmax5;
    private javax.swing.JSpinner pumpmax6;
    private javax.swing.JSpinner pumpmax7;
    private javax.swing.JSpinner pumpmax8;
    private javax.swing.JSpinner pumpmax9;
    private javax.swing.JLabel pumpsTitle;
    private javax.swing.JCheckBox updateatstart;
    private javax.swing.JLabel updaterlabel;
    // End of variables declaration//GEN-END:variables
}

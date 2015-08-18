/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alphamods.controlcenter;

import com.alphamods.controlcenter.utils.config;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
int vfan;
int vpump;
int vled;
int pumpfanmin = 3;
int pumpfanmax = 12;
boolean music;
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
        
        initmotors();
        leds.setValue(Integer.parseInt(config.getValue("leds")));
        fans.setValue(Integer.parseInt(config.getValue("fans")));
        pumps.setValue(Integer.parseInt(config.getValue("pumps")));
        music = Boolean.parseBoolean(config.getValue("music"));
        musicbox.setSelected(music);
        sensors.setValue(Integer.parseInt(config.getValue("sensors")));
    
    }
    public void initmotors(){
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
        
        for(int i=0; i<Integer.parseInt(config.getValue("fans")); i++){
            fanlabels.get(i).setVisible(true);
            fanmaxs.get(i).setVisible(true);
            if(config.getValue("fanmax"+i)!= null || config.getValue("fanmax"+i)!= ""){
                                System.out.println(i+"aaa"+config.getValue("pumpmax"+i));

                fanmaxs.get(i).setValue(Integer.parseInt(config.getValue("fanmax"+i)));
            }
        }

        for(int i=0; i<Integer.parseInt(config.getValue("pumps")); i++){
            pumplabels.get(i).setVisible(true);
            pumpmaxs.get(i).setVisible(true);
            if(config.getValue("pumpmax"+i)!= null || config.getValue("pumpmax"+i)!= ""){
            pumpmaxs.get(i).setValue(Integer.parseInt(config.getValue("pumpmax"+i)));
            }
        }
    }
        public void setmotors(){
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
            if(config.getValue("fanmax"+i)!= null || config.getValue("fanmax"+i)!= ""){
                fanmaxs.get(i).setValue(Integer.parseInt(config.getValue("fanmax"+i)));
            }
        }

        for(int i=0; i<vpump; i++){
            pumplabels.get(i).setVisible(true);
            pumpmaxs.get(i).setVisible(true);
            if(config.getValue("pumpmax"+i)!= null || config.getValue("pumpmax"+i)!= ""){
            pumpmaxs.get(i).setValue(Integer.parseInt(config.getValue("pumpmax"+i)));
            }
        }
    }
    public void setModels(boolean fanchanged, boolean ledchanged){
         vled = Integer.parseInt(leds.getValue().toString());
         vfan = Integer.parseInt(fans.getValue().toString());
         vpump = Integer.parseInt(pumps.getValue().toString());
        if (vfan + vpump >= 3){
            }else{
            if (fanchanged){
                pumps.setValue(3-vfan);
            }else{
                fans.setValue(3-vpump);
            }
        }
        if(vfan + vpump >pumpfanmax){
            if (fanchanged){
                if (vpump != 1){
                  pumps.setValue(vpump-1);

                }else{
                    fans.setValue(vfan -1);
                }
                
                
            }else{
                
              if (ledchanged){
                if((vled*3)+vfan+vpump <= 15){
                    
                }else {
                    
                      if(vpump >= 4 ){
                          pumps.setValue(vpump-3);
                      }else if (vpump==3){
                          pumps.setValue(vpump-2);
                          fans.setValue(vfan-1);
                      }else if (vpump == 2){
                          pumps.setValue(vpump-1);
                          fans.setValue(vfan-2);
                      }else if (vpump == 1){
                          fans.setValue(vfan-3);
                      }
                        
                        
                  
                }  
                  
                  
                  
              }else{
                 if (vfan != 1){
                fans.setValue(vfan-1);
                }else{
                    pumps.setValue(vpump-1);
                } 
              }
                
            }
        }
        
        represent();
        setmotors();
    }
    
private void savedata(){
        config.setValue("fans", Integer.toString(vfan));
        config.setValue("pumps", Integer.toString(vpump));
        config.setValue("leds", Integer.toString(vled));
        config.setValue("sensors", sensors.getValue().toString());
        for(int i=0; i<vfan; i++){
            config.setValue("fanmax"+i, fanmaxs.get(i).getValue().toString());
        }
        
        for(int i=0; i<vpump; i++){
            config.setValue("pumpmax"+i, pumpmaxs.get(i).getValue().toString());
        }
        config.setValue("music", Boolean.toString(music));
        
    }

public void represent(){
        triples1.setVisible(false);
            triples2.setVisible(false);
            triples4.setVisible(false);
            triples3.setVisible(false);
            triplel1.setVisible(false);
            label4.setVisible(false);
            label5.setVisible(false);
            label6.setVisible(false);
            label7.setVisible(false);
            label8.setVisible(false);
            label9.setVisible(false);
            label10.setVisible(false);
            label11.setVisible(false);
            label12.setVisible(false);
            label13.setVisible(false);
            label14.setVisible(false);
            label15.setVisible(false);
            raya4.setVisible(false);
            raya5.setVisible(false);
            raya6.setVisible(false);
            raya7.setVisible(false);
            raya8.setVisible(false);
            raya9.setVisible(false);
            raya10.setVisible(false);
            raya11.setVisible(false);
            raya12.setVisible(false);
            raya13.setVisible(false);
            raya14.setVisible(false);
            raya15.setVisible(false);
            
            
            
        vled = Integer.parseInt(leds.getValue().toString());
        vfan = Integer.parseInt(fans.getValue().toString());
        vpump = Integer.parseInt(pumps.getValue().toString());
        
        if (vled == 1){
            triples1.setVisible(true);
            triplel1.setVisible(true);
            
            triplel1.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 1"));
            
            switch(vfan){
                case 1:
                             raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));                                                     
                            switch(vpump){
                                case 2:
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                                                                                         
                                    break;
                                case 3:
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));   
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                                  
                                    break;
                                case 4:
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));   
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                            
                                    break;
                                case 5:
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));   
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                                                                             
                                    break;
                                case 6:
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));   
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));                                                                          
                                    break;
                                    
                                case 7:
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));   
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));        
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));                                                                    
                                    break;
                                    
                                case 8:
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));   
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));        
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));    
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8"));                                                                  
                                    break;
                                    
                                case 9:
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));   
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));        
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));    
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8"));                                   
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P9"));                                          
                                    break;
                                case 10:
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));   
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));        
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));    
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8"));                                   
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P9"));                                   
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P10"));                                   
                                    break;
                                case 11:
                                                                 raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));   
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));        
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));    
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8"));                                   
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P9"));                                   
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P10")); 
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P11")); 
                                    break;
                            }                         
                    break;
                
                case 2:
                             raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));                            
                            switch(vpump){
                                case 2:
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                                                                                       
                                    break;
                                case 3:
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                                   
                                    break;
                                case 4:
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                            
                                    break;
                                case 5:
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                                                                             
                                    break;
                                case 6:
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));                                                                         
                                    break;
                                    
                                case 7:
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));   
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));                                                                     
                                    break;
                                    
                                case 8:
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));   
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));                                    
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8"));                                 
                                    break;
                                    
                                case 9:
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));   
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));                                    
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8"));                                   
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P9"));          
                                    break;
                                case 10:
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));   
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));                                    
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8"));                                   
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P9")); 
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P10"));                                     
                                    break;
                            }                       
                    break;
                
                
                case 3:
                    
                             raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                            switch(vpump){
                                case 2:
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                                                                                         
                                    break;
                                case 3:
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                                   
                                    break;
                                case 4:
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                           
                                    break;
                                case 5:
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                                                                           
                                    break;
                                case 6:
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));  
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));                                                                       
                                    break;
                                    
                                case 7:
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));  
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));                                     
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));                                  
                                    break;
                                    
                                case 8:
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));  
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));                                     
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));                                 
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8")); 
                                    break;
                                    
                                case 9:
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2")); 
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));    
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));  
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));                                     
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));                                 
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8")); 
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P9"));          
                                    break;
                            }   
                    
                    break;
                
                
                case 4:
                            raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));   
                            switch(vpump){
                                case 2:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                                                                                        
                                    break;
                                case 3:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                                  
                                    break;
                                case 4:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                           
                                    break;
                                case 5:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                                                                           
                                    break;
                                case 6:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                       
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));                                  
                                    break;
                                    
                                case 7:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                       
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));                                  
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7")); 
                                    break;
                                    
                                case 8:
                                                                 raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));  
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                       
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));                                  
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7")); 
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8"));  
                                    break;
                            }                      
                
                    
                    break;
                
                
                case 5:
                     raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));                          
      
                            switch(vpump){
                                case 2:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                                                                                       
                                    break;
                                case 3:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                                 
                                    break;
                                case 4:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                                       
                                                          
                                    break;
                                case 5:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                                    
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                                        
                                    break;
                                case 6:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                                    
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                 
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));   
                                    break;
                                    
                                case 7:
                                                                 raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));  
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                                    
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                 
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6")); 
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));  
                                    break;
                            }                      
                
                    break;
                
                
                case 6:
                             raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F6"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));                            
      
                            switch(vpump){
                                case 2:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                                                                                     
                                    break;
                                case 3:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                                   
                                    break;
                                case 4:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                       
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                                                            
                                    break;
                                case 5:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                       
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                                 
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5")); 
                               
                                    break;
                                case 6:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                       
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                                 
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5")); 
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));    
                                    break;
                            }                      
                    
                    
                    break;
                
                
                case 7:
                             raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F6"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F7"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
       
                            switch(vpump){
                                case 2:
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                                                                                       
                                    break;
                                case 3:
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                        
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                  
                                    break;
                                case 4:
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                        
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                  
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));                             
                                    break;
                                case 5:
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                        
                                    
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                                  
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4")); 
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));                                    
                                    break;
                            }                    
                    break;
                
                
                case 8:
                             raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F6"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F7"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F8"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
               
                            switch(vpump){
                                case 2:
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                                                       
                                    break;
                                case 3:
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                  
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                                    break;
                                case 4:
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                  
                                    
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3")); 
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                                    
                                    break;
                            }
                    
                    break;
                
                
                case 9:
                    
                             raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F6"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F7"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F8"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F9"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));                       
                            switch(vpump){
                                case 2:
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                                   
                                    break;
                                case 3:
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2")); 
                                     
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                                    
                                    break;
                            }
                    
                    break;
                
                
                case 10:
                    
                             raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F6"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F7"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F8"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F9"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F10"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                            
                            switch(vpump){
                                case 2:
                                    
                                     raya15.setVisible(true);
                                    label15.setVisible(true);
                                    label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                                    
                                    break;
                            }
                            
                            
                    
                    break;
                
                
                case 11: 
                             raya4.setVisible(true);
                            label4.setVisible(true);
                            label4.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                            
                             raya5.setVisible(true);
                            label5.setVisible(true);
                            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                            
                             raya6.setVisible(true);
                            label6.setVisible(true);
                            label6.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                            
                             raya7.setVisible(true);
                            label7.setVisible(true);
                            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                            
                             raya8.setVisible(true);
                            label8.setVisible(true);
                            label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                            
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F6"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F7"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F8"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F9"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F10"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F11"));
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1")); 
                    
                    break;
                
                
            }
            
        }else if (vled == 2){
            triples1.setVisible(true);
            triples2.setVisible(true);
            triplel1.setVisible(true);
            label5.setVisible(true);
            
            triplel1.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 1"));
            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 2"));
            
            switch(vfan){
                case 1:
                    
                     raya7.setVisible(true);
                    label7.setVisible(true);
                    label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya8.setVisible(true);
                    label8.setVisible(true);
                    label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));

                    switch(vpump){
                        case 2:
                     raya9.setVisible(true);
                    label9.setVisible(true);
                    label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            break;
                            
                        case 3:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                            
                            break;
                        case 4:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            break;
                        case 5:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            break;
                        case 6:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));
                        case 7:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));
                            break;
                        case 8:
                             raya9.setVisible(true);
                            label9.setVisible(true);
                            label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7"));
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P8")); 
                            break;
                    }
                    
                 break;
                case 2:
                     raya7.setVisible(true);
                    label7.setVisible(true);
                    label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya8.setVisible(true);
                    label8.setVisible(true);
                    label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya9.setVisible(true);
                    label9.setVisible(true);
                    label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    
                    switch(vpump){
                        case 2:
                     raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            break;
                            
                        case 3:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            break;
                        case 4:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            break;
                        case 5:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));

                            break;
                        case 6:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));
                            
                            break;
                        case 7:
                             raya10.setVisible(true);
                            label10.setVisible(true);
                            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));                            
                            
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6"));
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P7")); 
                            break;
                    }
                    break;
                case 3:
                     raya7.setVisible(true);
                    label7.setVisible(true);
                    label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya8.setVisible(true);
                    label8.setVisible(true);
                    label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya9.setVisible(true);
                    label9.setVisible(true);
                    label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                    
                     raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));

                    switch(vpump){
                        case 2:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            break;
                            
                        case 3:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            break;
                        case 4:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            break;
                        case 5:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                            break;
                        case 6:
                             raya11.setVisible(true);
                            label11.setVisible(true);
                            label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P6")); 
                            break;
                    }
                    break;
                case 4:
                    
                     raya7.setVisible(true);
                    label7.setVisible(true);
                    label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya8.setVisible(true);
                    label8.setVisible(true);
                    label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya9.setVisible(true);
                    label9.setVisible(true);
                    label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                    
                     raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));

                    switch(vpump){
                        case 2:
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            break;
                            
                        case 3:
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            break;
                        case 4:
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            break;
                        case 5:
                             raya12.setVisible(true);
                            label12.setVisible(true);
                            label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5")); 
                            break;
                    }
                    
                    break;
                case 5:
                    
                     raya7.setVisible(true);
                    label7.setVisible(true);
                    label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya8.setVisible(true);
                    label8.setVisible(true);
                    label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya9.setVisible(true);
                    label9.setVisible(true);
                    label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                    
                     raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                    
                     raya12.setVisible(true);
                    label12.setVisible(true);
                    label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    
                    
                    
                    switch(vpump){
                        case 2:
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                    
                            break;
                            
                        case 3:
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            break;
                        case 4:
                             raya13.setVisible(true);
                            label13.setVisible(true);
                            label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                            
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4")); 
                            break;
                    }
                    
                    
                    
                    break;
                case 6:
                    
                     raya7.setVisible(true);
                    label7.setVisible(true);
                    label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya8.setVisible(true);
                    label8.setVisible(true);
                    label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya9.setVisible(true);
                    label9.setVisible(true);
                    label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                    
                     raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                    
                     raya12.setVisible(true);
                    label12.setVisible(true);
                    label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F6"));
                    
                     raya13.setVisible(true);
                    label13.setVisible(true);
                    label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    
                    
                    switch(vpump){
                        case 2:
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                    
                            break;
                            
                        case 3:
                            
                             raya14.setVisible(true);
                            label14.setVisible(true);
                            label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                             raya15.setVisible(true);
                            label15.setVisible(true);
                            label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));  
                            
                            break;
                    }
                    
                    
                    break;
                case 7:
                    
                     raya7.setVisible(true);
                    label7.setVisible(true);
                    label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya8.setVisible(true);
                    label8.setVisible(true);
                    label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya9.setVisible(true);
                    label9.setVisible(true);
                    label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                    
                     raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                    
                     raya12.setVisible(true);
                    label12.setVisible(true);
                    label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F6"));
                    
                     raya13.setVisible(true);
                    label13.setVisible(true);
                    label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F7"));
                    
                     raya14.setVisible(true);
                    label14.setVisible(true);
                    label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    switch(vpump){
                        case 2:
                            
                     raya15.setVisible(true);
                    label15.setVisible(true);
                    label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            break;
                    }
                    
                    
                    break;
                case 8:
                     raya7.setVisible(true);
                    label7.setVisible(true);
                    label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya8.setVisible(true);
                    label8.setVisible(true);
                    label8.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya9.setVisible(true);
                    label9.setVisible(true);
                    label9.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                    
                     raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                    
                     raya12.setVisible(true);
                    label12.setVisible(true);
                    label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F6"));
                    
                     raya13.setVisible(true);
                    label13.setVisible(true);
                    label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F7"));
                    
                     raya14.setVisible(true);
                    label14.setVisible(true);
                    label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F8"));
                    
                     raya15.setVisible(true);
                    label15.setVisible(true);
                    label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    
                    break;
            }
            
        }else if (vled == 3){
            triples1.setVisible(true);
            triples2.setVisible(true);
            triples3.setVisible(true);
            triplel1.setVisible(true);
            label5.setVisible(true);
            label7.setVisible(true);
            
            triplel1.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 1"));
            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 2"));
            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 3"));
            switch(vfan){
                case 1:
                     raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    
                     raya12.setVisible(true);
                    label12.setVisible(true);
                    label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                    
                     switch (vpump){
                         case 3:
                             raya13.setVisible(true);
                             label13.setVisible(true);
                             label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                             break;
                         case 4:
                             raya13.setVisible(true);
                             label13.setVisible(true);
                             label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                    
                             raya14.setVisible(true);
                             label14.setVisible(true);
                             label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                             break;
                         case 5:
                             raya13.setVisible(true);
                             label13.setVisible(true);
                             label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                    
                             raya14.setVisible(true);
                             label14.setVisible(true);
                             label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                             
                             
                             raya15.setVisible(true);
                             label15.setVisible(true);
                             label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P5"));
                             break;
                        
                     
                     }
                break;
                    
                case 2:
                    raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya12.setVisible(true);
                    label12.setVisible(true);
                    label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    
                    switch (vpump){
                         case 2:
                             raya13.setVisible(true);
                             label13.setVisible(true);
                             label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                             break;
                         case 3:
                             raya13.setVisible(true);
                             label13.setVisible(true);
                             label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                             
                             raya14.setVisible(true);
                             label14.setVisible(true);
                             label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                             break;
                         case 4:
                             raya13.setVisible(true);
                             label13.setVisible(true);
                             label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                             
                             raya14.setVisible(true);
                             label14.setVisible(true);
                             label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                             
                             raya15.setVisible(true);
                             label15.setVisible(true);
                             label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P4"));
                             break;
                    }
                break;
                case 3: 
                    raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya12.setVisible(true);
                    label12.setVisible(true);
                    label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                    
                     raya13.setVisible(true);
                    label13.setVisible(true);
                    label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    
                    switch(vpump){
                        case 2:
                     raya14.setVisible(true);
                    label14.setVisible(true);
                    label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            break;
                        case 3:
                     raya14.setVisible(true);
                    label14.setVisible(true);
                    label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));        

                    raya15.setVisible(true);
                    label15.setVisible(true);
                    label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P3"));
                    break; 
                    }
                    break;
                    
                    
                case 4:
                    raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya12.setVisible(true);
                    label12.setVisible(true);
                    label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                    
                     raya13.setVisible(true);
                    label13.setVisible(true);
                    label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                    
                     raya14.setVisible(true);
                    label14.setVisible(true);
                    label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    
                    switch(vpump){
                        case 2:
                     raya15.setVisible(true);
                    label15.setVisible(true);
                    label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                            break;
                    }
                    break;
                case 5:
                    raya10.setVisible(true);
                    label10.setVisible(true);
                    label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                     raya11.setVisible(true);
                    label11.setVisible(true);
                    label11.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                     raya12.setVisible(true);
                    label12.setVisible(true);
                    label12.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F3"));
                    
                     raya13.setVisible(true);
                    label13.setVisible(true);
                    label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F4"));
                    
                     raya14.setVisible(true);
                    label14.setVisible(true);
                    label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F5"));
                    
                     raya15.setVisible(true);
                    label15.setVisible(true);
                    label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    break;
                    
                    
                    
            }
        }else if (vled == 4){
            triples1.setVisible(true);
            triples2.setVisible(true);
            triples4.setVisible(true);
            triples3.setVisible(true);
            triplel1.setVisible(true);
            label5.setVisible(true);
            label7.setVisible(true);
            label10.setVisible(true);
            
            triplel1.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 1"));
            label5.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 2"));
            label7.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 3"));
            label10.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("LED 4"));
            
            switch(vfan){
                case 1:
                    raya13.setVisible(true);
                    label13.setVisible(true);
                    label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                    raya14.setVisible(true);
                    label14.setVisible(true);
                    label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                    
                    raya15.setVisible(true);
                    label15.setVisible(true);
                    label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P2"));
                break;
                    
                case 2:
                    raya13.setVisible(true);
                    label13.setVisible(true);
                    label13.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F1"));
                    
                    raya14.setVisible(true);
                    label14.setVisible(true);
                    label14.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("F2"));
                    
                    raya15.setVisible(true);
                    label15.setVisible(true);
                    label15.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("P1"));
                break;
            }
             
        }
        
        if (music){
            triplel1.setText(java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle").getString("MUSIC"));
            triplel1.setFont(triplel1.getFont().deriveFont(Font.BOLD));
            triplel1.setForeground(Color.green.darker());
        }else{
            triplel1.setFont(triplel1.getFont().deriveFont(Font.PLAIN));
            triplel1.setForeground(Color.black);
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
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        triples1 = new javax.swing.JLabel();
        triples2 = new javax.swing.JLabel();
        triples3 = new javax.swing.JLabel();
        triples4 = new javax.swing.JLabel();
        raya4 = new javax.swing.JLabel();
        raya5 = new javax.swing.JLabel();
        raya6 = new javax.swing.JLabel();
        raya7 = new javax.swing.JLabel();
        raya8 = new javax.swing.JLabel();
        raya9 = new javax.swing.JLabel();
        raya10 = new javax.swing.JLabel();
        raya11 = new javax.swing.JLabel();
        raya12 = new javax.swing.JLabel();
        raya13 = new javax.swing.JLabel();
        raya14 = new javax.swing.JLabel();
        raya15 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        label6 = new javax.swing.JLabel();
        label7 = new javax.swing.JLabel();
        label8 = new javax.swing.JLabel();
        label9 = new javax.swing.JLabel();
        label10 = new javax.swing.JLabel();
        label11 = new javax.swing.JLabel();
        label12 = new javax.swing.JLabel();
        label13 = new javax.swing.JLabel();
        label14 = new javax.swing.JLabel();
        label15 = new javax.swing.JLabel();
        triplel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        leds = new javax.swing.JSpinner();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fans = new javax.swing.JSpinner();
        pumps = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        sensors = new javax.swing.JSpinner();
        musicbox = new javax.swing.JCheckBox();
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
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("com/alphamods/controlcenter/res/Bundle"); // NOI18N
        setTitle(bundle.getString("SETTINGS")); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        updaterlabel.setText(bundle.getString("UPDATER")); // NOI18N

        updateatstart.setText(bundle.getString("CHECK FOR UPDATES AT START")); // NOI18N
        updateatstart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateatstartActionPerformed(evt);
            }
        });

        generallabel.setText(bundle.getString("GENERAL")); // NOI18N

        bootstart.setText(bundle.getString("START CONCTROL CENTER WITH SYSTEM BOOT")); // NOI18N
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
                .addContainerGap(130, Short.MAX_VALUE))
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
                .addContainerGap(237, Short.MAX_VALUE))
        );

        generaltab.addTab("General", jPanel1);

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/channels.png"))); // NOI18N
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        triples1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector3.png"))); // NOI18N
        jPanel5.add(triples1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, -1));

        triples2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector3.png"))); // NOI18N
        jPanel5.add(triples2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        triples3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector3.png"))); // NOI18N
        jPanel5.add(triples3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, 20));

        triples4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector3.png"))); // NOI18N
        jPanel5.add(triples4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, 20));

        raya4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 30, 40));

        raya5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 30, 20));

        raya6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 30, 40));

        raya7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 30, 20));

        raya8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 30, 20));

        raya9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 30, 20));

        raya10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya10, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 30, 20));

        raya11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya11, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 30, 20));

        raya12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya12, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 30, 20));

        raya13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 20, 20));

        raya14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya14, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 30, 20));

        raya15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/alphamods/controlcenter/res/selector1.png"))); // NOI18N
        jPanel5.add(raya15, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 30, 20));

        label4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label4.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, -1));

        label5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label5.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label5, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, -1, -1));

        label6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label6.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, -1, -1));

        label7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label7.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, -1, -1));

        label8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label8.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, -1, -1));

        label9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label9.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, -1, -1));

        label10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label10.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, -1, -1));

        label11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label11.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, -1));

        label12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label12.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, -1));

        label13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label13.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label13, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, -1, -1));

        label14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label14.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label14, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, -1, -1));

        label15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label15.setText(bundle.getString("F1")); // NOI18N
        jPanel5.add(label15, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, -1, -1));

        triplel1.setText("triplel1");
        jPanel5.add(triplel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, -1, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText(bundle.getString("SELECT THE NUMBER OF FANS")); // NOI18N

        leds.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));
        leds.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ledsStateChanged(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText(bundle.getString("SELECT THE NUMBER OF PUMPS")); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText(bundle.getString("SELECT THE NUMBER OF  LED STRIPS")); // NOI18N

        fans.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));
        fans.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                fansStateChanged(evt);
            }
        });

        pumps.setModel(new javax.swing.SpinnerNumberModel(2, 1, 12, 1));
        pumps.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pumpsStateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText(bundle.getString("SELECT THE NUMBER OF TEMPERATURE SENSORS")); // NOI18N

        sensors.setModel(new javax.swing.SpinnerNumberModel(0, 0, 11, 1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(leds, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(sensors, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pumps, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fans, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pumps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sensors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        musicbox.setText(bundle.getString("MY BOARD HAS A MUSIC-DEDICATED CHANNEL")); // NOI18N
        musicbox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                musicboxStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(musicbox))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(musicbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        generaltab.addTab("Board", jPanel3);

        fansTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        fansTitle.setText(bundle.getString("FANS")); // NOI18N

        pumpsTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        pumpsTitle.setText(bundle.getString("PUMPS")); // NOI18N

        fanlabel1.setText(bundle.getString("MAX. RPM CHANNEL 1")); // NOI18N

        fanlabel2.setText(bundle.getString("MAX. RPM CHANNEL 2")); // NOI18N

        fanlabel3.setText(bundle.getString("MAX. RPM CHANNEL 3")); // NOI18N

        fanlabel4.setText(bundle.getString("MAX. RPM CHANNEL 4")); // NOI18N

        fanlabel5.setText(bundle.getString("MAX. RPM CHANNEL 5")); // NOI18N

        fanlabel6.setText(bundle.getString("MAX. RPM CHANNEL 6")); // NOI18N

        fanlabel7.setText(bundle.getString("MAX. RPM CHANNEL 7")); // NOI18N

        fanlabel8.setText(bundle.getString("MAX. RPM CHANNEL 8")); // NOI18N

        fanlabel9.setText(bundle.getString("MAX. RPM CHANNEL 9")); // NOI18N

        fanlabel10.setText(bundle.getString("MAX. RPM CHANNEL 10")); // NOI18N

        fanlabel11.setText(bundle.getString("MAX. RPM CHANNEL 11")); // NOI18N

        pumplabel1.setText(bundle.getString("MAX. RPM CHANNEL 1")); // NOI18N

        pumplabel2.setText(bundle.getString("MAX. RPM CHANNEL 2")); // NOI18N

        pumplabel3.setText(bundle.getString("MAX. RPM CHANNEL 3")); // NOI18N

        pumplabel4.setText(bundle.getString("MAX. RPM CHANNEL 4")); // NOI18N

        pumplabel5.setText(bundle.getString("MAX. RPM CHANNEL 5")); // NOI18N

        pumplabel6.setText(bundle.getString("MAX. RPM CHANNEL 6")); // NOI18N

        pumplabel7.setText(bundle.getString("MAX. RPM CHANNEL 7")); // NOI18N

        pumplabel8.setText(bundle.getString("MAX. RPM CHANNEL 8")); // NOI18N

        pumplabel9.setText(bundle.getString("MAX. RPM CHANNEL 9")); // NOI18N

        pumplabel10.setText(bundle.getString("MAX. RPM CHANNEL 10")); // NOI18N

        pumplabel11.setText(bundle.getString("MAX. RPM CHANNEL 11")); // NOI18N

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pumplabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pumplabel11, javax.swing.GroupLayout.Alignment.TRAILING)
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
                                .addGap(18, 18, 18)
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
                .addContainerGap(22, Short.MAX_VALUE))
        );

        generaltab.addTab("RPM max.", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generaltab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(generaltab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
savedata();
                // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void musicboxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_musicboxStateChanged
        music = musicbox.isSelected();
        represent();
    }//GEN-LAST:event_musicboxStateChanged

    private void pumpsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pumpsStateChanged
        setModels(false, false);        // TODO add your handling code here:
    }//GEN-LAST:event_pumpsStateChanged

    private void fansStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_fansStateChanged
        setModels(true, false);        // TODO add your handling code here:
    }//GEN-LAST:event_fansStateChanged

    private void ledsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ledsStateChanged
        int value = Integer.parseInt(leds.getValue().toString());
        if (value == 1){
            pumpfanmax = 12;
        }else if (value == 2){
            pumpfanmax = 9;
        }else if (value == 3){
            pumpfanmax = 6;
        }else if (value == 4){
            pumpfanmax = 3;
        }
        setModels(false, true);

        // TODO add your handling code here:
    }//GEN-LAST:event_ledsStateChanged

    private void bootstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bootstartActionPerformed
        if(bootstart.isSelected()) {
            try {
                WinRegistry.writeStringValue(WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", "Alphamods Control Center", "\""+path+"\\Alpha Control Center.exe"+"\"");

            } catch (IllegalArgumentException ex) {
                Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                WinRegistry.deleteValue(WinRegistry.HKEY_CURRENT_USER,"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run", "Alphamods Control Center");
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(settings.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_bootstartActionPerformed

    private void updateatstartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateatstartActionPerformed
        if (updateatstart.isSelected()){
            config.setValue("startupdate", "true");

        }else{
            config.setValue("startupdate", "false");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_updateatstartActionPerformed

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
    private javax.swing.JSpinner fans;
    private javax.swing.JLabel fansTitle;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel generallabel;
    private javax.swing.JTabbedPane generaltab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel label10;
    private javax.swing.JLabel label11;
    private javax.swing.JLabel label12;
    private javax.swing.JLabel label13;
    private javax.swing.JLabel label14;
    private javax.swing.JLabel label15;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JLabel label6;
    private javax.swing.JLabel label7;
    private javax.swing.JLabel label8;
    private javax.swing.JLabel label9;
    private javax.swing.JSpinner leds;
    private javax.swing.JCheckBox musicbox;
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
    private javax.swing.JSpinner pumps;
    private javax.swing.JLabel pumpsTitle;
    private javax.swing.JLabel raya10;
    private javax.swing.JLabel raya11;
    private javax.swing.JLabel raya12;
    private javax.swing.JLabel raya13;
    private javax.swing.JLabel raya14;
    private javax.swing.JLabel raya15;
    private javax.swing.JLabel raya4;
    private javax.swing.JLabel raya5;
    private javax.swing.JLabel raya6;
    private javax.swing.JLabel raya7;
    private javax.swing.JLabel raya8;
    private javax.swing.JLabel raya9;
    private javax.swing.JSpinner sensors;
    private javax.swing.JLabel triplel1;
    private javax.swing.JLabel triples1;
    private javax.swing.JLabel triples2;
    private javax.swing.JLabel triples3;
    private javax.swing.JLabel triples4;
    private javax.swing.JCheckBox updateatstart;
    private javax.swing.JLabel updaterlabel;
    // End of variables declaration//GEN-END:variables
}

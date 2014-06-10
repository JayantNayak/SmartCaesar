package front;

import java.awt.*;

import servergui.*;
import clientgui.*;

import java.io.*;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
public class SelectServerOrClient  extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String etypes[] = {"Server","Client"};
	private JLabel text1,text2;
	JPanel  pGui1,pGui2,pGui3,mpan;
	 private int eindex=0;
	 File browseloc;
	 String str="",destn;
	 JButton runbt,browsebt;
	 GridBagLayout gbag;
	 GridBagConstraints c;
	public SelectServerOrClient() throws Exception
		{
				

			 setTitle("Select Server Or Client");
			 setSize(600,700);
			 setLocationRelativeTo(null) ;//center the frame
			 //setResizable(false);
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 
			 gbag = new GridBagLayout();
			 c = new  GridBagConstraints ();
			destn= new String(" browse destination folder ");
			
			JEditorPane editorPane ;//= new JEditorPane();
			
			 editorPane=new JEditorPane("text/html","<h3><u>SOFTWARE DESCRIPTION</u></h3>"+"<br>Smart Caesar is a file encryption-decryption software.</br>"+"<br>It can be used to encrypt a file as well as decrypt it."+ 
			 		"<br>It consist of two gui server and client.<br><br><u>USE</u>:-<br>  1.To encrypt text in a file using various methods of 	encryption. <br> 2.To decrypt text in a file <br>3.To send a file over newtork from one system to another.<br><br>"+
			 		"<u>TECHONOLOGY USED</u>:- File programing,Socket programing,Swing,html <br> "+		 		
			 		"<u>TOOLS USED</u>:- ECLIPSE JUNO.<br><br>"+
			 		"<u>PROJECT UNDER GUIDENCE OF</u>:- <br>"+
			 		 "Professor Bharti Mishra<br>"+ 
					  "Professor Suraj Sharma<br><br>"+
					 "<u>MEMBERS NAMES:-</u><br>"+
					   "Apurva kumar srivastav<br>"+
					    "Chaitan Majhi<br>"+
					   "Himanshu kumar Tanty<br>"+
					    "Jayant Kumar Nayak<br><br>"+
					    "This application is solely for the purpose of learning.<br>"+
					    "All the COPYRIGHTS are reserved by the team members.<br>");
			
			 
			 editorPane.setEditable(false);
			
				// JScrollPane pane = new JScrollPane(editorPane);
			 //mpan=new JPanel(new GridLayout(3,1));
			 mpan=new JPanel(gbag);
				mpan.setBorder(new TitledBorder("<html> <body><head></head><h2>SmartCaesar Installation</h2></body></html>"));
			 pGui1=new JPanel();//new GridLayout(3,1) 
			  pGui2=new JPanel();
			  pGui3=new JPanel();
			  
			  pGui1.add( editorPane);
			  //pGui1.add( pane);
			  browsebt=new JButton("Browse");	
			 browsebt.setToolTipText("Set the location");


			  runbt=new JButton("Run");
			 runbt.setToolTipText("start the Server or the Client");
			 runbt.setEnabled(false);
			 
		text1=new JLabel("select your choice :");
		pGui2.add(text1);	 
			 JRadioButton serverButton = new JRadioButton("Server");
			 serverButton.setSelected(true);

			 JRadioButton clientButton = new JRadioButton("Client");

			    //Group the radio buttons.
			 ButtonGroup choosegroup = new ButtonGroup();
			 choosegroup.add(serverButton);
			 choosegroup.add(clientButton);
			 /*c.gridx = 1;
				c.gridy = 1;
			 pGui2.add(serverButton,c);
			 c.gridx = 1;
				c.gridy = 2;
			 pGui2.add(clientButton,c);
			 */
			 pGui2.add(serverButton);
			 pGui2.add(clientButton);
		
			serverButton.addItemListener( new ItemListener(){
			 public void itemStateChanged(ItemEvent e) {
				 try{
				    if (e.getStateChange() == ItemEvent.SELECTED) {
				 
				    	 eindex=0;
				     	 System.out.println(" eindex is "+eindex);
				    }
				    else if (e.getStateChange() == ItemEvent.DESELECTED) {
				     
				    	 eindex=1;
				    	 System.out.println(" eindex is "+eindex);
				    }
				 }
				 catch(Exception s)
				 {s.printStackTrace();
				 
				 }
				}
			});
			
	
			
			text2=new JLabel(destn);
			text2.setBorder(new TitledBorder(""));
			 pGui3.add(text2);
			 pGui3.add(browsebt);
			 pGui3.add(runbt);
			 
				 
			
			 browsebt.addActionListener(new ActionListener(){ //browse
					@Override
						public void actionPerformed(ActionEvent e)
						{ 
							JFileChooser chooser= new JFileChooser();
							chooser.setCurrentDirectory(new java.io.File("."));
						    chooser.setDialogTitle("hello");
						    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						    //
						    // disable the "All files" option.
						    //
						    chooser.setAcceptAllFileFilterUsed(false);
						    //    
						    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
						    { 
						      System.out.println("getCurrentDirectory(): " 
						         +  chooser.getCurrentDirectory());
						      System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
						    //  destn=(chooser.getCurrentDirectory()).getAbsolutePath();
						    //  str=destn;
						     // text2.setText(destn);
						      
						      
						      String s=(chooser.getSelectedFile()).getAbsolutePath();
						     // String s=chooser.getAbsolutePath();
						      System.out.println("absolute path "+s);
								 ArrayList a = new ArrayList();
								 byte b[]=s.getBytes();  //path 
								 char c=(char)b[0];
								 String name=(chooser.getCurrentDirectory()).getName();
								 System.out.println("name "+name);
								 for(int i=0;i<s.length();i++) {
									 if(( c=(char)b[i] )=='\\'){
										 a.add(c);
										 a.add(c);
											 
									 }
									 else {
										 a.add(c);
									 }
								}
								 
								 for(int i=0;i<a.size();i++){
									 str+=a.get(i);
								 }
								// str=str.replace(name, "");
								 destn=str;
								 text2.setText(destn);
								 System.out.println("destination folder "+str);
								 runbt.setEnabled(true);
						    }
						}		
						});	
			 runbt.addActionListener(new ActionListener(){
					@Override
						public void actionPerformed(ActionEvent e)
						{ System.out.println("run");
						try{
						switch(eindex){
						
							case 0 : ServerGui obj=new ServerGui (str); break;
							case 1 : ClientGui obj1=new ClientGui (str); break;
						
						 }
						}catch(Exception k){
							
						}
						}
				});	
				
			 //c.gridx = 0;
			 c.gridy = 0;
			// c.ipady = 	100;
				 mpan.add(pGui1,c);
			// c.gridx = 0;
			 c.gridy = 1;
				mpan.add(pGui2,c);
			// c.gridx = 0;
			 c.gridy = 2;
				 mpan.add(pGui3,c);
				 this.add(mpan);
	
					
			 
			  setVisible(true);
			  }


		
		
		
		public static void main(String [] args) throws Exception
		{
			new SelectServerOrClient();
		}
	 }


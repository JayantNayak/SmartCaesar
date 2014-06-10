package servergui;
import java.util.*;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import client.Client;
import server.*;

import java.net.*;
import java.io.*;

import encrypt.*;
import timepack.*;
public class ServerGui  extends JFrame implements WindowListener {

	
	private static final long serialVersionUID = 1L;
	
	 private JLabel Etypelbl = new JLabel("Encryption Type:"  );
	 private JLabel Browselbl = new JLabel("Browse:"  );
	 private JLabel Entimelbl = new JLabel("Encryption Time:"  );

	 private JLabel Dlbl = new JLabel("DEcryption :"  );
	 private JLabel DBrowselbl = new JLabel("Browse:"  );
	 private JLabel Dtimelbl = new JLabel("Decryption Time:"  );

	 private String etypes[] = {"None","Additative","Multiplicative","Combination"},fname;
	 private JComboBox<String> jcbo ;
	 private int EComboitem[]=new int[4];
	 private int flag=0,eindex=0;
	 private JTextField EncrpTimeTf2, DecrpTimeTf2;
	 JButton Sendbt14;
	 File fbrowse1, fbrowse2;
	 Server s;
	 static String path1="";
//	 time fintime,runtime;
	 MasterEncryption en=new Encryption();
		  public ServerGui(String path) throws Exception
		{
				
			  path1=path;
			 setTitle("Smart Caesar(Server)");
			 setSize(725,450);
			 setLocationRelativeTo(null) ;//center the frame
			 //setResizable(false);
			 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 for(int i=0;i<5;i++){
					try{
					File f=new File(path1,"SmartCaesar");
					File fserver=new File(path1+"\\SmartCaesar","ServerFiles");
					//File fclient=new File(path1+"\\SmartCaesar","ClientFiles");
					File f1=new File(path1+"\\SmartCaesar\\ServerFiles","Encrypted");
					File f2=new File(path1+"\\SmartCaesar\\ServerFiles","Decrypted");
					File f3=new File(path1+"\\SmartCaesar\\ServerFiles","Received Files");
					boolean fs=fserver.mkdir();
					//boolean fc=fclient.mkdir();
					boolean r=f.mkdir();
					boolean r1=f1.mkdir();
					boolean r2=f2.mkdir();
					boolean r3=f3.mkdir();
				}
				catch(Exception f){}
				}
		
			JPanel pGui= new JPanel(new GridLayout( 1, 2,10,5));	 //create main panel 
			
			JPanel pGui1=new JPanel(new GridLayout( 5,1,5,5));
			pGui1.setBorder(new TitledBorder("Encryption"));
			Color c1= new Color(230, 215,181); pGui1.setBackground(c1);
			
			JPanel pGui2=new JPanel(new GridLayout( 4,1,5,5));
			pGui2.setBorder(new TitledBorder("DEcryption"));
			Color c2=  new Color(221, 230,170); pGui2.setBackground(c2);
			
			// add sub-panels to main panel
			pGui.add(pGui1);
			pGui.add(pGui2);
			this.add(pGui);	//add main panel 

			// creating contents of pGui1
			GridBagLayout gbag = new GridBagLayout();
			GridBagConstraints c = new  GridBagConstraints ();
			
			JPanel pGui11=new JPanel(gbag);	 //1
			pGui11.setBackground(c1);
			c.weightx=1.0;c.weighty=0.0;
		
			pGui11.add(Etypelbl,c);
			c.weightx=2.0;c.weighty=0.0;

			jcbo = new JComboBox<String>(etypes);  //JcomboBox initialization
			//jcbo.setSelectedItem(1);
			for(int i=0;i<4;i++)
				EComboitem[i]=0;
			EComboitem[0]=1;
			jcbo.setToolTipText("select the encryption type");
			pGui11.add(jcbo,c);
			pGui1.add(pGui11);
			 
				  
			JPanel pGui12=new JPanel(gbag );  //2
			pGui12.setBackground(c1);
			c.weightx=1.0;c.weighty=0.0;
			pGui12.add(Browselbl,c);
			JButton Browsebt11=new JButton("Browse");	
			Browsebt11.setToolTipText("Browse for the file to encrypt");
			c.weightx=2.0;c.weighty=0.0;
			pGui12.add(Browsebt11,c);
			pGui1.add(pGui12);
			  
			JPanel pGui13=new JPanel(gbag ); //3
			pGui13.setBackground(c1);
			JButton Runbt12=new JButton("Run");
			Runbt12.setToolTipText("start Encryption");
			c.weightx=1.0;c.weighty=0.0;
			pGui13.add(Runbt12,c);
			JButton Cancelbt13=new JButton("Stop");	
			Cancelbt13.setToolTipText("stops current encryption");
			c.weightx=2.0;c.weighty=0.0;
			pGui13.add(Cancelbt13,c);
			pGui1.add(pGui13);
			

			/// text fields of IP & Port & Send Button
			JPanel pGui14=new JPanel(gbag ); //4
			pGui14.setBackground(c1);


			/*JTextField IpAddTf1=new JTextField("enter the Ipadd ",8);
			IpAddTf1.setToolTipText("enter the IpAdd");
			IpAddTf1.setEditable(true);
			c.weightx=1.0;c.weighty=0.0;
			pGui14.add( IpAddTf1,c);*/
			final JTextField PortAddTf2=new JTextField("5222",5);
			PortAddTf2.setToolTipText("enter the PortNo.");
			PortAddTf2.setEditable(true);
			c.weightx=1.0;c.weighty=0.0;
			pGui14.add( PortAddTf2,c);
			
			JButton Conbt13=new JButton("Connect");
			Conbt13.setToolTipText("Connect port "+PortAddTf2.getText());
			c.weightx=2.0;c.weighty=0.0;
			pGui14.add(Conbt13,c);
			
			 Sendbt14=new JButton("Send");
			Sendbt14.setToolTipText("Send the file through network");
			Sendbt14.setEnabled(false);
			
			c.weightx=3.0;c.weighty=0.0;
			pGui14.add(Sendbt14,c);
			pGui14.setBackground(c1);
			pGui1.add(pGui14);


			// Encryption time text field and label
			JPanel pGui15=new JPanel(gbag ); //5
			pGui15.setBackground(c1);
			c.weightx=1.0;c.weighty=0.0;
			pGui15.add(Entimelbl,c);
			EncrpTimeTf2=new JTextField("ETime",6);
			EncrpTimeTf2.setToolTipText("time taken to Encrypt");

			EncrpTimeTf2.setEditable(false);
			c.weightx=2.0;c.weighty=0.0;
			pGui15.add( EncrpTimeTf2,c);
			pGui1.add(pGui15);

			/////////////////////////////////////////

			JPanel pGui21=new JPanel(gbag);	 //1
			pGui21.setBackground(c2);
			
			c.weightx=1.0;c.weighty=0.0;
			 pGui21.add(Dlbl,c);
			 pGui2.add(pGui21);
			 
				  
			JPanel pGui22=new JPanel(gbag );  //2
			pGui22.setBackground(c2);
			c.weightx=1.0;c.weighty=0.0;
			pGui22.add(DBrowselbl,c);
			JButton Browsebt21=new JButton("Browse");	
			Browsebt21.setToolTipText("Browse file to Decrypt");
			c.weightx=2.0;c.weighty=0.0;
			pGui22.add(Browsebt21,c);
			pGui2.add(pGui22);
			  
			JPanel pGui23=new JPanel(gbag ); //3
			pGui23.setBackground(c2);
			JButton Runbt22=new JButton("Run");
			Runbt22.setToolTipText("Press to start Decryption");
			c.weightx=1.0;c.weighty=0.0;
			pGui23.add(Runbt22,c);

			JButton Cancelbt23=new JButton("Stop");
			Cancelbt23.setToolTipText("Press to stop Decryption");
			c.weightx=2.0;c.weighty=0.0;
			pGui23.add(Cancelbt23,c);
			pGui2.add(pGui23);
			

			JPanel pGui24=new JPanel(gbag ); //4
			pGui24.setBackground(c2);
			c.weightx=1.0;c.weighty=0.0;
			pGui24.add(Dtimelbl,c);
			 DecrpTimeTf2=new JTextField("DTime",6);
			DecrpTimeTf2.setToolTipText("time taken to Decrypt");
		    DecrpTimeTf2.setEditable(false);
			c.weightx=2.0;c.weighty=0.0;
			pGui24.add( DecrpTimeTf2,c);
			pGui2.add(pGui24);
			
				
			System.out.println("ServerGui running");
		
	
			
		
			jcbo.addActionListener( new ActionListener(){
		    @Override
		    	public void actionPerformed(ActionEvent e) {
		    	//System.out.println("Process Save");
			    }
			 }
		    );
			
			jcbo.addItemListener( new ItemListener(){
			 
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
			
				if(arg0.getStateChange()==ItemEvent.SELECTED)
				{	
					setItemAction(jcbo.getSelectedIndex());
				}
				for(int i=0;i<4;i++)
				System.out.println(" item status " +EComboitem[i]);
				
				}
			});

			
		
			Browsebt11.addActionListener(new ActionListener(){
			@Override
				public void actionPerformed(ActionEvent e)
				{ 
					JFileChooser fc= new JFileChooser();
					if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) 
					System.out.println( "File Browse" );
					
				 fbrowse1=fc.getSelectedFile ( );
		
				
				}
			});	
			
			
			Runbt12.addActionListener(new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent e)
					{ System.out.println("run");
					time runtime =new time();
				
					File newf;
					FileInputStream fis;
					FileOutputStream fos;
					
					
					try{

					 fis=new FileInputStream(fbrowse1);
					newf=new File(path1+"\\SmartCaesar\\ServerFiles\\Encrypted\\"+eindex+fbrowse1.getName());
					boolean fr= newf.createNewFile();
					
					 fos=new FileOutputStream(newf);
					int i;
					char a;
					 
					while((i=fis.read())!=-1){
						a=en.encrypt((char)i);
						System.out.println(a);
						fos.write(a);
					}
					fis.close();
					}
					catch(Exception c){
						c.printStackTrace();
					}
					time fintime = new time();
					
					 String t=fintime.gettime(runtime, fintime);
					EncrpTimeTf2.setText(t);
				    }
			  });
			
			Browsebt21.addActionListener(new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent e)
					{ 
						JFileChooser fc= new JFileChooser();
						if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) 
						System.out.println("File Browse");
						
					 fbrowse2=fc.getSelectedFile ( );
					 int a=(fbrowse2.getName()).charAt(0);
					
						//System.out.println("thheeeee index is  "+a);
						 eindex=a-48;
						// System.out.println("thheeeee EEEEindex is  "+eindex);
						 
				 char f[]=(fbrowse2.getName()).toCharArray();
			//	 System.out.println("thheeeee file name length  "+f.length);
				  fname= new String(f,1,f.length-1);
				// System.out.println("thheeeee file name is  "+fn);
					}
				});	
		
			Runbt22.addActionListener(new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent e)
					{ System.out.println("run");
					time runtime =new time();
					
					File newf;
					FileInputStream fis;
					FileOutputStream fos;
					
					
					try{

					 fis=new FileInputStream(fbrowse2);
					newf=new File(path1+"\\SmartCaesar\\ServerFiles\\Decrypted\\"+fname);
					boolean fr= newf.createNewFile();
					
					 fos=new FileOutputStream(newf);
					int i;
					char a;
					 
					while((i=fis.read())!=-1){
						a=en.decrypt((char)i);
						System.out.println(a);
						fos.write(a);
					}
					fis.close();
					}
					catch(Exception c){
						c.printStackTrace();
					}
					time fintime = new time();
					
					 String t=fintime.gettime(runtime, fintime);
					 DecrpTimeTf2.setText(t);
				    }
			  });
			Conbt13.addActionListener(new ActionListener(){
				@Override
					public void actionPerformed(ActionEvent e)
					{ 
					
					//String Ip=IpAddTf1.getText();
						Integer Port=Integer.parseInt(PortAddTf2.getText());
						System.out.println(Port);
						if(flag==0){
							s=new Server(Port);
							s.setpath(path1);
							flag=1;
						}
						else{
						 s.closeall();
						s=new Server(Port);
						s.setpath(path1);
						flag=0;
						}
						Sendbt14.setEnabled(true);

				    }
			  });
			Sendbt14.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e)
				{ 
					s.getFileName();
				
			
				}
			    
		  });
		addWindowListener(this);

		setVisible(true);
		//End of Gui()

	}
		void setItemAction(int Eindex)
		{
		   for(int i=0;i<4;i++)
			EComboitem[i]=0;

			EComboitem[Eindex]=1;

			if (EComboitem[0]==1)
			{
			   System.out.println("None");
			   en=new Encryption();
			}
			if (EComboitem[1]==1)
			{
				System.out.println("Additive" );
				en=new Encryption();
				
			}
			if (EComboitem[2]==1)
			{  
				System.out.println("Multiplicative");
				en=new Encryption();
			}
			if (EComboitem[3]==1)
			{
				System.out.println("Combination");
				en=new Encryption();
			}
		}
	
	
		  
	 public void windowOpened(WindowEvent we1){}
	public void windowClosing(WindowEvent we1)
	{
		try {
			Server.closeall();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	public void windowClosed(WindowEvent we1){}
	public void windowIconified(WindowEvent we1){}
	public void windowDeiconified(WindowEvent we1){}
	public void windowActivated(WindowEvent we1){}
	public void windowDeactivated(WindowEvent we1){}
  /*	 
	public static void main(String[] args) throws Exception
	{
		 
		// new ServerGui();
		

	
		
	}
	*/

}

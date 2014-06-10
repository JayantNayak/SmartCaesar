package server;

import java.net.*;
import java.io.*;

import javax.swing.JFileChooser;
public class Server implements Runnable
{
  public static Socket s1=null;
  static ServerSocket ss=null;
  static PrintWriter pw=null;
  static FileInputStream fis =null;
	static FileOutputStream fos=null;
  static BufferedReader br=null;

  public static int flag=0;
  static String fname,recvfilename,paths;
  public static File filefname,recvfile,f1;
  public Server(int Port) 
	{
	  try{
	   ss= new ServerSocket(Port);
	   System.out.println("waiting");
	   s1=ss.accept();
	   pw=new PrintWriter(s1.getOutputStream(),false);
	   br=new BufferedReader(new InputStreamReader(s1.getInputStream()));
	   if (s1==null)
	   {
			     System.out.println("not connected");
				 System.exit(0);

	   }
	   System.out.println("connected");
	
	  }
	  catch(Exception e){
		  e.printStackTrace();
	  }
			Thread th1=new Thread(this);
			th1.start();
	  
  }
  @SuppressWarnings("deprecation")
public void run()
  {

		 while(true)
			{
				try
				{
					
						//recvfilename=br.readLine();
						//System.out.println(recvfilename);
						
						//recvfile=new File("d:\\SmartCaesar\\ClientFiles\\Received Files"+recvfilename);
					DataInputStream dis=new DataInputStream(s1.getInputStream());
					recvfilename=dis.readLine();
						fos = new FileOutputStream(paths+"\\SmartCaesar\\ServerFiles\\Received Files\\"+recvfilename);
						
						//System.out.println(br.readLine());
						//if(("101010").equals(br.readLine()))
						//{}
						System.out.println("recv file"+recvfilename);
							String data;
							
						//	System.out.println("near while"+br.readLine());
						 while ((data=br.readLine())!=null)
						 {
							 byte b[]=data.getBytes();
							 fos.write(b,0,b.length);
						 }
						
						
				}
				catch (Exception e)

				{
					e.printStackTrace();

				}
			}
  }
  
  	public static void getFileName(){
		JFileChooser fc= new JFileChooser();
		if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) 
		System.out.println("File Browse");
		
	 filefname=fc.getSelectedFile ( );
	 fname=filefname.getName();
	 System.out.println("server 1"+fname);
	 
	 /*  System.out.println("enter the file name");
	   br=new BufferedReader(new InputStreamReader(System.in));
	   String fname=br.readLine();*/
	   File f1=filefname;
	 try{
		// while(flag!=1){}
	   if (!f1.exists())
	   {
		   System.out.println("file not found");
		   System.exit(0);
	   }
	 
	   fis	=new FileInputStream(f1);
	   int ch;
	   System.out.println("server 2"+fname);
	   pw.println(fname);
	   //pw.println("101010");
	   while ((ch=fis.read())!=-1)
	   {
		   pw.write(ch);
		   pw.flush();
	   }

	 }
	 catch(Exception e){
		  e.printStackTrace();
	 }
  	}
	
	 public static  void closeall() {
		 try{
		pw.close();
		fis.close();
		fos.close();
		s1.close();
	   	}
		 catch (Exception e){
		 e.printStackTrace();
		 }
		 }
	 public static void setpath(String path)
	 {
		 paths=path;
		 
	 }
}

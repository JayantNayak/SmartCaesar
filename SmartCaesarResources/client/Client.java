package client;
import java.io.*;
import java.net.*;
import java.util.Date;

import javax.swing.JFileChooser;
public class Client implements Runnable
{
	static Socket s1=null;
	static BufferedReader br=null;
	 static FileInputStream fis =null;
	static FileOutputStream fos=null;
	 static PrintWriter pw=null;
	  public static int flag=0;
	 static String recvfilename, fname,pathc;
	 public static File filefname, recvfile,f1;
	 
	 public Client(String ipaddr,int port) 
	{	
		 
		 try{
		 InetAddress ip= InetAddress.getByName(ipaddr);
		 s1=new Socket(ip,port);
		 br = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			pw=new PrintWriter(s1.getOutputStream(),false);
		}
		catch(Exception e)
		{
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
						fos = new FileOutputStream(pathc+"\\SmartCaesar\\ClientFiles\\Received Files\\"+recvfilename);
						
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
	 public static void getFileName()
	 {
			JFileChooser fc= new JFileChooser();
			if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) 
			System.out.println("File Browse");
			
			 filefname=fc.getSelectedFile ( );
			 fname=filefname.getName();
			 System.out.println("server 1"+fname);
			 File f1=filefname;
			try{
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
	public static void	closeall()
		{ 
		try{
			pw.close();
			fis.close();
			fos.close();
		 s1.close();
	 }
		catch(Exception e){
			e.printStackTrace();
			}
		}
	public static void setpath(String path)
	 {
		 pathc=path;
		 
	 }
}

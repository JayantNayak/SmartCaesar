package timepack;


import java.util.Scanner;
public class time{
	public long curnttime,ts,ss,tm,mm,th,hh;
	public time(){
		 curnttime=System.currentTimeMillis();
			
			
	

		
	}
	
	
	public String gettime(time ob1,time ob2)
	{
		
		ts=(ob2.curnttime-ob1.curnttime)/1000;
		ss=ts%60;
		tm=ts/60 ;
		mm=tm%60;
		th=tm/60 ;//+ offset;
		 hh=th%24;
			
				return ( " "+(hh)+":"+(mm)+":"+(ss) );
				//return(" "+(ob2.ts-ob1.ts));
	}
}


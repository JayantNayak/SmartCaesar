package encrypt;

public class Encryption extends MasterEncryption
{
	static char[] ptext={'a','b','c','d','e','f','g','h',' ',',','.','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	static int v1,v2,i,j;
	static char ch1;
	public Encryption()
	{
	}
	public  char encrypt(char s)
	{
			for(j=0;j<29;j++)
			{
				if(s=='\n')
					return s;
				else if(s==ptext[j])
				{
					
					v1=j;break;
				}
			}
			int v2=(v1+1)%29;
		return ptext[v2];
	}
	
	
	
	
	public  char decrypt(char s)
	{
			for(j=0;j<29;j++)
			{
				if(s=='\n')
					return s;
				else if(s==ptext[j])
				{
					v1=j;break;
					}
			}
			int v2=(v1-1)%29;
			if(v2<0)
				v2+=29;
		
		return ptext[v2];
	}
}
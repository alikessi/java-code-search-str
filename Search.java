
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
public class Search {

	public static File[] finder( String dirName,String extension){
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() { 
			public boolean accept(File dir, String filename)
			{ return filename.endsWith("."+extension); }
		} );

	}

	public static boolean readFile(String path, String word) throws IOException{
		{ 

			FileReader fr =  new FileReader(path); 

			int i; 
			int k=1;
			
			boolean flag=false;
			//System.out.println(word.length());
			while ((i=fr.read()) != -1) {
				//ystem.out.print((char) i); 
				if(((char) i)==word.charAt(0)&&flag==false)
				{
					flag=true;
					
					for(int j=1;j<word.length();j++){
						i=fr.read();
						if(((char) i)!=word.charAt(j))
						{

							flag=false;
							break;
						}

					}

				}
			}
			return flag;
		} 

	}

	/**public static void read(String path) throws IOException
	{
		// pass the path to the file as a parameter 
		FileReader fr = 
				new FileReader(path); 

		int i; 
		while ((i=fr.read()) != -1) 
			System.out.print((char) i); 
	} 
	 **/
	public static void main (String[] args) throws IOException {
		final String dir = System.getProperty("user.dir");

		int len=args.length-1;
		String str="";
		int count=0;
		if(len<1){
			System.out.println("USAGE: java search [ext] [text]");
		}
		else
		{
			if (len>1){
				str=args[1];
				for(int i=2;i<len+1;i++)
					str=str+" "+args[i];
			}
			else str=args[1];
			File[] files=finder(dir,args[0]).clone();

			for(int i=0;i<files.length;i++){
			
				if(readFile(dir+"\\"+files[i].getAbsolutePath().substring(files[i].getAbsolutePath().lastIndexOf("\\")+1),str))
				{
					System.out.println(dir+"\\"+files[i].getAbsolutePath().substring(files[i].getAbsolutePath().lastIndexOf("\\")+1));
					count++;
				}
			}
			if (count==0) System.out.println("No files was found");

		}
	}
}



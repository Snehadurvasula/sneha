
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class Work2 {
	
	private static int new_revno;
	public static void main(String[] args) throws IOException
    {	
		BufferedReader br = null;
		BufferedReader br2 = null;
		BufferedWriter bw = null;
		BufferedWriter bw2 = null;
		String pgmpath = null;
		String srcpath = null;
		String destpath = null;
		Scanner in = new Scanner(System.in);
		System.out.println("Enter 0 for I drive Test program and 1 for custom Test program");
		int choice = Integer.parseInt(in.nextLine());
		if(choice == 0)
		{
			srcpath = "I:/cmtprogs/";
		}
		else
		{	
			srcpath = "L:/sdurvasu/"; 
		}
			System.out.println("Enter the Product name:");
		String pro_name = in.nextLine();
		 System.out.println("Product name is:"+pro_name);
		 System.out.println("Enter Program Name");
		 String prog_name = in.nextLine();
		 System.out.println("Program name is:"+prog_name);
        srcpath = srcpath+pro_name+"/"+prog_name;
    	System.out.println(srcpath);
    	if(choice == 0)
    	{
    	File dir = new File("L:/sdurvasu/"+pro_name);
    	if(!dir.exists())
    	dir.mkdir();
    	 String temp2[] = new String[3];
		 temp2 = prog_name.split("_");
		
		 if(temp2[0].charAt(temp2[0].length()-2) != 'r')
		 {
			 prog_name =temp2[0]+"r0" +"_"+temp2[1];
		 }

        destpath = "L:/sdurvasu/"+pro_name+"/"+prog_name;
        System.out.println(destpath);
    	}
    	else if(choice == 1)
    	{
    		 String temp3[] = new String[3];
    		 temp3 = prog_name.split("_");	
    		 int revno = Character.getNumericValue(temp3[0].charAt(temp3[0].length()-1));
    		 new_revno =  revno+1;
               temp3[0] =  temp3[0].substring(0, temp3[0].length() - 1);
    		 prog_name = temp3[0]+String.valueOf(new_revno)+"_"+temp3[1];
    		 
    		   System.out.println("Changed program name is:"+prog_name);
    		   destpath = "L:/sdurvasu/"+pro_name+"/"+prog_name;
    		   System.out.println(destpath);
    	         
    	}
    	
       File srcFolder = new File(srcpath);
    	File destFolder = new File(destpath);
    	in.close();
    	if(!srcFolder.exists()){
    		 
            System.out.println("Directory does not exist.");
            //just exit
            System.exit(0);
  
         }else{
  
            try{
         	copyFolder(srcFolder,destFolder);
            }catch(IOException e){
         	e.printStackTrace();
         	//error, just exit
                 System.exit(0);
            }
         } 
  
     	System.out.println("Done"); 
        if("hsu".equals(pro_name))
        {
        	 pgmpath = destpath+"/TPL/hsu_bx_class/Base/Base_Input_Files/pgm_rules.txt";
        }
        else if("bdu".equals(pro_name))
        {
        pgmpath = destpath+"/"+"TPL"+"/"+"pgm_rules.txt";
        }
        System.out.println(pgmpath);
     	String line = null;
     	String temp[] = new String[5];
        try
        {
        	br = new BufferedReader(new FileReader(pgmpath));
        	File file = new File(pgmpath);
        	File file2 = new File(destpath+"/"+"TPL"+"/"+"dummy_rules.txt");
        	File file3 = new File(destpath+"/"+"TPL"+"/"+"change_list.txt");
        	bw2 = new BufferedWriter(new FileWriter(file2));
        	File file1 = new File(pgmpath);
          while ((line = br.readLine()) != null)
        	{
        		if(!line.contains("iCGL_TpAltName"))
        		{
        			bw2.write(line);
        			bw2.newLine();
        			
        		}
        	    else
        		{
                   temp = line.split("\"");
                   System.out.println("Val is:"+temp[1].charAt((temp[1].length())-4));
                   System.out.println(temp[1].length());
                   System.out.println(temp[1]+"Hi");
                  
                   String s = Character.toString(temp[1].charAt((temp[1].length())-4));
                   
                 
                   String str = temp[1].substring((temp[1].length())-4);
                   System.out.println(str);
                  
                   System.out.println(line);
        		 
				if(s.equals("S"))
				{
					
					
                     line = line.replace(str,"rvro") ;
        		     bw2.write(line);
        		     bw2.newLine();
				}
				else if(s.equals("r"))
				{
					
					line = line.replace(temp[1].substring(temp[1].length() - 1), Integer.toString(new_revno));
					
					bw2.write(line);
					bw2.newLine();
				}
        		}
        		
        			
        	}
          FileWriter fw = null;
          PrintWriter pw = null;
            try {
         	   fw = new FileWriter(file3, true);

                pw = new PrintWriter(fw);

            pw.write(temp[1]);
            pw.write("\n");
                pw.close();
                fw.close();
            } catch (IOException ex) {
            }
          bw2.close();
          
          FileInputStream streamTemp1 = new FileInputStream( file2);
          FileChannel channel1 = streamTemp1.getChannel();
          FileOutputStream outputStream = new FileOutputStream(file);
          FileChannel channel2 = outputStream.getChannel();
          channel1.transferTo(0, file2.length(), channel2);
          streamTemp1.close();
          channel1.close();
          file2.delete();
      	br = new BufferedReader(new FileReader(pgmpath));

                  
          while ((line = br.readLine()) != null)
      	{
      		if(line.contains("iCGL_TpAltName"))
      		
      		{
                 temp = line.split("\"");
                 System.out.println("Val is:"+temp[1].charAt((temp[1].length())-4));
                 System.out.println(temp[1].length());
                 System.out.println(temp[1]+"Hi");
                 
        } 
      	}
          System.out.println(("After change:"+temp[1]));

          FileWriter fw2 = null;
          PrintWriter pw2 = null;
            try {
         	   fw2 = new FileWriter(file3, true);

                pw2 = new PrintWriter(fw2);

            pw.write(temp[1]);
            pw.write("\n");
                pw.close();
                fw.close();
            } catch (IOException ex) {
            }
        }
        catch (Exception ex) {
        	  System.out.println(ex.getMessage());
        	} 
     }
	public static void copyFolder(File src, File dest)
	    	throws IOException{
	 
	    	if(src.isDirectory()){
	 
	    		//if directory not exists, create it
	    		if(!dest.exists()){
	    		   dest.mkdir();
	    		   System.out.println("Directory copied from " 
	                              + src + "  to " + dest);
	    		}
	 
	    		//list all the directory contents
	    		String files[] = src.list();
	 
	    		for (String file : files) {
	    		   //construct the src and dest file structure
	    		   File srcFile = new File(src, file);
	    		   File destFile = new File(dest, file);
	    		   //recursive copy
	    		   copyFolder(srcFile,destFile);
	    		}
	 
	    	}else{
	    		//if file, then copy it
	    		//Use bytes stream to support all file types
	    		InputStream in = new FileInputStream(src);
	    	        OutputStream out = new FileOutputStream(dest); 
	 
	    	        byte[] buffer = new byte[1024];
	 
	    	        int length;
	    	        //copy the file content in bytes 
	    	        while ((length = in.read(buffer)) > 0){
	    	    	   out.write(buffer, 0, length);
	    	        }
	 
	    	        in.close();
	    	        out.close();
	    	      
	    	}
	}
	    
	  
}

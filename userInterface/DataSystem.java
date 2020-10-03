package userInterface;

import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sun.jna.platform.win32.Advapi32Util;

public class DataSystem extends Thread{
    String result;
	@Override
	public void run() {
		result = this.getSpecifications();
	}
	
	public String getResult() {return result;}

	public String getSpecifications() {
		String specs="<html>";
		
		        
		        specs += Advapi32Util.registryGetStringValue(HKEY_LOCAL_MACHINE,"HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\\","ProcessorNameString")+"<br>";
		        specs +=Advapi32Util.registryGetStringValue(HKEY_LOCAL_MACHINE, "HARDWARE\\DESCRIPTION\\System\\CentralProcessor\\0\\","Identifier") +"<br>";
		        
		     
		        
		        Runtime rt; 
		        Process pr; 
		        BufferedReader in;
		        String line = "";
		        String fullOSName = "";
		        String laptopModelName="";
		        String laptopManufacturer="";
		        String ramSize="";
		        String fullOwnerName="";
		        int ram=0;
		        StringBuilder sb;
		        final String LAPTOP_MANUFACTURER = "System Manufacturer:";
		        final String WINDOWS_TYPE = "OS Name:";
		        final String LAPTOP_MODEL = "System Model:";
		        final String RAM_SIZE="Total Physical Memory:";
		        final String RGISTERED_OWNER="Registered Owner:";

		        try
		        {
		           rt = Runtime.getRuntime();
		           pr = rt.exec("SYSTEMINFO");
		           in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		           
		          
		           while((line=in.readLine()) != null){
		        	//   System.out.println(line);
		        	   if(line.contains(WINDOWS_TYPE)) {
		                 fullOSName = line.substring(line.lastIndexOf(WINDOWS_TYPE) 
		                 + WINDOWS_TYPE.length(), line.length());
		              } 
		              
		        	   if(line.contains(RGISTERED_OWNER)) {
			                 fullOwnerName = line.substring(line.lastIndexOf(RGISTERED_OWNER) 
			                 + RGISTERED_OWNER.length(), line.length());
			              } 
		              
		              if(line.contains(LAPTOP_MODEL)){
		                 laptopModelName = line.substring(line.lastIndexOf(LAPTOP_MODEL) 
		                 + LAPTOP_MODEL.length(), line.length());
		              } 
		              
		              
		              if(line.contains(LAPTOP_MANUFACTURER)){
		            	  laptopManufacturer = line.substring(line.lastIndexOf(LAPTOP_MANUFACTURER) 
		                  + LAPTOP_MANUFACTURER.length(), line.length());
		               } 
		              
		              
		              if(line.contains(RAM_SIZE)){
		            	  ramSize = line.substring(line.lastIndexOf(RAM_SIZE) 
		                  + RAM_SIZE.length(), line.length());
		            	  
		            	  sb = new StringBuilder(ramSize.trim());
		            	  sb.deleteCharAt(sb.length()-1);
		            	  sb.deleteCharAt(sb.length()-1);
		            	  sb.deleteCharAt(sb.length()-1);
		            	  for(int i=0;  i<sb.length(); i++) {
		            		  if(sb.charAt(i)==',') sb.deleteCharAt(i);
		            	  }

		            	  ramSize = sb.toString();
		            	  ram = Integer.parseInt(ramSize);
		               } 
		           }  
		           

		           specs += "<br>Windows version:   " + fullOSName.trim() + "<br>";
		           specs += "RAM size:    " + Math.ceil((ram/Math.pow(2, 10)))+" GB<br>";
		           specs += "Manufacturer:    " + laptopManufacturer.trim() +"<br>";
		           specs += "Model:    " + laptopModelName.trim()+"<br>";
		           specs += "Registered Owner:   "+fullOwnerName.trim()+"</html>";
		        }
		           catch(IOException ioe)      
		           {   
		              System.err.println(ioe.getMessage());
		           }
		        
		        
		        return specs;
	}
}

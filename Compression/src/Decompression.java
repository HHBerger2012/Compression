import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.IIOException;

public class Decompression {
	static CustomLinkedList[] words = new CustomLinkedList[101];
	static long startTime = 0;
	static double totalTime = 0;
	public static void main(String[] args) throws IOException {
		startTime = System.nanoTime();
		DataInputStream in = null;
		try {
			in = new DataInputStream(new FileInputStream(args[0]));
		}catch(FileNotFoundException e){
			
		}
		
		
		for (int i=0;i<=100;i++)
        {
        	if (words[i]==null)
        	{
        		words[i] = new CustomLinkedList();
        	}
        }
		short keyCounter = 93;
		for(int i = 32; i < 126; i++){
			char current = (char)i;
	        	Dictionary currentDict = new Dictionary(String.valueOf(current), (short)(i - 32));
	        	int index = getValue(String.valueOf(current));
	        	//System.out.println(index);
	        	words[index].add(currentDict);
	        }
		char current1 = (char)10;
		Dictionary currentDict1 = new Dictionary(String.valueOf(current1), (short)93);
		int index = getValue(String.valueOf(current1));
		words[index].add(currentDict1);
		
		
		
		
		//BufferedWriter Stream1 = new BufferedWriter(new FileWriter(new File(args[0] + ".copy.text")));
		
//		FileWriter fileWriter = new FileWriter(new File("decompressed.text"));
//	    PrintWriter printWriter = new PrintWriter(fileWriter);
		DataOutputStream out = null;
		try {
			//FileReader test = new FileReader(args[0]+ ".zzz");
			out = new DataOutputStream(new FileOutputStream(args[0] + ".copy.text"));
		}catch(FileNotFoundException e){
			
		}
		
		boolean endOfFile = false;
		Dictionary currentDict = null;
		Short currentCode = 0;
		Short pastCode = 0;
		String currentKey = "";
		int i = 0;
		while (!endOfFile)
	      {
	           try
	           {
	        	   pastCode = currentCode;
	        	   currentCode = in.readShort();
	               currentKey = containsCodeOf(currentCode);
	        	   
	        	   if (i == 0) {
	        		   
	        		   out.writeUTF(currentKey);
	            	   ++i;
	               }else {
	            	   
	        	   
	        	   
	        	   
	               
	        	   //System.out.print(currentCode + " ");
	                
	        	   if (!(currentKey.equals("-1"))) {
	        		   ++keyCounter;
	        		   out.writeUTF(currentKey);// + " had it"
	        		   currentDict = new Dictionary((containsCodeOf(pastCode) + currentKey.charAt(0)), keyCounter );
	        		  // System.out.println(" " + currentDict.getCode() + " " + currentDict.getKey());
	        		   
	        		   words[getValue(containsCodeOf(pastCode))].add(currentDict);
	        	   }else {
	        		   
	        		   out.writeUTF(containsCodeOf(pastCode) + containsCodeOf(pastCode).charAt(0) );//+ " didnt have"
	        		   currentDict = new Dictionary((containsCodeOf(pastCode) + containsCodeOf(pastCode).charAt(0)), currentCode);
	        		   //System.out.println(" " + currentDict.getCode() + " " + currentDict.getKey());
	        	   }
	                
	                
	                
	                
	               		}
	        	   //Stream.close();
	           }
	           catch (EOFException e)
	           {
	                endOfFile = true;
	           }
	           
	      }
		totalTime =  ((System.nanoTime() - startTime)/1000000000.0);
        doLog(args[0]);

	}
	
public static String containsCodeOf(short x) {
		for(int i = 0; i < 101; ++i) {
			if (!(words[i].contains(x).equals("-1"))) {
				return words[i].contains(x);
			}
		}
		return "-1";
	}
public static int getValue(String x){
	int total = 0;
	for(int i = 0; i < x.length(); ++i) {
		total += x.charAt(i);
	}
	
	
	return total % 101;
	
	
	
}
public static void doLog(String x) throws IOException {
	String current = new java.io.File("").getCanonicalPath();
	//System.out.print(current);
	String logFile = ( x + ".copy.txt.log");
	try {
		
		
		
		BufferedWriter Stream = new BufferedWriter(new FileWriter(new File(logFile)));
		
		Stream.write("Compression of " + x);
		Stream.newLine();
		
		Stream.write("Compression took " + totalTime + " seconds");
		Stream.newLine();
		
		
		
		
		Stream.write("The tabel was doubled " + 0 + " times");
		Stream.close();
		System.out.println("The decompressed file is named " + x + ".copy.txt");
		System.out.println("The logfile is named " + x + ".copy.txt.log");
		
	}catch(IIOException exc) {
		System.exit(1);
	}catch(Exception e) {
		System.exit(1);
	}

	
			
}

}

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

import javax.imageio.IIOException;

public class Decompression {
	
	static CustomLinkedList[] words = new CustomLinkedList[101];
	
	public static void main(String[] args) throws IOException {
		
		DataInputStream in = null;
		
		try {
			in = new DataInputStream(new FileInputStream("output.dat"));
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
	        	words[index].add(currentDict);
	        }
		
		char current1 = (char)10;
		Dictionary currentDict1 = new Dictionary(String.valueOf(current1), (short)93);
		int index = getValue(String.valueOf(current1));
		words[index].add(currentDict1);
		
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
	            	   System.out.print(currentKey);
	            	   ++i;
	               }else {
	        	   if (!(currentKey.equals("-1"))) {
	        		   ++keyCounter;
	        		   System.out.print(currentKey);// + " had it"
	        		   currentDict = new Dictionary((containsCodeOf(pastCode) + currentKey.charAt(0)), keyCounter );
	        		   words[getValue(containsCodeOf(pastCode))].add(currentDict);
	        	   }else {
	        		   System.out.print(containsCodeOf(pastCode) + containsCodeOf(pastCode).charAt(0) );//+ " didnt have"
	        		   currentDict = new Dictionary((containsCodeOf(pastCode) + containsCodeOf(pastCode).charAt(0)), currentCode);
	        	   }
	               		}
	           }
	           catch (EOFException e)
	           {
	                endOfFile = true;
	           }          
	      }
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
}

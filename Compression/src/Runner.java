import java.io.File;
import java.io.FileNotFoundException;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.util.Scanner;
import java.util.concurrent.*;

import javax.imageio.IIOException; 

public class Runner{
	
	private static int tabelSize = 101;
	static CustomHashSet set = new CustomHashSet(101);
	static File inputFile = null;
	static File outPutFile = null;
	static long startTime = 0;
	static double totalTime = 0;
	static double timesHashed = 0;
	private static int currentPrime = 0;
	private static int[] primes = {101,911,1871, 5501,7879};
	
	public static void main(String[] args) throws IOException {
		
		startTime = System.nanoTime();
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(new FileOutputStream("output.dat"));
		}catch(FileNotFoundException e){
			
		}		

		short keyCounter = 93;
		
		Scanner file = null;
		inputFile = new File(args[0]);
		outPutFile = new File("output.dat");
        try {
			file = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			System.out.println("The file was not found");
		}
        
        String FileString = "";
        String code = "";
        
        while ( file.hasNextLine() ){
        	FileString += (file.nextLine() + "\n");
        }
        
        int i = 0;
        String currentPiece = String.valueOf(FileString.charAt(0));
        String pastPiece = "";
        short currentKey = 0;
        
        while(i < FileString.length()){
        	while (set.arrayContains(currentPiece)) {
        		currentKey = set.keyOf(currentPiece);
        		pastPiece = currentPiece;
        		++i;
	        	if(i < FileString.length()){
	        		currentPiece += String.valueOf(FileString.charAt(i));
	        	}else 
	        		break;
        	}
        	
        	++keyCounter;
        	set.add(currentPiece, keyCounter);
        	
        	if(set.howFull() > 70.0) {
        		rehash();
        		++timesHashed;
        	}
        	
        	out.writeShort(set.keyOf(pastPiece));
        	System.out.print(set.keyOf(pastPiece) + " ");
        	if(i < FileString.length()){
        		currentPiece = String.valueOf(FileString.charAt(i));
        	}else 
        		break;
        	
        	
        	
        }

        totalTime =  ((System.nanoTime() - startTime)/1000000000.0);
        doLog(args[0]);

	}
	public static int getValue(String x){
		int total = 0;
		for(int i = 0; i < x.length(); ++i) {
			total += x.charAt(i);
		}
		
		
		return total % 101;
		
		
		
	}
	
	public static void rehash()	{
		
		currentPrime = currentPrime + 1;
		tabelSize = primes[currentPrime];
		System.out.println(tabelSize);
		CustomHashSet temp = new CustomHashSet(tabelSize);
	  for(int r=0;r<set.getwords().length;++r)
	  {
	  	
	  	for (int k=0;k<set.getwords()[r].size();k++)
	  	{
	  		temp.add(set.getwords()[r].get(k).getCode(),set.getwords()[r].get(k).getKey());
	  	}
	  }
	  set = temp;	
		
	}
	
	public static void doLog(String x) throws IOException {
		String current = new java.io.File("").getCanonicalPath();
		String logFile = ( x + ".zzz.log");
		try {
			BufferedWriter Stream = new BufferedWriter(new FileWriter(new File(logFile)));
			Stream.write("Compression of " + x);
			Stream.newLine();
			Stream.write("Compressed from " + inputFile.length() + " kilobytes to " + outPutFile.length() + " kilobytes");
			Stream.newLine();
			Stream.write("Compression took " + totalTime + " seconds");
			Stream.newLine();
			Stream.write("Hash Tabel is " + set.howFull() + " percent full");
			Stream.newLine();
			Stream.write("The average Lined list is " + set.averageLength() + " elements long");
			Stream.newLine();
			Stream.write("The longest linked list is " + set.longestLength() + " elements long");
			Stream.newLine();
			Stream.write("The dictionary contains " + set.total() + " total elements");
			Stream.newLine();
			Stream.write("The tabel was rehashed " + timesHashed + " times");
			Stream.close();
			
		}catch(IIOException exc) {
			System.exit(1);
		}catch(Exception e) {
			System.exit(1);
		}			
	}	
}

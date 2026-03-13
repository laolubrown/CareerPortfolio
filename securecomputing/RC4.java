package securecomputing;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class RC4 {
    
    public static void rc(int[] S, int[] T, int[] K){
        for (int i = 0; i < 256; i++){
        S[i] = i;
        T[i] = K[i % K.length];
        }
    }
      
    public static int[] rc2(int[] S, int number){
    	
        int[] mikasa = new int[number];
        
        int i = 0;
        int j = 0;
        int count = 0;
        while(count < number) {
        i = (i + 1) % 256;
        j = (j + S[i]) % 256;
        int temp = S[i];
        S[i] = S[j];
        S[j] = temp;
        int t = (S[i] + S[j]) % 256;
        
        mikasa[count] = S[t];
        
        count++;
        }
        return mikasa;
        }

    public static void rc3(int[] S, int[] T){
        int j = 0;
        for(int i = 0; i < 256; i++){
        j = (j + S[i] + T[i]) % 256;
        int temp = S[i];
        S[i] = S[j];
        S[j] = temp;
        }
    }
    
  public static void main(String[] args ) {
      //RC4 encryption
      try {
        Scanner eren = new Scanner (new File("C:\\Users\\Laolu\\241\\lab7\\src\\securecomputing\\input-1 (2).txt"));
        
        String [] key = eren.nextLine().split(" ");
        
        int[] K = new int[key.length];
        
        for(int i = 0; i < K.length; i++) {
        	
            K[i] = Integer.valueOf(key[i]);
            System.out.println(K[i]);      
        }
        
        String p = eren.nextLine();
        System.out.println(p);

        int[] S = new int[256]; 
        int[] T = new int[256];
        
        rc(S, T, K);
        
        rc3(S, T);
        
        int[] prn = rc2(S, p.length()/2);
        
        String c = "";
        for(int i = 0 ; i < p.length()/ 2; i++)
        {
            int x = Integer.valueOf(p.substring(2*i, 2*i+2), 16) ^ prn[i];
            
            c += String.format("%02x", x);
        }
            PrintStream ps = new PrintStream (new File("C:\\Users\\Laolu\\241\\lab7\\src\\securecomputing\\output-1 (2).txt"));
            
            
        eren = new Scanner (new File("C:\\Users\\Laolu\\241\\lab7\\src\\securecomputing\\input-1 (2).txt"));
        
        key = eren.nextLine().split(" ");
        
        K = new int[key.length];

        for(int i = 0; i < K.length; i++) {
        	
            K[i] = Integer.valueOf(key[i]);
            
            System.out.println(K[i]);      
        }
        
        eren.nextLine();
        
        String cipher = eren.nextLine();
        System.out.println(p);

        S = new int[256]; 
        
        T = new int[256]; 
        
        rc(S, T, K);
        
        rc3(S, T);
        
        prn = rc2(S, cipher.length()/2);
        
        String Z = "";
        for(int i = 0 ; i < cipher.length()/ 2; i++) {
        	
            int b = Integer.valueOf(cipher.substring(2*i, 2*i+2), 16) ^ prn[i];
            
            Z += String.format("%02x", b);
         
           
            ps = new PrintStream (new File("C:\\Users\\Laolu\\241\\lab7\\src\\securecomputing\\output-1 (2).txt"));

            ps.println(c);
            
            ps.println(Z);
        }
            }   catch (FileNotFoundException e) 
      {
                System.out.println("File can't be found");
                
                e.printStackTrace();
            } 
        }
    }
    

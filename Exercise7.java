
import java.util.*;
import java.io.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exercise.pkg7;

/**
 *
 * @author Acer
 */


class RandomThread extends Thread {
    protected int rounds;
    private Boolean same; // true for same-thread, false for diff-thread
    private int trials;
    
    //fuction print the outputfile
    private PrintWriter output_file;
    
    //function check if the data in array is same or not
    public static boolean areDistinct(Integer arr[]){ 
        
        Set<Integer> s =  new HashSet<Integer>(Arrays.asList(arr)); 
  
        return (s.size() == arr.length); 
    }
    
    
    public RandomThread(String n, Boolean b) { super(n); same = b; }
    
    @Override


 
    public void run() {
        
        
        boolean loopdif = true;
        boolean loopsame = true;
        
        //create output.txt file
        try
        {
            output_file = new PrintWriter (new File (this.getName() + ".txt"));
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        
        Random rand = new Random();
        
        
        
        if (same==true)
        {
            
            //different number trials until it has all diferrent number in array 
            while (loopdif)
            {
                trials++;
                
                //create array to get number
                Integer []numbers = new Integer [8];
                
                //print in out to the file.txt
                output_file.printf("Trial %d >> ",trials);
                
                //random 7 number in one line
                for (int i = 0 ; i<8 ; i++){
                    numbers[i] = rand.nextInt(9)+1;
                    output_file.printf("%d ",numbers[i]);
                }
                
                output_file.printf("\r\n");
                
                
                //fumction checking if this array data is all the same in number
                if (areDistinct(numbers)) {
                    loopdif = false; //get of while loop
                }
            } //when loop = false get off this while loop
            System.out.printf("%s >> %d trials\r\n",this.getName(),trials);
        }
        
        
        
        // same number case
        else
        {
            while (loopsame)
            {
                trials++;
                int r1 = rand.nextInt(9)+1;
                int r2 = rand.nextInt(9)+1;
                int r3 = rand.nextInt(9)+1;
                int r4 = rand.nextInt(9)+1;
                
                output_file.printf("Trial %d >> %d %d %d %d\r\n",trials,r1,r2,r3,r4);
                
                if (r1 == r2 && r2 == r3 && r3 == r4)
                {
                    loopsame = false; //get of while loop
                }
                
                //System.out.printf("%s thread has finished with a result of %d %d %d %d %d\r\n",this.getName(),r1,r2,r3,r4,r5);
            }
            System.out.printf("%s >> %d trials\r\n",this.getName(),trials);
            //when loop = false get off this while loop
        }
        output_file.close();
    }
}

public class Exercise7 {

    
    public static void main(String[] args) {
        
        
        System.out.print("Number of threads: ");
        //get scan_num from user
        Scanner scan_num = new Scanner (System.in);
        
        int num = scan_num.nextInt();
        
        //create Arraylist
        ArrayList <Thread> list = new ArrayList<Thread>();
        
        for (int i = 0; i < num; i++)
        {
            Thread temp;
            
            //if number = even ----> Same 
            if (i%2 == 0)
            {
                boolean same = true;
                String T_name = "T" + (i+1);
                //create Tread name T(i+1)
                temp = new RandomThread (T_name,same);
                list.add(temp);
                list.get(i).start();
            }
            
            //if number = odd ----> Diff
            else
            {
                boolean same = false;
                String T_name = "T" + (i+1);
                //create Tread bane T(i+1)
                temp = new RandomThread (T_name,same);
                list.add(temp);
                list.get(i).start();
            }
        }
    }
    
}

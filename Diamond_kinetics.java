/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diamond_kinetics;

/**
 *
 * @author Akshay
 */

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class Diamond_kinetics {

    /**
     * @param args the command line arguments
     */
    

    //Gyro is a Class representing the Seven Columns of the CSV file
    // HashMap that Stores the index of a row and the contents of the row in a class.
    // Hash Map insert, search O(1)
    static HashMap<Integer, gyro> map = new HashMap<Integer, gyro>(); 
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\Akshay\\Desktop\\latestSwing.csv")); // Read the values of CSV file 
        Scanner valueScanner = null;
        int index =0;
        int row =0; // represents each row's index.
        
        while (scanner.hasNextLine()) {
	    valueScanner = new Scanner(scanner.nextLine());
	    valueScanner.useDelimiter(","); //Splitting the values based on commas
	    gyro value = new gyro();
                           
                         // Each row the objects get initalized with the contents of the CSV File.
	    while (valueScanner.hasNext()) {
		String data = valueScanner.next();
		if (index == 0){
		    value.setTimeStamp(Integer.parseInt(data));
		}
		else if (index == 1){
		    value.setAx(Float.parseFloat(data));
		}
		else if (index == 2){
		    value.setAy(Float.parseFloat(data));
		}
                
                                            else if (index == 3){
		    value.setAz(Float.parseFloat(data));
		}
                
                                            else if (index == 4){
		    value.setWx(Float.parseFloat(data));
		}
                
                                            else if (index == 5){
		    value.setWy(Float.parseFloat(data));
		}
                
                                         else if (index == 6){
		    value.setWz(Float.parseFloat(data));
		}
                
                
		index++;
	    }
	    index = 0;
                            map.put(row, value); // (K, V) => index, Object of the class gyro
                            row++;
                        
                          
	}

	scanner.close();
        
        System.out.println("Choose the desired Function: \n 0) Quit  \n 1) searchContinuityAboveValue \n 2) backSearchContinuityWithinRange \n 3) searchContinuityAboveValueTwoSignals\n 4) searchMultiContinuityWithinRange");
        scanner = new Scanner(System.in);
        
        int choice = scanner.nextInt();
        
        while(choice != 0)
                {
                    
                    switch(choice)
                    {
                        case 1: 
                        {
                            System.out.println("Enter the column ID which you want to work on (0 to 6)");
                            int column = scanner.nextInt();
                            System.out.println("Enter the starting index: ");
                            int indexBegin = scanner.nextInt();
                              System.out.println("Enter the ending  index: ");
                            int indexEnd =scanner.nextInt();
                            System.out.println("Enter the Threshold  value: ");
                            float threshold = scanner.nextFloat();
                            System.out.println("Enter the Number of Rows: ");
                            int winLength = scanner.nextInt();
                            int res = searchContinuityAboveValue(column, indexBegin, indexEnd, threshold, winLength);
                            System.out.println("The starting index is : "+ res);
                            break;
                            
                        }
                        
                        
                        case 2:
                        {
                            System.out.println("Enter the column ID which you want to work on (0 to 6)");
                            int column = scanner.nextInt();
                            System.out.println("Enter the starting index: ");
                            int indexBegin = scanner.nextInt();
                            System.out.println("Enter the ending  index: ");
                            int indexEnd =scanner.nextInt();
                            System.out.println("Enter the Threshold low value: ");
                            float thresholdLo = scanner.nextFloat();
                            System.out.println("Enter the Threshold High value: ");
                            float thresholdHi = scanner.nextFloat();
                            System.out.println("Enter the Number of Rows: ");
                            int winLength = scanner.nextInt();
                            int res = backSearchContinuityWithinRange(column, indexBegin, indexEnd, thresholdLo, thresholdHi, winLength);
                            System.out.println("The starting index is : "+ res);
                            break;
                            
                        }
                        
                        case 3:
                        {
                            System.out.println("Enter the first  column ID which you want to work on (0 to 6)");
                            int column1= scanner.nextInt();
                              System.out.println("Enter the second  column ID which you want to work on (0 to 6)");
                            int column2= scanner.nextInt();
                            System.out.println("Enter the starting index: ");
                            int indexBegin = scanner.nextInt();
                            System.out.println("Enter the ending  index: ");
                            int indexEnd =scanner.nextInt();
                            System.out.println("Enter the Threshold low value: ");
                            float threshold1 = scanner.nextFloat();
                            System.out.println("Enter the Threshold High value: ");
                            float threshold2 = scanner.nextFloat();
                            System.out.println("Enter the Number of Rows: ");
                            int winLength = scanner.nextInt();
                            
                            int res = searchContinuityAboveValueTwoSignals(column1, column2, indexBegin, indexEnd, threshold1, threshold2, winLength);
                            System.out.println("The starting index is : "+ res);
                            break;
                        }
                        
                        
                          case 4: 
                        {
                            System.out.println("Enter the column ID which you want to work on (0 to 6)");
                            int column = scanner.nextInt();
                            System.out.println("Enter the starting index: ");
                            int indexBegin = scanner.nextInt();
                              System.out.println("Enter the ending  index: ");
                            int indexEnd =scanner.nextInt();
                            System.out.println("Enter the ThresholdLo  value: ");
                            float thresholdLo = scanner.nextFloat();
                            System.out.println("Enter the ThresholdHi  value: ");
                            float thresholdHi = scanner.nextFloat();
                            System.out.println("Enter the Number of Rows: ");
                            int winLength = scanner.nextInt();
                           HashMap<Integer, Integer> res = new HashMap<Integer, Integer>();
                            res = searchMultiContinuityWithinRange(column, indexBegin, indexEnd, thresholdLo, thresholdHi, winLength);
                            System.out.println("The list of indexes: "+ res);
                            break;
                            
                        }
                        
                                    
                        
                        
                        
                    }
                    System.out.println("Do you want to continue?");
                    System.out.println("Choose the desired Function: \n 0) Quit \n 1) searchContinuityAboveValue \n 2) backSearchContinuityWithinRange \n 3) searchContinuityAboveValueTwoSignals\n 4) searchMultiContinuityWithinRange");
                   
                    choice = scanner.nextInt();
                    
                    
                }
        
        
       
    }
    
    //Data will represent the Column index from 0 - 6 
    //searchContinuityAboveValue 
   static public int searchContinuityAboveValue(int data,int indexBegin, int indexEnd, double threshold, int winLength)
    {
        int start = indexBegin;
        int end = indexBegin;
        int count =0;
        
        while(start <= indexEnd && end <= indexEnd)
        {
            gyro curr =  map.get(end); // Get the value of the Current Index
     
                        float value=0;
            
            switch(data) // Get the value of the column chosen by the user
            {
                case 0 : value = curr.getTimeStamp();
                            break;
                case 1 : value = curr.getAx();
                            break;
                case 2 : value = curr.getAy();
                            break;
                
               case 3 : value = curr.getAz();
                            break;
              case 4 : value = curr.getWx();
                            break;
              case 5 : value = curr.getWy();
                            break;
               case 6 : value = curr.getWz();
                            
            }
            if(value  > threshold) //(Check if the current value exceeds the threshold)
            {
                end++;
                count++;
                
            }
            // If it does not then change the start index to the next value
            else
            {
                start = end+1;
                end = start;
                count =0;
            }
            
            if(count == winLength) // the number of max values is equal to the threshold return the staring point
                return start;
        }
        
        return -1; // The number of max values are less than the win Threshold retur -1 (Not Found).
    }
   
   
  static public int backSearchContinuityWithinRange(int data,int  indexBegin,int  indexEnd,double  thresholdLo, double thresholdHi, int winLength)
  {
      
       int start = indexBegin;
        int end = indexBegin;
        int count =0;
        
        while(start >=  indexEnd && end >= indexEnd)
        {
            gyro curr =  map.get(end); // Get the value of the Current Index
            float value=0;
            
            switch(data) // Get the value of the column chosen by the user
            {
                case 0 : value = curr.getTimeStamp();
                            break;
                case 1 : value = curr.getAx();
                            break;
                case 2 : value = curr.getAy();
                            break;
                
               case 3 : value = curr.getAz();
                            break;
              case 4 : value = curr.getWx();
                            break;
              case 5 : value = curr.getWy();
                            break;
               case 6 : value = curr.getWz();
                            
            }
            System.out.println(value);
            if(value >= thresholdLo && value <= thresholdHi) //(Check if the current value exceeds the thresholdlo and less than thresholdHi)
            {
                end--;
                count++;
                
            }
            // If it does not then change the start index to the next value
            else
            {
                start = end-1;
                end = start;
                count =0;
            }
            
            if(count == winLength) // the number of max values is equal to the threshold return the staring point
            {
              System.out.println(end);
                return start;
                
            }
        }
        
      return -1;
  }
  
  static public HashMap  searchMultiContinuityWithinRange(int data, int indexBegin, int  indexEnd,double thresholdLo, double thresholdHi,int  winLength)
  {
      int start = indexBegin;
        int end = indexBegin;
        int count =0;
        HashMap<Integer, Integer> ans = new HashMap<Integer, Integer>();
        
        while(start <= indexEnd && end <= indexEnd)
        {
            gyro curr =  map.get(end); // Get the value of the Current Index
     
                        float value=0;
            
            switch(data) // Get the value of the column chosen by the user
            {
                case 0 : value = curr.getTimeStamp();
                            break;
                case 1 : value = curr.getAx();
                            break;
                case 2 : value = curr.getAy();
                            break;
                
               case 3 : value = curr.getAz();
                            break;
              case 4 : value = curr.getWx();
                            break;
              case 5 : value = curr.getWy();
                            break;
               case 6 : value = curr.getWz();
                            
            }
            if(value >= thresholdLo && value <= thresholdHi) //(Check if the current value satisfies the condition)
            {
                end++;
                count++;
                
            }
            // If it does not then change the start index to the next value
            else
            {
                start = end+1;
                end = start;
                count =0;
            }
            
            if(count == winLength) // the number of max values is greater than thresholdLo and less than ThresholdHi add the start and end point to the hashmap.
            {
                ans.put(start, end);
                start++;
                count--;
                
            }
        }
        
        
        return ans; // Returns all possible combination of subarrays satisfiying the given range
      
  }
  
  
  static public int searchContinuityAboveValueTwoSignals(int data1, int data2, int indexBegin,int indexEnd,double  threshold1, double threshold2,int winLength){
      
       int start = indexBegin;
        int end = indexBegin;
        int count =0;
        
        while(start <= indexEnd && end <= indexEnd)
        {
            gyro curr =  map.get(end); // Get the value of the Current Index
     
                        float value1=0;
                        float value2=0;
            
            switch(data1) // Get the value of the column chosen by the user
            {
                case 0 : value1 = curr.getTimeStamp();
                            break;
                case 1 : value1 = curr.getAx();
                            break;
                case 2 : value1 = curr.getAy();
                            break;
                
               case 3 : value1 = curr.getAz();
                            break;
              case 4 : value1 = curr.getWx();
                            break;
              case 5 : value1 = curr.getWy();
                            break;
               case 6 : value1 = curr.getWz();
                            
            }
            
            switch(data2) // Get the value of the column chosen by the user
            {
                case 0 : value2 = curr.getTimeStamp();
                            break;
                case 1 : value2=  curr.getAx();
                            break;
                case 2 : value2 = curr.getAy();
                            break;
                
               case 3 : value2 = curr.getAz();
                            break;
              case 4 : value2 = curr.getWx();
                            break;
              case 5 : value2 = curr.getWy();
                            break;
               case 6 : value2 = curr.getWz();
                            
            }
            if(value1 >= threshold1 && value2 >= threshold2) //(Check if the current value exceeds the threshold)
            {
                end++;
                count++;
                
            }
            // If it does not then change the start index to the next value
            else
            {
                start = end+1;
                end = start;
                count =0;
            }
            
            if(count == winLength) // the number of max values is equal to the threshold return the staring point
                return start;
        }
        
        return -1; // The number of max values are less than the win Threshold retur -1 (Not Found).

  }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;
import com.sun.xml.internal.ws.util.StringUtils;
import java.text.*;
import javax.swing.*;
import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author hamza6348
 */
public class Interpreter {

    public static void Interpreter(String input, String output)//constructor
    {
        Map<String,String> details = new HashMap<>();//declaring a hashmap for storing all the variables
        
        
        
        try {//logic for reading atext file containing all the code to interpret
            ArrayList<String> l = new ArrayList<String>();
            String inputLine = null;
            String outputLine = null;
            String line = "";
            String lineOut = "";
            boolean pushFlag = false;
            
            FileWriter fileWriter = new FileWriter(output);   //opening write file stream
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);   //wrap fileWriter in buffer stream
            
            FileReader fileReader = new FileReader(input);
            BufferedReader bufferedReader = new BufferedReader(fileReader);// creating a buffered reader object

            while((line = bufferedReader.readLine()) != null)
            {   String[] word = line.split("\\s+");//splitting the lines
            
                
            
                String[] spld=word[0].split("");
                
            
            
            
            
                if(word[0].equals("Let"))// checking for variable declaration
                    
                {
                    
                    String[] ss=word[1].split("");
                    if(ss.length==1){
                    details.put(word[1],"0x1fff");
                    
                    
                    }
                    else if(ss.length==3){
                    details.put(ss[0],ss[2]);
                    
                        //System.out.println(details);
                    
                    }
                    
                    //String s=details.get(word[1]);
                    //System.out.println(s);
                  
                  
                }
                else if(word[0].equals("print")) //print statement
                {
                    
                  String print=details.get("x");
                  System.out.println(print);
                }
                else if(spld[1].equals("=")&&(spld.length==3)){
                    
                    String iinterm=details.get(spld[2]);
                    details.put(spld[0], iinterm);
                
                
                
                
                }
                else if( spld[1].equals("=")&&(spld.length>3)&&(spld[3].equals("+")))//addition
                {
                    
                    
                    int ass;
                    String result="";
                    String inta=details.get(spld[2]);
                    int right=Integer.parseInt(inta);
                    int left=Integer.parseInt(spld[4]);
                    ass=right+left;
                    //System.out.println(ass);
                    
                    result=Integer.toString(ass);
                    details.put(spld[0],result);
                    
                    //System.out.println(details);
                
                
                
                }
                
                
                 else if( spld[1].equals("=")&&(spld.length>3)&&(spld[3].equals("x")))//multiplication
                {
                    
                    
                    int ass1;
                    String result1="";
                    String inta1=details.get(spld[2]);
                    int right1=Integer.parseInt(inta1);
                    int left1=Integer.parseInt(spld[4]);
                    ass1=right1*left1;
                    //System.out.println(ass);
                    
                    result1=Integer.toString(ass1);
                    details.put(spld[0],result1);
                
                
                
                }
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                else if(l.size() == 0)
                {
                    l.add(":error:");
                    continue;
                }
                else if(word[0].equals(":error:"))
                    l.add(":error:");
                else if(word[0].equals("neg"))
                {
                    String x = l.get(l.size()-1);
                    if(x.equals("0"))
                        continue;
                    else if(isNumeric(x.substring(0,1)))
                        l.set((l.size()-1),("-" + l.get(l.size()-1)));
                    else
                        l.set((l.size()-1),x.substring(1, x.length()));                        
                }
                else if(word[0].equals("add") || word[0].equals("sub") || word[0].equals("mul") || word[0].equals("div") || word[0].equals("rem"))
                {
                    if(l.size() < 2)
                    {
                        l.add(":error:");
                        continue;
                    }
                    String x = l.get(l.size()-1);
                    String y = l.get(l.size()-2);
                    if(isNumeric(x.substring(0,1)))
                            {
                                
                            }
                    else if(x.substring(0,1).equals( "-"))
                            {
                                
                            }
                    else
                        {
                            l.add(":error:");
                            continue;
                        }
                    if(isNumeric(y.substring(0,1)))
                    {
                        
                    }
                    else if(y.substring(0,1).equals( "-"))
                    {
                        
                    }
                    else
                        {
                            l.add(":error:");
                            continue;
                        }
                    if(word[0].equals("add"))
                    {
                        int xa = Integer.parseInt(l.get(l.size()-2)) + Integer.parseInt(l.get(l.size()-1));
                        l.remove(l.size()-1);
                        l.remove(l.size()-1);
                        l.add(Integer.toString(xa));
                    }
                    if(word[0].equals("sub"))
                    {
                        
                        int xs = Integer.parseInt(l.get(l.size()-2)) - Integer.parseInt(l.get(l.size()-1));
                        l.remove(l.size()-1);
                        l.remove(l.size()-1);
                        l.add(Integer.toString(xs));
                    }
                
                    else if(word[0].equals("rem"))
                    {
                        int test = Integer.parseInt(l.get(l.size()-2));
                        if(test == 0)
                        {
                            l.add(":error:");
                            continue;
                        }
                        int xmo = Integer.parseInt(l.get(l.size()-2)) % Integer.parseInt(l.get(l.size()-1));
                        l.remove(l.size()-1);
                        l.remove(l.size()-1);
                        l.add(Integer.toString(xmo));
                    }
                }
                else if(word[0].equals("swap"))
                {
                    if(l.size() < 2)
                    {
                        l.add(":error:");
                        continue;
                    }
                    
                    String temp = new String(l.get(l.size()-1));
                    String temp1 = new String( l.get(l.size()-2));
                    l.remove(l.size()-1);
                    l.remove(l.size()-1);
                    l.add(temp);
                    l.add(temp1);
                }
                
                else if(!(word[0].equals("quit")))
                {
                    l.add(":error:");
                    continue;
                }
                
            }
                
            
            
            
            
            
            
            
            
            /////////WRITE TO FILE//////////
            while(l.size() > 0)
            {
                if(l.get(l.size()-1) == "quit")
                    System.exit(0);
                System.out.println(l.get(l.size() - 1));
                bufferedWriter.write(l.get(l.size()-1) + "\n");
                l.remove(l.size() - 1);
            }
            bufferedWriter.close();
            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
        catch(IOException ex) {
            System.out.println("Error: " + ex);
        }
        
    }
    
    public static boolean isNumeric(String str)  
    {  
        try  
        {  
            int i = Integer.parseInt(str);  
        } 
            catch(NumberFormatException e)  
        {  
            return false;  
        }  
            return true;  
    }
    
    
    
    public static void main(String[] args) {
        Interpreter("input.txt","output.txt");
    }
    
}
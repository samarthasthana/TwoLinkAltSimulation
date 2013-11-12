/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package altsim;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Sam
 */
public class AltSim {

    /**
     * @param args the command line arguments
     */
    
 public double genExpo(){
        double val=0.0;
        Random rnd =new Random();
        return rnd.nextDouble();

    }
    public static void main(String[] args) {
        AltSim obj=new AltSim();
        double Lp=0.0;
        Scanner scan= new Scanner(System.in);        
        Integer mVal,choice,total=0,pass=0;
        double initVal=0.0,stepVal=0.0;        
        System.out.println("Enter the number of calls to simulate");
        total=scan.nextInt(); 
        System.out.println("Enter the value of m");
        mVal=scan.nextInt();// can be used to set the value of number of overflow paths 
        //mVal=10;
        double[][] BsArr=new double[mVal][2]; // initialize the Bs array for the required dimensions
        double [] Pm= new double [mVal+2];
        System.out.println("Enter 1 to define constant Bs value, 2 to define a gradually dec Bs value");
        choice=scan.nextInt();
        if(choice==1){
            System.out.println("Enter the constant Bs value for all links");
            initVal=scan.nextDouble();
            for(int i=0;i<mVal;i++){
                for(int j=0;j<2;j++){
                BsArr[i][j]=initVal;
                }            
            }
            BsArr[0][1]=0;// as there is no second link in the primary path
        }else{
            if(choice==2){
                System.out.println("Enter the initial value of Bs");
                initVal=scan.nextDouble();
                System.out.println("Enter the decreasing step value");
                stepVal=scan.nextDouble();
                if(stepVal*(double)((2*mVal)-1)> initVal){ System.out.println(" Bs value cannot be neg, illegal step value entered");
                return ;
                }
                BsArr[0][0]=initVal;
                BsArr[0][1]=0;
                int k=1;
                for(int i=1;i<mVal;i++){
                    for(int j=0;j<2;j++){
                    BsArr[i][j]=initVal-(stepVal*(k));
                    k++;
                    }            
                }
            }
        }
        
     for(int i=0;i<mVal;i++){
         for(int j=0;j<2;j++){
         System.out.printf("B %d %d = %f \n",i,j,BsArr[i][j]);
         }
     }
     for(int j=0;j<total;j++){ // to run for all the required numberof calls 
         for(int i=0;i<mVal;i++){ // to run for all mVal values              
            if(obj.genExpo() > BsArr[i][0] && obj.genExpo() > BsArr[i][1]){
                pass++;
                break;
            }             
         }
         
     }
     Lp=(1.0- (pass.doubleValue()/total.doubleValue()));
     System.out.printf("Number of calls carried = %d \n Loss Prob = %f \n",pass,Lp);
     
    }// void main 
    
}

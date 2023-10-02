import java.io.File; 
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.Math;
public class Polynomial{

    double[] coef;
    int[] expo;
    public Polynomial(){
    	this.coef = new double[0];
        }
    public Polynomial(double[] given){
    	this.coef  = new double[given.length];
        for (int i =0; i<given.length;i++) {
        	this.coef[i]=given[i];
        }
    }
    public Polynomial(double[] given, int[] exp){

    	this.coef  = new double[given.length];
    	this.expo = new int[exp.length];
    	for (int i =0; i<given.length;i++) {
    		this.coef[i]=given[i];
            }
    	for (int i =0; i<exp.length;i++) {
    		this.expo[i]=exp[i];
            }
        }
    public Polynomial(String file) {
    	//parseInt - chnages string to int
    	//parseDouble - chnages string to double (adds a .0 if the given string is an int)
    	//split plits at the given expressions but it doesn't keep the splitter (we would lose signs... how to deal with that)
		
		String strPoly="";
		double tmpCoef;
		int tmpExpo;
		ArrayList<Double> resultCoef = new ArrayList<Double>();
    	ArrayList<Integer> resultExpo = new ArrayList<Integer>();
		
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              strPoly = myReader.nextLine();
              System.out.println(strPoly);
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
		  
		  String tmpString="";
		  for (int i = 0; i < strPoly.length(); i++) {
			if ((strPoly.charAt(i)== '-') || (strPoly.charAt(i)== '+')){//this must be a coefficient
				tmpString += strPoly.charAt(i);
				i++;
				while ((i < strPoly.length()) &&(strPoly.charAt(i)!= 'x') && ((strPoly.charAt(i)!= '-') && (strPoly.charAt(i)!= '+'))){ //in case the number is a double, continue adding you find an x
					tmpString += strPoly.charAt(i);
					i++;
				}
				if (strPoly.charAt(i)!= 'x'){
					//if the value is not x that means that the coef has an exponent of 0
					resultExpo.add(0);
				}
				//cast into a double
				System.out.println(tmpString);
				tmpCoef = Double.parseDouble(tmpString); 
				resultCoef.add(tmpCoef);
				tmpString = "";//empty the string for next use
				i--;

			} else if (strPoly.charAt(i)== 'x'){
				i++;
				while ((i < strPoly.length()) && (strPoly.charAt(i)!= '-') && (strPoly.charAt(i)!= '+') ){ //in case the int has more than one number, continue adding you find an - or +
					tmpString += strPoly.charAt(i);
					i++;
				}
				//cast into an int
				System.out.println("SLUT");
				System.out.println(tmpString);
				tmpExpo = Integer.parseInt(tmpString); 
				resultExpo.add(tmpExpo);
				tmpString = "";//empty the string for next use
				i--;

			} else { //it's the first number of the string, must be a coefficent
				while ((i < strPoly.length()) && (strPoly.charAt(i)!= 'x') && (strPoly.charAt(i)!= '-') && (strPoly.charAt(i)!= '+')) { //in case the number is a double, continue adding you find an x
					tmpString += strPoly.charAt(i);
					i++;
				}
				if (strPoly.charAt(i)!= 'x'){
					
					//if the value is not x that means that the coef has an exponent of 0
					resultExpo.add(0);
				}
				
				//cast into a double
				System.out.println(tmpString);
				tmpCoef = Double.parseDouble(tmpString); 
				resultCoef.add(tmpCoef);
				tmpString = "";//empty the string for next use
				i--;
				
			}
		  }
		
		Double[] finalCoef = new Double[resultCoef.size()];
		finalCoef = resultCoef.toArray(finalCoef);
		Integer[] finalExpo = new Integer[resultExpo.size()];
		finalExpo = resultExpo.toArray(finalExpo);
		this.coef = unbox(finalCoef);
		this.expo = unbox(finalExpo);
    }
    private double[] unbox(Double[] d){
		double[] primitive = new double[d.length];
		for(int i =0; i<d.length;i++) {
			primitive[i] = (double)d[i];
		  }
		  return primitive;

	}
	private int[] unbox(Integer[] k){
		int[] primitive = new int[k.length];
		for(int i =0; i<k.length;i++) {
			primitive[i] = (int)k[i];
		  }
		  return primitive;

	}
    public Polynomial add(Polynomial poly){
    	double[] resultCoef;
    	int[] resultExpo;
    	//find the biggest coef list
    	if (this.expo.length<poly.expo.length){//poly is bigger
    		resultCoef = new double[poly.coef.length];
    		resultExpo = new int[poly.expo.length];
    		//loop through the biggest one
    		for (int i = 0; i< poly.expo.length;i++) {
    			resultExpo[i]=poly.expo[i];
    			resultCoef[i]=poly.coef[i];
    			for (int j = 0; j<this.expo.length;j++) {//loop through this to find all the numbers that have the same exponents
    				//if the exponents are the same then add them together
    				if (poly.expo[i]==this.expo[j]) {
    					resultCoef[i] += this.coef[j];
    				}
    				
    			}
    			
    		}
    		
    	} else {
    		resultCoef = new double[this.coef.length];
    		resultExpo = new int[this.expo.length];
    		for (int i = 0; i< this.expo.length;i++) {
    			resultExpo[i]=this.expo[i];
    			resultCoef[i]=this.coef[i];
    			for (int j = 0; j<poly.expo.length;j++) {//loop through this to find all the numbers that have the same exponents
    				//if the exponents are the same then add them together
    				if (poly.expo[j]==this.expo[i]) {
    					resultCoef[i] += poly.coef[j];
    				}
    			}
    		}
    	}
    	Polynomial resultPoly = new Polynomial(resultCoef,resultExpo);
    	return resultPoly;
    	
    }

    public double evaluate(double x){ //UPDATE EVALUATE - DONE
    	if (this.coef.length ==0) { // in case the poly is empty
    		return 0.0;
    	}
    	int len = this.coef.length;
    	double result=0.0;
    	for (int j = 0; j<len; j++) {
    		result += this.coef[j]*(Math.pow(x,this.expo[j]));
    	}
    	return result;
        }
    public boolean hasRoot(double root){

    	boolean result = false;
    	if (this.evaluate(root)==0) {
    		result = true;
    		}
    	return result;
        }
    
    public Polynomial multiply(Polynomial poly){
    	ArrayList<Double> resultCoef = new ArrayList<Double>();
    	ArrayList<Integer> resultExpo = new ArrayList<Integer>();
    	int tmpExpo = 0;
    	for (int i =0; i < poly.coef.length;i++) {
    		for (int j = 0; j < this.coef.length;j++) {
    			//add the exponents
    			tmpExpo = poly.expo[i]+this.expo[j];
    			if (resultExpo.contains(tmpExpo)) {//there IS number in the lsit with same exponent
    				for (int k =0; k<resultExpo.size();k++) {//find the index of the expo
    					if (resultExpo.get(k)== tmpExpo) {
    						resultCoef.set(k,resultCoef.get(k) +(poly.coef[i]*this.coef[j]));
    					}
    				}
    				
    			} else { 
    				resultExpo.add(tmpExpo); //add the exponent in th earraylist of exponents
				//multiply the coef and add to arraylist
    				resultCoef.add(poly.coef[i]*this.coef[j]);
    				
    				
    			}
    			//if the resulting exponent already exists add the corresponding coefs
    			//linkedhashsets doesn't let you store duplicate - could help with expo
    			
    		}
    	}
    	Double[] finalCoef = new Double[resultCoef.size()];
		finalCoef = resultCoef.toArray(finalCoef);
		Integer[] finalExpo = new Integer[resultExpo.size()];
		finalExpo = resultExpo.toArray(finalExpo);
		Polynomial finalpoly = new Polynomial(unbox(finalCoef),unbox(finalExpo));
    	return finalpoly;
		
	}

    public void saveToFile(String filename){
    	//String strPoly; convert coef and expo into one big string
    	// String strExpo = Arrays.toString(this.expo);
    	// String strCoef = Arrays.toString(this.coef);
		String strResult="";
		//make poly str
		for (int i = 0; i < this.coef.length; i++) {
			//add the coef and the x
			if (i==0){//for the first coef
				strResult += Double.toString(this.coef[i]);
				strResult += "x";
				
			} else {
				if (this.coef[i]>0){
					strResult += "+";
					strResult += Double.toString(this.coef[i]);
					
				} else {
					//no need to add the plus sign, the neg sign will come from the int itself so we don't need to manually add it
					strResult += Double.toString(this.coef[i]);
					
				}

			} 
			//now we add the expo
			if (this.expo[i]!=0){//only if the exponent is not zero
				strResult += "x";
				strResult += Integer.toString(this.expo[i]);
			}
			


		}//we finished writing the str now we need to add it to the file

    	try {
    	      FileWriter myWriter = new FileWriter(filename);
    	      myWriter.write(strResult);
    	      myWriter.close();
    	    } catch (IOException e) {
    	      System.out.println("An error occurred.");
    	      e.printStackTrace();
    	    }
    	
    }

    }

	
import java.io.File; 
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.Math;
public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6, -2, 5};
		int [] e1 = {0,1,3};
		Polynomial p1 = new Polynomial(c1,e1);
		double [] c2 = {0,-2,5	,-9};
		int[] e2 = {0,1,2,3};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p2.multiply(p1);
		System.out.println(Arrays.toString(s.expo));
		System.out.println(Arrays.toString(s.coef));
		Polynomial p3 = new Polynomial("testFile.txt");
		System.out.println(Arrays.toString(p3.expo));
		System.out.println(Arrays.toString(p3.coef));
		p1.saveToFile("demoFile.txt");
//		if(s.hasRoot(1))
//			System.out.println("1 is a root of s");
//		else
//			System.out.println("1 is not a root of s");
	//creating a file to test the code 
	    		
		
	}
}
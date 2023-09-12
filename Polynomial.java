public class Polynomial{

    double[] coef;
    public Polynomial(){
    	this.coef = new double[0];
        }
    public Polynomial(double[] given){
    	this.coef  = new double[given.length];
        for (int i =0; i<given.length;i++) {
        	this.coef[i]=given[i];
        }

        }
    public Polynomial add(Polynomial poly){
    	Polynomial resultPoly;
    	double[] resultCoef;
    	int i;
        if (this.coef.length < poly.coef.length) {
        	resultCoef = new double[poly.coef.length];
        	for (i=0; i < this.coef.length;i++) {
        		resultCoef[i] = this.coef[i]+poly.coef[i];
        	}
        	for (i=i;i<poly.coef.length;i++) {
        		resultCoef[i] = poly.coef[i];
        	}
        		
        }
        else {
        	resultCoef = new double[this.coef.length];
        	for (i=0; i < poly.coef.length;i++) {
        		resultCoef[i] = this.coef[i]+poly.coef[i];
        	}
        	for (i=i;i<this.coef.length;i++) {
        		resultCoef[i] = this.coef[i];
        	}
        }
        resultPoly = new Polynomial(resultCoef);
        return resultPoly;
        

        }

    public double evaluate(double x){
    	if (this.coef.length ==0) {
    		return 0.0;
    	}
    	int len = this.coef.length;
    	double result=0.0;
    	for (int j = 0; j<len; j++) {
    		result += this.coef[j]*(Math.pow(x,j));
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

    }
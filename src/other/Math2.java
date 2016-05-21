/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other;

public class Math2 {
    public static int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    
    public static int newtonsBinomial(int n, int i){
        int nFactorial = factorial(n);
        int iFactorial = factorial(i);
        int nMinIFactorial = factorial(n-i);
        
        return (nFactorial/(iFactorial*nMinIFactorial));
    }
}

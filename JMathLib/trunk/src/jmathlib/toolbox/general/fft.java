package jmathlib.toolbox.general;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

/**An external function which checks if the argument is a struct*/
public class fft extends ExternalFunction
{
    public OperandToken evaluate(Token[] operands)
    {

        if (getNArgIn(operands) != 1)
            throwMathLibException("fft: number of arguments != 1");
        
        if (!(operands[0] instanceof DoubleNumberToken))
            throwMathLibException("fft: not a number");
            
                
        double[][] val = ((DoubleNumberToken)operands[0]).getReValues();
        
        double[][] r = {{0.0}};
        r[0] = fftMag( val[0] );
        
        return new DoubleNumberToken(r);

    
    }

    private int n, nu;

    private int bitrev(int j) {

        int j2;
        int j1 = j;
        int k = 0;
        for (int i = 1; i <= nu; i++) {
            j2 = j1/2;
            k  = 2*k + j1 - 2*j2;
            j1 = j2;
        }
        return k;
    }

    public  double[] fftMag(double[] x) {
        // assume n is a power of 2
        n = x.length;
        nu = (int)(Math.log(n)/Math.log(2));
        int n2 = n/2;
        int nu1 = nu - 1;
        double[] xre = new double[n];
        double[] xim = new double[n];
        double[] mag = new double[n2];
        double tr, ti, p, arg, c, s;
        for (int i = 0; i < n; i++) {
            xre[i] = x[i];
            xim[i] = 0.0;
        }
        int k = 0;

        for (int l = 1; l <= nu; l++) {
            while (k < n) {
                for (int i = 1; i <= n2; i++) {
                    p = bitrev (k >> nu1);
                    arg = 2 * (double) Math.PI * p / n;
                    c = (double) Math.cos (arg);
                    s = (double) Math.sin (arg);
                    tr = xre[k+n2]*c + xim[k+n2]*s;
                    ti = xim[k+n2]*c - xre[k+n2]*s;
                    xre[k+n2] = xre[k] - tr;
                    xim[k+n2] = xim[k] - ti;
                    xre[k] += tr;
                    xim[k] += ti;
                    k++;
                }
                k += n2;
            }
            k = 0;
            nu1--;
            n2 = n2/2;
        }
        k = 0;
        int r;
        while (k < n) {
            r = bitrev (k);
            if (r > k) {
                tr = xre[k];
                ti = xim[k];
                xre[k] = xre[r];
                xim[k] = xim[r];
                xre[r] = tr;
                xim[r] = ti;
            }
            k++;
        }

        mag[0] = (double) (Math.sqrt(xre[0]*xre[0] + xim[0]*xim[0]))/n;
        for (int i = 1; i < n/2; i++)
            mag[i]= 2 * (double) (Math.sqrt(xre[i]*xre[i] + xim[i]*xim[i]))/n;
        return mag;
    }
}

/*
@GROUP
general
@SYNTAX
fft(values)
@DOC
@EXAMPLES
fft(sin([1:255]))
@NOTES
@SEE
*/

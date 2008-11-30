package jmathlib.core.graphics;

/** A fairly conventional 3D matrix object that can transform sets of
    3D points and perform a variety of manipulations on the transform */
public class Matrix3D {
    double xx, xy, xz, xo;
    double yx, yy, yz, yo;
    double zx, zy, zz, zo;
    static final double pi = 3.14159265;
    
    double[][] matrix = new double[3][4];

    double[][] unitMatrix =  {{1.0, 0.0, 0.0, 0.0},{0.0, 1.0, 0.0, 0.0},{0.0, 0.0, 1.0, 0.0}};
    
    /** Create a new unit matrix */
    public Matrix3D() 
    {
		xx = 1.0f;
		yy = 1.0f;
		zz = 1.0f;
 		matrix = unitMatrix;
    }
    
    /** Scale by f in all dimensions */
    public void scale(double f) 
    {
		//xx *= f; 	xy *= f;	xz *= f;	xo *= f; 
    	//yx *= f;	yy *= f;   	yz *= f;	yo *= f;
    	//zx *= f;	zy *= f;	zz *= f;	zo *= f;
    	
        scale(f, f, f);
    }
    
    /** Scale along each axis independently */
    public void scale(double xf, double yf, double zf)
    {
		xx *= xf;	xy *= xf;	xz *= xf;	xo *= xf;
		yx *= yf;	yy *= yf;	yz *= yf;	yo *= yf;
		zx *= zf;	zy *= zf;	zz *= zf;	zo *= zf;

       	for (int i=0; i<4; i++)
        {
        	matrix[0][i] *= xf;
            matrix[1][i] *= yf;
            matrix[2][i] *= zf;
        }

    }
    
    /** Translate the origin */
    public void translate(double x, double y, double z) 
    {
		xo += x;
		yo += y;
		zo += z;
    }
    
    /** rotate theta degrees about the y axis */
    public void yrot(double theta) 
    {
		theta *= (pi / 180);
		double ct = Math.cos(theta);
		double st = Math.sin(theta);

		double Nxx = (double) (xx * ct + zx * st);
		double Nxy = (double) (xy * ct + zy * st);
		double Nxz = (double) (xz * ct + zz * st);
		double Nxo = (double) (xo * ct + zo * st);

		double Nzx = (double) (zx * ct - xx * st);
		double Nzy = (double) (zy * ct - xy * st);
		double Nzz = (double) (zz * ct - xz * st);
		double Nzo = (double) (zo * ct - xo * st);

		xo = Nxo;
		xx = Nxx;
		xy = Nxy;
		xz = Nxz;
		zo = Nzo;
		zx = Nzx;
		zy = Nzy;
		zz = Nzz;
    }
    
    /** rotate theta degrees about the x axis */
    public void xrot(double theta) {
	theta *= (pi / 180);
	double ct = Math.cos(theta);
	double st = Math.sin(theta);

	double Nyx = (double) (yx * ct + zx * st);
	double Nyy = (double) (yy * ct + zy * st);
	double Nyz = (double) (yz * ct + zz * st);
	double Nyo = (double) (yo * ct + zo * st);

	double Nzx = (double) (zx * ct - yx * st);
	double Nzy = (double) (zy * ct - yy * st);
	double Nzz = (double) (zz * ct - yz * st);
	double Nzo = (double) (zo * ct - yo * st);

	yo = Nyo;
	yx = Nyx;
	yy = Nyy;
	yz = Nyz;
	zo = Nzo;
	zx = Nzx;
	zy = Nzy;
	zz = Nzz;
    }
    
    /** rotate theta degrees about the z axis */
    public void zrot(double theta) {
	theta *= (pi / 180);
	double ct = Math.cos(theta);
	double st = Math.sin(theta);

	double Nyx = (double) (yx * ct + xx * st);
	double Nyy = (double) (yy * ct + xy * st);
	double Nyz = (double) (yz * ct + xz * st);
	double Nyo = (double) (yo * ct + xo * st);

	double Nxx = (double) (xx * ct - yx * st);
	double Nxy = (double) (xy * ct - yy * st);
	double Nxz = (double) (xz * ct - yz * st);
	double Nxo = (double) (xo * ct - yo * st);

	yo = Nyo;
	yx = Nyx;
	yy = Nyy;
	yz = Nyz;
	xo = Nxo;
	xx = Nxx;
	xy = Nxy;
	xz = Nxz;
    }
    
    /** Multiply this matrix by a second: M = M*R */
    public void mult(Matrix3D rhs) {
	double lxx = xx * rhs.xx + yx * rhs.xy + zx * rhs.xz;
	double lxy = xy * rhs.xx + yy * rhs.xy + zy * rhs.xz;
	double lxz = xz * rhs.xx + yz * rhs.xy + zz * rhs.xz;
	double lxo = xo * rhs.xx + yo * rhs.xy + zo * rhs.xz + rhs.xo;

	double lyx = xx * rhs.yx + yx * rhs.yy + zx * rhs.yz;
	double lyy = xy * rhs.yx + yy * rhs.yy + zy * rhs.yz;
	double lyz = xz * rhs.yx + yz * rhs.yy + zz * rhs.yz;
	double lyo = xo * rhs.yx + yo * rhs.yy + zo * rhs.yz + rhs.yo;

	double lzx = xx * rhs.zx + yx * rhs.zy + zx * rhs.zz;
	double lzy = xy * rhs.zx + yy * rhs.zy + zy * rhs.zz;
	double lzz = xz * rhs.zx + yz * rhs.zy + zz * rhs.zz;
	double lzo = xo * rhs.zx + yo * rhs.zy + zo * rhs.zz + rhs.zo;

	xx = lxx;
	xy = lxy;
	xz = lxz;
	xo = lxo;

	yx = lyx;
	yy = lyy;
	yz = lyz;
	yo = lyo;

	zx = lzx;
	zy = lzy;
	zz = lzz;
	zo = lzo;
    }

    /** Reinitialize to the unit matrix */
    public void unit() 
    {
		xx = 1;	xy = 0;	xz = 0;	xo = 0;
    	yx = 0;	yy = 1;	yz = 0; yo = 0;
		zx = 0;	zy = 0;	zz = 1; zo = 0;
    }
    
    /** Transform nvert points from v into tv.  v contains the input
        coordinates in floating point.  Three successive entries in
	the array constitute a point.  tv ends up holding the transformed
	points as integers; three successive entries per point */
    public void transform(double x[], double y[], double z[],
    					  int   tx[], int   ty[], int   tz[]) 
    {
        
		for (int i=0; i<x.length; i++) 
        {
	    	tx[i] = (int) (x[i] * xx + y[i] * xy + z[i] * xz + xo);
	    	ty[i] = (int) (x[i] * yx + y[i] * yy + z[i] * yz + yo);
	    	tz[i] = (int) (x[i] * zx + y[i] * zy + z[i] * zz + zo);
		}
    }

    /** Transform nvert points from v into tv.  v contains the input
        coordinates in floating point.  Three successive entries in
	the array constitute a point.  tv ends up holding the transformed
	points as integers; three successive entries per point */
    public void transform(double x[][], double y[][], double z[][],
    					  int   tx[][], int   ty[][], int   tz[][]) 
    {
        
		for (int i=0; i<x.length; i++) 
        {
        	for (int j=0; j<x[0].length; j++)
            { 
	    		tx[i][j] = (int) (x[i][j] * xx + y[i][j] * xy + z[i][j] * xz + xo);
	    		ty[i][j] = (int) (x[i][j] * yx + y[i][j] * yy + z[i][j] * yz + yo);
	    		tz[i][j] = (int) (x[i][j] * zx + y[i][j] * zy + z[i][j] * zz + zo);
			}
        }
    }
    
    public java.awt.Point transform(double x, double y, double z)
    {
	    return new java.awt.Point(
		(int) (x * xx + y * xy + z * xz + xo),
		(int) (x * yx + y * yy + z * yz + yo)
	    );
    }
    
}

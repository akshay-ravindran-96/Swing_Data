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
public class gyro {
    
    private int timestamp;
    
    private float ax;
    private float ay;
    private float az;
    
    private float wx;
    private float wy;
    private float wz;
    
//    Getter and Setter for all Memebers
    public int getTimeStamp()
    {
      return timestamp;  
        
    }
    public void  setTimeStamp(int data)
    {
      this.timestamp = data;  
    }
    
    
    public float getAx()
    {
        return ax;
    }
    
    public void setAx(float data)
    {
        this.ax= data;
    }
    
    
     public float getAy()
    {
        return ay;
    }
    
    public void setAy(float data)
    {
        this.ay= data;
    }
    
    
    
     public float getAz()
    {
        return az;
    }
    
    public void setAz(float data)
    {
        this.az= data;
    }
    
    
     public float getWx()
    {
        return wx;
    }
    
    public void setWx(float data)
    {
        this.wx= data;
    }
    
    
     public float getWy()
    {
        return wy;
    }
    
    public void setWy(float data)
    {
        this.wy= data;
    }
    
    
     public float getWz()
    {
        return wz;
    }
    
    public void setWz(float data)
    {
        this.wz= data;
    }
    
//    Making print Eligible and easier to understand
    @Override
    public String toString() {
	return "Gyro [Time Stamp=" + timestamp + ", ax=" + ax +" "+ ", ay=" + ay + ", az=" + az+ ", wx=" + wx+ ", wy=" + wy+ ", wz=" + wz+" ";
        
    }
    
}

package cn.nj.storm.EJ2.examples.Chapter4.Item14;

// Encapsulation of data by accessor methods and mutators
class Point
{
    private double x;
    
    private double y;
    
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    
    public double getX()
    {
        return x;
    }
    
    public double getY()
    {
        return y;
    }
    
    public void setX(double x)
    {
        this.x = x;
    }
    
    public void setY(double y)
    {
        this.y = y;
    }
}

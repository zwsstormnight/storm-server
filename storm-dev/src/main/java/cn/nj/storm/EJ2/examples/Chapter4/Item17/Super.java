package cn.nj.storm.EJ2.examples.Chapter4.Item17;

public class Super
{
    // Broken - constructor invokes an overridable method
    public Super()
    {
        overrideMe();
    }
    
    public void overrideMe()
    {
    }
}

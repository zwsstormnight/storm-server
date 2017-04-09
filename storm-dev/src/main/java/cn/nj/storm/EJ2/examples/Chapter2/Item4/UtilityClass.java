package cn.nj.storm.EJ2.examples.Chapter2.Item4;

// Noninstantiable utility class
public class UtilityClass
{
    // Suppress default constructor for noninstantiability
    private UtilityClass()
    {
        throw new AssertionError();
    }
}

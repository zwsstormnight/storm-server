package cn.nj.storm.EJ2.examples.Chapter2.Item1;// Simple test program for server provider framework

public class Test
{
    public static void main(String[] args)
    {
        // Providers would execute these lines
        Services.registerDefaultProvider(DEFAULT_PROVIDER);
        Services.registerProvider("comp", COMP_PROVIDER);
        Services.registerProvider("armed", ARMED_PROVIDER);
        
        // Clients would execute these lines
        Service s1 = Services.newInstance();
        Service s2 = Services.newInstance("comp");
        Service s3 = Services.newInstance("armed");
        System.out.printf("%s, %s, %s%n", s1, s2, s3);
    }
    
    private static Provider DEFAULT_PROVIDER = new Provider()
    {
        public Service newService()
        {
            return new Service()
            {
                @Override
                public String toString()
                {
                    return "Default server";
                }
            };
        }
    };
    
    private static Provider COMP_PROVIDER = new Provider()
    {
        public Service newService()
        {
            return new Service()
            {
                @Override
                public String toString()
                {
                    return "Complementary server";
                }
            };
        }
    };
    
    private static Provider ARMED_PROVIDER = new Provider()
    {
        public Service newService()
        {
            return new Service()
            {
                @Override
                public String toString()
                {
                    return "Armed server";
                }
            };
        }
    };
}

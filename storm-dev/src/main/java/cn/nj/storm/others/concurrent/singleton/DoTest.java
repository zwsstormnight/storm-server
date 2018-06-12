package cn.nj.storm.others.concurrent.singleton;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author zhengweishun
 * @version [版本号, 2018/5/23]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DoTest
{
    public static void main(String[] args)
    {
        DbConnection con1 = DbConnection.getConnection();
        DbConnection con2 = DbConnection.getConnection();
        System.out.println(con1 == con2);
        
        int[] arr = {6, 3, 8, 2, 9, 1};
        System.out.println("排序前数组为：");
        for (int num : arr)
        {
            System.out.print(num + " ");
        }
        for (int i = 0; i < arr.length - 1; i++)
        {//外层循环控制排序趟数
            for (int j = 0; j < arr.length - 1 - i; j++)
            {//内层循环控制每一趟排序多少次
                if (arr[j] > arr[j + 1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("排序后的数组为：");
        for (int num : arr)
        {
            System.out.print(num + " ");
        }
    }
}

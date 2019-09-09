package www.qulusheng.com.test.arithmetic;

import java.util.Arrays;

public class StraightInsertionSort {
    public static int[] insertSort() {
        int[] datas = new int[]{32,43,23,13,5} ;
        int insertNum ;
        int lenght = datas.length ;
        for (int i = 1; i < lenght; i++) {
            //要插入的数
            insertNum = datas[i] ;
            int j = i - 1;
            while (j >= 0 && datas[j] > insertNum){
                datas[j + 1] = datas[j] ;
                j-- ;
                System.out.println("---" + Arrays.toString(datas));
            }
            datas[j + 1] = insertNum ;
            System.out.println(Arrays.toString(datas));
        }
        return datas ;
    }
}

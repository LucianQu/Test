package www.qulusheng.com.test.other;

import java.io.File;

/**************************************************************************
*
*  File name: Recursion.java
*  Date: 2019/2/19 10:28
*  Copyright (c) 2013-2018 by Automic.
*  Author: Lucian_qls
*  E-mail: 1017557706@qq.com
*
*  Function:
*   .递归调用
 *   else中递归调用函数
 *   递归的过程存在前行和退回阶段
 *   前行阶段我们每次调用函数后打印出了“抱着”，并重新调用该函数
 *   退回阶段将会执行代码“的我”
 *   栗子：
 *   你用你手中的钥匙打开一扇门，结果去发现前方还有一扇门，紧接着你又用钥匙打开了这扇门，然后你又看到一扇们...但是当你开到某扇门时，发现前方是一堵墙无路可走了，你选择原路返回——这就是递归
 * 但是如果你打开一扇门后，同样发现前方也有一扇们，紧接着你又打开下一扇门...但是却一直没有碰到尽头——这就是循环。
 * ChangeLog:
 * v1.0
**************************************************************************/
public class Recursion {
    public void recursion(int depth) {
        System.out.println("抱着");
        if (depth <= 0) {
            System.out.println("我的小锦鲤");
        }else {
            recursion(--depth);
        }
        System.out.println("的我"+depth);
    }
    public void start() {
        System.out.println("吓得我抱起了");
        recursion(2);
    }

    /**
     * 递归应用在斐波拉契数列
     * @param x
     */
    public int fib(int x) {
        if (x < 2) {
            return x == 0 ? 0 : 1 ;
        }else {
            return fib(x - 1) + fib(x - 2);
        }
    }
    public void start1() {
        System.out.println("斐波拉契数列:" + fib(6));
    }

    public void fileDisplay(String path) {
        File files = new File(path) ;
        File[] subFile = files.listFiles() ;
        for (int i = 0 ; i < subFile.length; i++) {
            if (!subFile[i].isDirectory()) {
                String fileName = subFile[i].getName() ;
                System.out.println("文件名：" + fileName);
            }else {
                fileDisplay(subFile[i].getAbsolutePath());
            }
        }
    }
}

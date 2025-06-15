import co.paralleluniverse.fibers.Fiber;//代表一个轻量级协程，类似于线程但是开销低
import co.paralleluniverse.fibers.SuspendExecution;//用于标记可暂停操作
import co.paralleluniverse.strands.SuspendableRunnable;//可以暂停的Runnable接口，地台Java标准的Runnable
//本代码展示了传统线程执行大量计算任务的过程
public class HelloFiber {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        Runnable r = new Runnable() {//创建Runnable接口的匿名内部类

            @Override
            public void run() {
                calc();//这个run方法调用calc静态方法
            }
        };

        int size = 10000;//创建10000个线程，每个线程都会执行Runnable任务

        Thread[] threads = new Thread[size];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(r);
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //进行了大约200万次加法运算，模拟了CPU密集型任务
    static void calc() {
        int result = 0;
        for (int m = 0; m < 10000; m++) {
            for (int i = 0; i < 200; i++)
                result += i;
        }
    }
}

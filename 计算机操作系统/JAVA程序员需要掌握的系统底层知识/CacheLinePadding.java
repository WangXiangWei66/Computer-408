public class CacheLinePadding {
    //高速缓存填充
    //本代码用于显示缓存行填充
    //volatile使得任何对数组的修改都会立即刷新到主内存
    public static volatile long[] arr = new long[2];

    //在声明时抛出异常
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 10000_0000L; i++) {
                arr[0] = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 10000_0000L; i++) {
                arr[1] = i;
            }
        });
        //获取当前系统时间的高速时间戳
        //final声明start为常量，确保值在初始化后不可变
        final long start = System.nanoTime();
        t1.start();
        t2.start();
        //join方法使主线程会等待两个线程执行完毕
        t1.join();
        t2.join();
        System.out.println((System.nanoTime() - start) / 100_0000);
    }
}

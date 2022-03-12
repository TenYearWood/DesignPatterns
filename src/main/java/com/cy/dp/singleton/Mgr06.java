package com.cy.dp.singleton;

/**
 * 懒汉式
 */
public class Mgr06 {

    private static volatile Mgr06 INSTANCE;

    private Mgr06() {

    }

    /**
     * 很多开源软件都是这种写法
     * 疑问：外面这层判断有必要存在吗？---有必要
     * 假如if (INSTANCE == null)这个判断不在的话，确实可以保障整个单例只有1个，但是他的效率会偏低。
     * 如果有一万个线程都来new这个对象，没有外面这层检查，这一万个线程都会申请上锁，申请上锁的过程是一个
     * 非常重量级的过程，都要去抢这把锁。如果有了外面这层检查，只要有一个线程new成功了，其他线程判断一下
     * 不为空，if (INSTANCE == null)，就再也不需要上锁这个过程了，这个判断1~2个纳秒而已，上锁好几百个纳秒。
     * 所以，double check lock外面这层检查不可以省略。
     *
     * 但是这段代码有没有问题呢？---有
     * INSTANCE = new Mgr06()是由三部构成：
     * 1.new #2 <T>  申请空间，赋予默认值
     * 2.invokespecial #3 <T.<init>> 构造方式初始值，建立关联，将t指向new出来的T()
     * 3.astore_1
     * 第一个线程过来了，执行到INSTANCE = new Mgr06()这句话，且执行到初始化的第1.步，正好这时候
     * (单线程里面只要不影响到最终的一致性，两个语句是可以换顺序的)2.和3.换了顺序，发生了指令重排序，
     * 这时候t就指向了new了一半的对象，正好在这个时间点上，第二个线程过来了，执行if (INSTANCE == null)这句话，
     * INSTANCE!=null，不会执行后面代码了，不需要上锁不需要new了，直接就拿来用了，但是就用了半初始化状态的对象，
     * 这就是问题所在。指令重排序所造成的。
     *
     * 同学疑问1：第一个线程都上锁了，第二个线程还能访问到锁定代码里这个对象的中间状态(半初始化状态)？
     * 不是说上锁的代码只有一个线程把这段代码执行完成之后，另外一个线程才可以访问吗？怎么第二个线程还能访问这个Mgr06对象的中间状态?
     * 回答：synchronized确实能保证原子性、可见性，但是唯一不能保证的就是有序性。
     * 第一，同样synchronized里面代码，里面顺序可以随便换，只要单线程的最终一致性，就可以随便换。
     * 第二，如果是两段代码都是位于同一把锁的情况下，绝对不可能说代码执行了一半，另一个线程就能看到能拿到中间状态，不可能的。
     * 但是问题在于，NSTANCE = new Mgr06()，new到一半的时候，是被上锁的线程执行的没有问题，但是它是和那句话冲突了呢？
     * 是和if (INSTANCE == null)这句话冲突了，第二个线程来的时候不需要上锁就能执行if (INSTANCE == null)，所以实际上就是
     * 上了锁的代码和没上锁的代码之间发生的，上了锁的代码和没上锁的代码是并发运行的，他们之间没有互斥、序列化等，没有，不上锁的
     * 代码就能访问到对象的中间状态。
     *
     * 为了追求效率，将锁的粒度变细，不是锁整个代码块，放开了就要忍受new Mgr06() new了一半的时候，另外一个线程判断if (INSTANCE == null)
     * 时是不需要锁的，是可以访问到这个对象的。这个时候如果发生指令重排序就会产生访问到中间状态的情况。
     *
     * 同学疑问2：2.和3.凭什么能换顺序？难道java里面的指令都可以随便换顺序吗？
     * java里面规定了八种情形不可以换顺序，happens-before原则。(JVM规定重排序必须遵守的规则)
     *
     * 解决以上问题的办法是在Mgr06前加上volatile关键字。
     * 加了volatile之后，new Mgr06()三个步骤的2.3.顺序不会被换掉了。
     *
     *
     * @return
     */
    public static Mgr06 getInstance() {
        //业务逻辑代码省略
        if (INSTANCE == null) {     //Double check lock   DCL
            //双重检查
            synchronized (Mgr06.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> System.out.println(getInstance().hashCode())).start();
        }
    }
}

package ttl.larku.concurrentcollections;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author whynot
 */
public class PriorityQueueDemo {


    public void go() {
        BlockingQueue<String> logQueue = new PriorityBlockingQueue<>(10);
        logQueue.add("One");
        logQueue.add("Two");
        logQueue.add("Six");
        logQueue.add("Fifteen");
        logQueue.add("ThirtyTwo");

        for(String s : logQueue) {
            System.out.println("s: " + s);
        }
    }

    public void serialExample() throws InterruptedException {
        BlockingQueue<MyStuff> logQueue = new PriorityBlockingQueue<>(10,
                Comparator.comparing(MyStuff::getPriority).reversed());

        logQueue.put(new MyStuff(5, "Simple message"));
        logQueue.put(new MyStuff(1, "Trace message"));
        logQueue.put(new MyStuff(7, "Warning message"));
        logQueue.put(new MyStuff(8, "Error message"));
        logQueue.put(new MyStuff(100, "End of the world message"));

        int sz = logQueue.size();
        for(int i = 0; i < sz; i++) {
            System.out.println("ms: " + logQueue.take());
        }


    }


    BlockingQueue<MyStuff> errorQueue = new PriorityBlockingQueue<>(10,
            Comparator.comparing(MyStuff::getPriority).reversed());
    private volatile boolean keepGoing = true;
    public class Producer implements Runnable {
        int [] msgOrder = {1, 33, 2, 19, 100, 3, 7, 9};

        @Override
        public void run() {
            for(int i : msgOrder) {
                MyStuff ms = new MyStuff(i, "MsgPrio:" + i);
                try {
                    errorQueue.put(ms);
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
            System.out.println("Producer is done");
        }
    }

    public class Consumer implements Runnable {
        @Override
        public void run() {
        	int nothingCount = 0;
            while(nothingCount < 10 && keepGoing) {
                try {
                    MyStuff ms = errorQueue.poll(100, TimeUnit.MILLISECONDS);
                    if(ms == null) {
                    	nothingCount++;
                        System.out.println("Consumer got nothing");
                    }
                    else {
                    	nothingCount = 0;
                        System.out.println("Consumer got: " + ms);
                    }
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

            System.out.println("Consumer is done");
        }
    }

    public void prodCon() throws InterruptedException {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Thread th1 = new Thread(producer);
        Thread th2 = new Thread(consumer);

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println("ProdCon Done");
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityQueueDemo pqd = new PriorityQueueDemo();
//        pqd.go();
//        pqd.serialExample();
        pqd.prodCon();
    }
}

class MyStuff
{
    public int priority;
    public String msg;

    public MyStuff(int p, String m) {
        priority = p;
        msg = m;
    }

    public int getPriority() {
        return priority;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "MyStuff{" +
                "priority=" + priority +
                ", msg='" + msg + '\'' +
                '}';
    }

	@Override
	public int hashCode() {
		return Objects.hash(msg, priority);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyStuff other = (MyStuff) obj;
		return Objects.equals(msg, other.msg) && priority == other.priority;
	}

}

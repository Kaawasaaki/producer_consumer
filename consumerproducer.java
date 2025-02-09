import java.util.concurrent.*;

class Producer implements Runnable{
    private BlockingQueue<Integer> queue;
    private int maxItems;

    public Producer(BlockingQueue<Integer> queue, int maxItems){
        this.queue = queue;
        this.maxItems = maxItems;
    }
    public void run(){
 try{
    for(int i = 1; i<=maxItems ; i++)
    {
        queue.put(i);
        System.out.println("Produced: " + i);
        Thread.sleep(500);
    }
}catch (InterruptedException e )
    {
        Thread.currentThread().interrupt();
    }
}
}

class Consumer implements Runnable{
    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }

    public void run(){
    try{
    while(true){
        int item = queue.take();
        System.out.println("Consumed: " + item);
        Thread.sleep(1000);
    }
    }catch (InterruptedException e){
        System.out.println("Consumer process stopped");
    }
}
}

public class consumerproducer{
    public static void main(String[] args)throws InterruptedException{
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(new Producer(queue,10));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();

        producer.join();
        consumer.interrupt();
        consumer.join();


        System.out.println("Process is completed");


        
    }
}
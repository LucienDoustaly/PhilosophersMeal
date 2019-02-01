import java.time.LocalDateTime;

public class Philosopher extends Thread {

    /** Maximum 30 secondes en ayant faim, au delà = MOOOOOORRRRTTTTT */
    private final int MAX_HUNGRY_TIME = 5000;

    private Thread t;
    private String philosopherName;
    private int no;
    private String state;
    private Fork forks;
    private int deadCounter;
    private boolean isDead;

    private long start, finish, timeEnlapsed;


    Philosopher(String name, int no ,Fork forks){
        this.philosopherName = name;
        this.no = no;
        this.forks = forks;
        this.state = "think";
        this.deadCounter = 0;
    }

    public String getPhilosopherName() {
        return this.philosopherName;
    }

    public void setState(String state){
        this.state = state;
    }

    public boolean isThinking(){
        if(this.state == "think"){
            return true;
        }
        else
            return false;
    }

    public boolean isHungry(){
        if(this.state == "hungry"){
            return true;
        }
        else
            return false;
    }

    public boolean isEating(){
        if(this.state == "eating"){
            return true;
        }
        else
            return false;
    }

    public boolean isInStarvation(){
        if(this.state == "starvation"){
            return true;
        }
        else
            return false;
    }

    public boolean isDead(){
        return this.isDead;
    }

    public void setDeadCounterToZero(){
        this.timeEnlapsed = 0;
        this.start = 0;
        this.finish = 0;
    }


    public void run() {
        try {

            while(timeEnlapsed != MAX_HUNGRY_TIME){
                if(this.isThinking()) {
                    System.out.println( java.time.LocalTime.now() + " " + philosopherName + " starts thinking" );
                    Thread.sleep((long) (Math.random() * (15000 - 5000)));
                    System.out.println( java.time.LocalTime.now() + " " + this.getPhilosopherName() + " stops thinking and now he is hungry.");
                    this.setState("hungry");
                }

                if(this.isEating()){
                    System.out.println(java.time.LocalTime.now() + " " + this.getPhilosopherName() + " starts eating.");
                    Thread.sleep(1000);
                    System.out.println(java.time.LocalTime.now() + " " + this.getPhilosopherName() + " stops eating.");
                    this.forks.pose(this.no);
                    this.setState("think");
                    this.setDeadCounterToZero();
                }

                if(this.isHungry() || this.isInStarvation()){
                    this.start = System.currentTimeMillis();
                    this.forks.take(this.no);
                    this.setState("eating");
                    this.finish = System.currentTimeMillis();

                    this.timeEnlapsed = this.finish - this.start;

                    if(timeEnlapsed == MAX_HUNGRY_TIME){
                        System.out.println(java.time.LocalTime.now() + " " + this.getPhilosopherName() + " is dead.");
                    }
                }
            }

        } catch (InterruptedException e) {
            System.out.println(java.time.LocalTime.now() + " " + "Philosopher " +  this.getPhilosopherName() + " interrupted.");
        }
    }

    public void start () {
        System.out.println(java.time.LocalTime.now() + " " + this.getPhilosopherName() + " sits at the table.");
        if (t == null) {
            t = new Thread (this, this.getPhilosopherName());
            t.start ();
        }
    }

}

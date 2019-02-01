import java.util.Arrays;

public class Fork {

    boolean[] forks;
    /** nb de fourchettes*/
    int nbforks;

    public Fork(int number) {
        this.nbforks = number;
        this.forks = new boolean[this.nbforks];
        Arrays.fill(this.forks, true);
    }

    public synchronized void take(int no) {
        int left = no;
        int right = (no+1)%this.nbforks;
        while (!this.forks[left] || !this.forks[right]) {
            try   {  wait();  } catch (InterruptedException e) {}
        }
        this.forks[left] = false;
        this.forks[right] = false;
    }

    public synchronized void pose(int no) {
        int left = no;
        int right = (no+1)%this.nbforks;
        this.forks[left] = true;
        this.forks[right] = true;
        notifyAll();
    }
}

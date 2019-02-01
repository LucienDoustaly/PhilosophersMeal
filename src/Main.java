public class Main {

    public static void main(String args[]) {

        final Fork forks = new Fork(5);

        Philosopher P1 = new Philosopher( "Freud", 0, forks);
        P1.start();

        Philosopher P2 = new Philosopher( "Volaire",1, forks);
        P2.start();

        Philosopher P3 = new Philosopher( "Platon",2, forks);
        P3.start();

        Philosopher P4 = new Philosopher( "Aristote", 3, forks);
        P4.start();

        Philosopher P5 = new Philosopher( "Descartes", 4, forks);
        P5.start();
    }
}

package it.beije.ananke.capitolo5;

 abstract class Ballroom {
    public final static int palle = 1;
    public Long number() {
        return 23L;
    }
}

interface Sticazz {
    default void hasMacchie() {
        System.out.println("SI");
    }
}

public class Ball extends Ballroom implements Sticazz{
    private int age = 6;
    public Ball(Object obj) {
        super();
    }

    @Override
    public void hasMacchie() {
        System.out.println("NO");
    }

    public static void main(String[] args) {
        Ball ballroom = new Ball("ciao");
//        Ball b3 = new Ballroom();
//        ballroom = b2;
        Ballroom b = (Ballroom) ballroom;
        System.out.println(b.palle);
        System.out.println(ballroom.palle);
        Sticazz c = (Sticazz) (Ballroom) ballroom;

    }

}
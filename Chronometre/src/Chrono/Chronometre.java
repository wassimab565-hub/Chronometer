package Chrono;

public class Chronometre implements Runnable {

    private int h = 0, m = 0, s = 0;
    private boolean running = false;
    private boolean alive = true;

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void reset() {
        h = m = s = 0;
    }

    public String getTime() {
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public void kill() {
        alive = false;
    }

    @Override
    public void run() {
        while (alive) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            if (running) {
                s++;
                if (s == 60) { s = 0; m++; }
                if (m == 60) { m = 0; h++; }
            }
        }
    }
}

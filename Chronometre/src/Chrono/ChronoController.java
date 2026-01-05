package Chrono;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChronoController {

    @FXML
    private Label timeLabel;

    private Chronometre chrono;
    private Thread thread;

    @FXML
    public void initialize() {
        chrono = new Chronometre();
        thread = new Thread(chrono);
        thread.setDaemon(true);
        thread.start();

        // Rafraîchissement UI
        new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    timeLabel.setText(chrono.getTime());
                });
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {}
            }
        }).start();
    }

    @FXML
    private void startChrono() {
        chrono.start();
    }

    @FXML
    private void stopChrono() {
        chrono.stop();
    }

    @FXML
    private void resetChrono() {
        chrono.reset();
    }
}

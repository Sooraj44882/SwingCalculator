
import javazoom.jl.player.Player;
import java.io.FileInputStream;

public class SoundPlayer {
    private static volatile boolean backgroundPlaying = false;
    private static Player backgroundPlayer;

    public static void playClick() {
        new Thread(() -> {
            try (FileInputStream input =
                     new FileInputStream("d/click.mp3")) {
                Player player = new Player(input);
                player.play();
            } catch (Exception e) {
                System.out.println("Click sound error: " + e.getMessage());
            }
        }).start();
    }

    public static synchronized void playBackground() {
        if (backgroundPlaying) {
            return;
        }

        backgroundPlaying = true;

        Thread musicThread = new Thread(() -> {
            while (backgroundPlaying) {
                try (FileInputStream input =
                         new FileInputStream("d/h.mp3")) {
                    Player player = new Player(input);

                    synchronized (SoundPlayer.class) {
                        backgroundPlayer = player;
                    }

                    if (backgroundPlaying) {
                        player.play();
                    }

                    synchronized (SoundPlayer.class) {
                        backgroundPlayer = null;
                    }
                } catch (Exception e) {
                    System.out.println("Music error: " + e.getMessage());
                    break;
                }
            }

            backgroundPlaying = false;
        });

        musicThread.setDaemon(true);
        musicThread.start();
    }

    public static synchronized void stopBackground() {
        backgroundPlaying = false;

        if (backgroundPlayer != null) {
            backgroundPlayer.close();
            backgroundPlayer = null;
        }
    }
}

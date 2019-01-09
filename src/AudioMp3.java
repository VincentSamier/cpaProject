import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class AudioMp3
{
    Media media = null;
    MediaPlayer player = null;

    public AudioMp3(String file) {

        //File fichier = new File("/Users/scoste/essai.mp3");
        // File fichier = new File("/Users/scoste/LocalSimplet/coste/Sylvie/Chansons/LesBlaireaux1999/01.mp3");
        File fichier = new File(file);

        new javafx.embed.swing.JFXPanel();

        String uriString = null;
        try {
            uriString = fichier.toURI().toString();
            media = new Media(uriString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("La durée du morceau est de " + media.getDuration() + " secondes");
    }

    public void jouerExtrait()
    {
        joue();
        Thread threadExtrait = new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    if (player.getBufferProgressTime().toSeconds() >= 10)
                    {
                        player.stop();
                    }
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
        threadExtrait.start();
    }

    public void joue() {
        try {
            player = new MediaPlayer(media);
        } catch (MediaException e) {
            e.printStackTrace();
        }

        //player.setVolume(0.75);
        player.play();
        System.out.println("Je joue ...");
    }

    public void stop() {
        player.stop();
        System.out.println("J'ai arrêté de jouer !");
    }
}
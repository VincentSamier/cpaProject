import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Morceau {
    String nom;
    String auteur;
    String fichier;

    private JLabel texte;
    private JButton bouton = new JButton("Go");
    private JButton bouton2 = new JButton("Stop");
    private JButton bExtrait = new JButton("Extrait");
    private boolean animated = true;

    private AudioMp3 audioMp3;

    public Morceau(String file, String nom){
        //audioMp3 = new AudioMp3(file); //A décommenter
        texte = new JLabel(nom);
        this.nom = nom;
    }

    public void ajouteMorceau(JPanel panel){
        Border blackline = BorderFactory.createLineBorder(Color.blue);

        texte.setBackground(Color.CYAN);
        texte.setBorder(blackline);
        texte.setHorizontalAlignment(JLabel.CENTER);
        bouton.addActionListener(new BoutonListener());
        bouton.setEnabled(true);
        bouton2.addActionListener(new Bouton2Listener());
        bouton2.setEnabled(false);
        bExtrait.addActionListener(new BExtraitListener());
        bExtrait.setEnabled(true);

        panel.add(texte);
        panel.add(bouton);
        panel.add(bouton2);
        panel.add(bExtrait);
    }

    class BoutonListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            animated = true;
            bouton.setEnabled(false);
            bouton2.setEnabled(true);
            bExtrait.setEnabled(false);
            audioMp3.joue();
        }
    }

    class Bouton2Listener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            animated = false;
            bouton.setEnabled(true);
            bouton2.setEnabled(false);
            audioMp3.stop();
        }
    }

    class BExtraitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            animated = false;
            bouton.setEnabled(false);
            bouton2.setEnabled(true);
            audioMp3.jouerExtrait();
        }

    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Fenetrebis extends JFrame {
    private JPanel container = new JPanel();
    private JLabel label = new JLabel("Les titres");
    Morceau morceau1;
    private JMenu menu;
    private int nbMorceaux = 1;
    private ArrayList<Morceau> listeMusique = new ArrayList<>();

    public Fenetrebis() {
        this.setTitle("Mes musiques");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        JPanel north = new JPanel();
        Font police = new Font("Tahoma", Font.BOLD, 16);
        label.setFont(police);
        label.setForeground(Color.blue);
        label.setHorizontalAlignment(JLabel.CENTER);
        north.setLayout(new GridLayout(nbMorceaux + 1, 1));
        north.add(label);
        north.add(container);

        initCollection(); //On charge les musiques depuis le fichier csv
        setJMenuBar(createMenu()); //Ajout de la barre de menu


        container.setBackground(Color.white);
        container.setLayout(new GridLayout(nbMorceaux, 4));

        morceau1 = new Morceau("/export/etu/vincent.samier/Documents/S3/CPA/Projet/sonsBienLourds/FilsTool-Bateau_ivre.mp3", "Inconnu");
        morceau1.ajouteMorceau(container);
        this.setContentPane(north);
        this.setVisible(true);
    }

    /**
     *   Créée la barre de menu.
     *   @return une JMenuBar contenant les noms des musiques.
     */
    private JMenuBar createMenu() {
        JMenuBar barreDeMenus = new JMenuBar();
        menu = new JMenu("Liste des musiques");
        barreDeMenus.add(menu);

        fillMenu();

        JButton bouton = new JButton("Trier par titre");
        JButton bouton2 = new JButton("Trier par auteur");

        bouton.setEnabled(true);
        bouton.addActionListener(new BoutonSortByArtist());
        bouton2.setEnabled(true);
        bouton.addActionListener(new BoutonSortByTitle());

        barreDeMenus.add(bouton);
        barreDeMenus.add(bouton2);

        return barreDeMenus;
    }

    /*
     * Vide la barre de menu, puis la remplit avec les valeurs
     * contenues dans listeMusique.
     */
    private void fillMenu()
    {
        menu.removeAll();
        for (Morceau m : listeMusique)
        {
            JMenuItem menuItem = new JMenuItem(m.nom);
            menu.add(menuItem);
            menuItem.addActionListener(new ActionListener()
            {
               public void actionPerformed(ActionEvent event)
               {
                   container.removeAll();
                   morceau1 = new Morceau(m.fichier, m.nom);
                   morceau1.ajouteMorceau(container);
                   container.revalidate();
               }
            });
        }
    }

    /**
     *   Charge les données du fichier "sonsBienLourds/listeMorceaux.csv".
     */
    private void initCollection()
    {
        try {
            Scanner sc = new Scanner(new File("sonsBienLourds/listeMorceaux.csv"));
            while(sc.hasNext())
            {
                String line = sc.nextLine();
                Scanner sc2 = new Scanner(line).useDelimiter(",");
                String instruOuVoix = sc2.next();
                String artiste = sc2.next();
                String titre = sc2.next();
                String path = "sonsBienLourds/"+sc2.next();
                Morceau m = new Morceau(path, titre);
                m.auteur = artiste;
                listeMusique.add(m);
                listeMusique.sort(new MorceauAuthorComparator());

            }
        } catch (FileNotFoundException e) {
            listeMusique.add(new Morceau("", "Impossible de charger les musiques"));
        }
    }

    class BoutonSort implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
        }
    }

    class BoutonSortByArtist extends BoutonSort {
        public void actionPerformed(ActionEvent arg0) {
            listeMusique.sort(new MorceauAuthorComparator());
            fillMenu();
        }
    }

    class BoutonSortByTitle extends BoutonSort {
        public void actionPerformed(ActionEvent arg0) {
            listeMusique.sort(new MorceauNameComparator());
            fillMenu();
        }
    }
}

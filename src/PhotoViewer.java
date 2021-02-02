/**
 * Homework 5
 * Jiahao Luo, jl2yt
 * 
 * Sources: Piazza, Youtube, StackOverflow.com
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;

/**
 * Class that creates a GUI with displays 5 photographs and allows the user to rate each photograph. Also allows user to sort photographs by
 * date, caption, or rating. 
 *
 */
public class PhotoViewer extends JFrame{
    private PhotographContainer imageAlbum;
    private JLabel rating = new JLabel();
    private JRadioButton r1 = new JRadioButton("1");
    private JRadioButton r2 = new JRadioButton("2");
    private JRadioButton r3 = new JRadioButton("3");
    private JRadioButton r4 = new JRadioButton("4");
    private JRadioButton r5 = new JRadioButton("5");
    private ArrayList<JPanel> panelList = new ArrayList<>();
    private ArrayList<JLabel> labelList = new ArrayList<>();
    private ArrayList<JTextArea> infoList = new ArrayList<>();    
    private JPanel thumbnail1 = new JPanel();
    private JPanel thumbnail2 = new JPanel();
    private JPanel thumbnail3 = new JPanel();
    private JPanel thumbnail4 = new JPanel();
    private JPanel thumbnail5 = new JPanel();
    private JLabel main = new JLabel();
    private static int currentindex = 1; // Used throughout the class to iterate through the 5 thumbnails and keep track of which one is the current large photograph. 
    private static Photograph mainphoto; // the large photograph 


    public PhotoViewer() {}

    public void addComponentsToPane(Container pane) {
        JPanel panel = new JPanel(); // This is the leftmost panel that contains the 5 thumbnails along with each thumbnails information.  
        panel.setBackground(Color.WHITE);

        // This creates the left JPanel which using the box layout stacks 5 JPanels representing the thumbnails ontop of each other.
        // Each thumbnail is its own JPanel with the actual photograph as a JLabel and caption/date/rating as a JTextArea.  
        Box thumbnails = Box.createVerticalBox();   // Found how to use a box layout on youtube
        thumbnail1.addMouseListener(new clickListener());
        thumbnail2.addMouseListener(new clickListener());
        thumbnail3.addMouseListener(new clickListener());
        thumbnail4.addMouseListener(new clickListener());
        thumbnail5.addMouseListener(new clickListener());
        JLabel t1 = new JLabel();
        JLabel t2 = new JLabel();
        JLabel t3 = new JLabel();
        JLabel t4 = new JLabel();
        JLabel t5 = new JLabel();
        JTextArea in1 = new JTextArea();
        JTextArea in2 = new JTextArea();
        JTextArea in3 = new JTextArea();
        JTextArea in4 = new JTextArea();
        JTextArea in5 = new JTextArea();

        // I made 3 ArrayLists. One to store the different JPanels(panelList) which within
        // it would have a JPanel(labelList containing the actual photographs) and JTextArea(infoList containing caption, date, rating). 
        panelList.add(thumbnail1);
        panelList.add(thumbnail2);
        panelList.add(thumbnail3);
        panelList.add(thumbnail4);
        panelList.add(thumbnail5);
        labelList.add(t1);
        labelList.add(t2);
        labelList.add(t3);
        labelList.add(t4);
        labelList.add(t5);
        infoList.add(in1);
        infoList.add(in2);
        infoList.add(in3);
        infoList.add(in4);
        infoList.add(in5);

        thumbnails.add(thumbnail1);
        thumbnails.add(thumbnail2);
        thumbnails.add(thumbnail3);
        thumbnails.add(thumbnail4);
        thumbnails.add(thumbnail5);
        panel.add(main);

        // Initial code to put each photograph in the correct place by iterating through the previously created 3 ArrayLists. This code is 
        // reused later on in the 3 sort by date/caption/rating JButtons so I made it a method called sortchanger().  
        for (int i = 0; i < panelList.size(); i++) {
            if (i == 0) {
                try { 
                    mainphoto = imageAlbum.photos.get(i);
                    BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                    Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                    main.setIcon(new ImageIcon(scaled));
                    ratingchanger();
                    BufferedImage test = ImageIO.read(new File(mainphoto.getFilename()));
                    Image scaled2 = test.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    labelList.get(i).setIcon(new ImageIcon(scaled2));
                    (panelList.get(i)).add(labelList.get(i));
                    infoList.get(i).setText("Caption: " + imageAlbum.photos.get(i).getCaption() +"\n" + "Date: " + imageAlbum.photos.get(i).getDateTaken() + "\n" + "Rating: "+ imageAlbum.photos.get(i).getRating());
                    panelList.get(i).add(infoList.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try { 
                    BufferedImage myPicture = ImageIO.read(new File(imageAlbum.photos.get(i).getFilename()));
                    Image scaled = myPicture.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    labelList.get(i).setIcon(new ImageIcon(scaled));
                    (panelList.get(i)).add(labelList.get(i));
                    infoList.get(i).setText("Caption: " + imageAlbum.photos.get(i).getCaption() +"\n" + "Date: " + imageAlbum.photos.get(i).getDateTaken() + "\n" + "Rating: "+ imageAlbum.photos.get(i).getRating());
                    panelList.get(i).add(infoList.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // This is the bottom panel underneath the large photograph that uses JRadioButtons to allow the user to change the photograph's rating. 
        JPanel panel2 = new JPanel();
        rating.setText("Photo rating: ");
        panel2.add(rating);

        // This section adds the JRadioButtons for rating the photograph to panel2.
        ButtonGroup ratings = new ButtonGroup();
        ratings.add(r1);
        ratings.add(r2);
        ratings.add(r3);
        ratings.add(r4);
        ratings.add(r5);
        r1.setActionCommand("radio");
        r2.setActionCommand("radio");
        r3.setActionCommand("radio");
        r4.setActionCommand("radio");
        r5.setActionCommand("radio");
        r1.addActionListener(new ButtonListener());
        r2.addActionListener(new ButtonListener());
        r3.addActionListener(new ButtonListener());
        r4.addActionListener(new ButtonListener());
        r5.addActionListener(new ButtonListener());
        panel2.add(r1);
        panel2.add(r2);
        panel2.add(r3);
        panel2.add(r4);
        panel2.add(r5);


        // This section adds the top JPanel with controls for exiting, going to the next or previous picture, along with changing the order of the
        // photographs by date, caption, or rating. 
        Box listeners = Box.createHorizontalBox();
        JButton exit = new JButton("Exit");
        exit.addActionListener(new CloseListener());
        JButton previous = new JButton("Previous");
        previous.setActionCommand("PREVIOUS");
        JButton next = new JButton("Next");
        next.setActionCommand("NEXT");
        JButton sortbydate = new JButton("Sort-By-Date");
        sortbydate.setActionCommand("DATE");
        JButton sortbycaption = new JButton("Sort-By-Caption");
        sortbycaption.setActionCommand("CAPTION");
        JButton sortbyrating = new JButton("Sort-By-Rating");
        sortbyrating.setActionCommand("RATING");

        previous.addActionListener(new ButtonListener());
        next.addActionListener(new ButtonListener());
        sortbydate.addActionListener(new ButtonListener());
        sortbycaption.addActionListener(new ButtonListener());
        sortbyrating.addActionListener(new ButtonListener());

        listeners.add(exit);
        listeners.add(previous);
        listeners.add(next);
        listeners.add(sortbydate);
        listeners.add(sortbycaption);
        listeners.add(sortbyrating);


        // Adds the different JPanels to the overall pane
        pane.add(panel, BorderLayout.EAST);
        pane.add(panel2, BorderLayout.SOUTH);
        pane.add(thumbnails, BorderLayout.WEST);
        pane.add(listeners, BorderLayout.NORTH);
    }


    private void createAndShowGUI() {
        //Create and set up the window.
        this.setTitle("Homework 5");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set up the content pane.
        this.addComponentsToPane(this.getContentPane());
        //Display the window.
        this.pack();
        this.setVisible(true);
    }

    // ActionListener class for the exit JButton to close the GUI
    private class CloseListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    // ActionListener for the rest of the JButtons along the top of the screen (Next, Previous, Sort by Date/Caption/Rating). Also is the ActionListener
    // for changing the rating of the photograph using JRadioButtons along the bottom of the screen. 
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("radio")) { // When the user presses one of the JRadioButtons, changes the rating of the current photograph to whichever button was clicked. 
                if (r1.isSelected()) {
                    mainphoto.setRating(1);
                    ratingchanger();
                    infochanger();
                }
                if (r2.isSelected()) {
                    mainphoto.setRating(2);
                    ratingchanger();
                    infochanger();
                }
                if (r3.isSelected()) {
                    mainphoto.setRating(3);
                    ratingchanger();
                    infochanger();
                }
                if (r4.isSelected()) {
                    mainphoto.setRating(4);
                    ratingchanger();
                    infochanger();
                }
                if (r5.isSelected()) {
                    mainphoto.setRating(5);
                    ratingchanger();
                    infochanger();
                }
            }
            if (e.getActionCommand().equals("NEXT")) { // Whenever the user presses the Next JButton, changes the large photograph to the next one. Uses the CurrentIndex instance variable to keep track of which photograph comes next. 
                if (currentindex == 5) {
                    currentindex = 0;
                }
                for (int i = 0; i < panelList.size(); i++) {
                    if (i == currentindex) {
                        try { 
                            mainphoto = imageAlbum.photos.get(i);
                            BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                            Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                            main.setIcon(new ImageIcon(scaled));
                            ratingchanger();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                if (currentindex < 5) {
                    currentindex++; 
                }
            } 
            if (e.getActionCommand().equals("PREVIOUS")) { // Whenever the user presses the Previous JButton, changes the large photograph to the previous one. Uses the CurrentIndex instance variable to keep track of which photograph comes previous. 
                currentindex--;
                if (currentindex == 0 ) {
                    currentindex = 5;
                }
                if (currentindex < 0) {
                    currentindex = 0;
                }
                for (int i = 0; i < panelList.size(); i++) {
                    if (currentindex == 0) {
                        try { 
                            mainphoto = imageAlbum.photos.get(4);
                            BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                            Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                            main.setIcon(new ImageIcon(scaled));
                            ratingchanger();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        currentindex = 5;
                        break;
                    }
                    if (i == (currentindex-1)) {
                        try { 
                            mainphoto = imageAlbum.photos.get(i);
                            BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                            Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                            main.setIcon(new ImageIcon(scaled));
                            ratingchanger();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            } 
            if (e.getActionCommand().equals("DATE")) { // Uses built-in Collections.sort method to sort photographs by date then uses sortchanger() method coded later on to reorganize the photographs.
                Collections.sort(imageAlbum.photos);
                sortchanger();
                currentindex = 1; // Resets currentindex to 1 so that the next and previous buttons will work. 
            }
            if (e.getActionCommand().equals("CAPTION")) { // Uses Collections.sort method along with the CompareByCaption comparator to sort photographs by caption then uses sortchanger() method to reorganize the photographs.
                Collections.sort(imageAlbum.photos, new CompareByCaption());
                sortchanger(); 
                currentindex = 1;
            }
            if (e.getActionCommand().equals("RATING")) { // Uses Collections.sort method along with the CompareByRating comparator to sort photographs by rating then uses sortchanger() method to reorganize the photographs.
                Collections.sort(imageAlbum.photos, new CompareByRating());
                sortchanger();
                currentindex = 1; 
            }
        }

    }

    // MouseListener class for changing the large photograph to whichever thumbnail you clicked.  
    public class clickListener implements MouseListener { //Found how to write a MouseListener on StackOverflow.com
        public void mouseClicked(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mousePressed(MouseEvent e) {
        }
        public void mouseReleased(MouseEvent e) {
            if (e.getSource().equals(thumbnail1)) {
                try { 
                    mainphoto = imageAlbum.photos.get(0);
                    BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                    Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                    main.setIcon(new ImageIcon(scaled));
                    ratingchanger();
                }                 
                catch (Exception e1) {
                    e1.printStackTrace();
                }
                currentindex = 1; 
            }
            if (e.getSource().equals(thumbnail2)) {
                try { 
                    mainphoto = imageAlbum.photos.get(1);
                    BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                    Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                    main.setIcon(new ImageIcon(scaled));
                    ratingchanger();
                }                 
                catch (Exception e1) {
                    e1.printStackTrace();
                }
                currentindex = 2; 
            }
            if (e.getSource().equals(thumbnail3)) {
                try { 
                    mainphoto = imageAlbum.photos.get(2);
                    BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                    Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                    main.setIcon(new ImageIcon(scaled));
                    ratingchanger();
                }                 
                catch (Exception e1) {
                    e1.printStackTrace();
                }
                currentindex = 3;
            }
            if (e.getSource().equals(thumbnail4)) {
                try { 
                    mainphoto = imageAlbum.photos.get(3);
                    BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                    Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                    main.setIcon(new ImageIcon(scaled));
                    ratingchanger();
                    currentindex = 4;
                }                 
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            if (e.getSource().equals(thumbnail5)) {
                try { 
                    mainphoto = imageAlbum.photos.get(4);
                    BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                    Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                    main.setIcon(new ImageIcon(scaled));
                    ratingchanger();
                    currentindex = 5;
                }                 
                catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }   
    }


    // Method used to reorganize the photographs whenever sort by date/caption/rating buttons are pressed. 
    public void sortchanger() {
        for (int i = 0; i < panelList.size(); i++) {
            if (i == 0) {
                try { 
                    mainphoto = imageAlbum.photos.get(i);
                    BufferedImage myPicture = ImageIO.read(new File(mainphoto.getFilename()));
                    Image scaled = myPicture.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
                    main.setIcon(new ImageIcon(scaled));
                    ratingchanger();
                    BufferedImage test = ImageIO.read(new File(imageAlbum.photos.get(i).getFilename()));
                    Image scaled2 = test.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    labelList.get(i).setIcon(new ImageIcon(scaled2));
                    (panelList.get(i)).add(labelList.get(i));
                    infoList.get(i).setText("Caption: " + imageAlbum.photos.get(i).getCaption() +"\n" + "Date: " + imageAlbum.photos.get(i).getDateTaken() + "\n" + "Rating: "+ imageAlbum.photos.get(i).getRating());
                    panelList.get(i).add(infoList.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                try { 
                    BufferedImage myPicture = ImageIO.read(new File(imageAlbum.photos.get(i).getFilename()));
                    Image scaled = myPicture.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
                    labelList.get(i).setIcon(new ImageIcon(scaled));
                    (panelList.get(i)).add(labelList.get(i));
                    infoList.get(i).setText("Caption: " + imageAlbum.photos.get(i).getCaption() +"\n" + "Date: " + imageAlbum.photos.get(i).getDateTaken() + "\n" + "Rating: "+ imageAlbum.photos.get(i).getRating());
                    panelList.get(i).add(infoList.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method used whenever the large photograph is changed to change the JRadioButtons in the bottom JPanel to the current rating. 
    public void ratingchanger() {
        if (mainphoto.getRating() == 1) {
            r1.setSelected(true);
        }
        if (mainphoto.getRating() == 2) {
            r2.setSelected(true);
        }
        if (mainphoto.getRating() == 3) {
            r3.setSelected(true);
        }
        if (mainphoto.getRating() == 4) {
            r4.setSelected(true);
        }
        if (mainphoto.getRating() == 5) {
            r5.setSelected(true);
        }
    }

    // Method used to update a thumbnail's rating whenever a JRadioButton is used to change the rating of the large photograph. 
    public void infochanger() {
        int i = imageAlbum.photos.indexOf(mainphoto);
        infoList.get(i).setText("Caption: " + mainphoto.getCaption() +"\n" + "Date: " + mainphoto.getDateTaken() + "\n" + "Rating: "+ mainphoto.getRating());
        panelList.get(i).add(infoList.get(i));
    }


    public static void main(String[] args) {
        PhotoViewer myViewer = new PhotoViewer();
        String imageDirectory =
                "images\\";
        Photograph p1 = new Photograph("Chainsaw", imageDirectory + "Chainsaw.jpg", "2018-06-30", 1);
        Photograph p2 = new Photograph("Phoenix", imageDirectory + "Phoenix.jpg", "2019-06-30", 4);
        Photograph p3 = new Photograph("Reptile", imageDirectory + "Reptile.jpg", "2017-06-30", 3);
        Photograph p4 = new Photograph("GGBridge", imageDirectory + "GGBridge.jpg", "2016-06-30", 5);
        Photograph p5 = new Photograph("Strawberry", imageDirectory + "Strawberry.jpg", "2015-06-30", 2);
        myViewer.imageAlbum = new PhotoLibrary("Test Library", 1);
        myViewer.imageAlbum.addPhoto(p1);
        myViewer.imageAlbum.addPhoto(p2);
        myViewer.imageAlbum.addPhoto(p3);
        myViewer.imageAlbum.addPhoto(p4);
        myViewer.imageAlbum.addPhoto(p5);
        Collections.sort(myViewer.imageAlbum.photos);
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.createAndShowGUI() );
    }

}

/**
 * Homework 5
 * Jiahao Luo, jl2yt
 * 
 * Sources: Piazza
 */

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Associates a Photograph with a caption and filename. Used in the Person class to make a feed of Photographs.
 * Has a method to compare if Photographs are equal to each other.
 */
public class Photograph implements Comparable<Photograph>{
    /** 
     * Holds the caption of the photograph
     */
    private String caption;
    /**
     * Holds the filename of the photograph
     */
    private final String filename;
    /**
     * Holds the date the Photograph was taken in "YYYY-MM-DD" format
     */
    private String dateTaken;
    /**
     * Holds the rating of the Photograph as an int from 0 to 5 (inclusive)
     */
    private int rating;
    private File imageFile;

    /** 
     * Constructor for a Photograph that takes 2 strings as parameters
     * @param caption Any String to represent the Photograph's caption
     * @param filename Any String to represent the Photograph's filename
     */
    public Photograph (String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
        imageFile = new File(filename);
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    /**
     * Constructor for a Photograph that takes 3 strings and an int as parameters
     * @param filename Any String to represent the Photograph's filename
     * @param caption Any String to represent the Photograph's caption
     * @param dateTaken A String in the "YYYY-MM-DD" format to represent the date the Photograph was taken
     * @param rating An int from 0 to 5 to represent the Photograph's rating
     */
    public Photograph (String caption, String filename, String dateTaken, int rating) {
        this.filename = filename;
        this.caption = caption;
        this.dateTaken = dateTaken;
        this.rating = rating;
        imageFile = new File(filename);
    }

    /**
     * @return the caption of the Photograph object
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @return the filename of the Photograph object
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @return the dateTaken of the Photograph object
     */
    public String getDateTaken() {
        return dateTaken;
    }

    /**
     * @return the rating of the Photograph object
     */
    public int getRating() {
        return rating;
    }

    /**
     * Changes the caption of the Photograph to the String parameter
     * @param caption A String that represents the new caption of the Photograph
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * Changes the rating of the Photograph to the int parameter if it is an int between 0 and 5
     * @param rating An int representing the new rating of the Photograph
     */
    public void setRating(int rating) {
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        }
    }

    /**
     * Method for Photograph that returns true if the Photograph object passed to equals() with caption and filename strings match (are equal to)
     * the caption and filename strings of the current Photograph object; otherwise, return false.
     * @param o Any object that is passed into the method
     * @return true if the Photograph object passed to equals() and o are equal and false if they are not equal
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Photograph)) {
            return false;
        }
        Photograph otherPhotograph = (Photograph) o;
        if (otherPhotograph.getCaption().equals(this.getCaption()) && otherPhotograph.getFilename().equals(this.getFilename())) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return a string representation of a Photograph in the form "Caption: xyz, Filename: zyx"
     */
    public String toString() {
        return "Caption: " + caption + ", Filename: " + filename; 
    }

    @Override
    public int hashCode() {
        return (this.caption + "---" + this.filename).hashCode();
    }

    /**
     * Compares the dateTaken of the current Photograph object with the parameter p. If the
     * current object’s dateTaken is before p’s, return a negative number. If p’s is earlier, return
     * a positive number. If they are equal, return the comparison of the this object’s caption
     * with p’s caption.
     * @param p Any photograph object to compare to the current object.
     * @return -1 or 1. Returns -1 if the current photo's dateTaken is before the parameter's dateTaken, and 1 if after.
     * If the dateTakens are equal, method looks at the photograph's captions. If current photo's caption comes first in alphabetical order,
     * returns -1, if after, returns 1. 
     */
    public int compareTo(Photograph p) {
        Integer thisphotoyear = Integer.parseInt(this.getDateTaken().substring(0, 4)); 
        Integer thisphotomonth = Integer.parseInt(this.getDateTaken().substring(5, 7));
        Integer thisphotoday = Integer.parseInt(this.getDateTaken().substring(8));
        Integer pphotoyear = Integer.parseInt(p.getDateTaken().substring(0, 4));
        Integer pphotomonth = Integer.parseInt(p.getDateTaken().substring(5, 7));
        Integer pphotoday = Integer.parseInt(p.getDateTaken().substring(8));
        Calendar currentphoto = new GregorianCalendar(thisphotoyear, thisphotomonth, thisphotoday); 
        Calendar parameterphoto = new GregorianCalendar(pphotoyear, pphotomonth, pphotoday);
        if (currentphoto.compareTo(parameterphoto) == 0) {
            for (int i = 0; i < this.getCaption().length(); i++) {
                if (this.getCaption().charAt(i) < p.getCaption().charAt(i)) {
                    return -1;
                }
                if (this.getCaption().charAt(i) > p.getCaption().charAt(i)) {
                    return 1;
                }    
            }        
        }
        return currentphoto.compareTo(parameterphoto); // Used same compareTo() method used in the getPhotosBetween method. If currentphoto<parameterphoto, returns -1. if they are equal returns 0. And if currentphoto>parameterphoto, returns 1.
    }
    
    

}

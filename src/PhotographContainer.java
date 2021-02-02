/**
 * Homework 5
 * Jiahao Luo, jl2yt
 * 
 * Sources: Piazza
 */

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class PhotographContainer {
    /**
     * A String to hold the Album's name
     */
    protected String name;
    /**
     * An ArrayList<Photograph> to hold an Album's Photographs
     */
    protected ArrayList<Photograph> photos = new ArrayList<Photograph>();

    public PhotographContainer(String name) {
        this.name = name;
    }


    /**
     * @return the name of the ALbum as a String
     */
    public String getName () {
        return name;
    }

    /**
     * Sets the name of the Album as the String parameter
     * @param name the new name of the Album as a String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the Album object's list of Photographs as an ArrayList<Photograph>
     */
    public ArrayList<Photograph> getPhotos() {
        return photos;
    }

    /**
     * Checks to see if p is in the current object's photos. If it isn't, it will add it and return true, 
     * if it is, it won't add it and return false. 
     * @param p. Any value of type Photograph
     * @return true if param p was not in the current object's photos and false if it was
     */
    public boolean addPhoto(Photograph p) {
        if (p == null) {
            return false;
        }
        else if (photos.contains(p)) {
            return false;
        } 
        else {
            photos.add(p);
            return true;
        }
    }

    /**
     * Checks to see if the current object's photos contains param p
     * @param p. Any value of type Photograph
     * @return true if the current object has p in its photos. Otherwise return false.
     */
    public boolean hasPhoto(Photograph p) {
        if (photos.contains(p)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks to see if param p is in the current PhotoLibrary object’s list of Photographs. 
     * If it is, remove p from the current object’s list. Return true if the Photograph was removed or false if it was not there.
     * @param p. Any value of type Photograph
     * @return true if p is in the current PhotoLibrary's photos and false if it isn't
     */
    public boolean removePhoto(Photograph p) {
        if (photos.contains(p)) {
            photos.remove(p);
            return true;
        } else {
            return false;
        }
    }

    /** 
     * @return the number of Photographs in the current album
     */
    public int numPhotographs() {
        return getPhotos().size(); 
    }

    /**
     * Checks to see if the id of the current PhotoLibrary object is equal to the id of param o. 
     * @param o. Any object
     * @return true if the current object's id is equal to the param o's id, and false if it isn't
     */
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Album)) {
            return false;
        }
        Album otherAlbum = (Album) o;
        if (otherAlbum.getName() == this.getName()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return a String representation of a Album in the form of "Name: xyz, photos: []"
     */
    public String toString() {
        return "Name: " + name + ", photos: " + photos;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    /**
     * @param rating the rating of the album
     * @return an ArrayList of all the Photographs from photos that has a rating
     * equal to or higher than the parameter. If the rating is incorrectly formatted,
     * return null. 
     */
    public ArrayList<Photograph> getPhotos(int rating){
        ArrayList<Photograph> ratinglist = new ArrayList<Photograph>();
        for (int i = 0; i < getPhotos().size(); i++) {
            if (getPhotos().get(i).getRating() >= rating && getPhotos().get(i).getRating() <= 5) {
                ratinglist.add(getPhotos().get(i));
            }
            if (getPhotos().get(i).getRating() < 0 || getPhotos().get(i).getRating() >5 ) {
                return null;
            }
        }
        return ratinglist;
    }

    /**
     * Returns an ArrayList<Photograph> of all the Photographs in the PhotoLibrary's photos if
     * that Photograph was taken in the same year as the parameter
     * @param year Any int to represent the year the Photograph was taken
     * @return ArrayList<Photograph> of all the Photographs in the PhotoLibrary's photos if
     * that Photograph was taken in the same year as the parameter. Returns null if the Photograph's
     * year date is formatted wrong. 
     */
    public ArrayList<Photograph> getPhotosInYear(int year) {
        if (year < 0) {
            return null;
        }
        ArrayList<Photograph> yearlist = new ArrayList<Photograph>();
        for (int i = 0; i < getPhotos().size(); i++) {
            try {
                Integer photoyear = Integer.parseInt(getPhotos().get(i).getDateTaken().substring(0, 4));
                if (photoyear == year) {
                    yearlist.add(getPhotos().get(i));
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return yearlist;
    }

    /**
     * Returns an ArrayList<Photograph> of all the Photographs in the PhotoLibrary's photos if
     * that Photograph was taken in the same year and same month as the parameters
     * @param year Any int to represent the year the Photograph was taken
     * @param month Any int to represent the month the Photograph was taken
     * @return ArrayList<Photograph> of all the Photographs in the PhotoLibrary's photos if
     * that Photograph was taken in the same year and month as the parameters. Returns null if the Photograph's
     * year date or month date is formatted wrong. 
     */
    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {
        if (month > 12 || year < 0) {
            return null;
        }
        ArrayList<Photograph> monthyearlist = new ArrayList<Photograph>();
        for (int i = 0; i < getPhotos().size(); i++) {       
            try {           //Checks to see if the Photograph's date is formatted in the correct "YYYY-MM-DD" format and if not for whatever reason, returns null.
                if ((!(getPhotos().get(i).getDateTaken().substring(4,5).equals("-")) || !(getPhotos().get(i).getDateTaken().substring(7,8)).equals("-"))){
                    return null;
                }
                Integer photoyear = Integer.parseInt(getPhotos().get(i).getDateTaken().substring(0, 4));
                Integer photomonth = Integer.parseInt(getPhotos().get(i).getDateTaken().substring(5, 7));
                if (photomonth > 12) {
                    return null;
                }
                if (photoyear == year && photomonth == month) {
                    monthyearlist.add(getPhotos().get(i));
                }
            } 
            catch (NumberFormatException e) {
                return null;
            }
            catch (NullPointerException e) {
                return null;
            }
        }
        return monthyearlist;
    }

    /**
     * A method that tries to return an ArrayList<Photograph> of all the Photographs taken between the beginDate and 
     * endDate. Also checks to see if the beginDate and endDate are correctly formatted in the "YYYY-MM-DD" format and
     * the endDate is actually after the beginDate, and if not, it returns null. 
     * @param beginDate a String to represent the begin date
     * @param endDate a String to represent the end date
     * @return an ArrayList<Photograph> of Photographs in the PhotoLibrary's photos that were taken between the beginDate 
     * and endDate. If the beginDate or endDate are incorrectly formatted or the endDate is before the beginDate, returns null.
     */
    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate){
        ArrayList<Photograph> betweenlist = new ArrayList<Photograph>();
        if (getPhotos() == null) {
            return null;
        }
        try { //Checks to see if the Photograph's date, parameter beginDate, and parameter endDate are formatted in the correct "YYYY-MM-DD" format and if not for whatever reason, returns null.
            if (beginDate.length() != 10 || endDate.length() != 10) {
                return null;
            }
            if ((!(beginDate.substring(4,5).equals("-")) || !(beginDate.substring(7,8)).equals("-"))){
                return null;
            }
            if ((!(endDate.substring(4,5).equals("-")) || !(endDate.substring(7,8)).equals("-"))){
                return null;
            }
            Integer beginYear = Integer.parseInt(beginDate.substring(0,4));
            Integer beginMonth = Integer.parseInt(beginDate.substring(5,7));
            Integer beginDay = Integer.parseInt(beginDate.substring(8));
            Integer endYear = Integer.parseInt(endDate.substring(0,4));
            Integer endMonth = Integer.parseInt(endDate.substring(5,7));
            Integer endDay = Integer.parseInt(endDate.substring(8));
            Calendar begin = new GregorianCalendar(beginYear, beginMonth, beginDay); 
            Calendar end = new GregorianCalendar(endYear, endMonth, endDay);
            if (beginYear < 0 || beginMonth > 12 || beginDay > 31) {
                return null;
            }
            if (endYear < 0 || endMonth > 12 || endDay > 31) {
                return null;
            }
            if (begin.compareTo(end) == 1) { //Used compareTo() method that TA said to use on Piazza. If date1.compareTo(date2) and date1 is after date2, it will return 1, therefore if it this is true, then it means beginDate is after endDate, leading to null being returned. 
                return null;
            }
            for (int i = 0; i < getPhotos().size(); i++) {
                if ((!(getPhotos().get(i).getDateTaken().substring(4,5).equals("-")) || !(getPhotos().get(i).getDateTaken().substring(7,8)).equals("-"))){
                    return null;
                }
                if (getPhotos().get(i).getDateTaken().length() != 10) {
                    return null;
                }
                Integer photoyear = Integer.parseInt(getPhotos().get(i).getDateTaken().substring(0, 4));
                Integer photomonth = Integer.parseInt(getPhotos().get(i).getDateTaken().substring(5, 7));
                Integer photoday = Integer.parseInt(getPhotos().get(i).getDateTaken().substring(8));
                if (photoyear < 0 || photomonth > 12 || photoday >31) {
                    return null;
                }
                Calendar current = new GregorianCalendar(photoyear, photomonth, photoday);
                if (current.compareTo(begin) == 1 && end.compareTo(current) == 1) {
                    betweenlist.add(getPhotos().get(i));
                }
                if ((current.compareTo(begin) == 0 && end.compareTo(current) == 1) || (current.compareTo(begin) == 1 && end.compareTo(current) == 0)) {
                    betweenlist.add(getPhotos().get(i));
                }
            }
        }
        catch (NumberFormatException e) {
            return null;
        }
        catch (NullPointerException e) {
            return null;
        }
        return betweenlist;
    }

}

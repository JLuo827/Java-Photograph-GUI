/**
 * Homework 5
 * Jiahao Luo, jl2yt
 * 
 * Sources: Piazza
 */

import java.util.*;


/**
 * The PhotoLibrary class uses the Photograph class to associate a feed of photos with a PhotoLibrarys's name and id. 
 * Has multiple methods to change the Photographs in the PhotoLibrary's feed and compare with another PhotoLibrary's feed. 
 */
public class PhotoLibrary extends PhotographContainer{

    /**
     * Holds the id of the PhotoLibrary as an int
     */
    private final int id;
    /**
     * Holds the Photographs of the PhotoLibrary as an ArrayList<Photograph>
     */
    private HashSet<Album> albums = new HashSet<Album>();

    public PhotoLibrary(String name, int id) {
        super(name);
        this.id = id;
    }

    /**
     * @return the id of the PhotoLibrary object 
     */
    public int getId() {
        return id;
    }

    /**
     * @return the HashSet of the PhotoLibrary object's Albums
     */
    public HashSet<Album> getAlbums() {
        return albums;
    }

    /**
     * Sets the name of the PhotoLibrary
     * @param name Any string to represent the PhotoLibrary's name
     */
    public void setName(String name){
        this.name = name;
    }

    @Override
    /**
     * Checks to see if param p is in the current PhotoLibrary object’s list of Photographs. 
     * If it is, remove p from the current object’s list. Return true if the Photograph was removed or false if it was not there.
     * @param p. Any value of type Photograph
     * @return true if p is in the current PhotoLibrary's photos and false if it isn't
     */
    public boolean removePhoto(Photograph p) {
        if (photos.contains(p)) {
            photos.remove(p);
            for (Album e: albums) {
                e.removePhoto(p);
            }
            return true;
        } 
        else {
            return false;
        }
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
        if (!(o instanceof PhotoLibrary)) {
            return false;
        }
        PhotoLibrary otherPhotoLibrary = (PhotoLibrary) o;
        if (otherPhotoLibrary.getId() == this.getId()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * @return a string representation of a PhotoLibrary in the form of "Name: xyz, id: 123, photos: [], List of Album Names: []"
     */
    public String toString() {
        ArrayList<String> listofnames = new ArrayList<String>();
        for (Album e: albums) {
            listofnames.add(e.getName());
        }
        return "Name: " + name + ", id: " + id + ", photos: " + photos+ ", List of Album names: " + listofnames;
    }

    /**
     * Uses the equals() method of the Photograph class to determine if two
     * Photograph objects represent the same photograph, and returns an ArrayList<Photograph> of
     * all the shared Photographs between param a's photos and param b's photos. 
     * @param a. Any value of type PhotoLibrary
     * @param b. Any value of type PhotoLibrary
     * @return an ArrayList<Photograph> of the photos that both PhotoLibrary a and PhotoLibrary b have in their photos
     */
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b){
        ArrayList<Photograph> same = new ArrayList<Photograph>();
        for (int i = 0; i < a.numPhotographs(); i++) {
            for (int j = 0; j < b.numPhotographs(); j++) {
                if (a.getPhotos().get(i).equals(b.getPhotos().get(j))) {
                    same.add(b.getPhotos().get(j));
                }
            }
        }
        return same;
    }

    /**
     * Uses the commonPhotos method of the PhotoLibrary class to determine how many Photographs
     * are shared between param a and param b and returns that number divided by the smaller of
     * the number of Photographs in param a or param b's photos as a float.
     * @param a. Any value of type PhotoLibrary
     * @param b. Any value of type PhotoLibrary
     * @return the number of shared Photographs between param a and param b divided by the smaller of
     * the number of Photographs in param a or param b's photos as a float. 
     * 
     */
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        int denominator = 0; 
        if (a.numPhotographs() == 0 || b.numPhotographs() == 0) {
            return 0.0;
        }
        else if(commonPhotos(a, b).size() == 0){
            return 0.0;
        }
        else { // Finds the smaller of the numPhotographs in a or b's photos and sets as denominator
            if (a.numPhotographs() < b.numPhotographs()) {
                denominator = a.numPhotographs();
            }
            else if (b.numPhotographs() < a.numPhotographs()) {
                denominator = b.numPhotographs();
            }
            else {
                denominator = a.numPhotographs();
            }
            return ((float) (commonPhotos(a, b)).size()) / denominator;
        }
    }

    /**
     * Creates a new Album with name albumName and adds it to the list of albums, only if an
     * Album with that name does not already exist. 
     * @param albumName A String to represent the name of the new Album
     * @return true if Album was added successfully and false if not.
     */
    public boolean createAlbum(String albumName) {
        Album newalbum = new Album(albumName);
        for (Album e: albums) {
            if (e.equals(newalbum)) {
                return false;
            }
        }
        getAlbums().add(newalbum);
        return true;
    }    

    /**
     * Removes the Album with name albumName if an Album with that name exists in the set
     * of albums.
     * @param albumName A String to represent the name of the Album you want to remove
     * @return true if the Album was removed successfully and false if not
     */
    public boolean removeAlbum(String albumName) {
        Album newalbum = new Album(albumName);
        for (Album e: albums) {
            if (e.equals(newalbum)) {
                albums.remove(e);
                return true;
            }
        }
        return false;
    }    

    /**
     * Add the Photograph p to the Album in the set of albums that has name albumName if and
     * only if it is in the PhotoLibrary’s list of photos and it was not already in that album.
     * @param p Any object of type Photograph
     * @param albumName A String to represent the name of an Album
     * @return true if the Photograph was added and false if not 
     */
    public boolean addPhotoToAlbum(Photograph p, String albumName) {
        Album newalbum = new Album(albumName);
        if (getPhotos().contains(p)) {
            for (Album e: albums) {
                if (e.equals(newalbum)) {
                    for (int i = 0; i < e.getPhotos().size(); i++) {
                        if(e.getPhotos().get(i).equals(p)) {
                            return false;
                        }
                    }
                    e.getPhotos().add(p);
                    return true;
                }
            }
        }
        return false; 
    }

    /**
     * Remove the Photograph p from the Album in the set of albums that has name albumName.
     * @param p Any object of type Photograph
     * @param albumName A String to represent the name of an ALbum
     * @return true if the Photograph was removed and false if not 
     */
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {
        Album newalbum = new Album(albumName);
        for (Album e: albums) {
            if (e.equals(newalbum)) {
                if (e.hasPhoto(p)) {
                    e.getPhotos().remove(p);
                    return true;
                }
            }
        }
        return false; 
    }

    /**
     * Given an album name, return the Album with that name
     * from the set of albums. If an album with that name is not found, return null.
     * @param albumName A string representing the albumName of the Album you want returned
     * @return an Album from the albums HashSet of the same name as the parameter, if there is one , else return null.
     */
    private Album getAlbumByName(String albumName) {
        Album newalbum = new Album(albumName);
        for (Album e : albums) {
            if (e.equals(newalbum));
            return e;
        }
        return null;
    }

}

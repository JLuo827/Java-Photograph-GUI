/**
 * Homework 5
 * Jiahao Luo, jl2yt
 * 
 * Sources: Piazza
 */

import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph> {

    /**
     * Implements the Comparator interface and compares two Photographs by rating (in descending order). 
     * If two ratings are identical, then compare by caption in alphabetical order.
     * @param a Any photograph object 
     * @param b Any photograph object
     * @return -1, 0, or 1. Returns -1 if the first parameter's rating is greater than the second parameter's or if they are equal, the first
     * parameter's caption is alphabetically before the second parameter's. Returns 0 if the rating and captions of both parameters are equal.
     * Returns 1 if the first parameter's rating is less than the second parameter's, or if they are equal, the second parameter's caption is alphabetically
     * before the first parameter's.  
     */
    public int compare(Photograph a, Photograph b) {
        if (a.getRating() == (b.getRating())) {
            for (int i = 0; i<a.getCaption().length(); i++) {
                if (a.getCaption().charAt(i) < b.getCaption().charAt(i)) {
                    return -1;
                }
                if (a.getCaption().charAt(i) > b.getCaption().charAt(i)) {
                    return 1;
                }    
            }
        }
        if (a.getRating() > b.getRating()) {
            return -1;
        }
        if (a.getRating() < b.getRating()) {
            return 1;
        }
        return 0;
    }
}    


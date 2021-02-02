/**
 * Homework 5
 * Jiahao Luo, jl2yt
 * 
 * Sources: Piazza
 */

import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph>{

    /**
     * Implements the Comparator interface and compares two Photographs by caption (in alphabetical order). 
     * If two captions are identical, then compare by rating, in descending order with the highest-rated photo first.
     * @param a Any photograph object 
     * @param b Any photograph object
     * @return -1, 0, or 1. Returns -1 if the first parameter's caption is alphabetically before the second's or if they are equal, the first
     * parameter's rating is greater than the second's. Returns 0 if the rating and captions of both parameters are equal.
     * Returns 1 if the first parameter's caption is alphabetically after the second parameter's, or if they are equal, 
     * the second parameter's rating is greater than the first parameter's.   
     */
    public int compare(Photograph a, Photograph b) {
        if (a.getCaption().equals(b.getCaption())) {
            if (a.getRating() > b.getRating()) {
                return -1;
            }
            if (a.getRating() < b.getRating()) {
                return 1;
            }
        }
        for (int i = 0; i < a.getCaption().length(); i++) {
            if (a.getCaption().charAt(i) < b.getCaption().charAt(i)) {
                return -1;
            }
            if (a.getCaption().charAt(i) > b.getCaption().charAt(i)) {
                return 1;
            }    
        }
        return 0;
    }
}

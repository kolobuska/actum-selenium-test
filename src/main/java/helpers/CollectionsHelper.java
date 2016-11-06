package helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CollectionsHelper {

    /**
     * Get n random elements from list
     *
     * @param list   list to explore
     * @param number num of elements to get
     * @return list with desired size
     */
    public static List getRandomElementsFromList(List list, int number) {

        if (number > list.size())
            throw new IllegalArgumentException("Number of elements can't be more than list size");

        List randomList = new ArrayList();
        Collections.shuffle(list);
        Iterator iter = list.iterator();
        for (int i = 0; i < number && iter.hasNext(); i++) {
            randomList.add(iter.next());
        }
        return randomList;
    }
}

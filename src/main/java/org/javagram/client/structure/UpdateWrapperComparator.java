package org.javagram.client.structure;

import java.util.Comparator;

public class UpdateWrapperComparator implements Comparator<UpdateWrapper> {
    
    @Override
    public int compare(UpdateWrapper o1, UpdateWrapper o2) {
        int result = Integer.compare(o1.getPts(), o2.getPts());
        if (result == 0) {
            result = Integer.compare(o2.getPtsCount(), o1.getPtsCount());
        }
        return result;
    }

}
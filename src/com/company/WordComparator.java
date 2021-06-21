package com.company;

import java.util.Comparator;

class WordComparator implements Comparator<Words> {

    public int compare(Words a, Words b){

        if(a.getCount()> b.getCount())
            return 1;
        else if(a.getCount()< b.getCount())
            return -1;
        else
            return 0;
    }
}
class WordKeyComparator implements Comparator<Words>{

    public int compare(Words a, Words b){

        return a.getNkey().compareTo(b.getNkey()) * (-1);
    }
}

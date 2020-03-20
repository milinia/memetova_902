package ru.kpfu.set;

import java.util.*;


public class NavigableSet<T> extends AbstractSet <T> implements java.util.NavigableSet <T> {

    /**
     * class attribute
     */
    private ArrayList<T> data;
    private Comparator<T> comparator;
    private int cursor;
    /**
     * class constructors
     */
    public NavigableSet(List<T> data1, Comparator<T> comparator) {
        this.data = (ArrayList<T>) data1;
        this.comparator = comparator;
    }

    public NavigableSet(ArrayList<T> arrayList,Comparator<T> comparator){
        this.data = (ArrayList<T>) arrayList.clone();
        this.comparator = comparator;
        this.cursor = 0;

    }

    public NavigableSet(Comparator<T> comparator) {
        this.comparator = comparator;
        this.data = new ArrayList<>();
        this.cursor = 0;
    }

    public NavigableSet(Comparator<T> comparator, Collection<T> collection) {

        this.comparator = comparator;
        this.data = new ArrayList<>();
        this.cursor = 0;

        while (collection.iterator().hasNext()){
            int i = 0;
            this.data.add(collection.iterator().next());
            i++;
        }
    }

    public NavigableSet() { }

    /**
     * set and get of cursor
     */
    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    /**
     *Returns the greatest element in this set less than or equal to
     *the given element, or {@code null} if there is no such element.
     */
    @Override
    public T lower(T t) {

            if (t.getClass().equals(data.getClass())){

                int number = -1;

                for (int i = 0; i < data.size(); i++) {

                    if (data.get(i).equals(t)){
                        number = i;
                        break;
                    }
                }
                if (number != -1){

                    if(data.get(number).equals(data.get(number - 1))){
                        return null;
                    }
                    return data.get(number - 1);
                }
                else {
                    return null;
                }
            }
            else {
                throw new ClassCastException("invalid value of given element");
            }
    }
    /**
     * Returns the least element in this set greater than or equal to
     * the given element, or {@code null} if there is no such element.
     */
    @Override
    public T floor(T t) {

        if (t.getClass().equals(data.getClass())){

            int number = -1;

            for (int i = 0; i < data.size(); i++) {

                if (data.get(i).equals(t)){
                    number = i;
                    break;
                }
            }
            if (number != -1){
                return data.get(number - 1);
            }
            else {
                return null;
            }
        }
        else {
            throw new ClassCastException("invalid value of given element");
        }
    }
    /**
     * Returns the least element in this set strictly greater than the
     * given element, or {@code null} if there is no such element.
     */
    @Override
    public T ceiling(T t) {

        if (t.getClass().equals(data.getClass())){

            int number = -1;

            for (int i = 0; i < data.size(); i++) {

                if (data.get(i).equals(t)){
                    number = i;
                    break;
                }
            }
            if (number != -1){
                return data.get(number + 1);
            }
            else {
                return null;
            }
        }
        else {
            throw new ClassCastException("invalid value of given element");
        }
    }
    /**
     * Retrieves and removes the first (lowest) element,
     * or returns {@code null} if this set is empty.
     */
    @Override
    public T higher(T t) {

        if (t.getClass().equals(data.getClass())){

            int number = -1;

            for (int i = 0; i < data.size(); i++) {

                if (data.get(i).equals(t)){
                    number = i;
                    break;
                }
            }
            if (number != -1){

                if(data.get(number).equals(data.get(number - 1))){
                    return null;
                }
                return data.get(number + 1);
            }
            else {
                return null;
            }
        }
        else {
            throw new ClassCastException("invalid value of given element");
        }
    }
    /**
     * Retrieves and removes the first (lowest) element,
     * or returns {@code null} if this set is empty.
     */
    @Override
    public T pollFirst() {

        if (data.isEmpty()){
            return null;
        }

        for (int i = 0; i < data.size(); i++){
            data.set(i,data.get(i + 1));
        }
        return data.get(0);
    }
    /**
     * Retrieves and removes the last (highest) element,
     * or returns {@code null} if this set is empty.
     */
    @Override
    public T pollLast() {

        if (data.isEmpty()){
            return null;
        }

        T number = data.get(data.size() - 1);
        data.remove(data.size() - 1);
        return number;
    }
    /**
     * Returns an iterator over the elements in this set, in ascending order.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            @Override
            public boolean hasNext() {

                if (data.size() < getCursor() + 1) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {

                if (data.size() < getCursor() + 1) {
                    setCursor(getCursor() + 1);
                    return data.get(getCursor() + 1);
                }
                else {
                    throw new java.util.NoSuchElementException("Next element does not exist");
                }
            }
        };
    }
    /**
     * Returns a reverse order view of the elements contained in this set.
     */
    @Override
    public java.util.NavigableSet<T> descendingSet() {

        for (int i = 0; i < data.size(); i++){
            T number = data.get(i);
            data.set(i,data.get(data.size() - i));
            data.set(data.size() - i,number);
        }
        return new NavigableSet<>(data,comparator);
    }
    /**
     * Returns an iterator over the elements in this set, in descending order.
     * Equivalent in effect to {@code descendingSet().iterator()}.
     */
    @Override
    public Iterator<T> descendingIterator() {

        setCursor(data.size());

        return new Iterator<T>(){

            @Override
            public boolean hasNext() {

                if ((data.size() > getCursor() - 1) & (getCursor() > 0)) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {

                if ((data.size() > getCursor() - 1) & (getCursor() > 0)) {
                    setCursor(getCursor() - 1);
                    return data.get(getCursor() - 1);
                }
                else {
                    throw new java.util.NoSuchElementException("Next element does not exist");
                }
            }
        };
    }
    /**
     * Returns a view of the portion of this set whose elements range from
     * {@code fromElement} to {@code toElement}.  If {@code fromElement} and
     * {@code toElement} are equal, the returned set is empty unless {@code
     * fromInclusive} and {@code toInclusive} are both true.
     */
    @Override
    public java.util.NavigableSet<T> subSet(T fromElement, boolean fromInclusive, T toElement, boolean toInclusive) {

        int start = 0;
        int end = 0;
        List<T> data1;

        if (fromElement.equals(toElement)) {
            return new NavigableSet<>();
        }
        else{
            for (int i = 0; i < data.size(); i++) {

                if (data.get(i).equals(fromElement)){
                    start = i;
                    break;
                }
            }
            for (int i = 0; i < data.size(); i++) {

                if (data.get(i).equals(toElement)){
                    end = i;
                    break;
                }
            }

            if (fromInclusive){

                if (toInclusive){
                    data1 = data.subList(start,end);
                    data1.set(end,data.get(end));
                }
                else{
                    data1 = data.subList(start,end);
                }
            }
            else {
                if (toInclusive){
                    data1 = data.subList(start,end);
                    data1.remove(0);
                }
                else {
                    data1 = data.subList(start,end);
                    data1.remove(0);
                }
            }
            return new NavigableSet<T>(data1,comparator);
        }
    }
    /**
     * Returns a view of the portion of this set whose elements are less than
     * (or equal to, if {@code inclusive} is true) {@code toElement}.
     */
    @Override
    public java.util.NavigableSet<T> headSet(T toElement, boolean inclusive) {

        int number = 0;

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).equals(toElement)){
                number = i;
                break;
            }
        }

        T element = data.get(number);
        data.subList(0,number);
        if (inclusive){
            data.add(number,element);
        }

        return new NavigableSet<>(data,comparator);
    }
    /**
     * Returns a view of the portion of this set whose elements are greater
     * than (or equal to, if {@code inclusive} is true) {@code fromElement}.
     */
    @Override
    public java.util.NavigableSet<T> tailSet(T fromElement, boolean inclusive) {

        int number = 0;

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).equals(fromElement)){
                number = i;
                break;
            }
        }

        T element = data.get(number);
        int size1 = data.size();
        data.subList(number,size1);
        if (inclusive){
            data.add(number,element);
        }

        return new NavigableSet<>(data,comparator);
    }
    /**
     *Equivalent to {@code subSet(fromElement, true, toElement, false)}.
     */
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        int start = 0;
        int end = 0;

        if (fromElement.equals(toElement)) {
            return null;
        }
        else {
            for (int i = 0; i < data.size(); i++) {

                if (data.get(i).equals(fromElement)) {
                    start = i;
                    break;
                }
            }
            for (int i = 0; i < data.size(); i++) {

                if (data.get(i).equals(toElement)) {
                    end = i;
                    break;
                }
            }
            data.subList(start,end);
            return new NavigableSet<T>(data, comparator);
        }
    }

    /**
     *Equivalent to {@code headSet(toElement, false)}
     */
    @Override
    public SortedSet<T> headSet(T toElement) {

        int number = 0;

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).equals(toElement)){
                number = i;
                break;
            }
        }

        T element = data.get(number);
        data.subList(0,number - 1);

        return new NavigableSet<>(data,comparator);
    }

    /**
     * Equivalent to {@code tailSet(fromElement, true)}
     */
    @Override
    public SortedSet<T> tailSet(T fromElement) {

        int number = 0;

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).equals(fromElement)){
                number = i;
                break;
            }
        }

        T element = data.get(number);
        int size1 = data.size();
        data.subList(number,size1);
        return new NavigableSet<>(data,comparator);
    }

    /**
     * Allows to compare items in the set
     */
    @Override
    public Comparator<? super T> comparator() {

        return new Comparator<T>() {

            @Override
            public int compare(Object o1, Object o2) {
                return o1.toString().compareTo(o2.toString());
            }

        };
    }

    /**
     * Return first element in set
     */
    @Override
    public T first() {
        return data.get(0);
    }
    /**
     * Return last element in set
     */
    @Override
    public T last() {
        return data.get(data.size() - 1);
    }
    /**
     * Return size of set
     */
    @Override
    public int size() {
        return data.size();
    }
}

package com.example.course_project;

import java.sql.ResultSet;
import java.util.*;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class RecordTable implements ObservableList<Record> {
    public RecordTable(ResultSet resultSet){
        try {
            int j = 0;
            while (resultSet.next()){
                Record record = new Record();
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    record.addRecord(resultSet.getMetaData().getColumnName(i),resultSet.getString(i));
                }
                add(record);
            }
        }catch (java.sql.SQLException sqlException){
            throw new RuntimeException(sqlException);
        }
    }
    public void view(){
        for (int i = 0; i < size(); i++) {
            get(i).view();
        }
    }
    public String getValue(int row,String key){
        return get(row).find(key);
    }

    @Override
    public void addListener(ListChangeListener<? super Record> listChangeListener) {

    }

    @Override
    public void removeListener(ListChangeListener<? super Record> listChangeListener) {

    }

    @Override
    public boolean addAll(Record... records) {
        return false;
    }

    @Override
    public boolean setAll(Record... records) {
        return false;
    }

    @Override
    public boolean setAll(Collection<? extends Record> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Record... records) {
        return false;
    }

    @Override
    public boolean retainAll(Record... records) {
        return false;
    }

    @Override
    public void remove(int i, int i1) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Record> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Record record) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Record> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Record> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Record get(int index) {
        return null;
    }

    @Override
    public Record set(int index, Record element) {
        return null;
    }

    @Override
    public void add(int index, Record element) {

    }

    @Override
    public Record remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<Record> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Record> listIterator(int index) {
        return null;
    }

    @Override
    public List<Record> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}

package ru.otus;

import java.util.*;

public class CustomerService {

    private final TreeMap<Customer, String> map = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> entry = map.firstEntry();
        return new AbstractMap.SimpleEntry<>(new Customer(entry.getKey()), entry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> entry = map.higherEntry(customer);
        return entry != null ?
                new AbstractMap.SimpleEntry<>(new Customer(entry.getKey()), entry.getValue()) :
                null;
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}

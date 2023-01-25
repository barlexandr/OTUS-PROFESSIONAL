package ru.otus;

import java.util.Deque;
import java.util.LinkedList;

public class CustomerReverseOrder {

    private final Deque<Customer> queue = new LinkedList<>();

    //todo: 2. надо реализовать методы этого класса
    //надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"

    public void add(Customer customer) {
        queue.add(customer);
    }

    public Customer take() {
        return queue.pollLast();
    }
}

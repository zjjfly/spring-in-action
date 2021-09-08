package com.springinaction.common;

import java.util.List;

/**
 * Created by zjjfly on 2017/2/27.
 */
public class Users {
    private int count;
    private int start;
    private int total;
    private List<User> users;

    @Override
    public String toString() {
        return "Users{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", users=" + users +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

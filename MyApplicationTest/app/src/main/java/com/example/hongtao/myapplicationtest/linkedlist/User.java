package com.example.hongtao.myapplicationtest.linkedlist;

/**
 * Created by hongtao on 17/5/24.
 */

public class User {
        User next;
        String  name;
    public User(String username){
        this.name=username;
    }
        public User(String username,User next){
            this.name=username;
            this.next=next;
        }
        public User next(){
            return next;
        }



    public void setNext(User next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "username="+name+",next name="+(next!=null?next.getName():"null");
    }
}

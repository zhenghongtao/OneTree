package com.example.hongtao.myapplicationtest.linkedlist;

import android.util.Log;

import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by hongtao on 17/5/24.
 */

public class TestLinkedList {
    User user;
    User user6;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser6() {
        return user6;
    }

    public void setUser6(User user6) {
        this.user6 = user6;
    }

    public void initData(){
        user=new User("张三");
        User user1=new User("张三1");
        User user2=new User("张三2");
        User user3=new User("张三3");
        User user4=new User("张三4");
        User user5=new User("张三5");
        user6=new User("张三6");
        user.setNext(user1);
        user1.setNext(user2);
        user2.setNext(user3);
        user3.setNext(user4);
        user4.setNext(user5);
        user5.setNext(user6);

    }
    /**
     *   递归反转 链表
     */

    public User  reverseList(User user){
        if(user!=null && user.next()!=null){
            User reverseUser=reverseList(user.next());
            user.next().setNext(user);
            user.setNext(null);
            return reverseUser;
        }else{
            return user;
        }
    }

    /**
     * 迭代反转
     * @param user
     */
    public User reverseList2(User user){
        User pre=user;
        User curr=user.next();
        while(curr!=null){
            User tempnext=curr.next();
            curr.setNext(pre);
            pre=curr;
            curr=tempnext;
        }
        user.setNext(null);
        return pre;
    }

    /**
     * 判断列表是否有环
     */
    public boolean isHasCircle(User head){
        User pre=head;
        User curr=head;
        while(curr!=null && curr.next!=null){
            pre=pre.next;
            curr=curr.next.next;
            if(curr==null)return false;
            if(pre.getName().equals(curr.getName())){
                return true;
            }
        }
        return true;// 如果只有一个元素，也可以说明为环
    }

    public boolean isHasCircle2(User head){
        User curr=head;
        if(curr!=null && curr.next==null) return true;
        HashMap<String,User>  hm=new HashMap<>();
        while (curr!=null ){
             if(hm.containsKey(curr.getName())){
                 return true;
             }else{
                 hm.put(curr.getName(),curr);
                 curr=curr.next();
             }
        }
        return false;
    }

    public  User getCircleUser(User head){
        User pre=head;
        User curr=head;
        int firstlengt=0;
        while(curr.next!=null){
            pre=pre.next;
            curr=curr.next.next;
            firstlengt++;
            if(curr==pre){//碰撞点
                break;//
            }
        }
        int length=0;
        pre=head;
        while(pre!=curr){
            pre=pre.next;
            curr=curr.next;
            length++;
            if(pre==curr){//环入口点
                break;
            }

        }
        return pre;
    }


    /**
     * 打印链表
     *
     * @param head
     */
    public  void printList(User head) {
        if (head == null)
            return;
        while (head != null) {
            Log.e("Linkedlist", " "+head.toString());
            head = head.next();
        }
    }
}

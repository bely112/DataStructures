package com.datastructure.ds.interview.linkedlists;

import com.datastructure.ds.interview.impl.LinkedListNode;

public class PalindromeL3 {

    boolean isPalindrome(LinkedListNode head) {
        int len = lengthNodes(head);
        Result r = check(head, len);
        return r.result;
    }

    Result check(LinkedListNode head, int len) {
        if (head == null || len <= 0) { // even number of nodes
            return new Result(head, true);
        } else if (len == 1) { // odd number of nodes
            return new Result(head.next, true);
        }

        Result res = check(head.next, len - 2);

        if (!res.result || res.node == null) {
            return res;
        }

        res.result = (head.data == res.node.data);
        res.node = res.node.next;

        return res;
    }

    int lengthNodes(LinkedListNode n) {
        int count = 0;
        while (n != null) {
            count++;
            n = n.next;
        }
        return count;
    }
}

class Result {
    public LinkedListNode node;
    public boolean result;

    public Result() {
    }

    public Result(LinkedListNode node, boolean result) {
        this.node = node;
        this.result = result;
    }
}
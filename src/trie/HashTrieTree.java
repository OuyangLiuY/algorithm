package trie;

import java.util.HashMap;

// 使用hash表实现
public class HashTrieTree {
    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> next;

        public Node2() {
            pass = 0;
            end = 0;
            next = new HashMap<>();
        }
    }

    public Node2 root;

    public HashTrieTree() {
        root = new Node2();
    }

    // 添加某个字符串，可以重复添加，每次算1个
    public void insert(String word) {
        if (word == null) {
            return;
        }
        Node2 node = root;
        node.pass++;
        char[] chars = word.toCharArray();
        int index = 0;
        // 从左往右遍历 a , b , c
        for (char chr : chars) {
            index = chr;
            // 路径不存在，则新建
            if (!node.next.containsKey(index)) {
                node.next.put(index, new Node2());
            }
            // 拿到下个节点node，并指向当前node
            node = node.next.get(index);
            node.pass++;
        }
        node.end++;
    }

    // 查询某个字符串在结构中还有几个
    public int search(String word) {
        if (word == null) {
            return 0;
        }
        Node2 node = root;
        char[] chars = word.toCharArray();
        for (char chr : chars) {
            if (!node.next.containsKey((int) chr)) {
                return 0;
            }
            node = node.next.get((int) chr);
        }
        return node.end;
    }

    // 删掉某个字符串，可以重复删除，每次算1个
    public void delete(String word) {
        // 有这个word则删除，否则不处理
        if (search(word) != 0) {
            Node2 node = root;
            node.pass--;
            char[] chars = word.toCharArray();
            for (char chr : chars) {
                // pass 为0,那么下个节点数据就不存在了...
                if (--node.next.get((int) chr).pass == 0) {
                    node.next.remove((int) chr);
                    return;
                }
                node = node.next.get((int) chr);
            }
            node.end--;
        }
    }

    // 查询有多少个字符串，是以str做前缀的
    public int prefixNumber(String word) {
        Node2 node = root;
        char[] chars = word.toCharArray();
        for (char chr : chars) {
            // 看下节点是否还在呢
            if (!node.next.containsKey((int) chr)) {
                return 0;
            }
            node = node.next.get((int) chr);
        }
        return node.pass;
    }
}
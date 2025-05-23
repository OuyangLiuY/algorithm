package trie;

import base.PreTreeNode;

// 也叫前缀树
// 使用前缀树实现
public class TrieTree {


    public static PreTreeNode root;

    public TrieTree() {
        root = new PreTreeNode();
    }

    // 将当前给定的字符串加入到前缀树中
    public void insert(String str) {
        if (str == null) return;
        PreTreeNode node = root;
        node.pass++;
        // 假如 a,b,c 进来
        for (char cur : str.toCharArray()) {
            int path = cur - 'a'; // 得到路径，也就是索引
            if (node.next[path] == null) {
                node.next[path] = new PreTreeNode();
            }
            node = node.next[path];
            node.pass++;
        }
        node.end++;
    }

    // 查询某个字符串出现的次数，并返回
    public int search(String str) {
        if (str == null) return 0;
        PreTreeNode node = root;

        for (char cur : str.toCharArray()) {
            int path = cur - 'a'; // 得到路径，也就是索引
            if (node.next[path] == null) {
                return 0;
            }
            node = node.next[path];
        }
        return node.end;
    }

    // 查询有多少个字符串，是以str做前缀的，
    public int prefixNumber(String str) {
        if (str == null) return 0;
        PreTreeNode node = root;
        for (char cur : str.toCharArray()) {
            int path = cur - 'a'; // 得到路径，也就是索引
            if (node.next[path] == null) {
                return 0;
            }
            node = node.next[path];
        }
        return node.pass;
    }

    //在这个前缀树中，删除当前str
    public void delete(String str) {
        if (search(str) != 0) { // 保证当前字符串，存在。
            PreTreeNode node = root;
            node.pass--; // 一开始头节点值先减去1
            for (char cur : str.toCharArray()) {
                int path = cur - 'a';
                // 找到下一个节点，并且减去1， 如为0， 说明只需要将后续next设置为空，即可
                // 并且结束当前循环。
                if (--node.next[path].pass == 0) {
                    node.next[path] = null;
                    return;
                }
                node = node.next[path];
            }
            node.end--; // 最后end 减去1
        }
    }
}

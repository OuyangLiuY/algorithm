package trie;

import utils.Utils;

import java.util.HashMap;

// 比较器
public class Right {
    public HashMap<String, Integer> box;

    public Right() {
        box = new HashMap<>();
    }

    public void insert(String word) {
        if (word == null)
            return;
        if (box.containsKey(word)) {
            box.put(word, box.get(word) + 1);
        } else {
            box.put(word, 1);
        }
    }

    public int search(String word) {
        if (!box.containsKey(word)) {
            return 0;
        }
        return box.get(word);
    }

    public void delete(String word) {
        if (box.containsKey(word)) {
            if (box.get(word) == 1) {
                box.remove(word);
            } else {
                box.put(word, box.get(word) - 1);
            }
        }
    }

    public int prefixNumber(String word) {
        int count = 0;
        for (String cur : box.keySet()) {
            if (cur.startsWith(word)) {
                count += box.get(cur);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = Utils.generateRandomStringArray(arrLen, strLen);
            TrieTree trie1 = new TrieTree();
            HashTrieTree trie2 = new HashTrieTree();
            Right right = new Right();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                trie1.insert(arr[j]);
                trie2.insert(arr[j]);
                right.insert(arr[j]);
                if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                    right.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    int ans3 = right.search(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops 0.75!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    int ans3 = right.prefixNumber(arr[j]);
                    if (ans1 != ans2 || ans2 != ans3) {
                        System.out.println("Oops else!" + i);
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}

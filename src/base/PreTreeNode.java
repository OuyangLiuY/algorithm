package base;

public class PreTreeNode {

    public int pass;
    public int end;
    public PreTreeNode[] next;
    public PreTreeNode(){
        // 只考虑 26 个字母的情况
        next = new PreTreeNode[26];
    }
}

package LeetCode;
import java.util.TreeSet;

//前缀和后缀搜索
public class WordsFilter {
    //字典树的根节点
    TireNode root = new TireNode();
    public WordsFilter(String[] words) {
        for (int i = 0; i < words.length; i++) {
            insert(words[i], i);
        }
    }
    
    public int f(String pref, String suff) {
        return find(pref, suff);
    }

    public void insert(String word, int index) {
        //插入 apple{apple, pple{apple
        for (int i = 0; i < word.length(); i++) {
            TireNode node = root;
            String wordToBeInserted = word.substring(i) + "{" + word;
            for (int j = 0; j < wordToBeInserted.length(); j++) {
                char ch = wordToBeInserted.charAt(j);
                if (node.next[ch - 'a'] == null) {
                    TireNode newNode = new TireNode();
                    node.next[ch - 'a'] = newNode;
                    newNode.set.add(index);
                    node = node.next[ch - 'a'];
                } else {
                    node.next[ch - 'a'].set.add(index);
                    node = node.next[ch - 'a'];
                }
            }
        }
    }

    public int find(String pref, String suff) {
        String wordToBeFound = suff + "{" + pref;
        TireNode node = root;
        for (int i = 0; i < wordToBeFound.length(); i++) {
            char ch = wordToBeFound.charAt(i);
            if (node.next[ch - 'a'] == null) {
                return -1;
            } else {
                node = node.next[ch - 'a'];
            }
        }
        return node.set.last();
    }

    public static void main(String[] args) {
        String[] words = {"apple"};
        WordsFilter tem = new WordsFilter(words);
        tem.f("a", "e");
    }
}

//字典树
class TireNode {
    //存储该节点经过的字符串编号
    TreeSet<Integer> set;
    TireNode[] next;
    public TireNode() {
        set = new TreeSet<>();
        //包含小写字母和'{'
        next = new TireNode[27];
    }
}

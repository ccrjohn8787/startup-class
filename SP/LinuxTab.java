package Snapchat; /**
 * Created by siyuzhan on 4/30/16.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
/*
 * 其实就是让实现一个trie，但是要注意一下有多个可能结果时的情况。小哥当时要求实现的效果跟command line behavior一样
 * 
 * 就是考你平时用tab completion的时候有没有注意它的反应细节。你可以开command line试一下，如果有多个match的时候它是不做补全的，
 * 而是把所有结果给你返回。E.g. 输入ap后调用tab completion，如果当前目录下有application，apple两个match,就都列出来而不是补全至其中一个。
 * 这里我是加了一个boolean helper找trie当前位置的子node个数.
 */
public class LinuxTab {
	// TODO: This is not a good solution. Cannot add all words to treenode since would be too much memory usage
	//       Need to do dfs with backtracking to find all 'isWord' node
    public List<String> getResults(String in, Set<String> files) {
        Trie root = new Trie('*');
        for (String word: files) {
            addWord(word, root);
        }
        List<String> result = new ArrayList<>();
        for (char cc: in.toCharArray()) {
            if (!root.children.containsKey(cc)) {
                break;
            }
            root = root.children.get(cc);
        }
        result.addAll(root.words);
        return result;
    }

    public class Trie {
        char c;
        HashMap<Character, Trie> children;
        boolean isWord;
        List<String> words;
        public Trie(char c) {
            this.c = c;
            this.children = new HashMap<>();
            this.isWord = false;
            this.words = new ArrayList<>();
        }
    }

    public void addWord(String word, Trie root) {
        for (char cc: word.toCharArray()) {
            if (!root.children.containsKey(cc)) {
                root.children.put(cc, new Trie(cc));
            }
            root = root.children.get(cc);
            root.words.add(word);
        }
        root.isWord = true;
    }
}

import java.io.*;
import java.util.*;
 
public class Solution{
  public List<String> getKEditDistance(String[] words, String target, int k) {
    List<String> result = new ArrayList<>();
     
    if (words == null || words.length == 0 || 
        target == null || target.length() == 0 || 
        k < 0) {
      return result;
    }
     
    Trie trie = new Trie();
    for (String word : words) {
      trie.add(word);
    }
     
    TrieNode root = trie.root;
     
    int[] prev = new int[target.length() + 1];
    for (int i = 0; i < prev.length; i++) {
      prev[i] = i;
    }
     
    getKEditDistanceHelper("", target, k, root, prev, result);
     
    return result;
  }
   
  private void getKEditDistanceHelper(String curr, String target, int k, TrieNode root, 
                                      int[] prevDist, List<String> result) {
    if (root.isLeaf) {
      if (prevDist[target.length()] <= k) {
        result.add(curr);
      } else {
        return;
      }
    }
     
    for (int i = 0; i < 26; i++) {
      if (root.children[i] == null) {
        continue;
      }
       
      int[] currDist = new int[target.length() + 1];
      currDist[0] = curr.length() + 1;
      for (int j = 1; j < prevDist.length; j++) {
        if (target.charAt(j - 1) == (char) (i + 'a')) {
          currDist[j] = prevDist[j - 1];
        } else {
          currDist[j] = Math.min(Math.min(prevDist[j - 1], prevDist[j]), 
                                 currDist[j - 1]) + 1;
        }
      }
       
      getKEditDistanceHelper(curr + (char) (i + 'a'), target, k, 
         root.children[i], currDist, result);
    }
  }
   
  public static void main(String[] args) {
    Solution solution = new Solution();
    String[] input = new String[]{"abcd", "abc", "abd", "ad"};
    String target = "ac";
    int k = 1;
     
    List<String> result = solution.getKEditDistance(input, target, k);
     
    for (String s : result) {
      System.out.println(s);
    }
  }
   
  class TrieNode {
    TrieNode[] children;
    boolean isLeaf;
     
    public TrieNode() {
      children = new TrieNode[26];
       
    }
  }
   
  class Trie {
    TrieNode root;
     
    public Trie() {
      root = new TrieNode();
    }
     
    // Add a word into trie
    public void add(String s) {
      if (s == null || s.length() == 0) {
        return;
      }
       
      TrieNode p = root;
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (p.children[c - 'a'] == null) {
          p.children[c - 'a'] = new TrieNode();
        }
         
        if (i == s.length() - 1) {
          p.children[c - 'a'].isLeaf = true;
        }
         
        p = p.children[c - 'a'];
      }
    }
  }
}

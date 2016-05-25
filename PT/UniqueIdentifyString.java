public class UniqueIdentString {
    public List<String> uniqueIdentify(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        Trie root = new Trie('*');
        for (String word: list) {
            addWord(root, word);
        }
        List<String> result = new ArrayList<>();
        for (String word: list) {
            Trie head = root;
            int idx = 0;
            while (idx < word.length()) {
                head = head.children.get(word.charAt(idx));
                idx++;
                if (head.numWords == 1) {
                    break;
                }
            }
            result.add(word.substring(0, idx));
        }
        return result;
    }

    public class Trie {
        Character c;
        HashMap<Character, Trie> children;
        int numWords;

        public Trie (char c) {
            this.c = c;
            this.numWords = 0;
            this.children = new HashMap<>();
        }
    }

    public void addWord(Trie root, String word) {
        Trie head = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!head.children.containsKey(c)) {
                head.children.put(c,  new Trie(c));
            }
            head = head.children.get(c);
            head.numWords++;
        }
    }

package com.company;

import org.w3c.dom.Node;

import java.util.function.Function;

public class Trie {
    private NodeTrie root;


    public Trie() {
        root = new NodeTrie();
    }

    public void insert(String word) {
        NodeTrie node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            Function function = k -> new NodeTrie();
            node = node.getChildren().computeIfAbsent(ch, function);
        }
        node.setWord(true);
    }

    public boolean contains(String word) {
        NodeTrie node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            node = node.getChildren().get(ch);

            if (node == null) {
                return false;
            }
        }

        return node.isWord();
    }

    private boolean delete(NodeTrie node, String word, int position) {
        if (word.length() == position) {
            if (!node.isWord())
                return false;
        }

        node.setWord(false);

        char ch = word.charAt(position);
        NodeTrie child = node.getChildren().get(ch);
        if (child == null) {
            return false;
        }

        boolean deleteChild = delete(child, word, position + 1);

        if (deleteChild && !child.isWord()) {
            node.getChildren().remove(ch);
        }

     return false;
}



    }









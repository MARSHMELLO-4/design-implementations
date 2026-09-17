package com.aman.AbuseMasker.service;

import com.aman.AbuseMasker.model.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


class TrieNode {
    Map<Character, TrieNode> children;
    boolean isEndOfWord;

    public TrieNode(){
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }
}

class Trie {
    private final TrieNode root;

    public Trie() {
        root = new TrieNode(); // The root is always an empty node
    }

    /**
     * Inserts a word into the Trie.
     * Time Complexity: O(L) where L is the length of the word.
     */
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            // If the character doesn't exist, create a new node
            current = current.children.computeIfAbsent(ch, k -> new TrieNode());
        }
        // Mark the end of the word
        current.isEndOfWord = true;
    }

    /**
     * Pattern Match 1: Exact Match Search
     * Time Complexity: O(L)
     */
    public boolean searchExact(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return false; // Character path doesn't exist
            }
        }
        return current.isEndOfWord; // Must be a complete word
    }

    /**
     * Pattern Match 2: Prefix Matching (e.g., Autocomplete)
     * Time Complexity: O(L)
     */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            current = current.children.get(ch);
            if (current == null) {
                return false;
            }
        }
        return true; // The prefix path exists in the Trie
    }

    /**
     * Pattern Match 3: Wildcard Matching
     * Supports '.' as a wildcard matching any single character.
     */
    public boolean searchWildcard(String pattern) {
        return wildcardHelper(pattern, 0, root);
    }

    private boolean wildcardHelper(String pattern, int index, TrieNode current) {
        if (current == null) return false;
        if (index == pattern.length()) return current.isEndOfWord;

        char ch = pattern.charAt(index);

        if (ch == '.') {
            // Wildcard behavior: check every possible child at this level
            for (TrieNode child : current.children.values()) {
                if (wildcardHelper(pattern, index + 1, child)) {
                    return true;
                }
            }
            return false;
        } else {
            // Exact character match behavior
            TrieNode nextNode = current.children.get(ch);
            return wildcardHelper(pattern, index + 1, nextNode);
        }
    }
}


@Service
public class AbuseMaskerService {

    private List<String> abusiveWords = List.of(
            "shit",
            "stupid",
            "fuck"
    );

    private Trie root = new Trie();

    public AbuseMaskerService(){
        //if the object of this is created then we immediatly have to create the trie for it
        createTrieList();
    }


    public String maskMessage(String messageContent){
        return Arrays.stream(messageContent.split(" "))
                .map(word -> {
                    String cleanedWord = word.toLowerCase();
                    return mask(cleanedWord);
                })
                .collect(Collectors.joining(" "));
    }



    private String mask(String word){
        //if found in the created trie then mask it
        if(root.searchExact(word)){
            return "*".repeat(word.length());
        }

        return word;
    }

    private void createTrieList(){
        for(String word : abusiveWords){
            root.insert(word);
            System.out.println(word + " inserted in trie");
        }
    }

}

package com.test;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    private List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            int i = index;
            int wordLength = 0;

            // Find all words that fit in one line
            while (i < words.length && wordLength + words[i].length() + (i - index) <= maxWidth) {
                wordLength += words[i].length();
                i++;
            }
            int numberOfWords = i - index;
            int numberOfGaps = numberOfWords - 1;
            int totalSpaces = maxWidth - wordLength;

            StringBuilder sb = new StringBuilder();
            // Last line OR only one word
            if (i == words.length || numberOfWords == 1) {
                for (int k = index; k < i; k++) {
                    sb.append(words[k]);
                    if (k != i - 1)
                        sb.append(" ");
                }
                while (sb.length() < maxWidth)
                    sb.append(" ");
            } else {
                int spacesPerGap = totalSpaces / numberOfGaps;
                int extraSpaces = totalSpaces % numberOfGaps;
                for (int k = index; k < i; k++) {
                    sb.append(words[k]);
                    if (k != i - 1) {
                        sb.append(" ".repeat(spacesPerGap));
                        if (extraSpaces > 0) {
                            sb.append(" ");
                            extraSpaces--;
                        }
                    }
                }
            }
            result.add(sb.toString());
            index = i;
        }
        return result;
    }
    public static void main(String[] args) {
        TextJustification obj = new TextJustification();
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> ans = obj.fullJustify(words, 16);
        ans.forEach(System.out::println);
    }
}
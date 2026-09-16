package com.test;

import java.util.*;

public class TextJustification1 {

    public List<String> fullJustify(String[] words, int maxWidth) {

        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {

            int lineLength = words[i].length();
            int j = i + 1;

            // Find all words that fit in current line
            while (j < words.length &&
                    lineLength + 1 + words[j].length() <= maxWidth) {

                lineLength += 1 + words[j].length();
                j++;
            }

            int numberOfWords = j - i;
            int totalWordLength = 0;

            for (int k = i; k < j; k++) {
                totalWordLength += words[k].length();
            }

            int totalSpaces = maxWidth - totalWordLength;

            StringBuilder sb = new StringBuilder();

            // Last line or only one word
            if (j == words.length || numberOfWords == 1) {

                for (int k = i; k < j; k++) {

                    sb.append(words[k]);

                    if (k != j - 1)
                        sb.append(" ");
                }

                while (sb.length() < maxWidth)
                    sb.append(" ");

            } else {

                int gaps = numberOfWords - 1;

                int spacePerGap = totalSpaces / gaps;

                int extraSpaces = totalSpaces % gaps;

                for (int k = i; k < j; k++) {

                    sb.append(words[k]);

                    if (k != j - 1) {

                        int spaces = spacePerGap;

                        if (extraSpaces > 0) {
                            spaces++;
                            extraSpaces--;
                        }

                        while (spaces-- > 0)
                            sb.append(" ");
                    }
                }
            }

            result.add(sb.toString());

            i = j;
        }

        return result;
    }

    public static void main(String[] args) {

        TextJustification1 obj = new TextJustification1();

        String[] words = {
                "This","is","an","example","of","text","justification."
        };

        List<String> ans = obj.fullJustify(words, 16);

        for (String s : ans)
            System.out.println("|" + s + "|");
    }
}

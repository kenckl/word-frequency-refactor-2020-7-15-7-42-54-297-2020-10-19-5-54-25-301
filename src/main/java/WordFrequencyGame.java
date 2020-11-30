import org.graalvm.compiler.word.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String DELIMITER = "\n";

    public String getResult(String sentence) {
//        if (sentence.split(WHITE_SPACE_REGEX).length == 1) {
//            return sentence + " 1";
//        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split(WHITE_SPACE_REGEX);

                List<WordFrequency> WordFrequencyList = new ArrayList<>();
                for (String word : words) {
                    WordFrequencyList.add(new WordFrequency(word, 1));
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> map = getListMap(WordFrequencyList);

                List<WordFrequency> wordCountList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : map.entrySet()) {
                      wordCountList.add(new WordFrequency(entry.getKey(), entry.getValue().size()));
                }

                wordCountList.sort((word1, word2) -> word2.getCount() - word1.getCount());

                StringJoiner wordFrequencyResult = new StringJoiner(DELIMITER);
                for (WordFrequency word : wordCountList) {
                    wordFrequencyResult.add(buildWordFrequencyLine(word));
                }
                return wordFrequencyResult.toString();
            } catch (Exception e) {


                return CALCULATE_ERROR;
            }
        //}
    }

    private String buildWordFrequencyLine(WordFrequency word) {
        return String.format("%s %d", word.getWord(), word.getCount());
    }


    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequency.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                map.put(wordFrequency.getWord(), arr);
            } else {
                map.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }


        return map;
    }


}

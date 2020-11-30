import org.graalvm.compiler.word.Word;

import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String DELIMITER = "\n";

    public String getResult(String sentence) {
              try {
                  List<WordFrequency> wordCountList = calculateWordFrequency(sentence);
                  wordCountList.sort((word1, word2) -> word2.getCount() - word1.getCount());

                  StringJoiner wordFrequencyResult = new StringJoiner(DELIMITER);
                  for (WordFrequency word : wordCountList) {
                      wordFrequencyResult.add(buildWordFrequencyLine(word));
                  }
                  return wordFrequencyResult.toString();
              }
             catch (Exception e) {

                return CALCULATE_ERROR;
            }
    }

    private String buildWordFrequencyLine(WordFrequency word) {
        return String.format("%s %d", word.getWord(), word.getCount());
    }

    private List<WordFrequency> calculateWordFrequency(String sentence){
        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE_REGEX));

        HashSet<String> uniqueWords = new HashSet<>(words);

        return uniqueWords.stream()
                .map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .collect(Collectors.toList());

    }
}

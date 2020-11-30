import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public String getResult(String sentence){


        if (sentence.split("\\s+").length==1) {
            return sentence + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = sentence.split("\\s+");

                List<WordFrequency> WordFrequencyList = new ArrayList<>();
                for (String s : words) {
                    WordFrequency wordFrequency = new WordFrequency(s, 1);
                    WordFrequencyList.add(wordFrequency);
                }

                //get the map for the next step of sizing the same word
                Map<String, List<WordFrequency>> map =getListMap(WordFrequencyList);

                List<WordFrequency> wordCountList = new ArrayList<>();
                for (Map.Entry<String, List<WordFrequency>> entry : map.entrySet()){
                    WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                    wordCountList.add(wordFrequency);
                }
                WordFrequencyList = wordCountList;

                WordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

                StringJoiner wordFrequencyResult = new StringJoiner("\n");
                for (WordFrequency word : WordFrequencyList) {
                    String wordFrequencyLine = word.getWord() + " " +word.getCount();
                    wordFrequencyResult.add(wordFrequencyLine);
                }
                return wordFrequencyResult.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }


    private Map<String,List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequency.getWord())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                map.put(wordFrequency.getWord(), arr);
            }

            else {
                map.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }


        return map;
    }


}

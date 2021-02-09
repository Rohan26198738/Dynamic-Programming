import java.util.HashMap;
import java.util.List;

/*
    Verify if is possible to target word be constructed by concatenating elements of the string array.
*/

public class Construct {

    public Construct() { }
    
    public boolean naiveCanConstruct(String target, List<String> wordBank) {
        if (target.isEmpty()) { return true; }
        for (String subWord : wordBank) {
            if (target.length() < subWord.length()) { continue; }
            if (!target.startsWith(subWord)) { continue; }
            if (naiveCanConstruct(target.replaceFirst(subWord, ""), wordBank)) {
                return true;
            } 
        }
        return false;
    }
    
    public boolean dynamicCanConstruct(String target, List<String> wordBank) {
        return dynamicCanConstruct(target, wordBank, new HashMap<>());
    }

    private boolean dynamicCanConstruct(String target, List<String> wordBank, HashMap<String, Boolean> memo) {
        if (memo.containsKey(target)) { return memo.get(target); }
        if (target.isEmpty()) { return true; }
        for (String subWord : wordBank) {
            if (target.length() < subWord.length()) { continue; }
            if (!target.startsWith(subWord)) { continue; }
            if (dynamicCanConstruct(target.replaceFirst(subWord, ""), wordBank, memo)) {
                memo.put(target, true);
                return true;
            }
        }
        memo.put(target, false);
        return false;
    }
    
    public int naiveCountConstruct(String target, List<String> wordBank) {
        if (target.isEmpty()) { return 1; }
        int sum = 0;
        for (String prefix : wordBank) {
            if (prefix.length() > target.length()) { continue; }
            if (!target.startsWith(prefix)) { continue; }
            sum += naiveCountConstruct(target.replaceFirst(prefix, ""), wordBank);
        }
        return sum;
    }

    public Integer dynamicCountConstruct(String target, List<String> wordBank) {
        return dynamicCountConstruct(target, wordBank, new HashMap<>());
    }

    private Integer dynamicCountConstruct(String target, List<String> wordBank, HashMap<String, Integer> memo) {
        if (memo.containsKey(target)) { return memo.get(target); }
        if (target.isEmpty()) { return 1; }
        Integer sum = 0;
        for (String prefix : wordBank) {
            if (prefix.length() > target.length()) { continue; }
            if (!target.startsWith(prefix)) { continue; }
            sum += dynamicCountConstruct(target.replaceFirst(prefix, ""), wordBank, memo);
        }
        memo.put(target, sum);
        return sum;
    }
    
}

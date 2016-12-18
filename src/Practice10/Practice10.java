package Practice10;

import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * Created by kraken on 20.11.16.
 */
public class Practice10 {
    public static void main(String[] args) {
      SearchDictionary s =new SearchDictionary();

        s.addWord("abcd");
        s.addWord("acdfs");
        s.addWord("anddd");
        s.addWord("blob");
        s.query("a*").forEach(System.out::println);

    }
}

class SearchDictionary {

    TreeSet<String> words;
    public SearchDictionary() {
        this.words=new TreeSet<>();

    }

    public void addWord(String word) {
        this.words.add(word);
    }

    public boolean delWord(String word) {
        return this.words.remove(word);
    }

    public boolean hasWord(String word) {
      return   this.words.contains(word);
    }


    public Stream<String> query(String query) {
        if (!query.endsWith("*"))
            return this.words.stream().filter(r->r.equals(query));
        return  this.words.stream().filter(r->r.startsWith(query.substring(0,query.length()-1)));

    }

    public int countWords() {
        return  this.words.size();
    }

}


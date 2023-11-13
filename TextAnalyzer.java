package lab8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class TextAnalyzer {
	// <word, its positions>
	private Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

	public TextAnalyzer(String fileName) throws IOException {
		this.load(fileName);
	}

	// load words in the text file given by fileName and store into map by using add
	// method in Task 2.1.
	// Using BufferedReader reffered in file TextFileUtils.java
	public void load(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;
		int index = 0;

		try {
			while ((line = reader.readLine()) != null) {
				StringTokenizer tokenizer = new StringTokenizer(line);
				while (tokenizer.hasMoreTokens()) {
					String word = tokenizer.nextToken();
					int value = index + 1;
					if (!tokenizer.hasMoreTokens()) {
						value = -value;
					}
					add(word, value);
					index++;
				}
			}
		} finally {
			reader.close();
		}
	}

	// In the following method, if the word is not in the map, then adding that word
	// to the map containing the position of the word in the file. If the word is
	// already in the map, then its word position is added to the list of word
	// positions for this word.
	// Remember to negate the word position if the word is at the end of a line in
	// the text file

	public void add(String word, int position) {
		if (map.containsKey(word)) {
			ArrayList<Integer> values = map.get(word);
			values.add(position);
		} else {
			ArrayList<Integer> values = new ArrayList<>();
			values.add(position);
			map.put(word, values);
		}
	}

	// This method should display the words of the text file along with the
	// positions of each word, one word per line, in alphabetical order
	public void displayWords() {
		TreeMap<String, ArrayList<Integer>> word = new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		word.putAll(map);
		Set<Map.Entry<String, ArrayList<Integer>>> set = word.entrySet();
		System.out.println(Arrays.toString(set.toArray()));
	}

	// This method will display the content of the text file stored in the map
	public void displayText() {
		Set<String> setKey = map.keySet();
		List<Integer> pos = new ArrayList<>();
		List<String> strings = new ArrayList<>();
		for (String k : setKey) {
			pos.addAll(map.get(k));
			for (Integer temp : map.get(k)) {
				strings.add(k);
			}
		}
		int index = 0;
		for (String s : strings) {
			index++;
			if (!pos.contains(index)) {
				System.out.println(strings.get(pos.indexOf(-index)) + " ");
				System.out.println();
			} else
				System.out.println(strings.get(pos.indexOf(index)) + " ");
		}
	}

	// This method will return the word that occurs most frequently in the text file
	public String mostFrequentWord() {
		Set<String> setKey = map.keySet();
		int max = 0;
		String res = "";
		for (String currentWord : setKey) {
			int value = map.get(currentWord).size();
			if (value > max) {
				max = value;
				res = currentWord;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		TextAnalyzer analyzer = new TextAnalyzer("src\\lab8\\short.txt");
		analyzer.displayWords();
		System.out.println(analyzer.mostFrequentWord());
		analyzer.displayText();
	}
}

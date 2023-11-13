package lab8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import lab7.Utils;

public class MyWordCountApp {
	// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "src\\lab8\\fit.txt";
	// <word, its occurences>
	private Map<String, Integer> map = new HashMap<String, Integer>();

	// Load data from fileName into the above map (containing <word, its
	// occurences>)
	// using the guide given in TestReadFile.java
	public MyWordCountApp() throws FileNotFoundException {
		this.loadData();
	}

	public void loadData() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		while (sc.hasNext()) {
			String word = sc.next();
			map.put(word, map.getOrDefault(word, 0) + 1);
		}

	}

	// Returns the number of distinct tokens in the file data/hamlet.txt or fit.txt
	public int countDistinct() {
		return map.size();
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt)
	// In this method, we do not consider the order of tokens.
	public void printWordCounts() throws FileNotFoundException {
		Collection<Integer> list = map.values();
		System.out.println(Arrays.toString(list.toArray()));
	}

	// Prints out the number of times each unique token appears in the file
	// data/hamlet.txt (or fit.txt) according to ascending order of tokens
	// Example: An - 3, Bug - 10, ...
	public void printWordCountsAlphabet() {
		Map<String, Integer> treeMap = new TreeMap<>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		treeMap.putAll(map);
		Set<Map.Entry<String, Integer>> set = treeMap.entrySet();
		System.out.println(Arrays.toString(set.toArray()));
	}

	public String toString() {
		return "" + map + "";

	}

	public static void main(String[] args) throws FileNotFoundException {
		MyWordCountApp app = new MyWordCountApp();
		app.printWordCounts();
		System.out.println(app);
	}
}

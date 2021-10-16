
/**
 * A2 starter code for 2540, 2020. 
 * Author: Jianguo Lu 
 * The purpose is to understand sorting algorithms and their performances. 
 * It prints out the frequency of the 200-th most frequent word.
 */
import java.io.File;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.StringBuilder;

public class WordCountSort {

	public static void selectionSort(String[] data) {
		int n = data.length;
		for (int i = 0; i < n - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < n; j++) {
				if (data[minIndex].compareTo(data[j]) < 0) {
					minIndex = j;
				}
			}
			if (i != minIndex)
				swap(data, minIndex, i);

		}
	}

	public static void insertionSort(String[] data) {
		int n = data.length;
		for (int k = 1; k < n; k++) {
			String cur = data[k];
			int j = k;
			while (j > 0 && data[j - 1].compareTo(cur) > 0) {
				data[j] = data[j - 1];
				j--;
			}
			data[j] = cur;
		}
	}

	/** Merge two strings. See the textbook for explanation. **/
	public static void merge(String[] S1, String[] S2, String[] S) {
		int i = 0, j = 0;
		while (i + j < S.length) {
			if (j == S2.length || (i < S1.length && S1[i].compareTo(S2[j]) < 0))
				S[i + j] = S1[i++];
			else
				S[i + j] = S2[j++];
		}
	}

	public static void mergeSort(String[] S) {
		int n = S.length;
		if (n < 2)
			return;
		int mid = n / 2;
		// partition the string into two strings
		String[] S1 = Arrays.copyOfRange(S, 0, mid);
		String[] S2 = Arrays.copyOfRange(S, mid, n);
		mergeSort(S1);
		mergeSort(S2);
		merge(S1, S2, S);
	}

	private static void swap(String[] array, int i, int j) {
		String tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static Integer count_ARRAY_SORT(String[] tokens, String sortMethod) {

		if (sortMethod.equals("SELECT"))
			selectionSort(tokens);
		else if (sortMethod.equals("INSERT"))
			insertionSort(tokens);
		else if (sortMethod.equals("MERGE"))
			mergeSort(tokens);
		else
			System.out.println(sortMethod + " sorting method does not exist.");

		int CAPACITY = 1000000;
		String[] words = new String[CAPACITY];
		int[] counts = new int[CAPACITY];
		int j = 0, k = 0;
		int len = tokens.length;
		while (j < len - 1) {
			int duplicates = 1;
			while (j < len - 2 & tokens[j].equals(tokens[j + 1])) {
				j++;
				duplicates++;
			}

			words[k] = tokens[j];
			counts[k] = duplicates;
			j++;
			k++;
		}

		String[] copyOfWords=new String[k];
		Integer[] copyOfCounts=new Integer[k];
		
		for (int i=0; i<k; i++) {
			copyOfCounts[i]=counts[i];
		}
			
		Arrays.sort(copyOfCounts);
		
		return copyOfCounts[k-200];
	}

	// static String[] readText(String PATH) throws Exception {
	// 	Scanner doc = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
	// 	// tokenize text. any characters other than English letters(a-z and A-Z) are delimiters.
		
		

	// 	int length = 0;
	// 	while (doc.hasNext()) {
	// 		doc.next();
	// 		length++;
	// 	}

	// 	String[] tokens = new String[length];
	// 	Scanner s = new Scanner(new File(PATH)).useDelimiter("[^a-zA-Z]+");
	// 	length = 0;
		
	// 	StringBuilder test = new StringBuilder(length);
		
	// 	while (s.hasNext()) {
	// 		tokens[length] = s.next().toLowerCase();
	// 		length++;
	// 	}
	// 	doc.close();
	// 	return tokens;
	// }

	public static Integer countFAST(String fileName) throws Exception {

		String [] tokens = readText(fileName);
		mergeSort(tokens);

		HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
		// boolean oneWord = false;

		for(int i = 0; i < tokens.length; i++){
			if(wordCountMap.isEmpty()){
				wordCountMap.put(tokens[i], 1);
			}
			else if(wordCountMap.containsKey(tokens[i])){
				wordCountMap.put(tokens[i], wordCountMap.get(tokens[i]) + 1);
			}
		}

		
		









		// int CAPACITY = 1000000;

		// String[] words = new String[CAPACITY];

		// int[] counts = new int[CAPACITY];
		// int j = 0, k = 0;
		// int len = tokens.length;
		// while (j < len - 1) {
		// 	int duplicates = 1;
		// 	while (j < len - 2 & tokens[j].equals(tokens[j + 1])) {
		// 		j++;
		// 		duplicates++;
		// 	}

		// 	words[k] = tokens[j];
		// 	counts[k] = duplicates;
		// 	j++;
		// 	k++;
		// }

		// String[] copyOfWords=new String[k];
		// Integer[] copyOfCounts=new Integer[k];
		
		// for (int i=0; i<k; i++) {
		// 	copyOfCounts[i]=counts[i];
		// }
			
		// Arrays.sort(copyOfCounts);
		
		return copyOfCounts[k-200];

		
	}

	public static <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue(HashMap<K, V> map) {
        List<Entry<K, V>> list = new List<>(map.entrySet());
        list.sort(Entry.comparingByValue());

        HashMap<K, V> result = new HashMap<>();
        for (Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

	static String [] readText(String PATH) throws Exception {
		BufferedReader br = new BufferedReader (new FileReader(PATH) ) ;
		
		StringBuilder test = new StringBuilder();
		
		String text =" ";
		String line =" ";
		while ((line = br.readLine()) != null)
		{	
			test.append(" " + line.trim());
		}	
		// test.trimToSize();
		String tokens[] = test.toString().trim().split("[^a-zA-Z]+");
		// .split("[^a-zA-Z]+") ;
		
		return tokens;
	}

	public static void main(String[] args) throws Exception {
		String PATH = "dblp";
		String[] METHODS = { "MERGE", "INSERT", "SELECT" };
		String[] DATASETS = { "200", "400","800","1600","3200","6400", "10000","20000","40000", "80000","160000", "320000", "640000", "1280000", "25600000"};//, "10k","100k"}; //, "5k", "10k", "100k", "1m", "" };
		
		String[] tokens;
		// run the experiments on different data sets
		for (int j = 1; j < 11; j++) {
			// run the experiments using different methods
			System.out.println("Data is " + DATASETS[j]);
			for (int i = 0; i < 3; i++){
				tokens = readText(PATH + DATASETS[j] + ".txt");
				long startTime = System.currentTimeMillis();
				Integer _200th_freq= count_ARRAY_SORT(tokens, METHODS[i]);
				long endTime = System.currentTimeMillis();
				String time = String.format("%12d", endTime - startTime);
				System.out.println(METHODS[i] + " method\t time=" + time + ".\t 200th freq is " + _200th_freq);
			}
		}
	}
}

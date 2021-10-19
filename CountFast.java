import java.util.Map.Entry;

// import org.graalvm.compiler.word.Word;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.StringBuilder;

public class CountFast{

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



	public static Integer countFAST(String fileName) throws Exception {

		String [] tokens = readText(fileName);
		// Arrays.sort(tokens);
		// int maxValue = 0;

		HashMap<String, Integer> wordCountMap = new HashMap<String, Integer>();
		int i = 0;
		while(i < tokens.length)	{

			if(wordCountMap.isEmpty()){
				wordCountMap.put(tokens[i], 1);
			}
			else if(wordCountMap.containsKey(tokens[i])){
				wordCountMap.put(tokens[i], wordCountMap.get(tokens[i]) + 1);
			}
			else{
				wordCountMap.put(tokens[i], 1);
			}
			i++;
		}

		Integer[] valueSet = wordCountMap.values().toArray(new Integer[0]);

		Arrays.sort(valueSet);

		return valueSet[valueSet.length - 200];
	}

	public static <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue(HashMap<K, V> map) {
        List<Entry<K,V>> list = new ArrayList<>(map.entrySet());
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


        long startTime = System.currentTimeMillis();


        int i = countFAST("dblp2560000.txt");

        long endTime = System.currentTimeMillis();
        String time = String.format("%12d", endTime - startTime);
        System.out.println(" method\t time=" + time + ".\t 200th freq is " + i);
    }
}
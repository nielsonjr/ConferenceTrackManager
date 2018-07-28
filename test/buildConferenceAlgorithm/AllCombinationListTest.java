package buildConferenceAlgorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.BeforeClass;
import org.junit.Test;

import algorithms.buildConference.CheckAllCombinationsOfTalksAlgorithm;
import static org.junit.Assert.*;

/**
 * Compare the combined list from the algorithm used as an expected result
 * @author Nielson
 *
 */
public class AllCombinationListTest {
	private static List<String> listToBeCombined = new ArrayList<String>();
	private static List<SortedSet<String>> expectedResult = new ArrayList<SortedSet<String>>();
	private static List<SortedSet<String>> expectedResultFalse = new ArrayList<SortedSet<String>>();
	
	@BeforeClass
	public static void loadListOfElements() {
		listToBeCombined.add("A");
		listToBeCombined.add("B");
		listToBeCombined.add("C");
	}
	
	@BeforeClass
	public static void loadTrueResult() {
		//all the combinations from the set
		TreeSet<String> treeSetForA = new TreeSet<String>();
		treeSetForA.add("A");
		
		expectedResult.add(treeSetForA);
		
		TreeSet<String> treeSetForB = new TreeSet<String>();
		treeSetForB.add("B");
		
		expectedResult.add(treeSetForB);
		
		TreeSet<String> treeSetForC = new TreeSet<String>();
		treeSetForC.add("C");		
		expectedResult.add(treeSetForC);
		
		TreeSet<String> treeSetForAB = new TreeSet<String>();
		treeSetForAB.add("A");
		treeSetForAB.add("B");
		expectedResult.add(treeSetForAB);
		
		TreeSet<String> treeSetForAC = new TreeSet<String>();
		treeSetForAC.add("A");
		treeSetForAC.add("C");
		expectedResult.add(treeSetForAC);
		
		TreeSet<String> treeSetForBC = new TreeSet<String>();
		treeSetForBC.add("B");
		treeSetForBC.add("C");
		expectedResult.add(treeSetForBC);
		
		TreeSet<String> treeSetForABC = new TreeSet<String>();
		treeSetForABC.add("A");
		treeSetForABC.add("B");
		treeSetForABC.add("C");
		expectedResult.add(treeSetForABC);
	}
	
	@BeforeClass
	public static void loadFalseResult() {
		//an invalid combination
		TreeSet<String> treeSetForA = new TreeSet<String>();
		treeSetForA.add("A");
		
		expectedResultFalse.add(treeSetForA);
		
		TreeSet<String> treeSetForB = new TreeSet<String>();
		treeSetForB.add("B");
		
		expectedResultFalse.add(treeSetForB);
		
		TreeSet<String> treeSetForC = new TreeSet<String>();
		treeSetForC.add("C");		
		expectedResultFalse.add(treeSetForC);
		
		TreeSet<String> treeSetForAB = new TreeSet<String>();
		treeSetForAB.add("A");
		treeSetForAB.add("B");
		expectedResultFalse.add(treeSetForAB);
		
		TreeSet<String> treeSetForAC = new TreeSet<String>();
		treeSetForAC.add("A");
		treeSetForAC.add("C");
		expectedResultFalse.add(treeSetForAC);
		
		TreeSet<String> treeSetForBC = new TreeSet<String>();
		treeSetForBC.add("B");
		treeSetForBC.add("D"); //false
		expectedResultFalse.add(treeSetForBC);
		
		TreeSet<String> treeSetForABC = new TreeSet<String>();
		treeSetForABC.add("A");
		treeSetForABC.add("B");
		treeSetForABC.add("C");
		expectedResultFalse.add(treeSetForABC);
	}
	
	@Test
	public void allCombinationsOfAStringSet() {
		CheckAllCombinationsOfTalksAlgorithm algorithm = new CheckAllCombinationsOfTalksAlgorithm();
		SortedSet<SortedSet<String>> combinations = algorithm.allCombinations(listToBeCombined);
		
		List<SortedSet<String>> combList = new ArrayList<SortedSet<String>>();
		combList.addAll(combinations);
		
		int sizeComp = combList.size() - expectedResult.size();  
		if (sizeComp == 0) {  
			Iterator<SortedSet<String>> o1iIterator = combList.iterator();  
			Iterator<SortedSet<String>> o2iIterator = expectedResult.iterator();
			
			
			while (sizeComp == 0 && o1iIterator.hasNext() ) {  
				sizeComp = compareSortedSet(o1iIterator.next(), o2iIterator.next());  
			}  
		} 
		
		assertEquals(0, sizeComp);
		
	}
	
	@Test
	public void allCombinationsOfAStringSetFalse() {
		CheckAllCombinationsOfTalksAlgorithm algorithm = new CheckAllCombinationsOfTalksAlgorithm();
		SortedSet<SortedSet<String>> combinations = algorithm.allCombinations(listToBeCombined);
		
		List<SortedSet<String>> combList = new ArrayList<SortedSet<String>>();
		combList.addAll(combinations);
		
		int sizeComp = combList.size() - expectedResultFalse.size();  
		if (sizeComp == 0) {  
			Iterator<SortedSet<String>> o1iIterator = combList.iterator();  
			Iterator<SortedSet<String>> o2iIterator = expectedResultFalse.iterator();
			
			
			while (sizeComp == 0 && o1iIterator.hasNext() ) {  
				sizeComp = compareSortedSet(o1iIterator.next(), o2iIterator.next());  
			}  
		} 
		
		assertNotEquals(0, sizeComp);
		
	}
	
	private int compareSortedSet(SortedSet<String> s1, SortedSet<String> s2) {
		int sizeComp = s1.size() - s2.size();  
		if (sizeComp == 0) {  
			Iterator<String> o1iIterator = s1.iterator();  
			Iterator<String> o2iIterator = s2.iterator();
			
			
			while (sizeComp == 0 && o1iIterator.hasNext() ) {  
				sizeComp = o1iIterator.next().compareTo(o2iIterator.next());  
			}  
		}  
		
		return sizeComp;
	}
}

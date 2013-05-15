/*
 * Author: Sam Backus
 * Date: Feb 7, 2013
 */

package cyrus.assingment;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import cyrus.assignment.FileUtils;
import cyrus.assignment.Person;
import cyrus.assignment.PersonFileParser;

public class APersonFileParserShould {

	private PersonFileParser fr;
	private final String abercrombie = "Abercrombie Neil Male 2/13/1943 Tan";
	private final String bishop = "Bishop Timothy Male 4/23/1967 Yellow";
	private final String kelly = "Kelly Sue Female 7/12/1959 Pink";
	private final String smith = "Smith Steve Male 3/3/1985 Red";
	private final String bonk = "Bonk Radek Male 6/3/1975 Green";
	private final String bouillon = "Bouillon Francis Male 6/3/1975 Blue";
	private final String kournikova = "Kournikova Anna Female 6/3/1975 Red";
	private final String hingis = "Hingis Martina Female 4/2/1979 Green";
	private final String seles = "Seles Monica Female 12/2/1973 Black";
		
	@Before
	public void init(){
		fr = new PersonFileParser();
	}
	
	@Test
	public void readLinesFromAFile(){
		Collection<String> lines = FileUtils.read("input_files/comma.txt");
		assertTrue(lines.contains("Abercrombie, Neil, Male, Tan, 2/13/1943"));
		assertTrue(lines.size()==3);
	}
	
	@Test
	public void parseCommaFilesToCreatePeople(){
		String[] expected = {abercrombie,bishop,kelly};
		fr.getPersonInfoFromFile("input_files/comma.txt");
		assertEquals(expected, fr.getPersons());
	}
		
	@Test 
	public void parsePipeFilesToCreatePeople(){
		String[] expected = {smith,bonk,bouillon};
		fr.getPersonInfo("input_files/pipe.txt");
		assertEquals(expected, fr.getPersons());
	}
	
	@Test 
	public void parseSpaceFilesToCreatePeople(){
		String[] expected = {kournikova,hingis,seles};
		fr.getPersonInfoFromFile("input_files/space.txt");
		assertEquals(expected, fr.getPersons());
	}
	
	@Test
	public void parseAllFilesInADirectory(){
		fr.getPersonInfo("input_files");
		String[] expected = {abercrombie,bishop,kelly,smith,bonk,bouillon,kournikova,hingis,seles};
		assertEquals(expected,fr.getPersons());
	}
	
	@Test
	public void outputSortedPersonData(){
		fr.getPersonInfo("input_files");
		String actual = fr.getSortedPersonData();
		String expected = FileUtils.getListAsString(FileUtils.read("model_output.txt"));
		org.junit.Assert.assertEquals(expected,actual);
	}
	
	
	private void assertEquals(String[] expected,ArrayList<Person> actual){
		org.junit.Assert.assertEquals(Arrays.asList(expected).toString(), actual.toString());
	}
}

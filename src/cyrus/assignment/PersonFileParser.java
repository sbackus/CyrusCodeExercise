/*
 * Author: Sam Backus
 * Date: Feb 7, 2013
 */

package cyrus.assignment;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class PersonFileParser {

	private ArrayList<Person> persons;
	
	public PersonFileParser(){
		persons = new ArrayList<Person>();
	}
	
	public ArrayList<Person> getPersons() {
		return persons;
	}

	public void getPersonInfoFromFile(String fileName) {
		for (String line : FileUtils.read(fileName)){
			if (line.matches(".*, .*, .*ale, .*, [0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$")){
				String[] personData = line.split(", ");
				persons.add(new Person(personData[0],personData[1],personData[2],personData[4],personData[3]));
			}
			else if (line.matches(".* \\| .* \\| [A-Z] \\| [?:M|F] \\| .* \\| [0-9]{1,2}-[0-9]{1,2}-[0-9]{4}$")){
				String[] personData = line.split(" \\| ");
				persons.add(new Person(personData[0],personData[1],personData[3],personData[5],personData[4]));
			}
			else if (line.matches(".* .* [A-Z] [?:M|F] [0-9]{1,2}-[0-9]{1,2}-[0-9]{4} .*")){
				String[] personData = line.split(" ");
				persons.add(new Person(personData[0],personData[1],personData[3],personData[4],personData[5]));
			}
			else {
				System.out.println("Error reading: "+ line +"\n" +
						"Format not recognized.  Skip.");
			}
		}
	}
	
	public void getPersonInfoFromFile(File file) {
		getPersonInfoFromFile(file.getPath());
	}
	
	public static void main(String args[]){
		if (args.length == 0){
			System.out.println("The location of the directory containing the input files"+
					"must be passed to PersonFileParser.  Please try again.");
		}else{
			String directory = args[0];
			PersonFileParser parser = new PersonFileParser();
			parser.getPersonInfo(directory);
			System.out.println(parser.getSortedPersonData());
		}

	}
	
	public void getPersonInfo(String fileName) {
		final File file = new File(fileName);
		if (file.isDirectory()) {
			for (final File fileEntry : file.listFiles()) {
		        getPersonInfoFromFile(fileEntry);
		    }
		}else{
			getPersonInfoFromFile(file);
		}
	}

	public String getSortedPersonData(){
		Collections.sort(persons, Person.lastNameComparator);
		Collections.sort(persons, Person.genderComparator);
		String output1 = FileUtils.getListAsString(persons);
		
		Collections.sort(persons, Person.lastNameComparator);
		Collections.sort(persons, Person.birthDayComparator);
		String output2 = FileUtils.getListAsString(persons);
		
		Collections.sort(persons, Person.lastNameComparator);
		Collections.reverse(persons);
		String output3 = FileUtils.getListAsString(persons);
		
		String output = "";
		output = output 
				+ "Output 1:\n" + output1 + "\n"
				+ "Output 2:\n" + output2 + "\n"
				+ "Output 3:\n" + output3 + "\n";
		return output;
	}

}

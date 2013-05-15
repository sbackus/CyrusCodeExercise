/*
 * Author: Sam Backus
 * Date: Feb 7, 2013
 */

package cyrus.assingment;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import cyrus.assignment.Person;

public class ACollectionOfPersonsShould {
	private ArrayList<Person> persons;
	@Before
	public void init(){
		persons = new ArrayList<Person>();
		persons.add(new Person("Backus","Sam", "Male", "01/16/1985","Green"));
		persons.add(new Person("Green","John", "M", "8-24-1977","Green"));
		persons.add(new Person("Granger","Hermione", "F", "9/19/1977","Red"));
		persons.add(new Person("Hopper","Grace", "Female", "12-9/1905","Blue"));
		persons.add(new Person("Clinton","Hilary", "F", "10-26-1947","Yellow"));
	}
	
	@Test 
	public void BeSortableByLastName(){
		Collections.sort(persons, Person.lastNameComparator);
		assertEquals(persons.toString(), 
				"[Backus Sam Male 01/16/1985 Green, "+
				"Clinton Hilary Female 10/26/1947 Yellow, "+
				"Granger Hermione Female 9/19/1977 Red, "+
				"Green John Male 8/24/1977 Green, "+
				"Hopper Grace Female 12/9/1905 Blue]");
	}
	
	@Test
	public void BeSortableByBirthDate(){
		Collections.sort(persons,Person.birthDayComparator);
		assertEquals(persons.toString(),
				"[Hopper Grace Female 12/9/1905 Blue, "+
				"Clinton Hilary Female 10/26/1947 Yellow, "+
				"Green John Male 8/24/1977 Green, "+
				"Granger Hermione Female 9/19/1977 Red, "+
				"Backus Sam Male 01/16/1985 Green]");
	}
	
	@Test
	public void BeSortableByGender(){
		Collections.sort(persons,Person.genderComparator);
		assertEquals(persons.toString(),
				"[Granger Hermione Female 9/19/1977 Red, "+
				"Hopper Grace Female 12/9/1905 Blue, "+
				"Clinton Hilary Female 10/26/1947 Yellow, "+
				"Backus Sam Male 01/16/1985 Green, "+
				"Green John Male 8/24/1977 Green]");
	}
}

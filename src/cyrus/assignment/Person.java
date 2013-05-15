/*
 * Author: Sam Backus
 * Date: Feb 7, 2013
 */

package cyrus.assignment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Person {

	private String lastName;
	private String firstName;
	private String middleInitial;
	private String gender;
	private String dateOfBirth;
	private String favoriteColor;
	
	public Person(String lastName, String firstName){
		this.lastName = lastName;
		this.firstName = firstName;
	}
		
	public Person(String lastName, String firstName, String gender, String dateOfBirth, String favoriteColor){
		this.lastName = lastName;
		this.firstName = firstName;
		setGender(gender);
		this.dateOfBirth = dateOfBirth.replaceAll("-", "/");
		this.favoriteColor = favoriteColor;
	}

	public String toString(){
		return lastName + " " +firstName + " " + gender + " " + 
				getDateOfBirth() + " " +favoriteColor; 
	}
	
	private void setGender(String gender) {
		if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male"))
			this.gender = "Male";
		else if(gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female"))
			this.gender = "Female";
		else{
			this.gender = gender;
		}
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getGender() {
		return gender;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	
	public boolean equals(Person person){
		return this.getLastName().equals(person.getLastName()) 
				&& this.getFirstName().equals(person.getFirstName());
	}

	
	/*The following comparators allow collections of Person objects to be 
	 * sorted in a variety of ways incuding by LastName, Gender, and Birthday.*/
	public static Comparator<Person> lastNameComparator 
	= new Comparator<Person>() {

		public int compare(Person person1, Person person2) {
			String personName1 = person1.getLastName();
			String personName2 = person2.getLastName();
			return personName1.compareTo(personName2);
		}
	};
	
	public static Comparator<Person> birthDayComparator
	= new Comparator<Person>(){
		public int compare(Person person1, Person person2){
					
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			try {
				Date date1 = dateFormat.parse(person1.getDateOfBirth());
				Date date2 = dateFormat.parse(person2.getDateOfBirth());
				return date1.compareTo(date2);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			return 0;
		}
	};
	
	public static Comparator<Person> genderComparator
	= new Comparator<Person>(){
		public int compare(Person person1, Person person2){
			return person1.getGender().compareTo(person2.getGender());
		}
	};
}

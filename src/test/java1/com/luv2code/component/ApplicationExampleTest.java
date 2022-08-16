package com.luv2code.component;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;

@SpringBootTest
public class ApplicationExampleTest {

	private static int count=0;
	
	@Value("${info.school.name}")
	private String schoolName;
	
	@Value("${info.app.name}")
	private String name;
	
	@Value("${info.app.description}")
	private String description;
	
	@Value("${info.app.version}")
	private String version;
	
	@Autowired
	CollegeStudent student;
	
	@Autowired
	StudentGrades studentGrades;
	
	@Autowired
	ApplicationContext context;
	
	@BeforeEach
	public void beforeEachtest() 
	{
		count=count+1;
		System.out.println("Testing: "+ name + "which is " + description +
				"Version : "+ version+"execution count "+ count);
		student.setFirstname("Eric");
		student.setLastname("roby");
		student.setEmailAddress("Eric06@gmail.com");
		studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0,85.0,
				76.5,91.75)));
		student.setStudentGrades(studentGrades);
	}
	
	@Test
	void addResults() 
	{
		assertEquals(353.25,studentGrades.addGradeResultsForSingleClass(student
				.getStudentGrades().getMathGradeResults()));
	}
	
	@Test
	void addResultsNotEqual()
	{
		assertNotEquals(0,studentGrades.addGradeResultsForSingleClass(student.getStudentGrades()
				.getMathGradeResults()));
	}
	
	@Test
	void isGreater()
	{
		assertTrue(studentGrades.isGradeGreater(10.0,2.0));
	}
	
	@Test
	void isGreaterfalse()
	{
		assertFalse(studentGrades.isGradeGreater(0.0,2.0));
	}
	
	@Test
	void CheckNullforStudentsGrades()
	{
		assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()));
	}
	
	@Test
	void CreateStudentsWithoutGradeInit()
	{
		CollegeStudent stdtwo=context.getBean("collegeStudent", CollegeStudent.class);
		stdtwo.setFirstname("Subrat");
		stdtwo.setLastname("nayak");
        stdtwo.setEmailAddress("ss@gmail.com");
		assertNotNull(stdtwo.getFirstname());
		assertNotNull(stdtwo.getLastname());
		assertNotNull(stdtwo.getEmailAddress());
		assertNull(studentGrades.checkNull(stdtwo.getStudentGrades()));
	}
	
	@Test 
	void testPrototypes()
	{
		CollegeStudent stdtwo=context.getBean("collegeStudent", CollegeStudent.class);
		assertNotSame(stdtwo,student);
	}
	
	/*
	 * @Test void GradePointaverage() {
	 * assertAll("",()->assertEquals(353.25,studentGrades.
	 * addGradeResultsForSingleClass(student
	 * .getStudentGrades().getMathGradeResults())),
	 * assertEquals(353.25,studentGrades.addGradeResultsForSingleClass(student
	 * .getStudentGrades().getMathGradeResults())) );
	 * 
	 * }
	 */
}

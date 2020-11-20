package com.example.springboot.concatenation;

import com.example.springboot.Concatenation;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import static org.junit.Assert.*;
import org.junit.*;

public class ConcatenationTest {
	
	@Before
	public void setup(){
		System.out.println("before testing...");
	}
	
	@After
	public void cleanup(){
		System.out.println("finishing and cleaning up the test");
	}
	
	@Test
	public void testConcatenation(){
		Concatenation c = new Concatenation();
		String word = c.concatenateString("bronco-", "calendar");
		Assert.assertEquals("bronco-calendar", word);
	}
	
	@Test
	public void testCompareString(){
		Concatenation c = new Concatenation();
		assertTrue(c.compareString("2020","2020"));
	}
	
	@Test
    public void testWebPage() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("https://asi.cpp.edu/");
            Assert.assertEquals("ASI, Cal Poly Pomona", page.getTitleText());

            final String pageAsText = page.asText();
            Assert.assertTrue(pageAsText.contains("Hey Broncos! Remember to use TurboVote at cpp.turbovote.org to register"));
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Model.User;
import Servlet.LoginServlet;
import net.sourceforge.jwebunit.junit.JWebUnit;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;
import static net.sourceforge.jwebunit.junit.JWebUnit.getTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import net.sourceforge.jwebunit.junit.WebTester;



/**
 *
 * @author Fatih
 */
public class LoginJspTest {
    
    public LoginJspTest() {
    }
    
    @Before
    public void setUp() {
        
       setBaseUrl("http://localhost:8080/Restoku"); }
    
    @After
    public void tearDown() {
    }

  
    @Test
    public void LoginSuccess() {
        beginAt("Restoku.html");
        clickLink("lognForm.jsp");
        JWebUnit.assertTitleEquals("Login");
        setTextField("username", "syuki");
        setTextField("password", "123");
        submit("Login");
        assertTitleEquals("AdminResto");
    }
}

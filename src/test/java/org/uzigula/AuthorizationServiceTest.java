package org.uzigula;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by umamani on 03/02/2015.
 */
// usando Stub = SImular o verificar estado (datos)
public class AuthorizationServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    private IUserStore userStoreStub;
    private AuthorizationService auth;
    private IEmailService emailServiceMock;

    private User getUser() {
        return new User("jperez", "1234567".hashCode(), false);
    }



    @Before
    public void setUp() throws Exception {
        userStoreStub = mock(IUserStore.class); //stub
        emailServiceMock = mock(IEmailService.class);
        auth = new AuthorizationService(userStoreStub, emailServiceMock);
    }

    @Test
    public void Login_SendAValidUserPassword_ShouldBeAuthorized() throws InvalidLoginException {

        when(userStoreStub.getUser("jperez")).thenReturn(getUser());

        String token = auth.Login("jperez", "1234567");

        assertEquals("Should get a valid Token",32,token.length());
    }

    @Test
    public void Login_SendAnInvalidUserPassword_ShouldGetAnInvalidLogonException() throws InvalidLoginException {
        expectedEx.expect(InvalidLoginException.class);
        expectedEx.expectMessage("Invalid UserName/Password");

        when(userStoreStub.getUser("jperez")).thenReturn(getUser());

        String token = auth.Login("jperez","0987662");

    }

    @Test
    public void Login_SendAnNoExistentUserName_ShouldReturnAnInvalidLoginException() throws InvalidLoginException {
        expectedEx.expect(InvalidLoginException.class);
        expectedEx.expectMessage("User not found");

        when(userStoreStub.getUser("jperez")).thenReturn(null);

        String token = auth.Login("jperez","65431");

    }

   @Test
    public void Login_SendABlockedValidUser_ShouldReturnAn_() throws InvalidLoginException{
       expectedEx.expect(InvalidLoginException.class);
       expectedEx.expectMessage("User Account is blocked");

       User user = new User("jperez","122345".hashCode(),true);

       when(userStoreStub.getUser("jperez")).thenReturn(user);
       String token = auth.Login("jperez","122345");
    }


    @Test
    public void GetResetPasswordLink_GivenAValidEmail_SHouldSendEMailToUserInbox() throws InvalidEmailException{

        when(userStoreStub.getUserFromEmail("jperez@acme.com")).thenReturn(getUser());
        auth.GetResetPasswordLink("jperez@acme.com");
        verify(emailServiceMock).SendEmail(any(MailMessage.class));

    }

    @Test
    public void GetResetPasswordLink_GivenAnInvalidValidEmail_ShouldThrowAnException() throws InvalidEmailException{
        expectedEx.expect(InvalidEmailException.class);
        expectedEx.expectMessage("Email address not found");
        when(userStoreStub.getUserFromEmail("jperez@acme.com")).thenReturn(null);
        auth.GetResetPasswordLink("jperez@acme.com");
    }

   @Test
    public void ValidateAuthorization(){

       when(userStoreStub.getApplicationsFor(anyString())).thenReturn(getApplicationsList()); // seteando el resultado
       List<ApplicationAuthorized> appsAuthorized = auth.getApplicationsAuthorized("jperez");
       assertEquals(2,appsAuthorized.size());
       assertEquals("Manager System", appsAuthorized.get(1).applicationName);
       assertEquals("Human Resources System", appsAuthorized.get(2).applicationName);
   }

    private List<ApplicationAuthorized> getApplicationsList() {
        List<ApplicationAuthorized> list = new ArrayList<ApplicationAuthorized>();
        list.add(new ApplicationAuthorized(1,"Manager System","MGR") );
        list.add(new ApplicationAuthorized(2,"Manager System","ADM") );
        list.add(new ApplicationAuthorized(3,"Manager System","SYS") );
        list.add(new ApplicationAuthorized(4,"Human Resources System","SYS") );

        return list;
    }

}

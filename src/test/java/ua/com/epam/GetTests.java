package ua.com.epam;

import org.testng.annotations.Test;

import static ua.com.epam.config.Parameters.*;

@Test
public class GetTests extends BaseTest {

    @Test
    public void getDifferentAuthors() {
        validator
                .getAuthorValidator()
                .validateAuthorGetByParameters(client, ASC, ONE, TRUE, TWENTY, AUTHOR_ID);
    }

    @Test
    public void getAuthorById() {
        validator
                .getAuthorValidator()
                .validateAuthorGetById(client, 11968);
    }

    @Test
    public void getAuthorByName() {
        validator
                .getAuthorValidator()
                .validateAuthorGetByName(client, gson, ISAAK_FISHER);
    }
}

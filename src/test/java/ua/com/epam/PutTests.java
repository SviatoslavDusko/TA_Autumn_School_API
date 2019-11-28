package ua.com.epam;

import org.testng.annotations.Test;

import static ua.com.epam.config.Parameters.*;

@Test
public class PutTests extends BaseTest {

    @Test
    public void putAuthorById() {
        validator
                .getAuthorValidator()
                .validateAuthorPut(client, gson, 1050, TOM_CAT, USA, BIRTH_TWO, DESCRIPTION);
    }
}

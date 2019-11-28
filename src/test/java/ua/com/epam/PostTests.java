package ua.com.epam;

import org.testng.annotations.Test;

import static ua.com.epam.config.Parameters.*;

@Test
public class PostTests extends BaseTest {

    @Test(description = "post single Author obj")
    public void postAuthorFromFileById() {
        validator
                .getAuthorValidator()
                .validateAuthorPostFromFile(client, testData, 11968);
    }

    @Test
    public void postAuthor() {
        validator
                .getAuthorValidator()
                .validateAuthorPost(client, gson, DUSKO_SVIATOSLAV, USA, BIRTH_ONE, DESCRIPTION);
    }
}

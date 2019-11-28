package ua.com.epam;

import org.testng.annotations.Test;

import static ua.com.epam.config.Parameters.TRUE;

@Test
public class DeleteTests extends BaseTest {

    @Test
    public void deleteAuthorById() {
        validator
                .getAuthorValidator()
                .validateAuthorDelete(client, 5407, TRUE);
    }
}

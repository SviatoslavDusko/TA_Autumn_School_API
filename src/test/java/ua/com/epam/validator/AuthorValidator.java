package ua.com.epam.validator;

import com.google.gson.Gson;
import org.testng.Assert;
import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.author.nested.Birth;
import ua.com.epam.entity.author.nested.Name;
import ua.com.epam.service.AuthorService;
import ua.com.epam.utils.DataFactory;

public class AuthorValidator {
    private AuthorService authorService = new AuthorService();

    public void validateAuthorDelete(RestClient client, long id, String forcibly) {
        Assert.assertEquals(authorService
                .deleteAuthor(client, id, forcibly)
                .getStatusCode(), 204);
    }

    public void validateAuthorPut(RestClient client, Gson gson, long id, Name authorName, String nationality, Birth authorBirth, String authorDescription) {
        Assert.assertEquals(authorService
                .putAuthor(client, gson, id, authorName, nationality, authorBirth, authorDescription)
                .getStatusCode(), 200);
    }

    public void validateAuthorPostFromFile(RestClient client, DataFactory testData, long id) {
        Assert.assertEquals(authorService
                .postAuthorFromFile(client, testData, id)
                .getStatusCode(), 201);
    }

    public void validateAuthorPost(RestClient client, Gson gson, Name authorName, String nationality, Birth authorBirth, String authorDescription) {
        Assert.assertEquals(authorService
                .postAuthorByParameters(client, gson, authorName, nationality, authorBirth, authorDescription)
                .getStatusCode(), 201);
    }

    public void validateAuthorGetById(RestClient client, long id) {
        Assert.assertEquals(authorService
                .getAuthorById(client, id)
                .getStatusCode(), 200);
    }

    public void validateAuthorGetByName(RestClient client, Gson gson, Name name) {
        Assert.assertEquals(authorService
                .getAuthorByName(client, gson, name)
                .getStatusCode(), 200);
    }

    public void validateAuthorGetByParameters(RestClient client, String orderType, String page, String pagination, String size, String sortBy) {
        Assert.assertEquals(authorService
                .getAuthorByParameters(client, orderType, page, pagination, size, sortBy)
                .getStatusCode(), 200);
    }
}

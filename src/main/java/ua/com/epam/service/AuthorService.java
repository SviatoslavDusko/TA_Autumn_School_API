package ua.com.epam.service;

import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import org.json.JSONArray;
import org.testng.collections.Maps;
import ua.com.epam.core.rest.RestClient;
import ua.com.epam.entity.Response;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.author.nested.Birth;
import ua.com.epam.entity.author.nested.Name;
import ua.com.epam.utils.DataFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import static ua.com.epam.config.Parameters.*;
import static ua.com.epam.config.URI.*;

public class AuthorService {

    private String encodeParams(Map<String, String> params) {
        StringBuilder uri = new StringBuilder();
        char separator = '?';
        for (Map.Entry<String, String> param : params.entrySet()) {
            uri.append(separator);
            separator = '&';
            try {
                uri.append(URLEncoder.encode(param.getKey(), UTF_8));
                if (!StringUtils.isNullOrEmpty(param.getValue())) {
                    uri.append("=");
                    uri.append(URLEncoder.encode(param.getValue(), UTF_8));
                }
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        return uri.toString();
    }

    private String parametersAuthors(String orderType, String page, String pagination, String size, String sortBy) {
        final Map<String, String> params = Maps.newHashMap();
        params.clear();

        params.put(ORDER_TYPE, orderType);
        params.put(PAGE, page);
        params.put(PAGINATION, pagination);
        params.put(SIZE, size);
        params.put(SORT_BY, sortBy);

        return encodeParams(params);
    }

    private String forcibly(String trueOrFalse) {
        final Map<String, String> params = Maps.newHashMap();
        params.put(FORCIBLY, trueOrFalse);
        return encodeParams(params);
    }

    public Response deleteAuthor(RestClient client, long id, String forcibly) {
        String uri;
        uri = id + forcibly(forcibly);
        client.delete(String.format(DELETE_AUTHOR_SINGLE_OBJ, uri));
        return client.getResponse();
    }

    public Response getAuthorByParameters(RestClient client, String orderType, String page, String pagination, String size, String sortBy) {
        client.get(GET_ALL_AUTHORS_ARR + parametersAuthors(orderType, page, pagination, size, sortBy));
        return client.getResponse();
    }

    public Response getAuthorByName(RestClient client, Gson gson, Name name) {
        client.get(GET_ALL_AUTHORS_ARR + parametersAuthors(ASC, ONE, TRUE, ONE_HUNDRED, AUTHOR_ID));
        JSONArray jsonArray = new JSONArray(client.getResponse().getBody());

        for (int i = 0; i < jsonArray.length(); i++) {
            Author author = gson.fromJson(String.valueOf(jsonArray.getJSONObject(i)), Author.class);
            if (author.getAuthorName().getFirst().equals(name.getFirst())
                    && author.getAuthorName().getSecond().equals(name.getSecond())) {
                client.get(String.format(GET_AUTHOR_SINGLE_OBJ, author.getAuthorId()));
                return client.getResponse();
            }
        }
        return client.getResponse();
    }

    public Response getAuthorById(RestClient client, long id) {
        client.get(String.format(GET_AUTHOR_SINGLE_OBJ, id));
        return client.getResponse();
    }

    public Response putAuthor(RestClient client, Gson gson, long id, Name authorName, String nationality, Birth authorBirth, String authorDescription) {
        getAuthorById(client, id);
        Author author = gson.fromJson(getAuthorById(client, id).getBody(), Author.class);

        author.setAuthorName(authorName);
        author.setNationality(nationality);
        author.setBirth(authorBirth);
        author.setAuthorDescription(authorDescription);

        client.put(String.format(PUT_AUTHOR_SINGLE_OBJ, id), author);

        return client.getResponse();
    }

    public Response postAuthorFromFile(RestClient client, DataFactory testData, long id) {
        List<Author> allAuthorsList = testData.authors().getAllAuthors();
        for (Author author : allAuthorsList) {
            if (author.getAuthorId().equals(id)) {
                client.post(POST_AUTHOR_SINGLE_OBJ, author);
                return client.getResponse();
            }
        }
        return client.getResponse();
    }

    public Response postAuthorByParameters(RestClient client, Gson gson, Name authorName, String nationality, Birth authorBirth, String authorDescription) {
        client.get(GET_ALL_AUTHORS_ARR + parametersAuthors(ASC, ONE, TRUE, ONE_HUNDRED, AUTHOR_ID));

        JSONArray jsonArray = new JSONArray(client.getResponse().getBody());
        int countAuthor = jsonArray.length();

        Author lastAuthor = gson.fromJson(String.valueOf(jsonArray.getJSONObject(countAuthor - 1)), Author.class);

        long id = lastAuthor.getAuthorId() + 1;
        Author author = new Author(id, authorName, nationality, authorBirth, authorDescription);

        client.post(POST_AUTHOR_SINGLE_OBJ, author);

        return client.getResponse();
    }
}

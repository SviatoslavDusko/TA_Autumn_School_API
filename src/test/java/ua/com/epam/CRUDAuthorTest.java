package ua.com.epam;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.com.epam.entity.author.Author;
import ua.com.epam.entity.author.nested.Name;

import java.util.List;

import static ua.com.epam.config.URI.GET_ALL_AUTHORS_ARR;
import static ua.com.epam.config.URI.POST_AUTHOR_SINGLE_OBJ;

@Test(description = "")
public class CRUDAuthorTest extends BaseTest {
}

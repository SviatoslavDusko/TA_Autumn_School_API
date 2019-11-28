package ua.com.epam.config;

import ua.com.epam.entity.author.nested.Birth;
import ua.com.epam.entity.author.nested.Name;

import java.time.LocalDate;

public interface Parameters {
    String UTF_8 = "utf-8";

    String ASC = "asc";
    String DESC = "desc";
    String AUTHOR_ID = "authorId";
    String ONE_HUNDRED = "100";
    String ONE = "1";
    String TWENTY = "20";

    String LVIV = "Lviv";
    String CHICAGO = "Chicago";
    String UKRAINE = "Ukraine";
    String USA = "USA";
    String DESCRIPTION = "Description";
    String UKRAINIAN = "Ukrainian";

    String TRUE = "true";
    String FALSE = "false";

    Name TOM_CAT = new Name("Tom", "Cat");
    Name DUSKO_SVIATOSLAV = new Name("Dusko", "Sviatoslav");
    Name ISAAK_FISHER = new Name("Isaac","Fisher");

    Birth BIRTH_ONE = new Birth(LocalDate.of(1814, 3, 9), UKRAINE, LVIV);
    Birth BIRTH_TWO = new Birth(LocalDate.of(1990, 11, 8), USA, CHICAGO);

    String FORCIBLY = "forcibly";
    String ORDER_TYPE = "orderType";
    String PAGE = "page";
    String PAGINATION = "pagination";
    String SIZE = "size";
    String SORT_BY = "sortBy";
}

package ua.com.epam.validator;

public class Validator {
    public AuthorValidator getAuthorValidator() {
        return new AuthorValidator();
    }
}

package com.example.veierovioum.lesson13registration;

/**
 * Created by nir on 29/01/2016.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_a-z0-9-\\+]{2,}+(\\.[_a-z0-9-]{2,}+)*@"
                    + "[a-z0-9-]{2,}+(\\.[a-z0-9]{2,}+)*(\\.[a-z]{2,})$";

    public EmailValidator() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validate hex with regular expression
     *
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean validate(final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
}

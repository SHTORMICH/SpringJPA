package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    private Map<String, String> placeholderValues;
    private static final String PLACEHOLDER_PATTERN = "#\\{([^}]*)\\}";
    private static final String ONLY_LETTERS_PATTERN = "[^a-zA-Z]";

    public TemplateEngine() {
        placeholderValues = new HashMap<>();
    }

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        setPlaceholderValue("fullName", client.getFullName());
        return generateTemplate(template.getTemplate());
    }

    public void setPlaceholderValue(String placeholder, String value) {
        placeholderValues.put(placeholder, value);
    }

    public String generateTemplate(String template) {
        validatePlaceholderValues(template);
        return replacePlaceholders(template);
    }

    private void validatePlaceholderValues(String template) {
        String[] placeholders = extractPlaceholders(template);
        for (String placeholder : placeholders) {
            if (!placeholderValues.containsKey(placeholder)) {
                throw new IllegalArgumentException("Missing value for placeholder: " + placeholder);
            }
        }
    }

    private String[] extractPlaceholders(String template) {
        List<String> placeholders = new ArrayList<>();
        Matcher matcher = Pattern.compile(PLACEHOLDER_PATTERN).matcher(template);
        while (matcher.find()) {
            String placeholder = matcher.group();
            placeholder = placeholder.replaceAll(ONLY_LETTERS_PATTERN, "");
            placeholders.add(placeholder);
        }
        return placeholders.toArray(new String[0]);
    }

    private String replacePlaceholders(String template) {
        for (Map.Entry<String, String> entry : placeholderValues.entrySet()) {
            String placeholder = "#{" + entry.getKey() + "}";
            String value = entry.getValue();
            template = template.replace(placeholder, value);
        }
        return template;
    }
}

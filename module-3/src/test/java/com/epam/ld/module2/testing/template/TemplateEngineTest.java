package com.epam.ld.module2.testing.template;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

public class TemplateEngineTest {
    private TemplateEngine templateEngine;

    @BeforeEach
    public void setUp() {
        templateEngine = new TemplateEngine();
    }


    @ParameterizedTest
    @CsvSource({
            "'Welcome to #{subject} World!', 'subject', 'Hello', 'Welcome to Hello World!'",
            "'Click #{url} for more information.', 'url', 'https://example.com', 'Click https://example.com for more information.'",
            "'Hello, #{name}!', 'name', 'John', 'Hello, John!'",
            "'Hello, #{name}!', 'name', 'Jürgen', 'Hello, Jürgen!'"
    })
    public void testGenerateTemplate(String template, String placeholder, String value, String expectedOutput) {
        templateEngine.setPlaceholderValue(placeholder, value);
        String result = templateEngine.generateTemplate(template);
        Assertions.assertEquals(expectedOutput, result);
    }

    @ParameterizedTest
    @CsvSource({
            "'My name is #{name} and I''m #{age} years old.', 'name', 'age', 'John', '25', 'My name is John and I''m 25 years old.'",
            "'Hello, #{name}! Welcome!', 'name', 'location', 'John', 'Kiev', 'Hello, John! Welcome!'",
    })
    public void testGenerateTemplate(String template, String firstPlaceholder, String secondPlaceholder, String firstValue, String secondValue, String expectedOutput) {
        templateEngine.setPlaceholderValue(firstPlaceholder, firstValue);
        templateEngine.setPlaceholderValue(secondPlaceholder, secondValue);
        String result = templateEngine.generateTemplate(template);
        Assertions.assertEquals(expectedOutput, result);
    }

    @TestFactory
    @DisplayName("Replace placeholders")
    public List<DynamicTest> testGenerateTemplate_ReplacePlaceholders() {
        return Arrays.asList(
                dynamicTest("Replace single placeholder", "subject", "Hello", "Welcome to Hello World!", "Welcome to #{subject} World!"),
                dynamicTest("Handle special characters in placeholders", "url", "https://example.com", "Click https://example.com for more information.", "Click #{url} for more information."),
                dynamicTest("Ignore nonexistent placeholders", "name", "Jame", "Hello, John! Welcome to location.", "Hello, John! Welcome to location."),
                dynamicTest("Support Latin1 character set", "name", "Jürgen", "Hello, Jürgen!", "Hello, #{name}!")
        );
    }

    private DynamicTest dynamicTest(String displayName, String placeholder, String value, String expectedOutput, String template) {
        return DynamicTest.dynamicTest(displayName, () -> {
            templateEngine.setPlaceholderValue(placeholder, value);
            String result = templateEngine.generateTemplate(template);
            Assertions.assertEquals(expectedOutput, result);
        });
    }

    @TestFactory
    @DisplayName("Missing placeholder value exception")
    public List<DynamicTest> testGenerateTemplate_MissingPlaceholderValueException1() {
        return Arrays.asList(
                dynamicTest("Missing name placeholder", "name", "Hello, #{name}!"),
                dynamicTest("Missing subject placeholder", "subject", "Welcome to #{subjects} World!")
        );
    }

    private DynamicTest dynamicTest(String displayName, String placeholder, String template) {
        return DynamicTest.dynamicTest(displayName, () -> {
            Assertions.assertThrows(IllegalArgumentException.class, () -> {
                templateEngine.generateTemplate(template);
            });
        });
    }

    @Test
    @DisplayName("Replace single placeholder")
    public void testGenerateTemplate_ReplaceSinglePlaceholder() {
        templateEngine.setPlaceholderValue("subject", "Hello");
        String template = "Welcome to #{subject} World!";
        String expectedOutput = "Welcome to Hello World!";
        String result = templateEngine.generateTemplate(template);
        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    @DisplayName("Replace multiple placeholders")
    public void testGenerateTemplate_ReplaceMultiplePlaceholders() {
        templateEngine.setPlaceholderValue("name", "John");
        templateEngine.setPlaceholderValue("age", "25");
        String template = "My name is #{name} and I'm #{age} years old.";
        String expectedOutput = "My name is John and I'm 25 years old.";
        String result = templateEngine.generateTemplate(template);
        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    @DisplayName("Handle special characters in placeholders")
    public void testGenerateTemplate_HandleSpecialCharactersInPlaceholders() {
        templateEngine.setPlaceholderValue("url", "https://example.com");
        String template = "Click #{url} for more information.";
        String expectedOutput = "Click https://example.com for more information.";
        String result = templateEngine.generateTemplate(template);
        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    @DisplayName("Missing placeholder value exception")
    public void testGenerateTemplate_MissingPlaceholderValueException() {
        String template = "Hello, #{name}!";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            templateEngine.generateTemplate(template);
        });
    }

    @Test
    @DisplayName("Ignore nonexistent placeholders")
    public void testGenerateTemplate_IgnoreNonexistentPlaceholders() {
        templateEngine.setPlaceholderValue("name", "John");
        templateEngine.setPlaceholderValue("location", "Kiev");
        templateEngine.setPlaceholderValue("a", "B");
        String template = "Hello, #{name}! Welcome to #{location}.";
        String expectedOutput = "Hello, John! Welcome to Kiev.";
        String result = templateEngine.generateTemplate(template);
        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    @DisplayName("Support Latin1 character set")
    public void testGenerateTemplate_SupportLatin1CharacterSet() {
        templateEngine.setPlaceholderValue("name", "Jürgen");
        String template = "Hello, #{name}!";
        String expectedOutput = "Hello, Jürgen!";
        String result = templateEngine.generateTemplate(template);
        Assertions.assertEquals(expectedOutput, result);
    }
}

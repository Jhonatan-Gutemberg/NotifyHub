package com.notifyhub.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("NotificationMessage Unit Tests")
class NotificationMessageTest {

    @Test
    @DisplayName("Should create a valid notification message with auto-generated UUID")
    void shouldCreateNotificationMessageSuccessfully() {
        // Arrange
        String expectedTitle = "System Alert";
        String expectedContent = "Maintenance scheduled for midnight.";

        // Act
        NotificationMessage message = new NotificationMessage(expectedTitle, expectedContent);

        // Assert
        assertAll("Verify all fields",
            () -> assertNotNull(message.getId(), "ID should be auto-generated"),
            () -> assertEquals(expectedTitle, message.getTitle(), "Title should match"),
            () -> assertEquals(expectedContent, message.getContent(), "Content should match")
        );
    }

    @Test
    @DisplayName("Should reconstruct notification message with a specific ID")
    void shouldReconstructNotificationMessageWithProvidedId() {
        // Arrange
        String customId = "msg_12345";
        
        // Act
        NotificationMessage message = new NotificationMessage(customId, "Title", "Content");

        // Assert
        assertEquals(customId, message.getId());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   "})
    @DisplayName("Should throw exception when title is empty or blank")
    void shouldThrowExceptionWhenTitleIsInvalid(String invalidTitle) {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            new NotificationMessage(invalidTitle, "Valid Content")
        );
        
        assertTrue(exception.getMessage().contains("Title is required"));
    }

    @Test
    @DisplayName("Should throw exception when content is null")
    void shouldThrowExceptionWhenContentIsNull() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            new NotificationMessage("Valid Title", null)
        );
        
        assertTrue(exception.getMessage().contains("Content is required"));
    }
}
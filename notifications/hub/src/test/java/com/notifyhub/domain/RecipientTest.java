package com.notifyhub.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Recipient Unit Tests")
class RecipientTest {

    @Test
    @DisplayName("Should create recipient with only address")
    void shouldCreateRecipientWithAddressOnly() {
        // Arrange
        String expectedAddress = "user@example.com";

        // Act
        Recipient recipient = new Recipient(expectedAddress);

        // Assert
        assertEquals(expectedAddress, recipient.getAddress(), "Address should match provided value");
        assertNull(recipient.getName(), "Name should be null when not provided");
    }

    @Test
    @DisplayName("Should create recipient with address and name")
    void shouldCreateRecipientWithAddressAndName() {
        // Arrange
        String expectedAddress = "john.doe@example.com";
        String expectedName = "John Doe";

        // Act
        Recipient recipient = new Recipient(expectedAddress, expectedName);

        // Assert
        assertAll("Verify full recipient data",
            () -> assertEquals(expectedAddress, recipient.getAddress()),
            () -> assertEquals(expectedName, recipient.getName())
        );
    }

    @Test
    @DisplayName("Should update address using setter")
    void shouldUpdateAddressUsingSetter() {
        // Arrange
        Recipient recipient = new Recipient();
        String newAddress = "new.email@example.com";

        // Act
        recipient.setAddress(newAddress);

        // Assert
        assertEquals(newAddress, recipient.getAddress(), "Address should be updated correctly");
    }

    @Test
    @DisplayName("Should verify default constructor initializes null values")
    void shouldHaveNullValuesInDefaultConstructor() {
        // Act
        Recipient recipient = new Recipient();

        // Assert
        assertNull(recipient.getAddress());
        assertNull(recipient.getName());
    }
}
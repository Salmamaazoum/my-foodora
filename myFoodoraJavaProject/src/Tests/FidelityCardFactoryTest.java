package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import FidelityCards.BasicFidelityCard;
import FidelityCards.FidelityCard;
import FidelityCards.FidelityCardFactory;
import FidelityCards.LotteryFidelityCard;
import FidelityCards.PointFidelityCard;

class FidelityCardFactoryTest {

    @Test
    @DisplayName("Create Basic Fidelity Card")
    void testCreateBasicFidelityCard() {
        FidelityCard card = FidelityCardFactory.createFidelityCard("BASIC");
        assertTrue(card instanceof BasicFidelityCard, "Should create an instance of BasicFidelityCard");
    }

    @Test
    @DisplayName("Create PointFidelity Card")
    void testCreatePointFidelityCard() {
        FidelityCard card = FidelityCardFactory.createFidelityCard("POINT");
        assertTrue(card instanceof PointFidelityCard, "Should create an instance of PointFidelityCard");
    }

    @Test
    @DisplayName("Create LotteryFidelity Card")
    void testCreateLotteryFidelityCard() {
        FidelityCard card = FidelityCardFactory.createFidelityCard("LOTTERY");
        assertTrue(card instanceof LotteryFidelityCard, "Should create an instance of LotteryFidelityCard");
    }

    @Test
    @DisplayName("Test invalid card type returns null")
    void testInvalidCardType() {
        FidelityCard card = FidelityCardFactory.createFidelityCard("UNKNOWN");
        assertNull(card, "Should return null for unknown fidelity card types");
    }

    @Test
    @DisplayName("Test case insensitivity of card type creation")
    void testCaseInsensitivity() {
        FidelityCard card = FidelityCardFactory.createFidelityCard("point");
        assertTrue(card instanceof PointFidelityCard, "Should handle case insensitive card type names");
    }

}

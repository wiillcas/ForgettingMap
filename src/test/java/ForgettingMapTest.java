import org.example.ForgettingMap;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ForgettingMapTest {

    @Test
    public void associationExistsAfterAssociationAdded() {
        ForgettingMap forgettingMap = new ForgettingMap(1);
        String key = "test";
        int expectedValue = 0;
        forgettingMap.Add(key, expectedValue);
        Optional<Object> value = forgettingMap.Find(key);
        assertEquals(expectedValue, value.get());
    }

    @Test
    public void forgettingMapEmptyAfterInit() {
        int expectedSize = 0;
        ForgettingMap forgettingMap = new ForgettingMap(0);
        assertEquals(expectedSize, forgettingMap.keySet().size());
    }

    @Test
    public void valueEmptyWhenKeyNotAdded() {
        ForgettingMap forgettingMap = new ForgettingMap(1);
        String key = "test";
        Optional<Object> value = forgettingMap.Find(key);
        assertFalse(value.isPresent());
    }

    @Test
    public void keySetEqualToOneAfterAssociationAdded() {
        int expectedSize = 1;
        ForgettingMap forgettingMap = new ForgettingMap(expectedSize);
        String key = "test";
        int value = 0;
        forgettingMap.Add(key, value);
        assertEquals(expectedSize, forgettingMap.keySet().size());
    }

    @Test
    public void replaceAssociationWhenForgettingMapFull() {
        int expectedSize = 2;
        ForgettingMap forgettingMap = new ForgettingMap(expectedSize);

        String key = "test";
        int value = 0;
        forgettingMap.Add(key, value);
        String key2 = "test2";

        int value2 = 4;
        forgettingMap.Add(key2, value2);
        forgettingMap.Find(key2);

        String key3 = "test3";
        int value3 = 3;
        forgettingMap.Add(key3, value3);

        assertEquals(expectedSize, forgettingMap.keySet().size());
        assertTrue(forgettingMap.keySet().contains(key2));
        assertTrue(forgettingMap.keySet().contains(key3));
    }
}

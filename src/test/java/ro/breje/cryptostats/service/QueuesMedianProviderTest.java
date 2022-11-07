package ro.breje.cryptostats.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.breje.cryptostats.service.impl.QueuesMedianProvider;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueuesMedianProviderTest {

    private QueuesMedianProvider medianProvider;

    @BeforeEach
    public void setup() {
        medianProvider = new QueuesMedianProvider();
    }

    @Test
    public void getMedian_whenNoAddition_thenThrowException() {
        IllegalStateException thrown = Assertions.assertThrows(IllegalStateException.class, () -> medianProvider.getMedian());

        assertEquals("No value provided yet!", thrown.getMessage());
    }

    @Test
    public void getMedian_whenHaving1Element_thenReturnValue() {
        medianProvider.add(1f);

        float expectedValue = medianProvider.getMedian();

        assertEquals(1f, expectedValue);
    }

    @Test
    public void getMedian_whenHaving2Elements_thenReturnValue() {
        medianProvider.add(1f);
        medianProvider.add(2f);

        float actualValue = medianProvider.getMedian();

        assertEquals(1.5f, actualValue);
    }

    @Test
    public void getMedian_whenHavingOddNumberOfMultipleElements_thenReturnValue() {
        medianProvider.add(4f);
        medianProvider.add(1f);
        medianProvider.add(100f);
        medianProvider.add(2f);
        medianProvider.add(3f);

        float actualValue = medianProvider.getMedian();

        assertEquals(3f, actualValue);
    }

    @Test
    public void getMedian_whenHavingEvenNumberOfMultipleElements_thenReturnValue() {
        medianProvider.add(1f);
        medianProvider.add(99f);
        medianProvider.add(2f);
        medianProvider.add(100f);

        float actualValue = medianProvider.getMedian();

        assertEquals(50.5f, actualValue);
    }

    @Test
    public void size_whenHaving1Element_thenReturnValue() {
        medianProvider.add(1f);

        long size = medianProvider.size();

        assertEquals(1l, size);
    }

    @Test
    public void size_whenHavingMultipleElements_thenReturnValue() {
        medianProvider.add(1f);
        medianProvider.add(99f);
        medianProvider.add(2f);
        medianProvider.add(2f);
        medianProvider.add(2f);
        medianProvider.add(2f);
        medianProvider.add(2f);
        medianProvider.add(2f);
        medianProvider.add(2f);
        medianProvider.add(2f);
        medianProvider.add(2f);
        medianProvider.add(2f);
        medianProvider.add(100f);

        long size = medianProvider.size();

        assertEquals(13l, size);
    }
}

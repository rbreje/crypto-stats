package ro.breje.cryptostats.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ro.breje.cryptostats.model.impl.BeHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BeHashMapTest {

    private BeHashMap myHashMap;

    @BeforeEach
    public void setup() {
        myHashMap = new BeHashMap();
    }

    @Test
    public void get_whenNoElements_thenReturnNull() {
        String actualValue = myHashMap.get("sample");

        assertNull(actualValue);
    }

    @Test
    public void get_whenHave1Element_thenReturnValue() {
        String expectedValue = "sampleValue";

        myHashMap.put("sampleKey", expectedValue);

        String actualValue = myHashMap.get("sampleKey");

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void get_whenHaveMultipleElements_thenReturnValue() {
        String expectedValue1 = "sampleValue1";
        String expectedValue2 = "sampleValue2";
        String expectedValue3 = "sampleValue3";

        myHashMap.put("sampleKey1", expectedValue1);
        myHashMap.put("sampleKey2", expectedValue2);
        myHashMap.put("sampleKey3", expectedValue3);

        String actualValue1 = myHashMap.get("sampleKey1");
        String actualValue2 = myHashMap.get("sampleKey2");
        String actualValue3 = myHashMap.get("sampleKey3");

        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
        assertEquals(expectedValue3, actualValue3);
    }

    @Test
    public void get_whenHaveOverridingValue_thenReturnValue() {
        String expectedFirstValue = "sampleValue";
        String expectedSecondValue = "sampleValue";

        myHashMap.put("sampleKey", expectedFirstValue);

        String initialValue = myHashMap.get("sampleKey");

        myHashMap.put("sampleKey", expectedSecondValue);

        String overridedValue = myHashMap.get("sampleKey");

        assertEquals(expectedFirstValue, initialValue);
        assertEquals(expectedSecondValue, overridedValue);
    }

    @Test
    public void get_whenHaveToExpandSizeOfMapElements_thenReturnValue() {
        String expectedValue1 = "sampleValue1";
        String expectedValue2 = "sampleValue2";
        String expectedValue3 = "sampleValue3";
        String expectedValue4 = "sampleValue4";
        String expectedValue5 = "sampleValue5";
        String expectedValue6 = "sampleValue6";
        String expectedValue7 = "sampleValue7";
        String expectedValue8 = "sampleValue8";
        String expectedValue9 = "sampleValue9";
        String expectedValue10 = "sampleValue10";
        String expectedValue11 = "sampleValue11";
        String expectedValue12 = "sampleValue12";
        String expectedValue13 = "sampleValue13";
        String expectedValue14 = "sampleValue14";
        String expectedValue15 = "sampleValue15";
        String expectedValue16 = "sampleValue16";
        String expectedValue17 = "sampleValue17";

        myHashMap.put("sampleKey1", expectedValue1);
        myHashMap.put("sampleKey2", expectedValue2);
        myHashMap.put("sampleKey3", expectedValue3);
        myHashMap.put("sampleKey4", expectedValue4);
        myHashMap.put("sampleKey5", expectedValue5);
        myHashMap.put("sampleKey6", expectedValue6);
        myHashMap.put("sampleKey7", expectedValue7);
        myHashMap.put("sampleKey8", expectedValue8);
        myHashMap.put("sampleKey9", expectedValue9);
        myHashMap.put("sampleKey10", expectedValue10);
        myHashMap.put("sampleKey11", expectedValue11);
        myHashMap.put("sampleKey12", expectedValue12);
        myHashMap.put("sampleKey13", expectedValue13);
        myHashMap.put("sampleKey14", expectedValue14);
        myHashMap.put("sampleKey15", expectedValue15);
        myHashMap.put("sampleKey16", expectedValue16);
        myHashMap.put("sampleKey17", expectedValue17);

        String actualValue1 = myHashMap.get("sampleKey1");
        String actualValue2 = myHashMap.get("sampleKey2");
        String actualValue3 = myHashMap.get("sampleKey3");
        String actualValue4 = myHashMap.get("sampleKey4");
        String actualValue5 = myHashMap.get("sampleKey5");
        String actualValue6 = myHashMap.get("sampleKey6");
        String actualValue7 = myHashMap.get("sampleKey7");
        String actualValue8 = myHashMap.get("sampleKey8");
        String actualValue9 = myHashMap.get("sampleKey9");
        String actualValue10 = myHashMap.get("sampleKey10");
        String actualValue11 = myHashMap.get("sampleKey11");
        String actualValue12 = myHashMap.get("sampleKey12");
        String actualValue13 = myHashMap.get("sampleKey13");
        String actualValue14 = myHashMap.get("sampleKey14");
        String actualValue15 = myHashMap.get("sampleKey15");
        String actualValue16 = myHashMap.get("sampleKey16");
        String actualValue17 = myHashMap.get("sampleKey17");

        assertEquals(expectedValue1, actualValue1);
        assertEquals(expectedValue2, actualValue2);
        assertEquals(expectedValue3, actualValue3);
        assertEquals(expectedValue4, actualValue4);
        assertEquals(expectedValue5, actualValue5);
        assertEquals(expectedValue6, actualValue6);
        assertEquals(expectedValue7, actualValue7);
        assertEquals(expectedValue8, actualValue8);
        assertEquals(expectedValue9, actualValue9);
        assertEquals(expectedValue10, actualValue10);
        assertEquals(expectedValue11, actualValue11);
        assertEquals(expectedValue12, actualValue12);
        assertEquals(expectedValue13, actualValue13);
        assertEquals(expectedValue14, actualValue14);
        assertEquals(expectedValue15, actualValue15);
        assertEquals(expectedValue16, actualValue16);
        assertEquals(expectedValue17, actualValue17);
    }
}

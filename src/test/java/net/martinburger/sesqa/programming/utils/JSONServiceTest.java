package net.martinburger.sesqa.programming.utils;

import net.martinburger.sesqa.programming.CityTest;
import net.martinburger.sesqa.programming.codeopolis.domainmodel.CityState;
import net.martinburger.sesqa.programming.codeopolis.utils.JSONService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONServiceTest {
    private JSONService<CityState> testCityStateJSONService = JSONService.cityStateJSONService();

    @Test
    public void testWrite() {
        CityState testState = new CityState(
                "TestCity",
                0,
                0,
                0,
                0,
                0
        );

        assertDoesNotThrow(()-> this.testCityStateJSONService.write(testState, "test.json"));
        CityState refState = this.testCityStateJSONService.read("test.json", testState);

        assertEquals(testState, refState);
    }
}

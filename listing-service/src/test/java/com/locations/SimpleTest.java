package com.locations;

import com.locations.listing.Application;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = Application.class)
public class SimpleTest {

    private static final int STRING_PART_SIZE = 5;

    @Test
    public void onCommaSeperatedList_ConfirmCanCollect() {
        var commaString = "a,b,c,d,e";
        var parts = of(commaString.split(",")).collect(toList());
        if (parts.size() != STRING_PART_SIZE) {
            throw new RuntimeException("");
        }
    }
}

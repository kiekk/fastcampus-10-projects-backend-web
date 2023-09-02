package org.example.customer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThatCode;

public class MenuItemTest {

    @DisplayName("매뉴 항목을 생성한다.")
    @Test
    void createTest() {
        assertThatCode(() -> new MenuItem("만두", 5_000))
                .doesNotThrowAnyException();
    }

}

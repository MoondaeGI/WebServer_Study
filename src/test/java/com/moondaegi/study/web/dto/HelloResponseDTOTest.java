package com.moondaegi.study.web.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDTOTest {
    @Test
    public void 롬북_기능_테스트() {
        String name = "test";
        int amount = 3000;

        HelloResponseDTO helloResponseDTO = new HelloResponseDTO(name, amount);

        assertThat(helloResponseDTO.getName()).isEqualTo(name);
        assertThat(helloResponseDTO.getAmount()).isEqualTo(amount);
    }
}

package br.com.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
}

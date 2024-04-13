package br.com.fiap.opengroup.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredicaoMessage {

    private String role;
    private String content;
}

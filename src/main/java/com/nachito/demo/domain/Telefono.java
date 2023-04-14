package com.nachito.demo.domain;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Telefono {
    private int number;
    private int citycode;
    private int contrycode;
}

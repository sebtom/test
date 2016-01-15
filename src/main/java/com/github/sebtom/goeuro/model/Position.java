package com.github.sebtom.goeuro.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Position {

    private String latitude;

    private String longitude;
}

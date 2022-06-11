package com.example.demo.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sequence {
    private final String id;
    private final String prefix;
    private final String suffix;
}

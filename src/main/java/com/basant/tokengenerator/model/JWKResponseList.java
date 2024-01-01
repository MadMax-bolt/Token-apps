package com.basant.tokengenerator.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class JWKResponseList {

    private List<JWKResponse> keys;

    public JWKResponseList() {
        this.keys = new ArrayList<>();
    }

    public void addKey(JWKResponse key) {
        this.keys.add(key);
    }
}

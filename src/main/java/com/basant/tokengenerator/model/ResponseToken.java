package com.basant.tokengenerator.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseToken {

    private String token;

    public ResponseToken token(String token) {
        this.setToken(token);
        return this;
    }



	public void setToken(String token) {
		this.token = token;
	}

}

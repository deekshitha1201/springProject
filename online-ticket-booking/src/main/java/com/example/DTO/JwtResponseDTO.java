package com.example.DTO;

import com.example.DTO.JwtResponseDTO.JwtResponseDTOBuilder;

public class JwtResponseDTO {
	private String accessToken;
	 
    public String getAccessToken() {
        return accessToken;
    }
 
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
 
    // Private constructor to enforce the use of the builder
    private JwtResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }
 
    public static JwtResponseDTOBuilder builder() {
        return new JwtResponseDTOBuilder();
    }
 
    public static class JwtResponseDTOBuilder {
        private String accessToken;
 
        private JwtResponseDTOBuilder() {
            // private constructor to enforce the use of the builder
        }
 
        public JwtResponseDTOBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }
 
        public JwtResponseDTO build() {
            return new JwtResponseDTO(accessToken);
        }
    }

}

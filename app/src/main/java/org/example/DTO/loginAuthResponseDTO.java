package org.example.DTO;

public class loginAuthResponseDTO extends AuthResponseDTO{

    private String token;

    public loginAuthResponseDTO(boolean success, String message, Object data, String token) {
        super(success, message, data);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    
}

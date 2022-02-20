package edu.fu.demoshop.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}

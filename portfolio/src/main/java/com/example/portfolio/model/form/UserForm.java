package com.example.portfolio.model.form;

import lombok.Data;

@Data
public class UserForm {

  private Integer userId;
  private String userName;
  private String email;
  private String password;
  private String familyName;
  private String firstName;

}

package com.pl.admin.dto;

public class RegisterDto extends LoginDto {
    private String email;
    private String confirm;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public boolean isSimple(){
        return "simple".equals(this.email);
    }
}

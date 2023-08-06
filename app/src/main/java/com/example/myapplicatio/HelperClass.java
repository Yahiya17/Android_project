package com.example.myapplicatio;

public class HelperClass {
    String username,auid,usn,password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuid() {
        return auid;
    }

    public void setAuid(String auid) {
        this.auid = auid;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HelperClass(String username, String auid, String usn, String password) {
        this.username = username;
        this.auid = auid;
        this.usn = usn;
        this.password = password;
    }

    public HelperClass() {
    }
}

package com.rebersincar.otologg.Models;

public class LoginPojo {

        private String admin;
        private int id;
        private String email;


        public String getAdmin(){
            return admin;
        }

        public int getId(){
            return id;
        }

        public void setEmail(String email){
            this.email = email;
        }

        public String getEmail(){
            return email;
        }

        @Override
        public String toString(){
            return
                    "LoginPojo{" +
                            "admin = '" + admin + '\'' +
                            ",id = '" + id + '\'' +
                            ",email = '" + email + '\'' +
                            "}";
        }

}

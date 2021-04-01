package com.example.p5;

import java.util.ArrayList;

public class UserBean {
    private String name;
    private ArrayList<PersonBean> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<PersonBean> getList() {
        return list;
    }

    public void setList(ArrayList<PersonBean> list) {
        this.list = list;
    }

    public UserBean(String name, ArrayList<PersonBean> list) {
        this.name = name;
        this.list = list;

    }

    public static class PersonBean{
        private int image;
        private String name;

        public PersonBean(int image, String name) {
            this.image = image;
            this.name = name;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}


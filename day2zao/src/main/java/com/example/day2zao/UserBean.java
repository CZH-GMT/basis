package com.example.day2zao;

import java.util.ArrayList;

public class UserBean {
    private int image;
    private String name;
    private ArrayList<personBean> list;

    public UserBean(int image, String name, ArrayList<personBean> list) {
        this.image = image;
        this.name = name;
        this.list = list;
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

    public ArrayList<personBean> getList() {
        return list;
    }

    public void setList(ArrayList<personBean> list) {
        this.list = list;
    }


    public static class personBean{
        private int image;
        private String name;

        public personBean(int image, String name) {
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

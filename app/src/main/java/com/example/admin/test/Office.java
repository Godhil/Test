package com.example.admin.test;

import java.net.URL;

/**
 * Created by admin on 02.03.2015.
 */
public class Office {

    private String name;
    private String address;
    private Image image;
    //private OfficeImage officeImage;

    public Office(){

    }

    public String getName(){
        return  name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getAdress(){
        return address;
    }
    public void setAdress(String address) {
        this.address = address;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public static class Image{
        private String name;
        private URL url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public URL getUrl() {
            return url;
        }

        public void setUrl(URL url) {
            this.url = url;
        }
    }

}

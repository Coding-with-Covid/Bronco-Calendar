package com.example.springboot.data.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.springboot.data.EventProduct;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class BroncoEventProductProvider implements EventProductProvider {

    @Override
    public List<EventProduct> listAllEventProducts() {
        List<EventProduct> eventList = new ArrayList<>();
        
        Document doc;
        try {
            doc = Jsoup.connect("").get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }
    
}

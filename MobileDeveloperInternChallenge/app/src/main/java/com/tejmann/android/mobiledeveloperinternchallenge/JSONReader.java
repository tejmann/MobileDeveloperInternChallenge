package com.tejmann.android.mobiledeveloperinternchallenge;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONReader  {

    public static ArrayList<Collection> readJson(String json) throws JSONException{
        //returns an ArrayList of custom collection
        ArrayList<Collection> Collections =new ArrayList<Collection>();
        JSONObject jsonObject=new JSONObject(json);
        JSONArray jsonArray=jsonObject.getJSONArray("custom_collections");
        int i=0;

        while(i<jsonArray.length()){
            JSONObject jsonObject1=(JSONObject) jsonArray.get(i);
            String title=jsonObject1.getString("title");
            String id=jsonObject1.getString("id");

            Collection collection1 =new Collection(title,id);
            Collections.add(collection1);
            i=i+1;
        }
       return Collections;
    }

    public static String readCollects(String json) throws JSONException{
        String productId="";
        JSONObject jsonObject=new JSONObject(json);
        JSONArray jsonArray=jsonObject.getJSONArray("collects");
        int i=0;
        while (i<jsonArray.length()){
            JSONObject jsonObject1= (JSONObject) jsonArray.get(i);
             String id=jsonObject1.getString("product_id");
             if(productId==""){productId=id;}
            else{ productId=productId+","+id;}


             i=i+1;


        }
        return productId;

    }

    public static ArrayList<Product> readProduct(String json) throws JSONException{
        ArrayList<Product> products=new ArrayList<Product>();
        JSONObject jsonObject=new JSONObject(json);
        JSONArray jsonArray=jsonObject.getJSONArray("products");
        int i=0;

        while(i<jsonArray.length()){
            JSONObject jsonObject1= (JSONObject) jsonArray.get(i);
            String name=jsonObject1.getString("title");
            JSONArray variants=jsonObject1.getJSONArray("variants");
            int k=0;
            int sum=0;
            while(k<variants.length()){
                JSONObject jsonObject2=variants.getJSONObject(k);
               String inventory_quantity= jsonObject2.getString("inventory_quantity");
               int quantity=Integer.parseInt(inventory_quantity);
               sum=sum+quantity;
               k=k+1;
            }
            String availabilty=Integer.toString(sum);
            String title=jsonObject1.getString("product_type");
            JSONObject image=jsonObject1.getJSONObject("image");
            String imurl=image.getString("src");
            Product product=new Product(name,availabilty,title,imurl);
            products.add(product);
            i=i+1;

        }

        return products;

        }


    }



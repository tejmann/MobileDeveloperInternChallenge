package com.tejmann.android.mobiledeveloperinternchallenge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks{
    private String site="https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6&ids=";
    private String productSite;
    private ArrayList<Product> ProductList;
    private Adapter2 adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Collection Details");
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
        site=site+id;
        getSupportLoaderManager().initLoader(-2,null,pl);

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    finish();}

    private LoaderManager.LoaderCallbacks pl=new LoaderManager.LoaderCallbacks() {
        @NonNull
        @Override
        public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
            return new ILoader(getApplicationContext(),site);
        }

        @Override
        public void onLoadFinished(@NonNull Loader loader, Object o) {
            ArrayList<Product> products=null;
            try {

                ProductList=JSONReader.readProduct((String) o);
                adapter2=new Adapter2(getApplicationContext(),-11,ProductList);
                ListView listView=findViewById(R.id.list_2);
                listView.setAdapter(adapter2);
            } catch (JSONException e) {
                e.printStackTrace();
            }


                ArrayList<String> urlList=listmaker(ProductList);

                int i=0;
                while(i<urlList.size()){
                    Bundle bundle=new Bundle();
                    bundle.putString("site",urlList.get(i));
                    getSupportLoaderManager().initLoader(i,bundle,il);

                    i=i+1;


            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader loader) {

        }
    };

    private LoaderManager.LoaderCallbacks il=new LoaderManager.LoaderCallbacks() {
        @NonNull
        @Override
        public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
            return new ImageLoader(getApplicationContext(),bundle.getString("site"));
        }

        @Override
        public void onLoadFinished(@NonNull Loader loader, Object o) {
            Bitmap bitmap= (Bitmap) o;
            Product product=addImage(ProductList.get(loader.getId()),bitmap);
            ProductList.set(loader.getId(),product);
            adapter2.notifyDataSetChanged();


        }

        @Override
        public void onLoaderReset(@NonNull Loader loader) {

        }
    };

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object o) {


    }

    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }
    private Product addImage(Product product, Bitmap bitmap){
        String title=product.getTitle();
        String name=product.getName();
        String avail=product.getAvailability();
        String imurl=product.getImageUrl();
        Product product1=new Product(name,avail,title,bitmap,imurl);
        return product1;
        }
        private ArrayList<String> listmaker(ArrayList<Product>  products){
        ArrayList<String> arrayList=new ArrayList<String>();
        int i=0;
        while (i<products.size()){
          String url=  products.get(i).getImageUrl();
          arrayList.add(url);
          i=i+1;
        }
        return arrayList;
        }
}

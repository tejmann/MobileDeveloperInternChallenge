package com.tejmann.android.mobiledeveloperinternchallenge;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks{
    private String site="https://shopicruit.myshopify.com/admin/custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
    private ArrayList<Collection> customList;
    private String weburl="https://shopicruit.myshopify.com/admin/collects.json?access_token=c32313df0d0ef512ca64d5b336a0d7c6&collection_id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportLoaderManager().initLoader(-30,null,this);
        setTitle("Custom Collections");
        ListView listView=findViewById(R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Collection collection=customList.get(i);
                String id=collection.getId();
                weburl=weburl+id;
                getSupportLoaderManager().initLoader(-20,null,loaderCallbacks);





            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @NonNull
    @Override
    public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
        return new ILoader(this,site);
    }

    @Override
    public void onLoadFinished(@NonNull Loader loader, Object o) {
        ArrayList<Collection> collectionArrayList =null;

        try {
            collectionArrayList =JSONReader.readJson((String) o);
//            Adapter adapter1=new Adapter(this,10, collectionArrayList);
//            ListView listView=findViewById(R.id.list);
//            listView.setAdapter(adapter1);

        } catch (JSONException e) {

            e.printStackTrace();
        }
        finally {
            this.customList=collectionArrayList;
           Adapter adapter1=new Adapter(this,-10, collectionArrayList);
           ListView listView=findViewById(R.id.list);
           listView.setAdapter(adapter1);

//           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//               @Override
//               public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                   Collection collection=customList.get(i);
//                   String id=collection.getId();
//                   weburl=weburl+id;
//                   getSupportLoaderManager().initLoader(-20,null,loaderCallbacks);
//
//
//
//
//
//               }
//           });

        }

    }


    @Override
    public void onLoaderReset(@NonNull Loader loader) {

    }

    private LoaderManager.LoaderCallbacks loaderCallbacks=new LoaderManager.LoaderCallbacks() {
        @NonNull
        @Override
        public Loader onCreateLoader(int i, @Nullable Bundle bundle) {
            return new ILoader(getApplicationContext(),weburl);
        }

        @Override
        public void onLoadFinished(@NonNull Loader loader, Object o) {

            String product_site="";
            try {
                product_site=JSONReader.readCollects((String) o);

            } catch (JSONException e) {
                e.printStackTrace();
            }
            finally {
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("id",product_site);
                startActivity(intent);
                finish();




            }

        }

        @Override
        public void onLoaderReset(@NonNull Loader loader) {



        }
    };
}

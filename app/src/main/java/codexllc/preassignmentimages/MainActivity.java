package codexllc.preassignmentimages;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import codexllc.preassignmentimages.adapter.DataAdapter;
import codexllc.preassignmentimages.model.photo;
import codexllc.preassignmentimages.network.RequestInterface;

import java.util.ArrayList;
import java.util.List;
import codexllc.preassignmentimages.model.worldpopulation;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://www.androidbegin.com/tutorial/";

    private RecyclerView mRecyclerView;

    private CompositeDisposable mCompositeDisposable;

    private DataAdapter adapter;

    private ArrayList<photo> mAndroidArrayList;
    private ArrayList<photo> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        loadJSON();
    }
    private void loadJSON() {

        RequestInterface requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RequestInterface.class);
        Log.e("RI", String.valueOf(requestInterface));

        Observable<worldpopulation> observable = requestInterface.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
        Log.e("RI", String.valueOf(observable));
        observable.subscribe(new Observer<worldpopulation>() {
            @Override
                    public void onSubscribe(Disposable d) {

                Log.e("Error","Yes");

            }

            @Override
            public void onNext(worldpopulation value) {
                Log.e("onNext","entered");
                Log.e("Value is", String.valueOf(value));
                list = new ArrayList<>(value.getPhotos());


                for (int i = 0; i < value.getPhotos().size(); i++) {

                    photo model = new photo();
                    model.setUrl(value.getPhotos().get(i).getUrl());
                    Log.e("Url is: ",value.getPhotos().get(i).getUrl());
                    list.add(model);
                }

            }

            @Override
            public void onError(Throwable e) {
                handleError(e);

            }

            @Override
            public void onComplete() {
                adapter= new DataAdapter(list);
                mRecyclerView.setAdapter(adapter);
            }

        });

        //.subscribe(this::handleResponse,this::handleError));
    }


    private void handleError(Throwable error) {

       Log.e("Error " ,error.getLocalizedMessage());
    }

}
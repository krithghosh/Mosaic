package com.images.krith.mosaic.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.images.krith.mosaic.App;
import com.images.krith.mosaic.Utils.Constant;
import com.images.krith.mosaic.Models.Photo;
import com.images.krith.mosaic.Models.Photo_Info;
import com.images.krith.mosaic.R;
import com.images.krith.mosaic.Utils.RestApi;
import com.images.krith.mosaic.StaggeredViewAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Home extends AppCompatActivity {

    @Inject
    Retrofit retrofit;

    @Inject
    RestApi restApi;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.grid_view)
    StaggeredGridView gridView;

    @BindView(R.id.activity_home)
    FrameLayout activityHome;

    private StaggeredViewAdapter adapter;
    private boolean mHasRequestedMore;
    public static final String SAVED_DATA_KEY = "SAVED_DATA";
    private ArrayList<String> mData;
    public Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);
        getSupportActionBar().setTitle("Gallery");
        mData = new ArrayList<String>();
        subscription = restApi.getPhotos(Constant.SEARCH_METHOD,
                Constant.API_KEY, Constant.TAGS, Constant.FORMAT, Constant.NOJSONCALLBACK)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Photo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Home.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(Photo photo) {
                        progressBar.setVisibility(View.GONE);
                        for (Photo_Info info : photo.getPhotos().getPhoto()) {
                            mData.add("https://farm" + info.getFarm() + ".staticflickr.com/" + info.getServer() +
                                    "/" + info.getId() + "_" + info.getSecret() + ".jpg");
                        }
                        adapter = new StaggeredViewAdapter(Home.this, R.layout.dynamic_image_view, mData);
                        gridView.setGridPadding(4, 0, 4, 0);
                        gridView.setAdapter(adapter);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

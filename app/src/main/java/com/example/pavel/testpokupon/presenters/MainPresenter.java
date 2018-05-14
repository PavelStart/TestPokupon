package com.example.pavel.testpokupon.presenters;

import android.content.Context;

import com.example.pavel.testpokupon.models.OrganCard;
import com.example.pavel.testpokupon.models.OrganResponse;
import com.example.pavel.testpokupon.utilits.NetState;
import com.example.pavel.testpokupon.utilits.RetrofitSingleton;
import com.example.pavel.testpokupon.views.IActivity;
import com.example.pavel.testpokupon.views.MainActivity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Pavel on 12.05.2018.
 */

public class MainPresenter implements IPresenter {

    private IActivity view;
    private Context context;

    public MainPresenter(MainActivity view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void onGetInfo(String input) {

        Observable<OrganResponse> observable = RetrofitSingleton.getApi().getOrganization(input);
        observable.
                subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<OrganResponse, OrganCard>() {
                    @Override
                    public OrganCard apply(@NonNull OrganResponse organResponse) throws Exception {
                        return new OrganCard(organResponse.getAvatarUrl(), organResponse.getName(),
                                organResponse.getLocation(),  organResponse.getBlog(),
                                organResponse.getReposUrl(), organResponse.getPublicRepos());
                    }
                })
                .subscribe(new Consumer<OrganCard>() {
                               @Override
                               public void accept(@NonNull OrganCard card) throws Exception {
                                   view.showInfo(card);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                if (!NetState.isConnected(context)){
                                    view.showError("No Internet Connection!");
                                } else {
                                    view.showError(throwable.getLocalizedMessage());
                                }
                            }
                        });



    }



}

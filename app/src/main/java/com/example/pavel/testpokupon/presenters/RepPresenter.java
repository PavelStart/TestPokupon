package com.example.pavel.testpokupon.presenters;

import android.content.Context;

import com.example.pavel.testpokupon.models.RepCard;
import com.example.pavel.testpokupon.models.repmodel.RepResponse;
import com.example.pavel.testpokupon.utilits.NetState;
import com.example.pavel.testpokupon.utilits.RetrofitSingleton;
import com.example.pavel.testpokupon.views.IRepActivity;
import com.example.pavel.testpokupon.views.RepActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Pavel on 12.05.2018.
 */

public class RepPresenter implements IRepPresenter {
    private IRepActivity view;
    private Context context;

    public RepPresenter(RepActivity view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void onGetInfo(String organName, String page) {
        Observable<List<RepResponse>> observable =
                RetrofitSingleton.getApi().getRepositories(organName, page);
        observable.
                subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<RepResponse>, ArrayList<RepCard>>() {
                    @Override
                    public ArrayList<RepCard> apply(@NonNull List<RepResponse> repResponses)
                            throws Exception {
                        ArrayList<RepCard> cards = new ArrayList<>();
                        for (RepResponse response:
                                repResponses) {
                            RepCard card =
                                    new RepCard(response.getName(), response.getDescription());
                            cards.add(card);
                        }
                        return cards;
                    }
                })
                .subscribe(new Consumer<ArrayList<RepCard>>() {
                               @Override
                               public void accept(@NonNull ArrayList<RepCard> repCards) throws Exception {
                                   view.onShowInfo(repCards);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                if (!NetState.isConnected(context)){
                                    view.onShowError("No internet connection");
                                } else {
                                    view.onShowError(throwable.getLocalizedMessage());
                                }
                            }
                        });

    }
}

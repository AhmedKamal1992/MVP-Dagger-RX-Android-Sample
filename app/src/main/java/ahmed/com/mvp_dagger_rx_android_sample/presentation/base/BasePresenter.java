package ahmed.com.mvp_dagger_rx_android_sample.presentation.base;

import android.os.Bundle;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ahmed Kamal on 25-10-2017.
 */

public abstract class BasePresenter<T extends MvpView> implements MvpPresenter<T>
{

    private WeakReference<T> view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected AtomicBoolean isViewAlive = new AtomicBoolean();


    @Override
    public void attachView(T view)
    {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void detechView()
    {
        view = null;
        compositeDisposable.clear();
        compositeDisposable.dispose();
    }

    public void initialize(Bundle extras) {

    }

    public void start() {
        isViewAlive.set(true);
    }

    public void finalizeView() {
        isViewAlive.set(false);
    }

    public T getView()
    {
        return view.get();
    }

    public void checkViewAttached()
    {
        if(!isViewAttached())
        {
            throw new MvpViewNotAttachedException();
        }
    }

    public boolean isViewAttached()
    {
        return view.get() != null;
    }

    public void addDisposable(Disposable disposable)
    {
        this.compositeDisposable.add(disposable);
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter");
        }
    }
}

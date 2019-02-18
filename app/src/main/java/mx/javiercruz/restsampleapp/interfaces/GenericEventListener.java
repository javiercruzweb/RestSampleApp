package mx.javiercruz.restsampleapp.interfaces;

/**
 * Created by caltecsoluciones on 18/02/19.
 */

public interface GenericEventListener<T> {
    void onSuccess(T item);
    void onError(String errorMsg);
}

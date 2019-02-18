package mx.javiercruz.restsampleapp.api;

import mx.javiercruz.restsampleapp.models.Product;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by caltecsoluciones on 18/02/19.
 */

public interface WebService {
    @GET("model/atg/commerce/catalog/ProductCatalogActor/getSkuSummaryDetails?storeId=0000009999&upc=00750129560012&skuId=00750129560012")
    Observable<Product> product();
}

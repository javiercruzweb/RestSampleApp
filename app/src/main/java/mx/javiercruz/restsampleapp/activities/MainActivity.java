package mx.javiercruz.restsampleapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.javiercruz.restsampleapp.R;
import mx.javiercruz.restsampleapp.api.Api;
import mx.javiercruz.restsampleapp.interfaces.GenericEventListener;
import mx.javiercruz.restsampleapp.models.Product;
import mx.javiercruz.restsampleapp.utils.UIUtil;

public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.textProductName)
    TextView textProductName;
    @BindView(R.id.textProductDepartment)
    TextView textProductDepartment;
    @BindView(R.id.textProductSKU)
    TextView textProductSKU;
    @BindView(R.id.textProductBasePrice)
    TextView textProductBasePrice;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        setUI();
    }

    private void setUI() {
        getProduct();
    }

    private void getProduct() {
        mostrarProgressDialog("Loading...");
        Api.getInstance().getProductAPI(new GenericEventListener<Product>() {
            @Override
            public void onSuccess(Product item) {
                ocultarProgressDialog();
                setProduct(item);
            }

            @Override
            public void onError(String errorMsg) {
                ocultarProgressDialog();
                UIUtil.showToast(mContext, String.valueOf(errorMsg));
            }
        });
    }

    private void setProduct(Product item) {
        if (item==null) {
            UIUtil.showToast(mContext, "Ocurrio un error :( ");
            return;
        }

        textProductName.setText(String.valueOf(item.skuDisplayNameText));
        textProductDepartment.setText(String.valueOf(item.department));
        textProductSKU.setText(String.valueOf(item.skuId));
        textProductBasePrice.setText(String.valueOf(item.basePrice));
    }
}

package edu.pw.f1stats.exception;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.api.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

import edu.pw.f1stats.R;

@EBean(scope = EBean.Scope.Singleton)
public class RestResponseErrorHandler implements RestErrorHandler  {

    @RootContext
    protected Context context;

    private Toast toast;

    @SuppressLint("ShowToast")
    @Override
    @UiThread
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        if (toast == null) {
            toast = Toast.makeText(context, context.getString(R.string.no_internet_message), Toast.LENGTH_SHORT);
        }
        toast.show();
    }
}

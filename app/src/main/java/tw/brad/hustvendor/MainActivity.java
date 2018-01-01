package tw.brad.hustvendor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private ConnectivityManager cmgr;
    private boolean isNetworkOK = false;
    private MyNetworkBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cmgr = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);

        receiver = new MyNetworkBroadcastReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver, filter);

    }

    @Override
    public void finish() {
        unregisterReceiver(receiver);
        super.finish();
    }

    private boolean isConnectNetwork(){
        NetworkInfo activeNetwork = cmgr.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    private class MyNetworkBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            isNetworkOK = isConnectNetwork();
        }
    }

    private void showDialog(){
        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("目前網路狀態無法正常連接\n請確認網路暢通下進行");
        dialog = builder.create();
        dialog.show();
    }


    public void goOrder(View view) {
        if (!isNetworkOK){
            showDialog();
            return;
        }

        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url",
                "/wp-admin/edit.php?post_type=shop_order");
        startActivity(intent);

    }
    public void goProduct(View view) {
        if (!isNetworkOK){
            showDialog();
            return;
        }

        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url",
                "/wp-admin/edit.php?post_type=product");
        startActivity(intent);
    }
    public void goShop(View view) {
        if (!isNetworkOK){
            showDialog();
            return;
        }

        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url",
                "/wp-admin/admin.php?page=yith_vendor_settings&tab=vendor-frontpage");
        startActivity(intent);
    }
    public void goFee(View view) {
        if (!isNetworkOK){
            showDialog();
            return;
        }

        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url",
                "/wp-admin/admin.php?page=yith_vendor_settings&tab=vendor-shipping");
        startActivity(intent);
    }
    public void goMedia(View view) {
        if (!isNetworkOK){
            showDialog();
            return;
        }

        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url",
                "/wp-admin/upload.php");
        startActivity(intent);
    }
}

package topica.edu.vn.broadcastreceiver_coding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnDangNhap;

    BroadcastReceiver wifiReceiver=new BroadcastReceiver() {//tu dong nhay vao lang nghe
        @Override
        public void onReceive(Context context, Intent intent) {//va xu ly
            ConnectivityManager connectivityManager= (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);//tra ve dich vu inter net
            if(connectivityManager.getActiveNetworkInfo()!=null) {//hoi co inter net khong
                btnDangNhap.setEnabled(true);
                Toast.makeText(context, "Ban vua bat internet", Toast.LENGTH_LONG).show();
            }
            else
            {
                btnDangNhap.setEnabled(false);
                Toast.makeText(context,"Ban vua Tắt internet",Toast.LENGTH_LONG).show();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);//tu dong lang nghe wifi
        //muon lan nghe cai gi nua
        //filter.addAction();
        registerReceiver(wifiReceiver,filter);//dang ky lang nghe cho nay
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(wifiReceiver!=null)
            unregisterReceiver(wifiReceiver);
        //huy bỏ cho nay
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
    }

    private void addControls() {
        btnDangNhap=findViewById(R.id.btnDangNhap);
    }
}
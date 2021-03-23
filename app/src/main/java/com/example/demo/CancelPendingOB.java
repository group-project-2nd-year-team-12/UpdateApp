package com.example.demo;

        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import java.util.concurrent.ExecutionException;

        import es.dmoral.toasty.Toasty;

public class CancelPendingOB extends AppCompatActivity {
    String order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_pending_o_b);
        Intent intent=getIntent();

        order_id=intent.getStringExtra("order_id");





        String type="updateCancelPendingB";

        BackgroundCancelPendingB b=new BackgroundCancelPendingB(this);
        try {
            String result=b.execute(type,order_id).get();
            if (result.equals("Successfully")){
                Intent intent1=new Intent(CancelPendingOB.this,PendingOrder.class);
                startActivity(intent1);
                Toasty.success(getApplicationContext(),"Order Cancalled", Toast.LENGTH_LONG).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
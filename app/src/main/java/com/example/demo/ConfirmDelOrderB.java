package com.example.demo;

        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import java.util.concurrent.ExecutionException;

        import es.dmoral.toasty.Toasty;

public class ConfirmDelOrderB extends AppCompatActivity {
    String order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_del_order_b);
        Intent intent=getIntent();

        order_id=intent.getStringExtra("order_id");





        String type="UpdateConfirmOrder";

        BackgroundConfirmOrder b=new BackgroundConfirmOrder(ConfirmDelOrderB.this);
        try {
            String result=b.execute(type,order_id).get();
            if (result.equals("Successfully")){
                Intent intent1=new Intent(ConfirmDelOrderB.this,DeliverOrderBoarder.class);
                startActivity(intent1);
                Toasty.success(getApplicationContext(),"Order Successfully Delivered", Toast.LENGTH_LONG).show();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
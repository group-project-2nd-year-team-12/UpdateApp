package com.example.demo;


import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.cardview.widget.CardView;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Adapter;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ImageView;
        import android.widget.ListAdapter;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        //import es.dmoral.toasty.Toasty;

public class BoardingPage extends AppCompatActivity {
    ListView listView;
    CardView payrent,paymenthis,chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boarding_page);

        payrent=findViewById(R.id.c1);
        paymenthis=findViewById(R.id.c2);
        chat=findViewById(R.id.c3);

        payrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PayRent.class);
                startActivity(intent);
             //   Toasty.warning(getApplicationContext(),"Welcome to Payments  Page", Toast.LENGTH_LONG).show();
            }
        });
        paymenthis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),PayHistory.class);
                startActivity(intent);
               // Toasty.info(getApplicationContext(),"Welcome to Payments History Page",Toast.LENGTH_LONG).show();
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ChatPage.class);
                startActivity(intent);
            //    Toasty.info(getApplicationContext(),"You can chat here",Toast.LENGTH_LONG).show();
            }
        });


        ///set the toolbar
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    ///create menu item in toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }


    ////click menu item in menu


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.item1:
                Intent intent=new Intent(this,HomeBO.class);
                startActivity(intent);
          //      Toasty.info(this,"Home page is again",Toast.LENGTH_LONG).show();
                // Toast.makeText(this,"Home page is selected",Toast.LENGTH_LONG).show();
                break;

            case R.id.item2:
                Intent intent1=new Intent(this,MainActivity.class);
                startActivity(intent1);
             //   Toasty.success(this,"Logout succesfully",Toast.LENGTH_LONG).show();
                // Toast.makeText(this,"Logout succesfully",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
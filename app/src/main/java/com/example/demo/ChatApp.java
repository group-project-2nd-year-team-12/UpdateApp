package com.example.demo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatApp extends AppCompatActivity {

    private WebSocket webSocket;
    private  MessageAdapter adapter;
    EditText messageBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_app);

        ListView messageList=findViewById(R.id.messageList);
         messageBox=findViewById(R.id.messageBox);
        TextView send=findViewById(R.id.send);


        instantiateWebSocet();

         adapter=new MessageAdapter();
        messageList.setAdapter(adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=messageBox.getText().toString();
                if (!message.isEmpty()){
                    webSocket.send(message);
                    messageBox.setText("");
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("message",message);
                        jsonObject.put("byServer",false);

                        adapter.addItem(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void instantiateWebSocet() {
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("ws://10.0.2.2:8080").build();
        SocketListener socketListener=new SocketListener(this);
         webSocket=client.newWebSocket(request,socketListener);
    }

    public class SocketListener extends WebSocketListener{
        public ChatApp chatApp;

        public SocketListener(ChatApp chatApp){
            this.chatApp=chatApp;

        }
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);


            chatApp.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(chatApp,"Connection Established",Toast.LENGTH_LONG).show();
                }
            });
        }

        @Override
        public void onMessage(WebSocket webSocket, final String text) {
            super.onMessage(webSocket, text);


            chatApp.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("message",text);
                        jsonObject.put("byServer",true);

                        adapter.addItem(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }



        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
        }
    }
    public class MessageAdapter extends BaseAdapter{

        List<JSONObject> messageList=new ArrayList<>();

        @Override
        public int getCount() {
            return messageList.size();
        }

        @Override
        public Object getItem(int position) {
            return messageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view==null)
                view=getLayoutInflater().inflate(R.layout.message_list_item,parent,false);

            TextView sendMessage=view.findViewById(R.id.sendMessage);
            TextView receivedMessage=view.findViewById(R.id.receivedMessage);

            JSONObject item=messageList.get(position);

            try {
                if (item.getBoolean("byServer")){

                    receivedMessage.setVisibility(View.VISIBLE);

                    receivedMessage.setText(item.getString("message"));
                    sendMessage.setVisibility(View.INVISIBLE);

                }else{
                    sendMessage.setVisibility(View.VISIBLE);
                    sendMessage.setText(item.getString("message"));
                    receivedMessage.setVisibility(View.INVISIBLE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return view;
        }


        void addItem(JSONObject item){
            messageList.add(item);
            notifyDataSetChanged();
        }
    }
}
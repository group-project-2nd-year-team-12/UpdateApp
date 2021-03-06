package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context mCtx;
    private List<Product> productList;
    private OnItemClickListener mListener;
    String name;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDelete(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.card_view_list_practise,null);

        ProductViewHolder holder=new ProductViewHolder(view,mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Product product=productList.get(position);

        holder.textfirst.setText(product.getFirst_name());
        holder.textlast.setText(product.getLast_name());
        holder.textaddress.setText(product.getAddress());


        Glide.with(mCtx).load(product.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textfirst,textlast,textaddress;
        Button button;


        public ProductViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView);
            textfirst=itemView.findViewById(R.id.textFirst);
            textlast=itemView.findViewById(R.id.textLast);
            textaddress=itemView.findViewById(R.id.textaddress);
            button=itemView.findViewById(R.id.btn);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener!=null){
                        int postion=getAbsoluteAdapterPosition();
                        if (postion!=RecyclerView.NO_POSITION){
                            listener.onItemClick(postion);
                        }
                    }

                }
            });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(mCtx,"Updated",Toast.LENGTH_LONG).show();

                    if (listener!=null){
                        int postion=getAbsoluteAdapterPosition();

                        if (postion!=RecyclerView.NO_POSITION){
                            listener.onDelete(postion);

                        }

                    }
                }
            });


        }
    }
}

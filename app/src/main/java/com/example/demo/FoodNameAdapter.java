package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FoodNameAdapter extends RecyclerView.Adapter<FoodNameAdapter.FoodNameViewHolder>{

    private Context mCtx;
    private List<FoodName> foodNameList;





    private OnItemClickListener mListener;
    String name;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onDelete(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }


    public FoodNameAdapter(Context mCtx, List<FoodName> foodNameList) {
        this.mCtx = mCtx;
        this.foodNameList = foodNameList;
    }


    @Override
    public FoodNameViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.card_order_food,null);
        FoodNameViewHolder foodNameViewHolder=new FoodNameViewHolder(view,mListener);
        return foodNameViewHolder;
    }

    @Override
    public void onBindViewHolder(FoodNameViewHolder holder, int position) {

        FoodName foodName=foodNameList.get(position);
        holder.textViewTitle.setText(foodName.getTitle());
        holder.textViewrating.setText(foodName.getRating());
        holder.textViewdaddress.setText(foodName.getAddress());
        holder.textViewdescription.setText(foodName.getDescription());
        Glide.with(mCtx).load(foodName.getImage()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return foodNameList.size();
    }

//    public void setOnItemClickListener(ProductAdapter.OnItemClickListener one_item_selected) {
//    }

    class FoodNameViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewTitle,textViewdescription,textViewdaddress,textViewrating;

        public FoodNameViewHolder( View itemView,final OnItemClickListener listener) {
            super(itemView);
            imageView=itemView.findViewById(R.id.foodimg);
            textViewTitle=itemView.findViewById(R.id.title);
            textViewdescription=itemView.findViewById(R.id.description);
            textViewdaddress=itemView.findViewById(R.id.location);
            textViewrating=itemView.findViewById(R.id.rating);



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
        }
    }
}

package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.models.Result;
import com.example.test.models.UsersResponse;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    List<Result> usersResponseList = Collections.emptyList();
//    public RecyclerAdapter(Context context, List<Result> usersResponseList){
//        this.context=context;
//        this.usersResponseList= (List<Result>) usersResponseList;
//    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adapter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          holder.bind(usersResponseList.get(position));
//        holder.textView.setText(usersResponseList.get(position).getName().getFirst());
//        Picasso.get().load(usersResponseList.get(position).getPicture().getLarge()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(usersResponseList!=null){
            return usersResponseList.size();
        }
        return  0;
    }
    public void setUsersResponseList(List<Result> usersResponseList){
        this.usersResponseList= usersResponseList;
        notifyDataSetChanged();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        public MyViewHolder(View itemview){
            super(itemview);
            textView=(TextView)itemview.findViewById(R.id.textView);
            imageView=(ImageView)itemview.findViewById(R.id.imageView);
        }
        public void bind(Result result){
            textView.setText(result.getName().getFirst());
            Picasso.get().load(result.getPicture().getMedium()).into(imageView);
        }

    }
}

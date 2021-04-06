package com.example.authapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.authapp.Model.QRCode;
import com.example.authapp.R;

import java.util.List;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class RecycleViewQRCode extends RecyclerView.Adapter<RecycleViewQRCode.MyViewHolder> {

    private Context context;
    private List<QRCode> qrc_list;

    public RecycleViewQRCode(Context context, List<QRCode> qrc_list) {
        this.context = context;
        this.qrc_list = qrc_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(context);
        v = inflater.inflate(R.layout.item_qrc, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.theater_title.setText(qrc_list.get(position).getTheater_title());
        holder.title.setText(qrc_list.get(position).getTitle());
        holder.date.setText(qrc_list.get(position).getDate());
        holder.time.setText(qrc_list.get(position).getTime());
        holder.ticket_type.setText(qrc_list.get(position).getTicket_type());

            String value = qrc_list.get(position).getTheater_title() + "\n";
                 value += qrc_list.get(position).getTitle()+ "\n";
                 value += qrc_list.get(position).getDate()+ "\n";
                 value += qrc_list.get(position).getTime()+ "\n";
                 value += qrc_list.get(position).getTicket_type();



                 if(value.length() >0) {
                     QRGEncoder qrgEncoder = new QRGEncoder(value, null, QRGContents.Type.TEXT, 500);
                     try {
                         Bitmap qrBits = qrgEncoder.getBitmap();
                         holder.qrcImg.setImageBitmap(qrBits);
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                 }else{
                     Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
                 }

    }



    @Override
    public int getItemCount() {
        return qrc_list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView theater_title, title, date, time, ticket_type;
        ImageView qrcImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            theater_title = itemView.findViewById(R.id.ds_theater_title);
            title = itemView.findViewById(R.id.ds_title);
            date = itemView.findViewById(R.id.ds_date);
            time = itemView.findViewById(R.id.ds_time);
            ticket_type = itemView.findViewById(R.id.ds_ticket_type);
            qrcImg = itemView.findViewById(R.id.ds_qrImage);

        }
    }
}

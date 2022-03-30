package com.example.batumicinema.adapters;



import static com.example.batumicinema.network.models.ChatMessageResponse.LayoutOne;
import static com.example.batumicinema.network.models.ChatMessageResponse.LayoutTwo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batumicinema.R;
import com.example.batumicinema.chat.ChatItem;
import com.example.batumicinema.network.models.ChatMessageResponse;


import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {


    private List<ChatMessageResponse> chatItems;
    private String textMessageMy, textMessageOpp;
    private String textNameMy, textNameOpp;
    private String textTimeMy, textTimeOpp;

    public ChatAdapter(List<ChatMessageResponse> chatItems) {
        this.chatItems = chatItems;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LayoutOne:
                View viewOne = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_my, parent, false);
                return new LayoutOneViewHolder(viewOne);
            case LayoutTwo:
                View viewTwo = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item_opponent, parent, false);
                return new LayoutTwoViewHolder(viewTwo);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (chatItems.get(position).getViewType()){
            case LayoutOne:
                textMessageMy = chatItems.get(position).getText();
                textNameMy = chatItems.get(position).getFirstName() + chatItems.get(position).getLastName();
                textTimeMy = chatItems.get(position).getCreationDateTime();
                ((LayoutOneViewHolder)holder).setTxtMessage(textMessageMy);
                ((LayoutOneViewHolder)holder).setTxtNamePerson(textNameMy);
                ((LayoutOneViewHolder)holder).txtMessageTime.setText(textTimeMy);
                ((LayoutOneViewHolder)holder).imageView.setImageResource(R.drawable.disc_arraw);
                break;
            case LayoutTwo:
                textMessageOpp = chatItems.get(position).getText();
                textNameOpp = chatItems.get(position).getFirstName() + chatItems.get(position).getLastName();
                textTimeOpp = chatItems.get(position).getCreationDateTime();
                ((LayoutTwoViewHolder)holder).setTxtMessage(textMessageOpp);
                ((LayoutTwoViewHolder)holder).setTxtNamePerson(textNameOpp);
                ((LayoutTwoViewHolder)holder).txtMessageTime.setText(textTimeOpp);
                ((LayoutTwoViewHolder)holder).imageView.setImageResource(R.drawable.disc_arraw);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (chatItems.get(position).getViewType()) {
            case 0:
                return LayoutOne;
            case 1:
                return LayoutTwo;
            default:
                return -1;
        }
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    class LayoutOneViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMessage;
        private TextView txtNamePerson;
        private TextView txtMessageTime;
        public ImageView imageView;

        public LayoutOneViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_chat_my);
            txtNamePerson = itemView.findViewById(R.id.txt_chat_name_my);
            txtMessageTime = itemView.findViewById(R.id.txt_chat_time_my);
            imageView = itemView.findViewById(R.id.image_my);
        }


        public TextView getTxtMessage() {
            return txtMessage;
        }

        public void setTxtMessage(String txt) {
            txtMessage.setText(txt);
        }

        public TextView getTxtNamePerson() {
            return txtNamePerson;
        }

        public void setTxtNamePerson(String txtName) {
            txtNamePerson.setText(txtName);
        }
    }
    class LayoutTwoViewHolder extends RecyclerView.ViewHolder {

        private TextView txtMessage;
        private TextView txtNamePerson;
        private TextView txtMessageTime;
        public ImageView imageView;


        public LayoutTwoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_chat_opponent);
            txtNamePerson = itemView.findViewById(R.id.txt_chat_name_opponent);
            txtMessageTime = itemView.findViewById(R.id.txt_chat_time_opponent);
            imageView = itemView.findViewById(R.id.image_opponent);
        }

        public TextView getTxtMessage() {
            return txtMessage;
        }

        public void setTxtMessage(String txt) {
            txtMessage.setText(txt);
        }

        public TextView getTxtNamePerson() {
            return txtNamePerson;
        }

        public void setTxtNamePerson(String txtName) {
            txtNamePerson.setText(txtName);
        }


    }

}

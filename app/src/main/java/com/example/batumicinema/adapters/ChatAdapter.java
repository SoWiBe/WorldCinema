package com.example.batumicinema.adapters;

import static com.example.batumicinema.chat.ChatItem.LayoutOne;
import static com.example.batumicinema.chat.ChatItem.LayoutTwo;

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


import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter {


    private List<ChatItem> chatItems;

    public ChatAdapter(List<ChatItem> chatItems) {
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
                String text = chatItems.get(position).getText();
                ((LayoutOneViewHolder)holder).setTxtMessage(text);
                ((LayoutOneViewHolder)holder).setTxtNamePerson(text);
                ((LayoutOneViewHolder)holder).imageView.setImageResource(R.drawable.disc_arraw);
                break;
            case LayoutTwo:
                String text1 = chatItems.get(position).getText();
                ((LayoutTwoViewHolder)holder).setTxtMessage(text1);
                ((LayoutTwoViewHolder)holder).setTxtNamePerson(text1);
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
        public ImageView imageView;

        public LayoutOneViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_chat_my);
            txtNamePerson = itemView.findViewById(R.id.txt_chat_name_my);
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
        public ImageView imageView;

        public LayoutTwoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMessage = itemView.findViewById(R.id.txt_chat_opponent);
            txtNamePerson = itemView.findViewById(R.id.txt_chat_name_opponent);
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

package com.example.batumicinema.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.batumicinema.Discussions.Discussion;
import com.example.batumicinema.R;

import java.util.ArrayList;

public class DiscussionsAdapter extends RecyclerView.Adapter<DiscussionsAdapter.ViewHolder> {

    private ArrayList<Discussion> discussions;
    private Context context;

    public DiscussionsAdapter(ArrayList<Discussion> discussions, Context context) {
        this.discussions = discussions;
        this.context = context;
    }

    @NonNull
    @Override
    public DiscussionsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discussions_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscussionsAdapter.ViewHolder holder, int position) {
        holder.txtChatDiscussions.setText(discussions.get(position).getDiscChatText());
        holder.txtTitleDiscussions.setText(discussions.get(position).getDiscTitle());
        holder.iconDiscussions.setImageResource(discussions.get(position).getDiscIcon());
    }

    @Override
    public int getItemCount() {
        return discussions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final private ImageView iconDiscussions;
        final private TextView txtTitleDiscussions;
        final private TextView txtChatDiscussions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iconDiscussions = (ImageView) itemView.findViewById(R.id.iconDiscussion);
            this.txtTitleDiscussions = (TextView) itemView.findViewById(R.id.txt_disc_title);
            this.txtChatDiscussions = (TextView) itemView.findViewById(R.id.txt_disc_chat);
        }
    }
}

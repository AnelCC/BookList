package com.anelcalvo.www.myapplication;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {
    private List<Repo> repositories;
    private Context context;

    public RepositoryAdapter(List<Repo> repositories) {
        this.repositories = repositories;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            context  = itemView.getContext();
            title = itemView.findViewById(R.id.book_title);
            imageView = itemView.findViewById(R.id.book_image);
        }

        public void bind(Repo repo){
            title.setText(repo.getTitle());
            Picasso.with(context).load(repo.getImageURL()).fit().centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageView);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.book_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

}

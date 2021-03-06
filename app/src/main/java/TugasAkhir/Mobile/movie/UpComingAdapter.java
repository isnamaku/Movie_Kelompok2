package TugasAkhir.Mobile.movie;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.ViewHolder> {

    private List<MovieResult> results;
    private final Context context;
    private int rowLayout;


    public UpComingAdapter(List<MovieResult>  results, int rowLayout, Context context){
        this.results = results;
        this.context = context;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
                holder.tvJudul.setText(results.get(position).getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500"+results.get(position).getPosterPath())
                .placeholder(R.mipmap.iconmovie)
                .error(R.mipmap.iconmovie)
                .into(holder.ivPoster);
        holder.tvRate.setText(results.get(position).getVoteAverage() + " / 10");
        holder.tvVote.setText(results.get(position).getVoteCount()+" Votes");
        holder.tvPopular.setText( results.get(position).getPopularity());

        holder.bind(results.get(position));
    }


    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvJudul, tvRate, tvVote, tvPopular;
        private ImageView ivPoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.MovieTitle);
            ivPoster = itemView.findViewById(R.id.MovieImg);
            tvRate = itemView.findViewById(R.id.tv_rate);
            tvVote = itemView.findViewById(R.id.tv_vote);
            tvPopular = itemView.findViewById(R.id.tv_popularitas2);
        }

        public void bind(final MovieResult movieResult) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MovieDetails.class);
                    intent.putExtra("id", movieResult.getId());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}



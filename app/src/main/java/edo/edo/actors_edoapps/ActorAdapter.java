package edo.edo.actors_edoapps;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.actorHolder> {

    List<Actor> aktor;
    Context konteks;

    public ActorAdapter(List<Actor> aktor, Context konteks) {
        this.aktor = aktor;
        this.konteks = konteks;
    }

    @NonNull
    @Override
    public actorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        actorHolder o = new actorHolder(v);
        return o;
    }

    @Override
    public void onBindViewHolder(@NonNull actorHolder holder, int position) {
        holder.nama.setText(aktor.get(position).getName());
        Picasso.get().load(aktor.get(position).getImage()).into(holder.gambar);
        holder.dob.setText(aktor.get(position).getDob());

        final String nama = holder.nama.getText().toString();
        final String gambar = aktor.get(position).getImage();
        final String dob = holder.dob.getText().toString();
        final String deskripsi = aktor.get(position).getDescription();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(konteks.getApplicationContext(),DetailActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("nama",nama);
                i.putExtra("gambar",gambar);
                i.putExtra("deskripsi",deskripsi);
                i.putExtra("dob",dob);
                konteks.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return aktor.size();
    }

    public static class actorHolder extends RecyclerView.ViewHolder{

        private CardView cardView;
        private TextView nama,dob;
        private ImageView gambar;

        public actorHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.idcardview);
            nama = itemView.findViewById(R.id.idnama);
            gambar = itemView.findViewById(R.id.idgambar);
            dob = itemView.findViewById(R.id.iddob);
        }
    }

}

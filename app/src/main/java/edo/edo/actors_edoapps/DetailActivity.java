package edo.edo.actors_edoapps;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    //Variabel global
    private TextView nama,dob,deskripsi;
    private ImageView gambar;
    private ProgressDialog pd;
    private String idnama,iddob,iddeskripsi,idgambar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        // Tombol Arah Panah Back
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Konfigurasi Progress Dialog
        pd = new ProgressDialog(this);
        pd.setMessage("Silahkan tunggu ya .....");
        pd.setIndeterminate(true);
        pd.show();

        //Binding data
        BindingData();

        //Get data dari Intent
        if(getIntent() != null){
            idnama = getIntent().getStringExtra("nama");
            iddeskripsi = getIntent().getStringExtra("deskripsi");
            iddob = getIntent().getStringExtra("dob");
            idgambar = getIntent().getStringExtra("gambar");

            nama.setText(idnama);
            Picasso.get().load(idgambar).into(gambar);
            deskripsi.setText(iddeskripsi);
            dob.setText(iddob);

            pd.dismiss();
        }else{
            pd.dismiss();
        }

        setTitle("Detail of "+idnama);

    }

    private void BindingData(){
        nama =  findViewById(R.id.iddetailnama);
        dob = findViewById(R.id.iddetaildob);
        deskripsi = findViewById(R.id.iddetaildeskripsi);
        gambar = findViewById(R.id.iddetailgambar);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Tombol back panah
        if (item.getItemId() == android.R.id.home) {
            finish(); //Tutup Activity
        }

        return super.onOptionsItemSelected(item);
    }
}

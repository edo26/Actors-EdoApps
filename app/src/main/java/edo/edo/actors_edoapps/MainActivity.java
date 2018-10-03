package edo.edo.actors_edoapps;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anggy Edo Prasetya
 */

public class MainActivity extends AppCompatActivity {

    //Variabel Global
    private RecyclerView rv;
    private ActorAdapter adapter;
    private List<Actor> data;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //Konfigurasi ProgressDialog
        pd = new ProgressDialog(this);
        pd.setMessage("Tolong tunggu ya.... By : Anggy Edo Prasetya");
        pd.setIndeterminate(true);
        pd.show();

        //Method untuk binding data
        allBindingData();
        
        //Konfigurasi RecyclerView
        rv.setHasFixedSize(true);
        StaggeredGridLayoutManager namaobjek = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(namaobjek);
        
        //Fetch atau Load data dari API
        fetchDataFromAPI();
    }

    private void allBindingData(){
        rv = findViewById(R.id.idrecyclerview);
    }
    
    private void fetchDataFromAPI(){
        SampleAPI objek = SampleAPI.Factory.getInstance(this);
        objek.getActors().enqueue(new Callback<ActorsModel>() {
            @Override
            public void onResponse(Call<ActorsModel> call, Response<ActorsModel> response) {
                if(response.isSuccessful()){
                    data = response.body().getActors();
                    adapter = new ActorAdapter(data,getApplicationContext());
                    rv.setAdapter(adapter);
                    pd.dismiss();
                }else{
                    pd.dismiss();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                    alertDialogBuilder.setTitle("Kesalahan").setMessage("Terdapat Pesan : "+response.message()).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                }
            }

            @Override
            public void onFailure(Call<ActorsModel> call, Throwable t) {
                pd.dismiss();
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());
                alertDialogBuilder.setTitle("Kesalahan").setMessage("Terdapat Error : "+t.getMessage()).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
            }
        });
    }
}

package sg.edu.np.mad.madpractical;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {

    ArrayList<User> ls;
    Context mContext;
    AlertDialog.Builder builder;
    public Adapter (Context context, ArrayList<User> input){
        ls = input;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        User s = ls.get(position);
        if (s.name.endsWith("7")) {
            return 0;
        }
        return 1;
    }
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent) {
        View item1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        return new ViewHolder(item1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;
        if (viewType == 1){
             item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item, parent, false);
        }
        else {
             item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item2, parent, false);
        }
        return new ViewHolder(item);

    }

    public void onBindViewHolder(ViewHolder holder, int position){
        User s = ls.get(position);

        holder.name.setText(s.name);
        holder.descr.setText(s.description);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Profile");
                builder.setMessage("MADness").setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //AlertDialog();
                        //Integer i = randomOTP();
                        // go back to main activity
                        Intent intent = new Intent(mContext, MainActivity.class);
                        intent.putExtra("user", s);
                        mContext.startActivity(intent);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemCount(){
        return ls.size();
    }
}

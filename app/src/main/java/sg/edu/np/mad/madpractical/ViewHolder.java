package sg.edu.np.mad.madpractical;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class ViewHolder extends RecyclerView.ViewHolder{

    // declare whats in it
    TextView name;
    TextView descr;
    ImageView img;

    public ViewHolder(View itemView){
        super(itemView);
        name = itemView.findViewById(R.id.Name);
        descr = itemView.findViewById(R.id.description);
        img = itemView.findViewById(R.id.alert);
    }


}


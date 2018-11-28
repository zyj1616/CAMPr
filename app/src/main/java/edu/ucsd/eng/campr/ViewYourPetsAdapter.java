package edu.ucsd.eng.campr;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Iterator;
import java.util.List;

public class ViewYourPetsAdapter extends RecyclerView.Adapter<ViewYourPetsAdapter.ViewHolder>{
    private List<Pets> values;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView petPicture;
        public TextView txtHeader;
        public TextView txtFooter;
        public TextView txtId;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            petPicture = (ImageView) v.findViewById(R.id.icon);
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            txtId = (TextView) v.findViewById(R.id.pet_id);

            v.setOnClickListener(this);
        }

        public void onClick(View v) {
            DatabaseInterface db = DatabaseInterface.getInstance();
            TextView textView = v.findViewById(R.id.pet_id);
            String key = textView.getText().toString();
            //Pets temp = ListerPetList.myPets.get(key);
            Pets temp = db.getPetByID(key);
            Intent intent = new Intent(v.getContext(), ViewPetActivity.class);
            intent.putExtra("parcel_data", temp);
            v.getContext().startActivity(intent);
        }
    }

    /*public void add(int position, Pets item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }*/

    public ViewYourPetsAdapter(List<Pets> myDataset) {
        Iterator<Pets> iter = myDataset.iterator();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getUid();

        while (iter.hasNext()) {
            Pets p = iter.next();
            if (p.getListerID() == userID) {
                values.add(p);
            }
        }
        values = myDataset;
    }

    @Override
    public ViewYourPetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewYourPetsAdapter.ViewHolder vh = new ViewYourPetsAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewYourPetsAdapter.ViewHolder holder, final int position) {
        final Pets pet = values.get(position);
        holder.txtHeader.setText(pet.getName());
        /*holder.txtHeader.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //add(position, pet);
            }
        });*/

        holder.txtFooter.setText(pet.getGender());
        holder.txtId.setText(pet.getName()+pet.getGender()+pet.getInfo());
        holder.petPicture.setImageBitmap(pet.getPetPic());
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

}

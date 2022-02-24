package com.example.petishop;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petishop.data.PetsContract;

public class CursorAdapter extends RecyclerView.Adapter<CursorAdapter.ViewHolder> {
    private Context context;
    private Cursor cursor;

    public CursorAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public CursorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_cursor_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CursorAdapter.ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        @SuppressLint("Range")
        String name = cursor.getString(cursor.getColumnIndex(PetsContract.PetEntry.COLUMN_PET_NAME));
        @SuppressLint("Range")
        String breed = cursor.getString(cursor.getColumnIndex(PetsContract.PetEntry.COLUMN_PET_BREED));
        @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(PetsContract.PetEntry._ID));
        Uri currentUri = ContentUris.withAppendedId(PetsContract.PetEntry.CONTENT_URI,id);
        holder.mName.setText(name);
        holder.mBreed.setText(breed);

        holder.mLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditorActivity.class);
                i.setData(currentUri);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mName, mBreed;
        LinearLayout mLinear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name_text_view);
            mBreed = itemView.findViewById(R.id.breed_text_view);
            mLinear = itemView.findViewById(R.id.parent_layout);
        }
    }
}



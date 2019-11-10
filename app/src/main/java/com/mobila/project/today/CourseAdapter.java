package com.mobila.project.today;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobila.project.today.model.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private List<Course> courses;
    private ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int index);
    }

    public CourseAdapter(Context context, List<Course> courses) {
        this.courses = courses;
        this.activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseName;
        TextView tvLecturer;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvCourseName = itemView.findViewById(R.id.txt_course_name);
            tvLecturer = itemView.findViewById(R.id.txt_lecturer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(courses.indexOf((Course)itemView.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,
                false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(courses.get(position));

        holder.tvCourseName.setText(courses.get(position).getName());
        holder.tvLecturer.setText(courses.get(position).getLecturer());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}

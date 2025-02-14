package android.example.uptoskills.Adapters

import android.content.Context
import android.example.uptoskills.R
import android.example.uptoskills.models.FreeCourse
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

class MyFreeCourseAdapter(val context: Context, val listener: CourseItemClicked): RecyclerView.Adapter<MyFreeCourseAdapter.CourseViewholder>() {

    private var courses: ArrayList<FreeCourse> = ArrayList()

    inner class CourseViewholder(itemview: View): RecyclerView.ViewHolder(itemview) {
        var courseImage: ImageView = itemview.findViewById(R.id.courseImage)
        var courseName: TextView = itemview.findViewById(R.id.CourseName)
        var course: MaterialCardView = itemview.findViewById(R.id.course)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewholder {
        val viewholder = CourseViewholder(LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false))
        viewholder.course.setOnClickListener {
            listener.onCourseCLick(courses[viewholder.adapterPosition].id)
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: CourseViewholder, position: Int) {
        holder.courseName.text = courses[position].course_name
        Glide.with(holder.courseImage.context).load(courses[position].course_image).centerCrop().into(holder.courseImage)
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    fun updateCourses(newCourses: ArrayList<FreeCourse>) {
        courses.clear()
        courses.addAll(newCourses)
        notifyDataSetChanged()
    }
}

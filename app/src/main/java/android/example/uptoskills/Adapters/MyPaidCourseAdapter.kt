package android.example.uptoskills.Adapters

import android.content.Context
import android.example.uptoskills.R
import android.example.uptoskills.models.PaidCourse
import android.example.uptoskills.models.Users
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

class MyPaidCourseAdapter(val context: Context, val listener: CourseItemClicked, val curUser: Users): RecyclerView.Adapter<MyPaidCourseAdapter.CourseViewholder>() {

    private var courses: ArrayList<PaidCourse> = ArrayList()


    inner class CourseViewholder(itemview: View): RecyclerView.ViewHolder(itemview) {
        var courseImage: ImageView = itemview.findViewById(R.id.courseImage)
        var courseName: TextView = itemview.findViewById(R.id.CourseName)
        var course: MaterialCardView = itemview.findViewById(R.id.course)
        val isCompleted: TextView = itemview.findViewById(R.id.isComplete)
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
        if(curUser.paidcourses?.get(courses[position].id)?.lowercase()  != "no") {
            holder.isCompleted.visibility = View.VISIBLE

        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    fun updateCourses(newCourses: ArrayList<PaidCourse>) {
        courses.clear()
        courses.addAll(newCourses)
        notifyDataSetChanged()
    }
}
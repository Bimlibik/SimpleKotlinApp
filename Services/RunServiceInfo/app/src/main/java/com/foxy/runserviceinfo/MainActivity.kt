package com.foxy.runserviceinfo

import android.app.ActivityManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val am : ActivityManager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningServices : List<ActivityManager.RunningServiceInfo> = am.getRunningServices(50)
        val adapter = ServiceAdapter(runningServices)
        recycler.adapter = adapter
    }




    class ServiceAdapter(services: List<ActivityManager.RunningServiceInfo>) :
        RecyclerView.Adapter<ServiceAdapter.ServiceHolder>() {

        var services: List<ActivityManager.RunningServiceInfo> = services
            set(services) {
                field = services
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            return ServiceHolder(view)
        }

        override fun getItemCount(): Int = services.size

        override fun onBindViewHolder(holder: ServiceHolder, position: Int) {
            val service = services[position]
            val lastActivityTime = SimpleDateFormat("hh:mm:ss a").format(Date(service.lastActivityTime))
            holder.title.text = "Process ${service.process} with component ${service.service.className}. Last activity time = $lastActivityTime"
        }

        class ServiceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var title: TextView = itemView.findViewById(R.id.tv_service) as TextView
        }


    }

}

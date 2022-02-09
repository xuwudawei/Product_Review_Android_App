package com.example.multifeaturekotlinapp
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener

class LandingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var jobScheduler: JobScheduler? =null
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_landing_page)
        var toolbar=findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.drawable.logo_toolbar)
        toolbar.setTitle("Rate Us")
        setSupportActionBar(toolbar)
        var btnSubmit=findViewById<Button>(R.id.btnSubmit)
        val txtView=findViewById<TextView>(R.id.textViewLength)
        val textInput=findViewById<EditText>(R.id.editTextInput)
        val textProgressBar=findViewById<ProgressBar>(R.id.txtProgressBar)
        val simpleRatingBar=findViewById<RatingBar>(R.id.simpleRatingBar)

        jobScheduler=getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val componentName=ComponentName(this,BackgroundMusicJobService::class.java)
        val builder= JobInfo.Builder(123,componentName)
        builder.setMinimumLatency(3000)
        builder.setOverrideDeadline(5000)
        builder.setPersisted(true)
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
        builder.setRequiresCharging(false)
        jobScheduler!!.schedule(builder.build())

        txtView.text=0.toString()+" / "+textProgressBar.max
        textInput.addTextChangedListener {
            textProgressBar.progress=textInput.text.length
            txtView.text=textInput.text.length.toString()+" / "+textProgressBar.max
        }

        simpleRatingBar.onRatingBarChangeListener=RatingBar.OnRatingBarChangeListener{
                ratingBar:RatingBar?,rating:Float,fromUser:Boolean->Toast.makeText(this,"Product Rating: $rating",Toast.LENGTH_SHORT).show()
        }

        btnSubmit.setOnClickListener {
            val vg: ViewGroup? = findViewById(R.id.custom_toast)
            val inflater=layoutInflater

            val layout: View =inflater.inflate(R.layout.custom_toast_layout,vg)

            val tv=layout.findViewById<TextView>(R.id.txtThankYou)
            tv.text="Thank you for your valuable feedback! We look forward to working with you in the future."
            val toast=Toast(applicationContext)

            toast.setGravity(Gravity.CENTER_VERTICAL,0,100)
            toast.duration=Toast.LENGTH_LONG
            toast.setView(layout)
            toast.show()

            if(jobScheduler!=null){
                jobScheduler!!.cancel(123)
                jobScheduler=null
                Toast.makeText(this,"Job Cancelled",Toast.LENGTH_SHORT).show()
            }

        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id:Int=item.itemId
        if(id==R.id.action_settings){
            Toast.makeText(applicationContext,"Settings",Toast.LENGTH_SHORT).show()
            return true
        }
        else if(id==R.id.action_logout){
            Toast.makeText(applicationContext,"Logout",Toast.LENGTH_SHORT).show()
            return true
        }
        else if(id==R.id.action_learn_more){
            Toast.makeText(applicationContext,"Learn More...",Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)

    }
}
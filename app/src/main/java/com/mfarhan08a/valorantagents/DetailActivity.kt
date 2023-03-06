package com.mfarhan08a.valorantagents

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import com.mfarhan08a.valorantagents.databinding.ActivityDetailBinding
import com.mfarhan08a.valorantagents.databinding.ActivityMainBinding
import java.util.jar.Attributes

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_AGENT = "extra_agent"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val agent = if (Build.VERSION.SDK_INT > 33) {
            intent.getParcelableExtra(EXTRA_AGENT, Agent::class.java)
        } else {
            @Suppress("DEPRECIATION")
            intent.getParcelableExtra(EXTRA_AGENT)
        }

        if (agent != null) {
            binding.tvAgentName.text = agent.name
            binding.tvAgentRole.text = agent.role
            binding.tvAgentBiography.text = agent.biography
            binding.imgAgentArt.setImageResource(agent.art)

            val shareTitle = "Valorant Agent Profile"
            val shareText =
                "Valorant Agent Profile. Name: ${agent.name}, Role: ${agent.role}, Biography: ${agent.biography}, Icon: ${agent.icon}"
            binding.btnShare.setOnClickListener {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, shareTitle)
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareText)
                val chooser = Intent.createChooser(shareIntent, "Share Using..")
                startActivity(chooser)
            }
        }
    }
}
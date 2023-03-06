package com.mfarhan08a.valorantagents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfarhan08a.valorantagents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Agent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAgents.setHasFixedSize(true)
        list.addAll(getListAgents())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding.rvAgents.layoutManager = LinearLayoutManager(this)
        val listAgentAdapter = ListAgentAdapter(list)
        binding.rvAgents.adapter = listAgentAdapter

        listAgentAdapter.setOnItemClickCallback(object : ListAgentAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Agent) {
                showSelectedAgent(data)
                val detailActivityIntent = Intent(this@MainActivity, DetailActivity::class.java)
                detailActivityIntent.putExtra(DetailActivity.EXTRA_AGENT, data)
                startActivity(detailActivityIntent)
            }
        })
    }

    private fun showSelectedAgent(agent: Agent) {
        Toast.makeText(this, "You're choosing " + agent.name, Toast.LENGTH_SHORT).show()
    }

    private fun getListAgents(): ArrayList<Agent> {
        val dataName = resources.getStringArray(R.array.data_agent_name)
        val dataRole = resources.getStringArray(R.array.data_agent_role)
        val dataBiography = resources.getStringArray(R.array.data_agent_biography)
        val dataIcon = resources.getStringArray(R.array.data_agent_icon)
        val dataArt = resources.obtainTypedArray(R.array.data_agent_art)
        val listAgent = ArrayList<Agent>()
        for (i in dataName.indices) {
            val agent = Agent(
                dataName[i],
                dataRole[i],
                dataBiography[i],
                dataIcon[i],
                dataArt.getResourceId(i, -1)
            )
            listAgent.add(agent)
        }
        return listAgent
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.about_page ) {
            val aboutIntent = Intent(this@MainActivity,AboutActivity::class.java)
            startActivity(aboutIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
package com.firstcode.interviewapp.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.firstcode.interviewapp.databinding.ItemPostBinding
import com.firstcode.interviewapp.db.DBPost
import com.firstcode.interviewapp.model.Post
import com.firstcode.interviewapp.util.PostItemListener

class PostsAdapter(var postItemListener: PostItemListener)
    :PagedListAdapter<DBPost, PostsAdapter.PostsViewHolder>(diffUtil){

    inner class PostsViewHolder(itemPostBinding: ItemPostBinding)
        : RecyclerView.ViewHolder(itemPostBinding.root){
        val item = itemPostBinding
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<DBPost>(){
            override fun areItemsTheSame(oldItem: DBPost, newItem: DBPost): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DBPost, newItem: DBPost): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        holder.item.post = getItem(position)

        holder.item.itemMainLayout.setOnClickListener {
            postItemListener.onItemClicked(position, PostItemListener.POST_CLICK)
        }

        holder.item.postDelete.setOnClickListener {
            postItemListener.onItemClicked(position, PostItemListener.POST_DELETE)
        }

        holder.item.postEdit.setOnClickListener {
            postItemListener.onItemClicked(position, PostItemListener.POST_EDIT)
        }

    }
}
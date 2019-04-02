package com.core.app.ui.project.list.adapter

//class ProjectListDataBindingAdapter constructor(
//        private val mCallback: AdapterCallback
//) : BaseViewDataBindingAdapter<Project, ProjectListItemBinding>() {

//    interface AdapterCallback {
//        fun itemSelected(position: Int)
//    }

    //    override fun onCreateViewHolder(parent: ViewGroup, binding: ProjectListItemBinding, viewType: Int): ProjectListViewHolder {
//        return ProjectListViewHolder(binding)
//    }

//    override fun getItemViewType(position: Int): Int = R.layout.project_list_item

//    override fun onBindViewHolder(holder: ProjectListViewHolder, position: Int) {
//        holder.binding.apply {
//            root.setOnClickListener { mCallback.itemSelected(position) }
//            project = getItem(position)
//            executePendingBindings()
//        }
//    }

//    class ProjectListViewHolder constructor(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)
//}
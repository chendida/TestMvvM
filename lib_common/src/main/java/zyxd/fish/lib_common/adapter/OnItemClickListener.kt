package zyxd.fish.lib_common.adapter

interface OnItemClickListener<Data> {
    /**
     * 点击事件
     */
    fun onItemClick(data: Data,position : Int)

    /**
     * 长按点击事件
     */
    fun onItemLongClick(data: Data,position: Int) : Boolean
}
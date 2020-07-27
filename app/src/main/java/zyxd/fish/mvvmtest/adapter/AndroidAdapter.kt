package zyxd.fish.mvvmtest.adapter

import zyxd.fish.lib_common.adapter.BaseDBRVAdapter
import zyxd.fish.mvvmtest.BR
import zyxd.fish.mvvmtest.R
import zyxd.fish.mvvmtest.bean.JueJinBean
import zyxd.fish.mvvmtest.databinding.ItemJueJinBinding

class AndroidAdapter : BaseDBRVAdapter<JueJinBean.DBean.EntrylistBean,
        ItemJueJinBinding>(R.layout.item_jue_jin,BR.bean) {
}
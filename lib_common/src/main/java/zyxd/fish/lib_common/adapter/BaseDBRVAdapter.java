package zyxd.fish.lib_common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDBRVAdapter<Data,DB extends ViewDataBinding> extends RecyclerView.Adapter<BaseDBRVHolder> {
    private List<Data>data;
    protected Context context;
    private int itemId;
    protected int variableId;
    protected OnItemClickListener<Data> listener;

    public BaseDBRVAdapter(@LayoutRes int itemId, int variableId) {
        this.itemId = itemId;
        this.variableId = variableId;
        data = new ArrayList<>();
    }

    public BaseDBRVAdapter(List<Data> data, int itemId, int variableId) {
        this.data = data == null? new ArrayList<Data>() : data;
        this.itemId = itemId;
        this.variableId = variableId;
    }

    @NonNull
    @Override
    public BaseDBRVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        DB binding = DataBindingUtil.inflate(inflater, itemId, parent, false);
        return new BaseDBRVHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull BaseDBRVHolder holder, final int position) {
        DB binding = DataBindingUtil.getBinding(holder.itemView);
        final Data itemData = this.data.get(position);
        binding.setVariable(variableId,itemData);
        onBindViewHolder(itemData,binding,position);
        //迫使数据立马绑定
        binding.executePendingBindings();
        if (listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(itemData,position);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return listener.onItemLongClick(itemData,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 绑定数据
     * @param itemData
     * @param binding
     * @param position
     */
    protected void onBindViewHolder(Data itemData, DB binding, int position) {

    }

    /**
     * 设置新数据
     * @param data
     */
    public void setNewData(List<Data> data){
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addData(List<Data> data){
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addData(Data data){
        this.addData(data);
        notifyDataSetChanged();
    }

    /**
     * 设置item的点击和长按事件
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener<Data> listener){
        this.listener = listener;
    }
}

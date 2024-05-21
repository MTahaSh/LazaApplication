package com.example.lazaapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Item> cartItems;
    private OnItemRemoveListener onItemRemoveListener;

    public CartAdapter(List<Item> cartItems, OnItemRemoveListener onItemRemoveListener) {
        this.cartItems = cartItems;
        this.onItemRemoveListener = onItemRemoveListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Item item = cartItems.get(position);
        holder.imageViewItem.setImageResource(item.getImageResId());
        holder.textViewItemName.setText(item.getName());
        holder.textViewItemPrice.setText("$" + item.getPrice());
        holder.textViewItemQuantity.setText("Quantity: " + item.getQuantity());
        holder.buttonRemoveItem.setOnClickListener(v -> onItemRemoveListener.onItemRemove(item.getId()));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewItem;
        TextView textViewItemName, textViewItemPrice, textViewItemQuantity;
        Button buttonRemoveItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewItem = itemView.findViewById(R.id.image_view_item);
            textViewItemName = itemView.findViewById(R.id.text_view_item_name);
            textViewItemPrice = itemView.findViewById(R.id.text_view_item_price);
            textViewItemQuantity = itemView.findViewById(R.id.text_view_item_quantity);
            buttonRemoveItem = itemView.findViewById(R.id.button_remove_item);
        }
    }

    public interface OnItemRemoveListener {
        void onItemRemove(int itemId);
    }
}

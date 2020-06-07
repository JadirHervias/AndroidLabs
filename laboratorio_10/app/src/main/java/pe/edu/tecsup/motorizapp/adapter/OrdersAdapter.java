package pe.edu.tecsup.motorizapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pe.edu.tecsup.motorizapp.R;
import pe.edu.tecsup.motorizapp.model.Order;

// Adapter es necesario al crear un recycler view
public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrdersViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(String address);
    }

    private List<Order> orders;
    private Context context;
    private final OnItemClickListener listener;

    public static class OrdersViewHolder extends RecyclerView.ViewHolder {

        private TextView txtOrder;

        public OrdersViewHolder(View itemView) {
            super(itemView);
            txtOrder = itemView.findViewById(R.id.txtOrder);
        }

        public void bindMaterial(final Order order, final OnItemClickListener listener) {

            String productOrder = String.format("%s - %s", order.getQuantity(), order.getProductName());

            txtOrder.setText(productOrder);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(order.getAddress());
                }
            });
        }
    }

    public OrdersAdapter(Context context, List<Order> orders,
                         OnItemClickListener listener) {
        this.context = context;
        this.orders = orders;
        this.listener = listener;
    }

    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                            .inflate(R.layout.item_order, viewGroup, false);
        OrdersViewHolder ordersViewHolder = new OrdersViewHolder(itemView);
        return ordersViewHolder;
    }

    @Override
    public void onBindViewHolder(OrdersViewHolder viewHolder, int pos) {
        Order item = orders.get(pos);
        viewHolder.bindMaterial(item, listener);
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }
}
package pe.com.patitas.veteriapp.fragments;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import java.util.List;
import pe.com.patitas.veteriapp.R;
import pe.com.patitas.veteriapp.VeteriApp;
import pe.com.patitas.veteriapp.adapters.OrdersAdapter;
import pe.com.patitas.veteriapp.models.Order;
import pe.com.patitas.veteriapp.utils.DividingLineDecoration;
public class OrdersFragment extends Fragment {
    private RecyclerView rcvOrders;
    private List<Order> orders;

    public OrdersFragment() {
        // Required empty public constructor
    }

    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        rcvOrders = (RecyclerView) view.findViewById(R.id.rcvOrders);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        refresh();
    }
    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        orders = ((VeteriApp) getActivity().getApplicationContext()).getOrderList();
        OrdersAdapter ordersAdapter = new OrdersAdapter(orders);
        rcvOrders.setAdapter(ordersAdapter);
        rcvOrders.addItemDecoration(new DividingLineDecoration(getActivity()));
        rcvOrders.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }
}

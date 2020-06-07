package pe.edu.tecsup.motorizapp.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import pe.edu.tecsup.motorizapp.MotorizApp;
import pe.edu.tecsup.motorizapp.R;
import pe.edu.tecsup.motorizapp.adapter.OrdersAdapter;
import pe.edu.tecsup.motorizapp.model.Order;
import pe.edu.tecsup.motorizapp.service.OrderService;

public class OrdersFragment extends Fragment {

    private RecyclerView rcvOrders;
    private List<Order> orders;
    private BroadcastReceiver ordersReceiver;
    private OrdersAdapter ordersAdapter;
    private CoordinatorLayout coordinatorLayout;

    // Constuctor vacío para que se pueda instanciar
    public OrdersFragment() {
        // Required empty public constructor
    }

    // Para poder crear instancias :v
    public static OrdersFragment newInstance() {
        OrdersFragment fragment = new OrdersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Parte del ciclo de vida particular de un Fragment (aquí la creación del layout)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        rcvOrders = view.findViewById(R.id.rcvOrders);
        coordinatorLayout = view.findViewById(R.id.coordinatorLayout);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Demo para servicio fake
        // test();

        getActivity().startService(new Intent(getActivity().getApplicationContext(), OrderService.class));

        ordersReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals("pe.edu.tecsup.motizapp.NEW_ORDER_INTENT")) {
                    ordersAdapter.notifyDataSetChanged();
                }
            }
        };

        initAdapter();

        // refresh();
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().registerReceiver(ordersReceiver, new IntentFilter("pe.edu.tecsup.motizapp.NEW_ORDER_INTENT"));
        ordersAdapter.notifyDataSetChanged();

        // refresh();
    }

    // Al usar un broadcast con contexto se tiene que implementar estos métodos: onPause, onResume
    @Override
    public void onPause() {
        getActivity().unregisterReceiver(ordersReceiver);
        super.onPause();
    }

//    public void test() {
//        for (int i = 0; i < 20; i++) {
//
//            String quantity;
//
//            if (i + 1 < 20) {
//                quantity = "0" + String.valueOf(i + 1);
//            } else {
//                quantity = String.valueOf(i + 1);
//            }
//
//            Order order = new Order(quantity,
//                    "Bacon Cheese Burger", "Av. Siempre Viva 123");
//            ((MotorizApp) getActivity().getApplicationContext()).addOrder(order);
//        }
//    }

    public void initAdapter() {
        orders = ((MotorizApp) getActivity().getApplicationContext()).getOrderList();
        ordersAdapter = new OrdersAdapter(getActivity(),
                orders, new OrdersAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(String address) {
                Snackbar snackbar = Snackbar.make(coordinatorLayout,
                        "Dirección: " + address, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        rcvOrders.setAdapter(ordersAdapter);
        rcvOrders.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false));
    }

//    public void refresh() {
//        orders = ((MotorizApp) getActivity().getApplicationContext()).getOrderList();
//        OrdersAdapter ordersAdapter = new OrdersAdapter(orders);
//        rcvOrders.setAdapter(ordersAdapter);
//        rcvOrders.setLayoutManager(
//                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//    }
}

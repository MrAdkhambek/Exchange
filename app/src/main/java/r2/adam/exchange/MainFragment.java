package r2.adam.exchange;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import r2.adam.exchange.adapter.ExchangeAdapter;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ExchangeAdapter exchangeAdapter = new ExchangeAdapter();
        MainActivityVM viewModel = ViewModelProviders.of(this).get(MainActivityVM.class);
        viewModel.exchangeLiveData.observe(getViewLifecycleOwner(), exchanges -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                exchanges.sort((o1, o2) -> (int) (o2.getNewPrice() - o1.getNewPrice()));
            }
            exchangeAdapter.setData(exchanges);
        });

        RecyclerView rv = view.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv.setAdapter(exchangeAdapter);
    }
}

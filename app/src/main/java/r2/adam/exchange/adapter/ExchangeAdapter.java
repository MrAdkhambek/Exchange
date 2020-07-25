package r2.adam.exchange.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import r2.adam.exchange.R;
import r2.adam.exchange.models.Exchange;


public class ExchangeAdapter extends RecyclerView.Adapter<ExchangeAdapter.ExchangeVH> {

    private ArrayList<Exchange> arr = new ArrayList<>();

    public void setData(List<Exchange> data) {
        ArrayList<Exchange> temp  = new ArrayList<>(data);
        DiffUtil.Callback cb = new ExchangeDiffUtil(arr, temp);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(cb);
        arr = temp;
        result.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ExchangeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exchange, parent, false);
        return new ExchangeVH(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ExchangeVH holder, int position) {
        Exchange item = arr.get(position);

        TextView currency = holder.itemView.findViewById(R.id.tv1);
        TextView price = holder.itemView.findViewById(R.id.tv2);
        ImageView status = holder.itemView.findViewById(R.id.tv3);

        if (item.getOldPrice() > item.getNewPrice()) status.setImageResource(R.drawable.bottom);
        else status.setImageResource(R.drawable.top);

        currency.setText(item.getCurrency());
        price.setText(String.format("%.2f", item.getNewPrice()));
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class ExchangeVH extends RecyclerView.ViewHolder {
        public ExchangeVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}

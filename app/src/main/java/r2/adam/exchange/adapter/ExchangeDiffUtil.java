package r2.adam.exchange.adapter;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

import r2.adam.exchange.models.Exchange;

public class ExchangeDiffUtil extends DiffUtil.Callback {

    private List<Exchange> oldList;
    private List<Exchange> newList;

    public ExchangeDiffUtil(List<Exchange> oldList, List<Exchange> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int op, int np) {
        return oldList.get(op).getId().equals(newList.get(np).getId());
    }

    @Override
    public boolean areContentsTheSame(int op, int np) {
        return oldList.get(op).getId().equals(newList.get(np).getId()) &&
                oldList.get(op).getNewPrice().equals(newList.get(np).getNewPrice()) &&
                oldList.get(op).getOldPrice().equals(newList.get(np).getOldPrice());
    }
}

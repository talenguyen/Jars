package vn.tale.jars.ui.list.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.tale.jars.R;
import vn.tale.jars.base.adapter.ListAdapter;
import vn.tale.jars.model.Jar;

/**
 * Created by Giang Nguyen at Tiki on 4/9/16.
 */
public class JarListAdapter extends ListAdapter<Jar, JarViewHolder> {

    @Override
    public JarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_jar, parent, false);
        return new JarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JarViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }
}

package vn.tale.jars.ui.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import vn.tale.jars.R;
import vn.tale.jars.model.Jar;

/**
 * Created by Giang Nguyen at Tiki on 4/9/16.
 */
public class JarViewHolder extends RecyclerView.ViewHolder {

  @Bind(R.id.tvName)
  TextView tvName;
  @Bind(R.id.tvAmount)
  TextView tvAmount;

  public JarViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void bindTo(Jar jar) {
    tvName.setText(jar.name());
    tvAmount.setText(String.valueOf(jar.amount()));
  }
}

package vn.tale.jars.ui.binding;

import android.view.View;
import android.widget.TextView;

import vn.tale.lcebinding.ErrorView;

/**
 * Author giangnguyen. Created on 4/2/16.
 */
public class ErrorTextView implements ErrorView {

  private final TextView textView;
  private final View errorView;

  public ErrorTextView(View errorView, TextView textView) {
    this.errorView = errorView;
    this.textView = textView;
  }

  @Override public void setError(String s) {
    textView.setText(s);
  }

  @Override public void show() {
    errorView.setVisibility(View.VISIBLE);
  }

  @Override public void hide() {
    errorView.setVisibility(View.GONE);
  }
}

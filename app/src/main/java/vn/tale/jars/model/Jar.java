package vn.tale.jars.model;

import com.google.gson.annotations.SerializedName;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

/**
 * Created by Giang Nguyen at Tiki on 4/8/16.
 */
@Value.Immutable
@Gson.TypeAdapters
public interface Jar {
    @SerializedName("name") String name();
    @SerializedName("full_name") String fullName();
    @SerializedName("rate") String rate();
    @SerializedName("amount") String amount();
}

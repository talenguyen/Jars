package vn.tale.jars.ui.list;

import java.util.List;
import rx.Observable;
import vn.tale.jars.model.User;

/**
 * Author giangnguyen. Created on 3/29/16.
 */
public interface UserListApi {
  Observable<List<User>> getUsers();
}

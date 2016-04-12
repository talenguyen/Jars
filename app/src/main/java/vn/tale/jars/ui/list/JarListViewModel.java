package vn.tale.jars.ui.list;

import java.util.List;

import vn.tale.androiddataloading.NotEmptyTransformer;
import vn.tale.jars.base.lcebinding.LceBindingViewModel;
import vn.tale.jars.model.Jar;
import vn.tale.jars.util.ThreadScheduler;
import vn.tale.jars.util.ThreadSchedulerTransformer;
import vn.tale.lcebinding.LceBindingTransformer;
import vn.tale.lcebinding.LoadingContentError;

/**
 * Created by Giang Nguyen at Tiki on 4/9/16.
 */
public class JarListViewModel extends LceBindingViewModel<List<Jar>> {

    private final ThreadScheduler threadScheduler;
    private final JarRepository jarRepository;

    public JarListViewModel(LoadingContentError lce,
                            ThreadScheduler threadScheduler,
                            JarRepository jarRepository) {
        super(lce);
        this.threadScheduler = threadScheduler;
        this.jarRepository = jarRepository;
    }

    public void load() {
        jarRepository.query(null)
                .compose(new ThreadSchedulerTransformer<>(threadScheduler))
                .compose(new NotEmptyTransformer<>())
                .compose(new LceBindingTransformer<>(getLce()))
                .subscribe(this::setData, Throwable::printStackTrace);
    }

}

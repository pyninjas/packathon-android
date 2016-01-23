package org.packathon.android.services;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.packathon.android.enums.FragmentType;
import org.packathon.android.utils.ObjectCache;

@EBean
public class FragmentService {

    private static final String TAG = "FragmentService";
    public static final String FRAGMENT_KEY = "fragment_type";

    @Bean ObjectCache objectCache;
    public FragmentType fragmentType;

    public FragmentType getFragmentType() {
        if (objectCache.getObject(FRAGMENT_KEY, FragmentType.class) != null)
            return objectCache.getObject(FRAGMENT_KEY, FragmentType.class);
        return FragmentType.HOME;
    }

    public void setFragmentType(FragmentType fragmentType) {
        objectCache.putObject(FRAGMENT_KEY, fragmentType);
        objectCache.commit();
    }

}

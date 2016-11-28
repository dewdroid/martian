package com.mokinjazz.and.martian.presentation.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.ImageView;

import com.mokinjazz.and.martian.presentation.MovieDataActivity;
import com.mokinjazz.and.martian.presentation.MartianApplication;
import com.mokinjazz.and.martian.presentation.R;
import com.mokinjazz.and.martian.presentation.di.component.DaggerUserComponent;
import com.mokinjazz.and.martian.presentation.di.module.TestUserModule;
import com.mokinjazz.and.martian.presentation.di.module.UserModule;
import com.mokinjazz.and.martian.presentation.presenter.SingleMoviePresenter;
import com.mokinjazz.and.martian.presentation.service.ImageLoader;
import com.mokinjazz.and.martian.presentation.view.model.MovieModel;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Created by vvasilyev on 5/29/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovieDetailsActivityTest {

    private SingleMoviePresenter presenter = mock(SingleMoviePresenter.class);

    private ImageLoader imageLoader = mock(ImageLoader.class);

    @Rule
    public ActivityTestRule<MovieDataActivity> rule = new ActivityTestRule<MovieDataActivity>(MovieDataActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            setUp();
        }
    };

    public void setUp() {
        MartianApplication app = application();
        UserModule module = mock(UserModule.class);
        when(module.providesMovieDetailsPresenter(any(), any())).thenReturn(presenter);
        when(module.providesImageLoader(any())).thenReturn(imageLoader);
        app.use(
                DaggerUserComponent.builder()
                        .applicationComponent(app.getApplicationComponent())
                        .userModule(new TestUserModule(presenter))
                        .build()
        );
    }

    private MartianApplication application() {
        return (MartianApplication) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }

    private Fragment fragment() {
        return rule.getActivity().getSupportFragmentManager().getFragments().get(0);
    }

    private SingleMovieView view() {
        return (SingleMovieView) fragment();
    }

    @Test
    public void testContainsFragment() {
        Fragment fragment = fragment();
        assertThat(fragment, is(notNullValue()));
        assertThat(fragment, is(instanceOf(SingleMovieView.class)));
    }

    @Test
    public void testDataRequestedOnload() {
        verify(presenter).movieData(anyLong());
    }

    @Test
    public void testOkCase() {

        MovieModel model = new MovieModel();
        model.tagline = "First in First out";
        model.title = "Regular queue";
        model.overview = "Once upon a time..";
        model.imageUrl = "some url";

        rule.getActivity().runOnUiThread(()-> view().renderMovie(model));

        onView(withId(R.id._d_tagline_tv)).check(matches(withText(model.tagline)));
        onView(withId(R.id._d_title_tv)).check(matches(withText(model.title)));
        onView(withId(R.id._d_overview_tv)).check(matches(withText(model.overview)));

        Mockito.verify(imageLoader).image(model.imageUrl, (ImageView) background());
    }

    public View background() {
        return rule.getActivity().findViewById(R.id._d_background_iv);
    }
}

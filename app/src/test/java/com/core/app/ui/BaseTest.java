package com.core.app.ui;

import android.content.Context;
import android.content.res.Resources;

import com.core.app.AppApplication;
import com.core.app.AppApplicationTest;

import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.io.File;

import io.reactivex.Scheduler;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 21, application = AppApplicationTest.class, packageName = "com.core.app")
public abstract class BaseTest {

    @Rule
    public TestRule injectMocksRule = new TestRule() {
        @Override
        public Statement apply(Statement base, Description description) {
            MockitoAnnotations.initMocks(BaseTest.this);
            return base;
        }
    };

    @Rule
    public TestRule immediateSchedulersRule = new ImmediateSchedulersRule();

    public static Context context() {
        return RuntimeEnvironment.application;
    }

    public static File cacheDir() {
        return RuntimeEnvironment.application.getCacheDir();
    }

    public static Resources getResources() {
        return RuntimeEnvironment.application.getResources();
    }

    private class ImmediateSchedulersRule implements TestRule {
        @Override
        public Statement apply(final Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    RxJavaPlugins.setIoSchedulerHandler(new Function<Scheduler, Scheduler>() {
                        @Override
                        public Scheduler apply(Scheduler scheduler) throws Exception {
                            return Schedulers.trampoline();
                        }
                    });
                    RxJavaPlugins.setComputationSchedulerHandler(new Function<Scheduler, Scheduler>() {
                        @Override
                        public Scheduler apply(Scheduler scheduler) throws Exception {
                            return Schedulers.trampoline();
                        }
                    });
                    RxJavaPlugins.setNewThreadSchedulerHandler(new Function<Scheduler, Scheduler>() {
                        @Override
                        public Scheduler apply(Scheduler scheduler) throws Exception {
                            return Schedulers.trampoline();
                        }
                    });

                    try {
                        base.evaluate();
                    }
                    finally {
                        RxJavaPlugins.reset();
                    }
                }
            };
        }
    }
}

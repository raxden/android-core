package com.core.app.injector.component

import com.core.app.AppApplication
import com.core.app.AppApplicationModule
import com.core.app.base.BaseApplication
import com.core.app.base.BaseApplicationModule
import com.core.app.injector.module.InjectorModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

/**
 * Injects application dependencies.
 *
 * - (*) Generated Classes by Dagger.
 * - BaseApplicationModule - Module that contains all dependencies that the project will use.
 * - InjectorModule - Module responsible to inject dependencies to activities used in project
 * - AndroidInjectionModule - Dagger module required to use this approach.
 *
 * =================================================================================================
 * |                                                                                               |
 * |  ApplicationComponent ----- BaseApplicationModule - InjectorModule - AndroidInjectionModule   |
 * |        |   |                                            |                                     |
 * |        |   |                                      RegisterActivity                              |
 * |        |   |                                       HomeActivity                               |
 * |        |   |                                                                                  |
 * |        |   |                                                                                  |
 * |        |   |                                                    BaseActivityModule            |
 * |        |   |                                                           |                      |
 * |        |   |                                                BaseFragmentActivityModule        |
 * |        |   |                                                         |         |              |
 * |        | *SplashActivitySubComponent ------------------ AccountActivityModule   |              |
 * |        |                          |                                            |              |
 * |      *HomeActivitySubComponent ---|----------------------------- SampleLocaleActivityModule   |
 * |                     |             |                                                           |
 * |                     |             |                                                           |
 * |                     |             |                                BaseFragmentModule         |
 * |                     |             |                                     |    |                |
 * |                     |  *SplashFragmentSubComponent --- RegisterCodeFragmentModule  |                |
 * |                     |                                                        |                |
 * |          *HomeFragmentSubcomponent --------------------------- SampleLocaleFragmentModule     |
 * |                                                                                               |
 * =================================================================================================
 *
 * Base elements (annotations) of Dagger2
 *
 * - @Inject - base annotation whereby the "dependency is requested".
 * - @Module - classes which methods "provide dependencies".
 * - @Provide - methods inside @Module, which "tell Dagger how we what to build and present a
 * dependency".
 * - @Component - bridge between @Inject and @Module.
 * - @Scope - enables to create global and local singletons.
 * - @Qualifier - if different objects of the same type are necessary.
 *
 */
@Singleton
@Component(modules = [
        AndroidInjectionModule::class,
        AppApplicationModule::class,
        InjectorModule::class
])
interface ApplicationComponent : AndroidInjector<AppApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AppApplication>()
}

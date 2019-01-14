package com.core.domain.interactor.impl

import com.core.domain.repository.Repository

abstract class BaseUseCaseImpl<T : Repository>(val repository: T)

/*
 * Copyright (C) 2016 The Dagger Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dagger.internal;

import dagger.Lazy;
import java.util.Objects;
import javax.inject.Provider;

/**
 * A {@link Lazy} and {@link Provider} implementation that memoizes the value returned from a
 * delegate using the double-check idiom described in Item 71 of <i>Effective Java 2</i>.
 */
public final class DoubleCheck<T>
  implements Provider<T>, Lazy<T>
{
  private Provider<T> _provider;
  private Object _instance;

  private DoubleCheck( final Provider<T> provider )
  {
    _provider = Objects.requireNonNull( provider );
    // This is used as the sentinel as some providers are allowed to return null
    _instance = this;
  }

  @SuppressWarnings( "unchecked" )
  @Override
  public T get()
  {
    // This is sentinel indicating un initialized
    if ( this == _instance )
    {
      _instance = _provider.get();
      _provider = null;
    }
    return (T) _instance;
  }

  /**
   * Returns a {@link Provider} that caches the value from the given delegate provider.
   */
  // This method is declared this way instead of "<T> Provider<T> provider(Provider<T> delegate)"
  // to work around an Eclipse type inference bug: https://github.com/google/dagger/issues/949.
  public static <P extends Provider<T>, T> Provider<T> provider( P delegate )
  {
    Objects.requireNonNull( delegate );
    /*
     * delegate instanceof DoubleCheck should be a rare case, but if we have a
     * scoped @Binds that delegates to a scoped binding, we shouldn't
     * cache the value again.
     */
    return delegate instanceof DoubleCheck ? delegate : new DoubleCheck<>( delegate );
  }

  /**
   * Returns a {@link Lazy} that caches the value from the given provider.
   */
  // This method is declared this way instead of "<T> Lazy<T> lazy(Provider<T> delegate)"
  // to work around an Eclipse type inference bug: https://github.com/google/dagger/issues/949.
  public static <P extends Provider<T>, T> Lazy<T> lazy( P provider )
  {
    if ( provider instanceof Lazy )
    {
      @SuppressWarnings( "unchecked" )
      final Lazy<T> lazy = (Lazy<T>) provider;
      // Avoids memoizing a value that is already memoized.
      // NOTE: There is a pathological case where Provider<P> may implement Lazy<L>, but P and L
      // are different types using covariant return on get(). Right now this is used with
      // DoubleCheck<T> exclusively, which is implemented such that P and L are always
      // the same, so it will be fine for that case.
      return lazy;
    }
    return new DoubleCheck<>( Objects.requireNonNull( provider ) );
  }
}

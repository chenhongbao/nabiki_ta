/*
 * Copyright (c) 2020 Hongbao Chen <chenhongbao@outlook.com>
 *
 * Licensed under the  GNU Affero General Public License v3.0 and you may not use
 * this file except in compliance with the  License. You may obtain a copy of the
 * License at
 *
 *                    https://www.gnu.org/licenses/agpl-3.0.txt
 *
 * Permission is hereby  granted, free of charge, to any  person obtaining a copy
 * of this software and associated  documentation files (the "Software"), to deal
 * in the Software  without restriction, including without  limitation the rights
 * to  use, copy,  modify, merge,  publish, distribute,  sublicense, and/or  sell
 * copies  of  the Software,  and  to  permit persons  to  whom  the Software  is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE  IS PROVIDED "AS  IS", WITHOUT WARRANTY  OF ANY KIND,  EXPRESS OR
 * IMPLIED,  INCLUDING BUT  NOT  LIMITED TO  THE  WARRANTIES OF  MERCHANTABILITY,
 * FITNESS FOR  A PARTICULAR PURPOSE AND  NONINFRINGEMENT. IN NO EVENT  SHALL THE
 * AUTHORS  OR COPYRIGHT  HOLDERS  BE  LIABLE FOR  ANY  CLAIM,  DAMAGES OR  OTHER
 * LIABILITY, WHETHER IN AN ACTION OF  CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE  OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.nabiki.ta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Moving average that simply computes the average value of the latest elements back
 * to the size of {@code window}.
 */
public class MA extends ArrayList<Double> {
  private final int window;
  protected final transient ArrayList<Double> base = new ArrayList<>();

  public MA(int window) {
    if (window <= 0)
      throw new ValueOutOfBoundException("not positive");
    this.window = window;
  }

  public int getWindow() {
    return window;
  }

  @Override
  public boolean add(Double d) {
    base.add(d);
    var w = Math.min(getWindow(), base.size());
    var range = super.subList(base.size() - w, base.size());
    return super.add(Commons.average(range));
  }

  /**
   * @deprecated Moving average is strongly coherent with order of elements, no element should be
   * added except at the tail.
   */
  @Deprecated(since = "0.1")
  @Override
  public void add(int index, Double element) {
    throw new UnsupportedOperationException();
  }

  /**
   * @deprecated Removal of any element in a moving average sequence affects the following values
   * from that index. Don't do that.
   */
  @Deprecated(since = "0.1")
  @Override
  public Double remove(int index) {
    throw new UnsupportedOperationException();
  }

  /**
   * @deprecated Removal of any element in a moving average sequence affects the following values
   * from that index. Don't do that.
   */
  @Deprecated(since = "0.1")
  @Override
  public boolean remove(Object o) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(Collection<? extends Double> c) {
    if (c.size() == 0)
      return false;
    for (var v : c)
      add(v);
    return true;
  }

  /**
   * @deprecated Moving average is strongly coherent with order of elements, no element should be
   * added except at the tail.
   */
  @Deprecated(since = "0.1")
  @Override
  public boolean addAll(int index, Collection<? extends Double> c) {
    throw new UnsupportedOperationException();
  }

  /**
   * @deprecated Removal of any element in a moving average sequence affects the following values
   * from that index. Don't do that.
   */
  @Deprecated(since = "0.1")
  @Override
  protected void removeRange(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException();
  }

  /**
   * @deprecated Removal of any element in a moving average sequence affects the following values
   * from that index. Don't do that.
   */
  @Deprecated(since = "0.1")
  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  /**
   * @deprecated Removal of any element in a moving average sequence affects the following values
   * from that index. Don't do that.
   */
  @Deprecated(since = "0.1")
  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  /**
   * @deprecated Removal of any element in a moving average sequence affects the following values
   * from that index. Don't do that.
   */
  @Deprecated(since = "0.1")
  @Override
  public boolean removeIf(Predicate<? super Double> filter) {
    throw new UnsupportedOperationException();
  }

  /**
   * @deprecated The order of elements in a moving average sequence affects its all the values.
   * Don't change the order.
   */
  @Deprecated(since = "0.1")
  @Override
  public void sort(Comparator<? super Double> c) {
    throw new UnsupportedOperationException();
  }
}

/*
 * Copyright (C) 2012-2017 Philip Washington Sorst <philip@sorst.net>
 * and individual contributors as indicated
 * by the @authors tag.
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
package net.dontdrinkandroot.cache;

import net.dontdrinkandroot.cache.utils.Duration;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JUnitUtils
{
    public static double PARETO_EIGHTY_PERCENT_UNDER_HUNDRED = .35;

    public static double PARETO_EIGHTY_PERCENT_UNDER_THOUSAND = 0.24;

    public static double PARETO_EIGHTY_PERCENT_UNDER_TENTHOUSAND = 0.18;

    public static double PARETO_EIGHTY_PERCENT_UNDER_TWENTYTHOUSAND = 0.165;

    public static double PARETO_EIGHTY_PERCENT_UNDER_HUNDREDTHOUSAND = 0.14;

    public static double PARETO_EIGHTY_PERCENT_UNDER_TWOHUNDREDTHOUSAND = 0.135;

    public static <T> void assertContainsExactly(Collection<T> coll, T... objects)
    {
        List<T> expected = new ArrayList<T>(coll);
        for (T object : objects) {
            int idx = expected.indexOf(object);
            if (idx == -1) {
                Assert.fail("Object " + object + " not found");
            } else {
                expected.remove(idx);
            }
        }

        if (!expected.isEmpty()) {
            Assert.fail("Remaining objects: " + coll);
        }
    }

    public static long getFutureExpiry()
    {
        return System.currentTimeMillis() + Duration.days(1);
    }

    public static double pareto(double alpha)
    {
        return Math.pow(1 - Math.random(), -1.0 / alpha) - 1.0;
    }
}

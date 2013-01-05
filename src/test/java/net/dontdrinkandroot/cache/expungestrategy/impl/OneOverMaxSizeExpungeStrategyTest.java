/**
 * Copyright (C) 2012, 2013 Philip W. Sorst <philip@sorst.net>
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
package net.dontdrinkandroot.cache.expungestrategy.impl;

import net.dontdrinkandroot.cache.statistics.impl.SimpleCacheStatistics;

import org.junit.Assert;


public class OneOverMaxSizeExpungeStrategyTest {

	public void testTriggers(AbstractOneOverMaxSizeExpungeStrategy strategy) {

		final SimpleCacheStatistics statistics = new SimpleCacheStatistics();
		statistics.setCurrentSize(0);
		Assert.assertFalse(strategy.triggers(statistics));
		statistics.setCurrentSize(9);
		Assert.assertFalse(strategy.triggers(statistics));
		statistics.setCurrentSize(10);
		Assert.assertTrue(strategy.triggers(statistics));
		statistics.setCurrentSize(11);
		Assert.assertTrue(strategy.triggers(statistics));
		statistics.setCurrentSize(100);
		Assert.assertTrue(strategy.triggers(statistics));
	}
}

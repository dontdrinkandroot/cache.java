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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.dontdrinkandroot.cache.JUnitUtils;
import net.dontdrinkandroot.cache.metadata.impl.JUnitMetaData;
import net.dontdrinkandroot.cache.statistics.impl.SimpleCacheStatistics;

import org.junit.Assert;
import org.junit.Test;


public class LruRecyclingExpungeStrategyTest {

	@Test
	public void testTriggers() {

		SimpleCacheStatistics statistics = new SimpleCacheStatistics();
		LruRecyclingExpungeStrategy strategy = new LruRecyclingExpungeStrategy(10, 3);

		statistics.setCurrentSize(9);
		Assert.assertFalse(strategy.triggers(statistics));

		statistics.setCurrentSize(12);
		Assert.assertFalse(strategy.triggers(statistics));

		statistics.setCurrentSize(13);
		Assert.assertTrue(strategy.triggers(statistics));

		strategy = new LruRecyclingExpungeStrategy(10, .3f);

		statistics.setCurrentSize(9);
		Assert.assertFalse(strategy.triggers(statistics));

		statistics.setCurrentSize(12);
		Assert.assertFalse(strategy.triggers(statistics));

		statistics.setCurrentSize(13);
		Assert.assertTrue(strategy.triggers(statistics));
	}


	@Test
	public void testGetToExpungeMetaData() {

		Map<String, JUnitMetaData> metaData = new HashMap<String, JUnitMetaData>();
		metaData.put("m1", new JUnitMetaData().setExpiry(JUnitUtils.getFutureExpiry()).setLastAccess(3));
		metaData.put("m2", new JUnitMetaData().setExpiry(JUnitUtils.getFutureExpiry()).setLastAccess(2));
		metaData.put("m3", new JUnitMetaData().setExpiry(JUnitUtils.getFutureExpiry()).setLastAccess(1));
		metaData.put("m4", new JUnitMetaData().setExpiry(0).setHitCount(10));

		LruRecyclingExpungeStrategy strategy = new LruRecyclingExpungeStrategy(3, 1);
		Collection<Entry<String, JUnitMetaData>> toExpungeMetaData = strategy.getToExpungeMetaData(metaData.entrySet());
		Set<String> keys = new HashSet<String>();
		for (Entry<String, JUnitMetaData> entry : toExpungeMetaData) {
			keys.add(entry.getKey());
		}

		JUnitUtils.assertContainsExactly(keys, "m3", "m4");
	}

}

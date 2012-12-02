/**
 * Copyright (C) 2012 Philip W. Sorst <philip@sorst.net>
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

import java.util.Comparator;
import java.util.Map.Entry;

import net.dontdrinkandroot.cache.expungestrategy.ExpungeStrategy;
import net.dontdrinkandroot.cache.metadata.MetaData;
import net.dontdrinkandroot.cache.metadata.comparator.impl.LfuComparator;


/**
 * {@link ExpungeStrategy} that triggers on each entry added over the max size and selects the least
 * frequently used entry.
 * 
 * @author Philip W. Sorst <philip@sorst.net>
 */
public class LfuExpungeStrategy extends AbstractOneOverMaxSizeExpungeStrategy {

	public LfuExpungeStrategy(final int maxSize) {

		super(maxSize);
	}


	@Override
	protected <K, M extends MetaData> Comparator<Entry<K, M>> getComparator() {

		return new LfuComparator<K, M>();
	}

}

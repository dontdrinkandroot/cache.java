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
package net.dontdrinkandroot.cache.metadata.comparator.impl;

import net.dontdrinkandroot.cache.metadata.MetaData;
import net.dontdrinkandroot.cache.metadata.comparator.MetaDataComparator;
import net.dontdrinkandroot.cache.utils.ObjectUtils;

import java.util.Map.Entry;

/**
 * Sorts items by ascending access time (older first), if the access time is equal the one with the
 * lower count is picked.
 *
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class LruComparator<K, M extends MetaData> implements MetaDataComparator<K, M>
{
    @Override
    public int compare(Entry<K, M> entry1, Entry<K, M> entry2)
    {
        MetaData meta1 = entry1.getValue();
        MetaData meta2 = entry2.getValue();

        int result = ObjectUtils.compare(meta1.getLastAccess(), meta2.getLastAccess());
        if (result != 0) {
            return result;
        } else {
            result = ObjectUtils.compare(meta1.getHitCount(), meta2.getHitCount());
            if (result != 0) {
                return result;
            } else {
                return ObjectUtils.compare(meta1.getCreated(), meta2.getCreated());
            }
        }
    }
}

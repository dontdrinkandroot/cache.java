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
package net.dontdrinkandroot.cache.impl.disk.indexed;

import net.dontdrinkandroot.cache.CacheException;
import net.dontdrinkandroot.cache.utils.SerializationException;
import net.dontdrinkandroot.cache.utils.Serializer;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author Philip Washington Sorst <philip@sorst.net>
 */
public class SerializableIndexedDiskCache<K extends Serializable, V extends Serializable> extends AbstractIndexedDiskCache<K, V>
{
    public SerializableIndexedDiskCache(
            final String name,
            final long defaultTimeToLive,
            final int maxSize,
            final int recycleSize,
            final File baseDir
    ) throws IOException {
        super(name, defaultTimeToLive, maxSize, recycleSize, baseDir);
    }

    public SerializableIndexedDiskCache(
            final String name,
            final long defaultTimeToLive,
            final long defaultMaxIdleTime,
            final int maxSize,
            final int recycleSize,
            final File baseDir
    ) throws IOException
    {
        super(name, defaultTimeToLive, defaultMaxIdleTime, maxSize, recycleSize, baseDir);
    }

    @Override
    protected <T extends V> byte[] dataToBytes(T data) throws CacheException {
        try {

            return Serializer.serialize(data);
        } catch (final SerializationException e) {
            throw new CacheException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <T extends V> T dataFromBytes(byte[] data) throws CacheException {
        try {
            return (T) Serializer.deserialize(data);
        } catch (final SerializationException e) {
            throw new CacheException(e);
        }
    }

    public int getIndexFileNumAllocatedBlocks()
    {
        return this.indexFile.getNumAllocated();
    }

    public int getDataFileNumAllocatedBlocks()
    {
        return this.dataFile.getNumAllocated();
    }
}

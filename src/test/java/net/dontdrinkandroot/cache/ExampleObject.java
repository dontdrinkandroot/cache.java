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

import java.io.Serializable;

public class ExampleObject implements Serializable
{

    private static final long serialVersionUID = 7121793410453788968L;

    private final String string;

    private long id;

    public ExampleObject(final long id)
    {
        this.id = id;
        this.string = this.createString(id);
    }

    public ExampleObject(final long l, final String string)
    {
        this.id = l;
        this.string = string;
    }

    private String createString(final long l)
    {
        final StringBuffer s = new StringBuffer();
        for (int i = -1; i < l % 100; i++) {
            s.append(Long.toString(l));
        }

        return s.toString();
    }

    public void setId(final long id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.id ^ this.id >>> 32);
        result = prime * result + (this.string == null ? 0 : this.string.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final ExampleObject other = (ExampleObject) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.string == null) {
            if (other.string != null) {
                return false;
            }
        } else if (!this.string.equals(other.string)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "ExampleObject[id=" + this.id + "]";
    }
}
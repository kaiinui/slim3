/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.slim3.gae.jdo;

import javax.jdo.JDOHelper;
import javax.jdo.ObjectState;

import org.slim3.gae.unit.DatastoreTestCase;

import slim3.it.model.Sample;
import slim3.it.model.Sample2;
import slim3.it.model.Sample2Meta;
import slim3.it.model.Sample3;
import slim3.it.model.Sample3Meta;
import slim3.it.model.SampleMeta;

import com.google.appengine.api.datastore.Key;

/**
 * @author higa
 * 
 */
public class JDOTemplateTest extends DatastoreTestCase {

    /**
     * @throws Exception
     */
    public void testExecuteAndDetach() throws Exception {
        Sample s = new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample sample = new Sample();
                makePersistent(sample);
                return sample;
            }
        }.run();
        assertEquals(ObjectState.DETACHED_CLEAN, JDOHelper.getObjectState(s));
    }

    /**
     * @throws Exception
     */
    public void testFrom() throws Exception {
        new JDOTemplate() {
            @Override
            public Object doRun() {
                SampleMeta s = new SampleMeta();
                assertNotNull(from(s));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByKey() throws Exception {
        final Key key = new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample sample = new Sample();
                makePersistent(sample);
                return sample.getKey();
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByKey(Sample.class, key));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByKeyForName() throws Exception {
        final Key key = new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample sample = new Sample();
                sample.setKey(key(Sample.class, "hoge"));
                makePersistent(sample);
                return sample.getKey();
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByKey(Sample.class, key));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectById() throws Exception {
        final Long id = new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample2 sample2 = new Sample2();
                makePersistent(sample2);
                return sample2.getId();
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectById(Sample2.class, id));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByName() throws Exception {
        final String name = "hoge";
        new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample3 sample3 = new Sample3();
                sample3.setName(name);
                makePersistent(sample3);
                return null;
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByName(Sample3.class, name));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByKeyId() throws Exception {
        final Long id = new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample sample = new Sample();
                makePersistent(sample);
                return sample.getKey().getId();
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByKeyId(Sample.class, id));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByKeyName() throws Exception {
        final String name = "hoge";
        new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample sample = new Sample();
                sample.setKey(key(Sample.class, name));
                makePersistent(sample);
                return null;
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByKeyName(Sample.class, name));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByKeyForMeta() throws Exception {
        final Key key = new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample sample = new Sample();
                makePersistent(sample);
                return sample.getKey();
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByKey(new SampleMeta(), key));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByKeyForNameForMeta() throws Exception {
        final Key key = new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample sample = new Sample();
                sample.setKey(key(Sample.class, "hoge"));
                makePersistent(sample);
                return sample.getKey();
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByKey(new SampleMeta(), key));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByIdForMeta() throws Exception {
        final Long id = new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample2 sample2 = new Sample2();
                makePersistent(sample2);
                return sample2.getId();
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectById(new Sample2Meta(), id));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByNameForMeta() throws Exception {
        final String name = "hoge";
        new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample3 sample3 = new Sample3();
                sample3.setName(name);
                makePersistent(sample3);
                return null;
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByName(new Sample3Meta(), name));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByKeyIdForMeta() throws Exception {
        final Long id = new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample sample = new Sample();
                makePersistent(sample);
                return sample.getKey().getId();
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByKeyId(new SampleMeta(), id));
                return null;
            }
        }.run();
    }

    /**
     * @throws Exception
     */
    public void testGetObjectByKeyNameForMeta() throws Exception {
        final String name = "hoge";
        new JDOTemplate() {
            @Override
            public Object doRun() {
                Sample sample = new Sample();
                sample.setKey(key(Sample.class, name));
                makePersistent(sample);
                return null;
            }
        }.run();
        new JDOTemplate() {
            @Override
            public Object doRun() {
                assertNotNull(getObjectByKeyName(new SampleMeta(), name));
                return null;
            }
        }.run();
    }
}
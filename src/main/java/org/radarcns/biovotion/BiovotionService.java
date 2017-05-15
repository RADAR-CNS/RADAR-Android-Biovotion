/*
 * Copyright 2017 Uniklinik Freiburg and The Hyve
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

package org.radarcns.biovotion;

import org.radarcns.android.device.BaseDeviceState;
import org.radarcns.android.device.DeviceManager;
import org.radarcns.android.device.DeviceService;
import org.radarcns.android.device.DeviceTopics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A service that manages a BiovotionDeviceManager and a TableDataHandler to send store the data of a
 * Biovotion VSM and send it to a Kafka REST proxy.
 */
public class BiovotionService extends DeviceService {
    private static final Logger logger = LoggerFactory.getLogger(BiovotionService.class);
    private BiovotionTopics topics;

    @Override
    public void onCreate() {
        logger.info("Creating Biovotion VSM service {}", this);
        super.onCreate();

        topics = BiovotionTopics.getInstance();
    }

    @Override
    protected DeviceManager createDeviceManager() {
        return new BiovotionDeviceManager(this, this, getUserId(), getDataHandler(), topics);
    }

    @Override
    protected BaseDeviceState getDefaultState() {
        return new BiovotionDeviceStatus();
    }

    @Override
    protected DeviceTopics getTopics() {
        return topics;
    }
}

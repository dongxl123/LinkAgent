/**
 * Copyright 2021 Shulie Technology, Co.Ltd
 * Email: shulie@shulie.io
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shulie.instrument.module.config.fetcher.config.event.model;

import com.pamirs.pradar.ConfigNames;
import com.pamirs.pradar.PradarSwitcher;
import com.pamirs.pradar.internal.config.MatchConfig;
import com.pamirs.pradar.pressurement.agent.shared.exit.ArbiterHttpExit;
import com.pamirs.pradar.pressurement.agent.shared.service.GlobalConfig;
import com.shulie.instrument.module.config.fetcher.ConfigFetcherModule;
import com.shulie.instrument.module.config.fetcher.config.event.FIELDS;
import com.shulie.instrument.module.config.fetcher.config.impl.ApplicationConfig;

import java.util.Set;

/**
 * @author: wangjian
 * @since : 2020/9/8 17:02
 */
public class UrlWhiteList implements IChange<Set<MatchConfig>, ApplicationConfig> {

    private static UrlWhiteList INSTANCE;

    public static UrlWhiteList getInstance() {
        if (INSTANCE == null) {
            synchronized (UrlWhiteList.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UrlWhiteList();
                }
            }
        }
        return INSTANCE;
    }

    public static void release() {
        INSTANCE = null;
    }

    @Override
    public Boolean compareIsChangeAndSet(ApplicationConfig applicationConfig, Set<MatchConfig> newValue) {
//        Set<MatchConfig> urlWhiteList = GlobalConfig.getInstance().getUrlWhiteList();
//        if (ObjectUtils.equals(urlWhiteList.size(), newValue.size())
//                && CollectionUtils.equals(urlWhiteList, newValue)) {
//            return Boolean.FALSE;
//        }
        if (ConfigFetcherModule.shadowPreparationEnabled) {
            return true;
        }
        // 变更后配置更新到内存
        applicationConfig.setUrlWhiteList(newValue);
        GlobalConfig.getInstance().setUrlWhiteList(newValue);
        ArbiterHttpExit.clearHttpMatch();
        PradarSwitcher.turnConfigSwitcherOn(ConfigNames.URL_WHITE_LIST);
        return Boolean.TRUE;
    }
}

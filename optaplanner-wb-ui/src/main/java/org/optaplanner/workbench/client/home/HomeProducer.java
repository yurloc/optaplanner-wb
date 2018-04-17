/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.optaplanner.workbench.client.home;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.kie.workbench.common.screens.home.model.HomeModel;
import org.kie.workbench.common.screens.home.model.HomeModelProvider;
import org.kie.workbench.common.screens.home.model.ModelUtils;
import org.optaplanner.workbench.client.resources.i18n.AppConstants;
import org.uberfire.client.mvp.PlaceManager;

import static org.kie.workbench.common.workbench.client.PerspectiveIds.LIBRARY;
import static org.kie.workbench.common.workbench.client.PerspectiveIds.SERVER_MANAGEMENT;
import static org.uberfire.workbench.model.ActivityResourceType.PERSPECTIVE;

@ApplicationScoped
public class HomeProducer implements HomeModelProvider {

    @Inject
    private PlaceManager placeManager;

    @Inject
    private TranslationService translationService;

    public HomeModel get() {
        final HomeModel model = new HomeModel(translationService.getTranslation(AppConstants.HomeProducer_Heading),
                                              translationService.getTranslation(AppConstants.HomeProducer_SubHeading),
                                              "images/home_bg.jpg");
        model.addShortcut(ModelUtils.makeShortcut("pficon pficon-blueprint",
                                                  translationService.getTranslation(AppConstants.HomeProducer_Design),
                                                  translationService.getTranslation(AppConstants.HomeProducer_DesignDescription),
                                                  () -> placeManager.goTo(LIBRARY),
                                                  LIBRARY,
                                                  PERSPECTIVE));
        model.addShortcut(ModelUtils.makeShortcut("fa fa-gears",
                                                  translationService.getTranslation(AppConstants.HomeProducer_DevOps),
                                                  translationService.getTranslation(AppConstants.HomeProducer_DevOpsDescription),
                                                  () -> placeManager.goTo(SERVER_MANAGEMENT),
                                                  SERVER_MANAGEMENT,
                                                  PERSPECTIVE));

        return model;
    }
}

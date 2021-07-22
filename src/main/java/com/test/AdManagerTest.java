package com.test;

import com.google.api.ads.admanager.axis.factory.AdManagerServices;
import com.google.api.ads.admanager.axis.v202105.Network;
import com.google.api.ads.admanager.axis.v202105.NetworkServiceInterface;
import com.google.api.ads.admanager.lib.client.AdManagerSession;
import com.google.api.ads.common.lib.auth.OfflineCredentials;
import com.google.api.client.auth.oauth2.Credential;

public class AdManagerTest {
    public static  void main(String[] args) throws Exception {
        Credential creds = new OfflineCredentials.Builder()
                .forApi(OfflineCredentials.Api.AD_MANAGER)
                .fromFile()
                .build()
                .generateCredential();

        AdManagerSession session = new AdManagerSession.Builder()
                .fromFile()
                .withOAuth2Credential(creds)
                .build();

        AdManagerServices services = new AdManagerServices();
        NetworkServiceInterface networkService = services.get(session, NetworkServiceInterface.class);
        Network network = networkService.getCurrentNetwork();

        System.out.printf("Found Network %s (%s)", network.getDisplayName(), network.getNetworkCode());

    }
}

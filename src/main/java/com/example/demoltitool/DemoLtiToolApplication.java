package com.example.demoltitool;

import com.example.demoltitool.api.CanvasLMS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.context.annotation.RequestScope;
import uk.ac.ox.ctl.lti13.security.oauth2.client.lti.authentication.OidcAuthenticationToken;

@SpringBootApplication
public class DemoLtiToolApplication {

//	@Bean
//	@RequestScope
//	CanvasLMS canvasLMS(OAuth2AuthorizedClientService clientService) {
//		Authentication authentication =
//				SecurityContextHolder.getContext().getAuthentication();
//		String accessToken = null;
//		System.out.println("Authentication class: " + authentication.getClass());
//
//		if (authentication instanceof OidcAuthenticationToken) {
//			OidcAuthenticationToken oidcAuthentication =
//					(OidcAuthenticationToken) authentication;
//			String clientRegistrationId =
//					oidcAuthentication.getAuthorizedClientRegistrationId();
//			System.out.println("ClientId: " + oidcAuthentication.getAuthorizedClientRegistrationId());
//			oidcAuthentication.getCredentials()
//			OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
//					clientRegistrationId, oidcAuthentication.getName());
//			accessToken = client.getAccessToken().getTokenValue();
//		}
////		if (authentication.getClass()
////				.isAssignableFrom(OAuth2AuthenticationToken.class)) {
////			OAuth2AuthenticationToken oauthToken =
////					(OAuth2AuthenticationToken) authentication;
////			String clientRegistrationId =
////					oauthToken.getAuthorizedClientRegistrationId();
////			if (clientRegistrationId.equals("canvas")) {
////				OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
////						clientRegistrationId, oauthToken.getName());
////				accessToken = client.getAccessToken().getTokenValue();
////			}
////		}
//		System.out.println("TOKEN: " + accessToken);
//		return new CanvasLMS(canvasApiBaseUrl, accessToken);
//	}


	public static void main(String[] args) {
		SpringApplication.run(DemoLtiToolApplication.class, args);
	}

}

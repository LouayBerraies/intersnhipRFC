
package com.example.finale.configuration;


import com.example.finale.filters.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecuirtyConfigurarion {

    @Autowired
    private JwtRequestFilter authFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return  http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("authenticate", "/sign-up",
                "/reset-password","/forgot-password",
                        "/userList",
                        "/user/{userId}",
                        "/formationDetails/saveFormation",
                        "/formationDetails/formationList",
                        "/logementDetails/allLogementIds",
                        "/logementDetails/{sno}/recommend",
                        "/logementDetails/{sno}/unrecommend",
                        "/logementDetails/address/{recommendation}",
                        "/logementDetails/recommendation",

                        "/formationDetails/deleteFormation/{sno}",
                        "/formationDetails/updateFormation/{sno}",
                        "/questionDetails/saveQuestion",
                        "/formationDetails/getFormationById/{sno}",
                        "/restaurantDetails/restaurantList",
                        "/restaurantDetails/saveRestaurant",
                        "/restaurantDetails/getRestaurantById/{sno}",
                        "/restaurantDetails/updateRestaurant/{sno}",
                        "/restaurantDetails/deleteRestaurant/{sno}",
                        "/restaurantDetails/command-counts",
                        "/commandeDetails/commandeList",
                        "/commandeDetails/saveCommande",
                        "/commandeDetails/saveCommande/{id}",
                        "/commandeDetails/getCommandeById/{sno}",
                        "/commandeDetails/updateCommande/{sno}",
                        "/commandeDetails/deleteCommande/{sno}",
                        "/commandeDetails/addCommandeAndAffect/{restaurantId}",
                        "/commandeDetails/countWeek",
                        "/commandeDetails/countToday",
                        "/commandeDetails/{id}/",
                        "/commandeDetails/addCommandeAndAffect/{restaurantId}/{sno}",
                        "/commandeDetails/{id}/rate/{rating}",
                        "/reservationDetails/reservationList",
                        "/reservationDetails/saveReservation",
                        "/reservationDetails/getReservationById/{sno}",
                        "/reservationDetails/updateReservation/{sno}",
                        "/reservationDetails/deleteReservation/{sno}",
                        "/reservationDetails/refuserReservation/{id}",
                        "/reservationDetails/addReservationAndAffect/{id}",
                        "/reservationDetails/reservationsTotal",
                        "/reservationDetails/reservationsEn_Attente",
                        "/reservationDetails/acceptReservation/{id}",
                        "/reservationDetails/addReservationAndAffect/{id}/{sno}",
                        "/ressourceDetails/ressourceList",
                        "/ressourceDetails/saveressource",
                        "/ressourceDetails/getRessourceById/{sno}",
                        "/ressourceDetails/updateRessource/{sno}",
                        "/ressourceDetails/deleteRessource/{sno}",
                        "/trajetDetails/trajetList",
                        "/trajetDetails/saveTrajet",
                        "/trajetDetails/getTrajetById/{sno}",
                        "/trajetDetails/updateTrajet/{sno}",
                        "/trajetDetails/deleteTrajet/{sno}",
                        "/reservationTrajetDetails/reservationTrajetList",
                        "/reservationTrajetDetails/saveReservationTrajet",
                        "/reservationTrajetDetails/getReservationTrajetById/{sno}",
                        "/reservationTrajetDetails/updateReservationTrajet/{sno}",
                        "/reservationTrajetDetails/deleteReservationTrajet/{sno}",
                        "/logementDetails/logementList",
                        "/logementDetails/saveLogement",
                        "/logementDetails/getLogementById/{sno}",
                        "/logementDetails/updateLogement/{sno}",
                        "/logementDetails/deleteLogement/{sno}",
                        "/colocataireDetails/*",
                        "/reservationTrajetDetails/*",
                        "/colocataireDetails/deleteColocataire/{sno}",
                        "/colocataireDetails/*/*",
                        "/reservationTrajetDetails/findbytrajet/{idTrajet}",
                        "/reservationTrajetDetails/reservationcount",
                          "/trajetDetails/totalAvailableSeats",
                        "/trajetDetails/*/*/*",
                        "/trajetDetails/search-trajet/{sno}",
                        "/apitest/{{lieuDepart}}/{{lieuArrivee}}",
                        "/events",
                        "/reservationTrajetDetails/noficication",
                       "/reservationTrajetDetails/allHistory",
                        "/reservationTrajetDetails/allHistory/{{sno}}"




                        ).permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/api/**")
                .authenticated().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return  config.getAuthenticationManager();
    }

}
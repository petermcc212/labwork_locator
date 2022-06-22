package com.mccreadie.springlabworklocator.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login, password, enabled from user where login=?")
                .authoritiesByUsernameQuery("select login, role from role where login =?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/login")
//                    .permitAll()
//                .antMatchers("/", "/showClinicians", "/viewCliniciansLabWork/*", "/showLaboratories",
//                                        "/selectPatient", "/viewPatients", "/viewPatientsLabWork/*", "/search",
//                                        "/viewAllLabWork", "/processLabWork", "/editProsthesis/*", "/labWorkDueTomorrow",
//                                        "/labWorkDueToday")
//                .hasAnyRole("ADMIN", "CLINICIAN", "RECEPTIONIST")
//                .antMatchers("/addProsthetic/*", "/addPatient", "/addPatientDuringProsthesisCreation",
//                                        "/processNewPatientFromNewProsthesis", "/processNewPatient", "/editPatientDetails/*")
//                    .hasAnyRole("CLINICIAN", "ADMIN")
//                .antMatchers("/addClinician", "/processNewClinician", "/editClinicianDetails/*", "/addLaboratory",
//                                        "/processNewLaboratory", "/signup", "/processSignup")
//                    .hasAnyRole("ADMIN")
//                .anyRequest().denyAll()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/checkUserAccount")
                        .defaultSuccessUrl("/", true)
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/forbidden");
    }

}

package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.web.rest.vm.ConfigPropsVM;
import com.mycompany.myapp.web.rest.vm.EnvVM;
import io.quarkus.runtime.configuration.ProfileManager;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.spi.ConfigSource;

@Path("/management")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class JHipsterConfigurationEndpoint {

    @GET
    @Path("/configprops")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public ConfigPropsVM getConfigs() {
        return new ConfigPropsVM();
    }

    @GET
    @Path("/env")
    @RolesAllowed(AuthoritiesConstants.ADMIN)
    public EnvVM getEnvs() {
        Iterable<ConfigSource> configSources = ConfigProvider.getConfig().getConfigSources();
        List<EnvVM.PropertySource> propertySources = StreamSupport.stream(configSources.spliterator(), false)
            .map(configSource -> new EnvVM.PropertySource(configSource.getName(), configSource.getProperties()))
            .collect(Collectors.toList());

        return new EnvVM(List.of(ProfileManager.getActiveProfile()), propertySources);
    }
}

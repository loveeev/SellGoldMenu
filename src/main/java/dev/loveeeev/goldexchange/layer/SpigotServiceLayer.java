package dev.loveeeev.goldexchange.layer;


import dev.loveeeev.goldexchange.Main;
import dev.loveeeev.goldexchange.exception.ModuleStateException;
import dev.loveeeev.goldexchange.module.Service;
import dev.loveeeev.goldexchange.module.impl.ExchangeService;
import dev.loveeeev.goldexchange.util.semantics.Layer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;


@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
@Getter
public class SpigotServiceLayer extends Layer {

    HashMap<Class<? extends Service>, Service> loadedServices = new HashMap<>();

    public SpigotServiceLayer(Main plugin) {
        super(plugin);
        loadServices();
    }

    private void loadServices() {
        load(new ExchangeService(plugin));
    }

    @SneakyThrows(ModuleStateException.class)
    public void load(Service service) {
        if (loadedServices.containsKey(service.getClass()))
            throw new ModuleStateException("Service is already loaded to loader");
        loadedServices.put(service.getClass(), service);
        loadedServices.get(service.getClass()).enable();
    }

    @SneakyThrows(ModuleStateException.class)
    public <T extends Service> T get(Class<T> serviceClass) {
        if (!loadedServices.containsKey(serviceClass))
            throw new ModuleStateException("Service is not loaded to loader");
        return serviceClass.cast(loadedServices.get(serviceClass));
    }

    @Override
    public void disable() {
        super.disable();
        loadedServices.values().forEach(Service::disable);
    }
}
